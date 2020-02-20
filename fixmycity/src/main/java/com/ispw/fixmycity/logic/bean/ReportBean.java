package com.ispw.fixmycity.logic.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.ispw.fixmycity.logic.model.CitizenUser;

public abstract class ReportBean {

	private int idReportBean;
	private String titleBean;
	private String categoryBean;
	private String fullDescriptionBean;
	private BigDecimal latitudeBean;
	private BigDecimal longitudeBean;
	private String addressBean;
	private byte[] imageBean;
	private Date dateSubmissionBean;
	private CitizenUser referenceSubmitterBean;
	private String cityBean;

	public int getIdReport() {
		return idReportBean;
	}

	public void setIdReport(int idReport) {
		this.idReportBean = idReport;
	}

	public String getTitle() {
		return titleBean;
	}

	public void setTitle(String title) {
		this.titleBean = title;
	}

	public String getCategory() {
		return categoryBean;
	}

	public void setCategory(String category) {
		this.categoryBean = category;
	}

	public String getFullDescription() {
		return fullDescriptionBean;
	}

	public void setFullDescription(String fullDescription) {
		this.fullDescriptionBean = fullDescription;
	}

	public BigDecimal getLatitude() {
		return latitudeBean;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitudeBean = latitude;
	}

	public BigDecimal getLongitude() {
		return longitudeBean;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitudeBean = longitude;
	}

	public String getAddress() {
		return addressBean;
	}

	public void setAddress(String address) {
		this.addressBean = address;
	}

	public byte[] getImage() {
		return imageBean;
	}

	public void setImage(byte[] file) {
		this.imageBean = file;
	}

	public Date getDateSubmission() {
		return dateSubmissionBean;
	}

	public void setDateSubmission(Date dateSubmission) {
		this.dateSubmissionBean = dateSubmission;
	}

	public CitizenUser getSubmitter() {
		return this.referenceSubmitterBean;
	}

	public void setSubmitter(CitizenUser referenceSubmitter) {
		this.referenceSubmitterBean = referenceSubmitter;
	}

	public String getCity() {
		return cityBean;
	}

	public void setCity(String city) {
		this.cityBean = city;
	}

}
