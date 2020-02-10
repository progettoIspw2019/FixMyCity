package com.ispw.fixmycity.logic.controller;

import java.util.List;
import java.util.Map;

import com.ispw.fixmycity.logic.bean.BaseUserBean;
import com.ispw.fixmycity.logic.bean.CitizenUserBean;
import com.ispw.fixmycity.logic.bean.CommunityReportBean;
import com.ispw.fixmycity.logic.bean.CommunityReportBeanView;
import com.ispw.fixmycity.logic.bean.CompanyReportBeanView;
import com.ispw.fixmycity.logic.bean.CompanyUserBean;
import com.ispw.fixmycity.logic.bean.ReportBeanView;
import com.ispw.fixmycity.logic.bean.VolunteeringEventBean;

public class SystemFacade {
	
	public void reportProblem(ReportBeanView repBean) {
		new ReportProblemController().reportProblem(repBean);
	}


	public List<CommunityReportBeanView> getMyCommunityReports() {
		return new LoadReportsController().getMyCommunityReports();
	}
	

	public List<CompanyReportBeanView> getMyCompanyReports() {
		return new LoadReportsController().getMyCompanyReports();
	}
	
	public List<CompanyReportBeanView> getCompanyReports () {
		return new LoadReportsController().getCompanyReports();
	}
	
	public List<CommunityReportBeanView> getCommunityReports() {
		return new LoadReportsController().getCommunityReports();
	}
	
	public boolean signupCompanyUser(CompanyUserBean user) {
		return new LoginController().signupCompanyUser(user);
	}
	
	public boolean signupCitizenUser(CitizenUserBean user) {
		return new LoginController().signupCitizenUser(user);
	}
	
	public boolean isSignedUp(BaseUserBean userBean) {
		return new LoginController().checkCredentials(userBean);
	}
	
	public void createVolunteeringEvent(VolunteeringEventBean volEventBean) {
		new VolunteeringEventController().createVolunteeringEvent(volEventBean);
	}
	
	public Map<Integer, String> getMappedCommunityReports() {
		return new VolunteeringEventController().getCommunityReportMap();
	}
	
	public CommunityReportBean getCommunityReportFromId(Integer id) {
		return new VolunteeringEventController().getCommunityReportFromId(id);
	}
}
