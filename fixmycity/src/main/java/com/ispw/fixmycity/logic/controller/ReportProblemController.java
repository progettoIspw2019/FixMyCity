package com.ispw.fixmycity.logic.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.impl.client.HttpClients;

import com.ispw.fixmycity.logic.app.exceptions.NoMatchingCompanyFound;
import com.ispw.fixmycity.logic.bean.AddressBean;
import com.ispw.fixmycity.logic.bean.CommunityReportBean;
import com.ispw.fixmycity.logic.bean.CompanyReportBean;
import com.ispw.fixmycity.logic.bean.ReportBeanView;
import com.ispw.fixmycity.logic.dao.CommunityReportDAO;
import com.ispw.fixmycity.logic.dao.CompanyReportDAO;
import com.ispw.fixmycity.logic.dao.CompanyUserDAO;
import com.ispw.fixmycity.logic.dao.UserDAO;
import com.ispw.fixmycity.logic.model.CitizenUser;
import com.ispw.fixmycity.logic.model.City;
import com.ispw.fixmycity.logic.model.CityFactory;
import com.ispw.fixmycity.logic.model.CompanyUser;
import com.ispw.fixmycity.logic.util.CityEnum;
import com.ispw.fixmycity.logic.view.SessionView;

import fr.dudie.nominatim.client.JsonNominatimClient;
import fr.dudie.nominatim.model.Address;

public class ReportProblemController {

	public ReportProblemController() {
		// there is nothing to instantiate
	}

	public void reportProblem(ReportBeanView repBean) throws NoMatchingCompanyFound {
		// if repBean has category for companies -> reportProblem company
		// else -> reportProblem community
		
		City city = new CityFactory().getCity(CityEnum.valueOf(repBean.getCity().toUpperCase()));

		if (city.isForCommunity(repBean.getCategory())) {
			reportProblemCommunity(repBean);
		}

		if (city.isForCompany(repBean.getCategory())) {
			reportProblemCompany(repBean);
		}
	}

	private void reportProblemCompany(ReportBeanView repBean) throws NoMatchingCompanyFound {
		CompanyUser compUser = this.findCompany(repBean.getCategory(), repBean.getCity());

		if (compUser == null) {
			Logger.getLogger("fixmycity").log(Level.SEVERE, "No Company User Found with matching category and city!\n\n");
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

		compRepDAO.add(compRepBean);
	}

	public void setAddressForReport(BigDecimal longitude, BigDecimal latitude) {
		JsonNominatimClient client = new JsonNominatimClient(HttpClients.createDefault(),
				"progetto.ispw.uniroma2@gmail.com");

		SessionView.setLatitudeSetOnMap(latitude);
		SessionView.setLongitudeSetOnMap(longitude);

		AddressBean addr = new AddressBean();
		try {
			Address address = client.getAddress(longitude.doubleValue(), latitude.doubleValue());

			for (var elem : address.getAddressElements()) {
				if (elem.getKey().equals("road"))
					addr.setRoad(elem.getValue());
				if (elem.getKey().equals("city"))
					addr.setCity(elem.getValue());
				if (elem.getKey().equals("country"))
					addr.setCountry(elem.getValue());
			}
			SessionView.setAddressSetOnMap(addr);
		} catch (IOException e) {
			Logger.getLogger("fixmycity").log(Level.SEVERE, e.toString());
		}
	}

	private void reportProblemCommunity(ReportBeanView repBean) {
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

		commRepDAO.add(commRepBean);
	}

	private CompanyUser findCompany(String category, String areaOfInterest) {
		CompanyUserDAO compUserDAO = new CompanyUserDAO();
		return compUserDAO.findByCategoryAndCity(category, areaOfInterest);
	}
}
