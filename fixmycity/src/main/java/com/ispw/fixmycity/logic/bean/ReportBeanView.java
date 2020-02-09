package com.ispw.fixmycity.logic.bean;

import java.math.BigDecimal;
import java.util.Date;

public class ReportBeanView {
	private String title;
	private String category;
	private String city;
	private String fullDescription;
	private BigDecimal latitude;
	private BigDecimal longitude;
	private String address;
	private byte[] image;
	private Date dateSubmission;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return fullDescription;
	}

	public void setDescription(String description) {
		this.fullDescription = description;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public byte[] getImage() {
		return image;
		
	}

	public Date getDateSubmission() {
		return dateSubmission;
	}

	public void setDateSubmission(Date dateSubmission) {
		this.dateSubmission = dateSubmission;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
