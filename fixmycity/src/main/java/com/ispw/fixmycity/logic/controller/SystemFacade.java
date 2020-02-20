package com.ispw.fixmycity.logic.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ispw.fixmycity.logic.bean.BaseUserBean;
import com.ispw.fixmycity.logic.bean.CitizenUserBean;
import com.ispw.fixmycity.logic.bean.CommunityReportBean;
import com.ispw.fixmycity.logic.bean.CommunityReportBeanView;
import com.ispw.fixmycity.logic.bean.CompanyReportBeanView;
import com.ispw.fixmycity.logic.bean.CompanyUserBean;
import com.ispw.fixmycity.logic.bean.JobBeanView;
import com.ispw.fixmycity.logic.bean.ReportBeanView;
import com.ispw.fixmycity.logic.bean.VolunteeringEventBean;
import com.ispw.fixmycity.logic.bean.VolunteeringEventListElementBean;
import com.ispw.fixmycity.logic.exceptions.CompanyReportIsAcceptedException;
import com.ispw.fixmycity.logic.exceptions.CouldNotConnectToGeolocationServiceException;
import com.ispw.fixmycity.logic.exceptions.EmptyResultListException;
import com.ispw.fixmycity.logic.exceptions.NoMatchingCompanyFound;
import com.ispw.fixmycity.logic.exceptions.InvalidDateIntervalException;
import com.ispw.fixmycity.logic.exceptions.InvalidReportException;
import com.ispw.fixmycity.logic.exceptions.UserNotFoundException;
import com.ispw.fixmycity.logic.view.SessionView;

public class SystemFacade {

	public long reportProblem(ReportBeanView repBean) throws NoMatchingCompanyFound, InvalidReportException {
		return new ReportProblemController().reportProblem(repBean);
	}

	public List<CommunityReportBeanView> getMyCommunityReports() throws EmptyResultListException {
		return new LoadReportsController().getMyCommunityReports();
	}

	public List<CompanyReportBeanView> getMyCompanyReports() throws EmptyResultListException {
		return new LoadReportsController().getMyCompanyReports();
	}

	public List<JobBeanView> getJobsByCurrentCompany() throws EmptyResultListException {
		return new LoadReportsController().getJobsByCurrentCompany();
	}

	public List<CompanyReportBeanView> getCompanyReports() {
		return new LoadReportsController().getCompanyReports();
	}
	
	public List<CompanyReportBeanView> getCompanyReportsFiltered() {
		return new LoadReportsController().getCompanyReportsFiltered();
	}

	public List<CommunityReportBeanView> getCommunityReports() {
		return new LoadReportsController().getCommunityReports();
	}

	public Map<Integer, String> getCommunityReportMap() {
		return new VolunteeringEventController().getCommunityReportMap();
	}

	public boolean signupCompanyUser(CompanyUserBean user) {
		return new LoginController().signupCompanyUser(user);
	}

	public boolean signupCitizenUser(CitizenUserBean user) {
		return new LoginController().signupCitizenUser(user);
	}

	public BaseUserBean isSignedUp(BaseUserBean userBean) throws UserNotFoundException {
		return new LoginController().checkCredentials(userBean);
	}

	public boolean createVolunteeringEvent(VolunteeringEventBean volEventBean) {
		return new VolunteeringEventController().createVolunteeringEvent(volEventBean);
	}

	public List<VolunteeringEventListElementBean> getActiveVolunteeringEvents() throws EmptyResultListException {
		return new VolunteeringEventController().getActiveVolunteeringEvents();
	}

	public Map<Integer, String> getMappedCommunityReports() {
		return new VolunteeringEventController().getCommunityReportMap();
	}

	public CommunityReportBean getCommunityReportFromId(Integer id) {
		return new VolunteeringEventController().getCommunityReportFromId(id);
	}

	public void setAddressForReport(BigDecimal longitude, BigDecimal latitude)
			throws CouldNotConnectToGeolocationServiceException {
		new ReportProblemController().setAddressForReport(longitude, latitude);
	}

	public void joinVolunteeringEvent(VolunteeringEventBean eventBean) {
		new VolunteeringEventController().joinVolunteeringEvent(eventBean);
	}

	public void quitVolunteeringEvent(VolunteeringEventBean eventBean) {
		new VolunteeringEventController().quitVolunteeringEvent(eventBean);
	}

	public void logout() {
		new LoginController().logout();
	}

	public List<CompanyReportBeanView> loadMyCompanyReports() {
		return new AcceptOrRefuseAJobController().loadCompanyReports(SessionView.getUsername());
	}

	public int rejectReport(JobBeanView job) throws CompanyReportIsAcceptedException {
		return new AcceptOrRefuseAJobController().rejectReport(job);
	}

	public boolean jobCreation(JobBeanView jBean)
			throws CompanyReportIsAcceptedException, InvalidDateIntervalException {
		return new AcceptOrRefuseAJobController().jobCreation(jBean);
	}

	public Map<String, List<String>> getCategoriesForCompanySignUp() {
		return new LoginController().getCategoriesForCompanySignUp();
	}
}
