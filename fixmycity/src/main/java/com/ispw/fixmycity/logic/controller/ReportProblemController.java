package com.ispw.fixmycity.logic.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.ispw.fixmycity.logic.bean.AddressBean;
import com.ispw.fixmycity.logic.bean.CommunityReportBean;
import com.ispw.fixmycity.logic.bean.CompanyReportBean;
import com.ispw.fixmycity.logic.bean.ReportBeanView;
import com.ispw.fixmycity.logic.dao.CommunityReportDAO;
import com.ispw.fixmycity.logic.dao.CompanyReportDAO;
import com.ispw.fixmycity.logic.dao.CompanyUserDAO;
import com.ispw.fixmycity.logic.dao.UserDAO;
import com.ispw.fixmycity.logic.exceptions.CouldNotConnectToGeolocationServiceException;
import com.ispw.fixmycity.logic.exceptions.NoMatchingCompanyFound;
import com.ispw.fixmycity.logic.model.CitizenUser;
import com.ispw.fixmycity.logic.model.City;
import com.ispw.fixmycity.logic.model.CityFactory;
import com.ispw.fixmycity.logic.model.CompanyUser;
import com.ispw.fixmycity.logic.util.CityEnum;
import com.ispw.fixmycity.logic.view.SessionView;

public class ReportProblemController {

	private Logger log;

	public ReportProblemController() {
		log = Logger.getLogger("fixmycity");
	}

	public Integer reportProblem(ReportBeanView repBean) throws NoMatchingCompanyFound {
		// if repBean has category for companies -> reportProblem company
		// else -> reportProblem community

		City city = new CityFactory().getCity(CityEnum.valueOf(repBean.getCity().toUpperCase()));

		if (city.isForCommunity(repBean.getCategory())) {
			return reportProblemCommunity(repBean);
		}

		if (city.isForCompany(repBean.getCategory())) {
			return reportProblemCompany(repBean);
		}
		return -1;
	}

	private Integer reportProblemCompany(ReportBeanView repBean) throws NoMatchingCompanyFound {
		CompanyUser compUser = this.findCompany(repBean.getCategory(), repBean.getCity());

		if (compUser == null) {
			log.severe("No Company User Found with matching category and city!\n\n");
			throw new NoMatchingCompanyFound();
		}

		CitizenUser submitter = new UserDAO().findAllCitizensFromUsername(repBean.getSubmitter());

		CompanyReportDAO compRepDAO = new CompanyReportDAO();

		CompanyReportBean compRepBean = new CompanyReportBean();
		compRepBean.setAddress(repBean.getAddress());
		compRepBean.setCategory(repBean.getCategory());
		compRepBean.setDateSubmission(repBean.getDateSubmission());
		compRepBean.setFullDescription(repBean.getDescription());
		compRepBean.setLatitude(repBean.getLatitude());
		compRepBean.setLongitude(repBean.getLongitude());
		compRepBean.setTitle(repBean.getTitle());
		compRepBean.setImage(repBean.getImage());
		compRepBean.setCompany(compUser);
		compRepBean.setSubmitter(submitter);
		compRepBean.setCity(repBean.getCity());

		return compRepDAO.add(compRepBean).getIdReport();
	}

	public void setAddressForReport(BigDecimal longitude, BigDecimal latitude)
			throws CouldNotConnectToGeolocationServiceException {
		SessionView.setLatitudeSetOnMap(latitude);
		SessionView.setLongitudeSetOnMap(longitude);

		AddressBean addr = new AddressBean();

		URL url;
		JsonReader jRdr;

		try {
			String req = "https://reverse.geocoder.ls.hereapi.com/6.2/reversegeocode.json"
					+ "?apiKey=JUvOvJVjLrhz-jdc2OBX9V63RJyWzTKjp54rBd-1oE4" + "&mode=retrieveAddresses" + "&prox="
					+ latitude.doubleValue() + "," + longitude.doubleValue() + "&maxresults=1";
			Logger.getLogger("fixmycity").info(() -> "Requesting... \n" + req);
			url = new URL(req);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();

			if (responsecode != 200)
				throw new CouldNotConnectToGeolocationServiceException();

			log.info("\nParsing...\n");
			jRdr = new JsonReader(new InputStreamReader(url.openStream()));

			JsonObject addrJson = JsonParser.parseReader(jRdr).getAsJsonObject().get("Response").getAsJsonObject()
					.get("View").getAsJsonArray().get(0).getAsJsonObject().get("Result").getAsJsonArray().get(0)
					.getAsJsonObject().get("Location").getAsJsonObject().get("Address").getAsJsonObject();

			addr.setCity(addrJson.get("City").getAsString());
			addr.setCountry(addrJson.get("AdditionalData").getAsJsonArray().get(0).getAsJsonObject().get("value")
					.getAsString());
			String num = "";
			if (addrJson.get("HouseNumber") != null)
				num = addrJson.get("HouseNumber").getAsString();
			if (num.isBlank())
				addr.setRoad(addrJson.get("Street").getAsString());
			else
				addr.setRoad(num + ", " + addrJson.get("Street").getAsString());

			String label = addr.getRoad() + ", " + addr.getCity() + ", " + addr.getCountry();
			log.fine(() -> "\nJSON parsed\n" + label);
			SessionView.setAddressSetOnMap(addr);

		} catch (IOException e) {
			log.severe(e.getLocalizedMessage());

		}
	}

	private Integer reportProblemCommunity(ReportBeanView repBean) {
		CommunityReportDAO commRepDAO = new CommunityReportDAO();

		CitizenUser submitter = new UserDAO().findAllCitizensFromUsername(repBean.getSubmitter());

		CommunityReportBean commRepBean = new CommunityReportBean();
		commRepBean.setAddress(repBean.getAddress());
		commRepBean.setCategory(repBean.getCategory());
		commRepBean.setDateSubmission(repBean.getDateSubmission());
		commRepBean.setFullDescription(repBean.getDescription());
		commRepBean.setLatitude(repBean.getLatitude());
		commRepBean.setLongitude(repBean.getLongitude());
		commRepBean.setTitle(repBean.getTitle());
		commRepBean.setImage(repBean.getImage());
		commRepBean.setCity(repBean.getCity());
		commRepBean.setSubmitter(submitter);

		return commRepDAO.add(commRepBean).getIdReport();

	}

	private CompanyUser findCompany(String category, String areaOfInterest) {
		CompanyUserDAO compUserDAO = new CompanyUserDAO();
		return compUserDAO.findByCategoryAndCity(category, areaOfInterest);
	}
}
