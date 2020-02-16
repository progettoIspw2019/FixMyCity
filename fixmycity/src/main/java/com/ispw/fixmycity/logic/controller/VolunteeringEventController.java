package com.ispw.fixmycity.logic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ispw.fixmycity.logic.bean.CommunityReportBean;
import com.ispw.fixmycity.logic.bean.VolunteeringEventBean;
import com.ispw.fixmycity.logic.bean.VolunteeringEventListElementBean;
import com.ispw.fixmycity.logic.dao.CommunityReportDAO;
import com.ispw.fixmycity.logic.dao.VolunteeringEventDAO;
import com.ispw.fixmycity.logic.exceptions.EmptyResultListException;
import com.ispw.fixmycity.logic.model.CitizenUser;
import com.ispw.fixmycity.logic.model.CommunityReport;
import com.ispw.fixmycity.logic.model.VolunteeringEvent;
import com.ispw.fixmycity.logic.view.SessionView;

public class VolunteeringEventController {

	List<CommunityReportBean> filteredReports = new ArrayList<>();

	public void createVolunteeringEvent(VolunteeringEventBean volunteeringEventBean) {

		VolunteeringEventDAO dao = new VolunteeringEventDAO();
		VolunteeringEvent event = dao.addVolunteeringEvent(volunteeringEventBean);
		dao.joinVolunteeringEvent(SessionView.getUsername(), event.getIdEvent());

	}

	public void joinVolunteeringEvent(VolunteeringEventBean volunteeringEventBean) {
		VolunteeringEventDAO dao = new VolunteeringEventDAO();
		dao.joinVolunteeringEvent(SessionView.getUsername(), volunteeringEventBean.getEventId());
	}

	public void quitVolunteeringEvent(VolunteeringEventBean volunteeringEventBean) {
		VolunteeringEventDAO dao = new VolunteeringEventDAO();
		dao.quitVolunteeringEvent(SessionView.getUsername(), volunteeringEventBean.getEventId());
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

		Map<Integer, String> commReps = new HashMap<>();

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

	public List<VolunteeringEventListElementBean> getActiveVolunteeringEvents() throws EmptyResultListException {

		VolunteeringEventDAO dao = new VolunteeringEventDAO();
		List<VolunteeringEvent> events = dao.findActiveEvents();

		if (events == null || events.isEmpty()) {
			throw new EmptyResultListException("At the moment, there are no active events available", null);
		}

		ArrayList<VolunteeringEventListElementBean> eventsBean = new ArrayList<>();
		String currentUser = SessionView.getUsername();
		for (VolunteeringEvent event : events) {

			VolunteeringEventListElementBean eventBean = new VolunteeringEventListElementBean();

			eventBean.setParticipantsNumber(event.getCitizenUsers().size());
			eventBean.setCreationDate(event.getCreationDate());
			eventBean.setEventDate(event.getEventDate());
			eventBean.setFullDescription(event.getFullDescription());
			eventBean.setTitle(event.getTitle());
			eventBean.setEventId(event.getIdEvent());

			for (CitizenUser citizenUser : event.getCitizenUsers()) {
				if (citizenUser.getUsername().equals(currentUser)) {
					eventBean.setUserJoined(true);
					break;
				}
				eventBean.setUserJoined(false);
			}

			eventBean.setAddress(event.getCommunityReport().getAddress());
			eventBean.setCity(event.getCommunityReport().getCity());
			eventBean.setImage(event.getCommunityReport().getImage());

			eventsBean.add(eventBean);
		}

		return eventsBean;
	}

}
