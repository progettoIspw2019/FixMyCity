package com.ispw.fixmycity.logic.bean;

import java.util.Date;
import java.util.List;

import com.ispw.fixmycity.logic.model.CitizenUser;

public class VolunteeringEventListElementBean {

	private Integer eventId;
	private Date creationDate;
	private Date eventDate;
	private String title;
	private String fullDescription;
	private String address;
	private String city;
	private Integer participantsNumber;
	private boolean userJoined;

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getParticipantsNumber() {
		return participantsNumber;
	}

	public void setParticipantsNumber(Integer participantsNumber) {
		this.participantsNumber = participantsNumber;
	}

	public boolean hasUserJoined() {
		return userJoined;
	}

	public void setUserJoined(boolean userJoined) {
		this.userJoined = userJoined;
	}

}
