package com.ispw.fixmycity.logic.bean;

import java.math.BigDecimal;
import java.util.Date;

public class ReportBeanView {
	private String titleView;
	private String categoryView;
	private String cityView;
	private String fullDescriptionView;
	private BigDecimal latitudeView;
	private BigDecimal longitudeView;
	private String addressView;
	private byte[] imageView;
	private Date dateSubmissionView;
	private String submitterView;

	public String getTitle() {
		return titleView;
	}

	public void setTitle(String title) {
		this.titleView = title;
	}

	public String getCategory() {
		return categoryView;
	}

	public void setCategory(String category) {
		this.categoryView = category;
	}

	public String getDescription() {
		return fullDescriptionView;
	}

	public void setDescription(String description) {
		this.fullDescriptionView = description;
	}

	public BigDecimal getLatitude() {
		return latitudeView;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitudeView = latitude;
	}

	public BigDecimal getLongitude() {
		return longitudeView;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitudeView = longitude;
	}

	public String getAddress() {
		return addressView;
	}

	public void setAddress(String address) {
		this.addressView = address;
	}
	
	
	public void setImage(byte[] image) {
		this.imageView = image;
	}
	
	public byte[] getImage() {
		return imageView;
		
	}

	public Date getDateSubmission() {
		return dateSubmissionView;
	}

	public void setDateSubmission(Date dateSubmission) {
		this.dateSubmissionView = dateSubmission;
	}

	public String getCity() {
		return cityView;
	}

	public void setCity(String city) {
		this.cityView = city;
	}

	public String getSubmitter() {
		return submitterView;
	}

	public void setSubmitter(String submitter) {
		this.submitterView = submitter;
	}
}
