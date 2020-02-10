package com.ispw.fixmycity.logic.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.ispw.fixmycity.logic.bean.CommunityReportBean;
import com.ispw.fixmycity.logic.bean.CompanyReportBean;
import com.ispw.fixmycity.logic.bean.ReportBeanView;
import com.ispw.fixmycity.logic.bean.UserSessionBean;
import com.ispw.fixmycity.logic.dao.CommunityReportDAO;
import com.ispw.fixmycity.logic.dao.CompanyReportDAO;
import com.ispw.fixmycity.logic.dao.CompanyUserDAO;
import com.ispw.fixmycity.logic.model.CompanyUser;
import com.ispw.fixmycity.logic.util.Category;

public class ReportProblemController {

	public ReportProblemController() {
		//there is nothing to instantiate
	}

	public void reportProblem(ReportBeanView repBean) {
		// if repBean has category for companies -> reportProblem company
		// else -> reportProblem community

		if (Category.isForCommunity(Category.valueOf(repBean.getCategory().toUpperCase()))) {
			reportProblemCommunity(repBean);
		}

		if (Category.isForCompany(Category.valueOf(repBean.getCategory().toUpperCase()))) {
			reportProblemCompany(repBean);
		}
	}

	private void reportProblemCompany(ReportBeanView repBean) {
		CompanyUser compUser = this.findCompany(repBean.getCategory(), repBean.getCity());

		if (compUser == null) {
			Logger.getLogger("fixmycity").log(Level.SEVERE, "No Company User Found with matching category and city!");
			// TODO: throw some exception
		}

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
		compRepBean.setSubmitter(UserSessionBean.getInstance().getActiveCitizenUser());

		compRepDAO.add(compRepBean);
	}

	private void reportProblemCommunity(ReportBeanView repBean) {
		CommunityReportDAO commRepDAO = new CommunityReportDAO();

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
		commRepBean.setSubmitter(UserSessionBean.getInstance().getActiveCitizenUser());

		commRepDAO.add(commRepBean);
	}

	private CompanyUser findCompany(String category, String areaOfInterest) {
		CompanyUserDAO compUserDAO = new CompanyUserDAO();
		return compUserDAO.findByCategoryAndCity(category, areaOfInterest);
	}
}
