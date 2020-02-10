package com.ispw.fixmycity.logic.bean;

import java.util.Date;

import com.ispw.fixmycity.logic.model.CompanyReport;

public class JobBean {
	private int idJob;
	private Date endDate;
	private byte[] jobInfo;
	private Date startDate;
	private String state;
	private CompanyReport relatedReport;
	
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
	public CompanyReport getRelatedReport() {
		return relatedReport;
	}
	public void setRelatedReport(CompanyReport relatedReport) {
		this.relatedReport = relatedReport;
	}
}
