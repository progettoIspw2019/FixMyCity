package com.ispw.fixmycity.logic.bean;

import java.util.Date;
import java.util.List;

import com.ispw.fixmycity.logic.model.CitizenUser;
import com.ispw.fixmycity.logic.model.CommunityReport;

public class VolunteeringEventBean {

	private Date creationDate;
	private Date eventDate;
	private List<CitizenUser> citizenUsers;
	private CommunityReport communityReport;

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

	public List<CitizenUser> getCitizenUsers() {
		return citizenUsers;
	}

	public void setCitizenUsers(List<CitizenUser> citizenUsers) {
		this.citizenUsers = citizenUsers;
	}

	public CommunityReport getCommunityReport() {
		return communityReport;
	}

	public void setCommunityReport(CommunityReport communityReport) {
		this.communityReport = communityReport;
	}
}
