package com.ispw.fixmycity.logic.bean;

import java.math.BigDecimal;

public class CommunityReportBean extends ReportBean {
	private BigDecimal latitude;

	private BigDecimal longitude;

	private String title;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
