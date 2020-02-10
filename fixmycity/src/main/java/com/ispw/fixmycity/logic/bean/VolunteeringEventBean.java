package com.ispw.fixmycity.logic.bean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ispw.fixmycity.logic.app.exceptions.InvalidFieldException;
import com.ispw.fixmycity.logic.model.CitizenUser;

public class VolunteeringEventBean {

	private Date creationDate;
	private Date eventDate;
	private String title;
	private String fullDescription;
	private Date eventTime;

	private List<CitizenUser> citizenUsers;
	private CommunityReportBean communityReport;

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public void setEventDate(String eventDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = formatter.parse(eventDate);
			setEventDate(date);
		} catch (ParseException e) {
			// TODO devo implementare l'eccezione creata
			e.printStackTrace();
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
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFullDescription() {
		return fullDescription;
	}

	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}

	public Date getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) throws InvalidFieldException {
		try {
			DateFormat df = new SimpleDateFormat("HH:mm");
			Date time = df.parse(eventTime);
			this.eventTime = time;
		} catch (ParseException e) {
			throw new InvalidFieldException("Incorrect date");
		}
	}

}
