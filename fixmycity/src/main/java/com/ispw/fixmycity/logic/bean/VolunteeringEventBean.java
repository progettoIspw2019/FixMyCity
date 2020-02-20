package com.ispw.fixmycity.logic.bean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ispw.fixmycity.logic.exceptions.InvalidFieldException;
import com.ispw.fixmycity.logic.model.CitizenUser;

public class VolunteeringEventBean {

	private Integer volEventId;
	private Date evCreationDate;
	private Date evDate;
	private String titleBean;
	private String fullDescriptionBean;
	private Date evTime;

	private List<CitizenUser> citizenUsers;
	private CommunityReportBean communityReport;

	public Date getCreationDate() {
		return evCreationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.evCreationDate = creationDate;
	}

	public Date getEventDate() {
		return evDate;
	}

	public void setEventDate(Date eventDate) {
		this.evDate = eventDate;
	}

	public void setEventDate(String eventDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = formatter.parse(eventDate);
			setEventDate(date);
		} catch (ParseException e) {
			Logger.getLogger("fixmycity").log(Level.SEVERE, e.toString());
		}
	}

	public List<CitizenUser> getCitizenUsers() {
		return citizenUsers;
	}

	public void setCitizenUsers(List<CitizenUser> citizenUsers) {
		this.citizenUsers = citizenUsers;
	}

	public CommunityReportBean getCommunityReport() {
		return communityReport;
	}

	public void setCommunityReport(CommunityReportBean communityReport) {
		this.communityReport = communityReport;
	}

	public String getTitle() {
		return titleBean;
	}

	public void setTitle(String title) {
		this.titleBean = title;
	}

	public String getFullDescription() {
		return fullDescriptionBean;
	}

	public void setFullDescription(String fullDescription) {
		this.fullDescriptionBean = fullDescription;
	}

	public Date getEventTime() {
		return evTime;
	}

	public void setEventTime(String eventTime) throws InvalidFieldException {
		try {
			DateFormat df = new SimpleDateFormat("HH:mm");
			Date time = df.parse(eventTime);
			this.evTime = time;
		} catch (ParseException e) {
			throw new InvalidFieldException("Incorrect date");
		}
	}

	public Integer getEventId() {
		return volEventId;
	}

	public void setEventId(Integer eventId) {
		this.volEventId = eventId;
	}


}
