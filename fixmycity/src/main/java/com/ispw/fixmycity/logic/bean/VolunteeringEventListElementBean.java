package com.ispw.fixmycity.logic.bean;

import java.util.Date;

public class VolunteeringEventListElementBean {

	private Integer elemEventId;
	private Date elemCreationDate;
	private Date elemEventDate;
	private String elemTitle;
	private String elemFullDescription;
	private String elemAddress;
	private String elemCity;
	private Integer elemParticipantsNumber;
	private byte[] elemImage;

	public byte[] getImage() {
		return elemImage;
	}

	public void setImage(byte[] image) {
		this.elemImage = image;
	}

	private boolean userJoined;

	public Integer getEventId() {
		return elemEventId;
	}

	public void setEventId(Integer eventId) {
		this.elemEventId = eventId;
	}

	public Date getCreationDate() {
		return elemCreationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.elemCreationDate = creationDate;
	}

	public Date getEventDate() {
		return elemEventDate;
	}

	public void setEventDate(Date eventDate) {
		this.elemEventDate = eventDate;
	}

	public String getTitle() {
		return elemTitle;
	}

	public void setTitle(String title) {
		this.elemTitle = title;
	}

	public String getFullDescription() {
		return elemFullDescription;
	}

	public void setFullDescription(String fullDescription) {
		this.elemFullDescription = fullDescription;
	}

	public String getAddress() {
		return elemAddress;
	}

	public void setAddress(String address) {
		this.elemAddress = address;
	}

	public String getCity() {
		return elemCity;
	}

	public void setCity(String city) {
		this.elemCity = city;
	}

	public Integer getParticipantsNumber() {
		return elemParticipantsNumber;
	}

	public void setParticipantsNumber(Integer participantsNumber) {
		this.elemParticipantsNumber = participantsNumber;
	}

	public boolean hasUserJoined() {
		return userJoined;
	}

	public void setUserJoined(boolean userJoined) {
		this.userJoined = userJoined;
	}
	
	public String toString() {
		return "Title: " + this.getTitle() +"\nDescription: " + this.getFullDescription()
			+ "\nEvent date: " + this.getEventDate() + "\nSubmission date: " + this.getCreationDate()
			+ "\nParticipants: " + this.getParticipantsNumber();
	}
}
