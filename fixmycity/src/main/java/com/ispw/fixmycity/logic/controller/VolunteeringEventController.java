package com.ispw.fixmycity.logic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ispw.fixmycity.logic.bean.CommunityReportBean;
import com.ispw.fixmycity.logic.bean.VolunteeringEventBean;
import com.ispw.fixmycity.logic.dao.CommunityReportDAO;
import com.ispw.fixmycity.logic.dao.VolunteeringEventDAO;
import com.ispw.fixmycity.logic.model.CommunityReport;
import com.ispw.fixmycity.logic.model.VolunteeringEvent;

public class VolunteeringEventController {

	List<CommunityReportBean> filteredReports = new ArrayList<CommunityReportBean>();

	public void createVolunteeringEvent(VolunteeringEventBean volunteeringEventBean) {

		VolunteeringEventDAO dao = new VolunteeringEventDAO();
		VolunteeringEvent event = dao.addVolunteeringEvent(volunteeringEventBean);

		dao.joinVolunteeringEvent(LoginController.getInstance().getActiveCitizenUser().getUsername(), event);

	}

	public VolunteeringEventController() {
		this.initializeCommunityReportList();
	}

	public void initializeCommunityReportList() {
		CommunityReportDAO dao = new CommunityReportDAO();
		List<CommunityReport> reports = dao.findAll();

		for (CommunityReport report : reports) {
			if (report.getVolunteeringEvents().isEmpty()) {
				CommunityReportBean commRepBean = new CommunityReportBean();
				commRepBean.setIdReport(report.getIdReport());
				commRepBean.setAddress(report.getAddress());
				commRepBean.setDateSubmission(report.getDateSubmission());
				commRepBean.setFullDescription(report.getFullDescription());
				commRepBean.setImage(report.getImage());
				commRepBean.setLatitude(report.getLatitude());
				commRepBean.setLongitude(report.getLongitude());
				commRepBean.setTitle(report.getTitle());
				filteredReports.add(commRepBean);
			}
		}

	}

	public Map<Integer, String> getCommunityReportMap() {

		Map<Integer, String> commReps = new HashMap<Integer, String>();

		for (CommunityReportBean report : filteredReports) {
			commReps.put(report.getIdReport(), report.getTitle() + " - " + report.getAddress());
		}

		return commReps;
	}

	public CommunityReportBean getCommunityReportFromId(Integer selectionId) {
		for (CommunityReportBean report : filteredReports) {
			if (selectionId.equals(report.getIdReport())) {
				return report;
			}
		}
		return null;
	}

}
