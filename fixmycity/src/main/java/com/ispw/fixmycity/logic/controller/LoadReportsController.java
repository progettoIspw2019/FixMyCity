package com.ispw.fixmycity.logic.controller;

import java.util.ArrayList;
import java.util.List;

import com.ispw.fixmycity.logic.bean.CommunityReportBeanView;
import com.ispw.fixmycity.logic.bean.CompanyReportBeanView;
import com.ispw.fixmycity.logic.dao.CommunityReportDAO;
import com.ispw.fixmycity.logic.dao.CompanyReportDAO;
import com.ispw.fixmycity.logic.model.CommunityReport;
import com.ispw.fixmycity.logic.model.CompanyReport;

public class LoadReportsController {

	public LoadReportsController() {
		// there is nothing to initialize
	}

	public List<CommunityReportBeanView> getCommunityReports() {

		CommunityReportDAO daoComm = new CommunityReportDAO();

		List<CommunityReport> reports = daoComm.findAll();

		ArrayList<CommunityReportBeanView> commRepBeanList = new ArrayList<>();

		for (var rep : reports) {
			CommunityReportBeanView commRepBean = new CommunityReportBeanView();

			commRepBean.setAddress(rep.getAddress());
			commRepBean.setCategory(rep.getCategory());
			commRepBean.setCity(rep.getCity());
			commRepBean.setDateSubmission(rep.getDateSubmission());
			commRepBean.setDescription(rep.getFullDescription());
			commRepBean.setImage(rep.getImage());
			commRepBean.setLatitude(rep.getLatitude());
			commRepBean.setLongitude(rep.getLongitude());
			commRepBean.setTitle(rep.getTitle());

			ArrayList<String> eventsStr = new ArrayList<>();

			rep.getVolunteeringEvents().forEach(ev -> eventsStr.add(String.valueOf(ev.getIdEvent())));

			commRepBean.setEvents(eventsStr);
			
			commRepBeanList.add(commRepBean);
		}

		return commRepBeanList;
	}
	
	public List<CompanyReportBeanView> getCompanyReports() {
		CompanyReportDAO compRepDAO = new CompanyReportDAO();
		
		List<CompanyReport> reports = compRepDAO.findAll();
		
		ArrayList<CompanyReportBeanView> compRepBeanList = new ArrayList<>();
		for (var rep : reports) {
			CompanyReportBeanView compRepBean = new CompanyReportBeanView();

			compRepBean.setAddress(rep.getAddress());
			compRepBean.setCategory(rep.getCategory());
			compRepBean.setCity(rep.getCity());
			compRepBean.setDateSubmission(rep.getDateSubmission());
			compRepBean.setDescription(rep.getFullDescription());
			compRepBean.setImage(rep.getImage());
			compRepBean.setLatitude(rep.getLatitude());
			compRepBean.setLongitude(rep.getLongitude());
			compRepBean.setTitle(rep.getTitle());
			
			ArrayList<String> jobsStr = new ArrayList<>();
			
			rep.getJobs().forEach(j -> jobsStr.add(String.valueOf(j.getIdJob())));
			
			compRepBean.setJobs(jobsStr);
			
			compRepBeanList.add(compRepBean);
		}
		
		return compRepBeanList;
	}
}
