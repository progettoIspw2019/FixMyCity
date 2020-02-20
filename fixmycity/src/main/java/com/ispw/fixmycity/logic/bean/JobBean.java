package com.ispw.fixmycity.logic.bean;

import java.util.Date;

import com.ispw.fixmycity.logic.model.CompanyReport;

public class JobBean {
	private int idJobBean;
	private Date endDateBean;
	private byte[] jobInfoBean;
	private Date startDateBean;
	private CompanyReport relatedReport;
	
	public int getIdJob() {
		return idJobBean;
	}
	public void setIdJob(int idJob) {
		this.idJobBean = idJob;
	}
	public Date getEndDate() {
		return endDateBean;
	}
	public void setEndDate(Date endDate) {
		this.endDateBean = endDate;
	}
	public byte[] getJobInfo() {
		return jobInfoBean;
	}
	public void setJobInfo(byte[] jobInfo) {
		this.jobInfoBean = jobInfo;
	}
	public Date getStartDate() {
		return startDateBean;
	}
	public void setStartDate(Date startDate) {
		this.startDateBean = startDate;
	}
	
	public CompanyReport getRelatedReport() {
		return relatedReport;
	}
	public void setRelatedReport(CompanyReport relatedReport) {
		this.relatedReport = relatedReport;
	}
}
