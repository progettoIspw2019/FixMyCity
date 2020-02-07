package com.ispw.fixmycity.logic.bean;

import java.math.BigDecimal;
import java.util.Date;

public abstract class ReportBean {

	private int idReport;
	private String title;
	private String category;
	private String fullDescription;
	private BigDecimal latitude;
	private BigDecimal longitude;
	private String address;
	private byte[] image;
	private Date dateSubmission;

	public int getIdReport() {
		return idReport;
	}

	public void setIdReport(int idReport) {
		this.idReport = idReport;
	}

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

	public String getFullDescription() {
		return fullDescription;
	}

	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Date getDateSubmission() {
		return dateSubmission;
	}

	public void setDateSubmission(Date dateSubmission) {
		this.dateSubmission = dateSubmission;
	}

}
