package com.ispw.fixmycity.logic.bean;

import java.util.Date;

public class JobBeanView {
	private int idJob;
	private Date endDate;
	private byte[] jobInfo;
	private Date startDate;
	private String state;
	private String rejectingMotivation;
	private int relatedReport;
	private String relatedCompany;

	public int getIdJob() {
		return idJob;
	}

	public void setIdJob(int idJob) {
		this.idJob = idJob;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public byte[] getJobInfo() {
		return jobInfo;
	}

	public void setJobInfo(byte[] jobInfo) {
		this.jobInfo = jobInfo;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getRelatedReport() {
		return relatedReport;
	}

	public void setRelatedReport(int relatedReport) {
		this.relatedReport = relatedReport;
	}

	public String getRejectingMotivation() {
		return rejectingMotivation;
	}

	public void setRejectingMotivation(String rejectingMotivation) {
		this.rejectingMotivation = rejectingMotivation;
	}

	public String getRelatedCompany() {
		return relatedCompany;
	}

	public void setRelatedCompany(String relatedCompany) {
		this.relatedCompany = relatedCompany;
	}

}
