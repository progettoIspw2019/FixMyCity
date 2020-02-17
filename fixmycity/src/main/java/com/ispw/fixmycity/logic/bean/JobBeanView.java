package com.ispw.fixmycity.logic.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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

	public void setEndDate(String endDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = formatter.parse(endDate);
			setEndDate(date);
		} catch (ParseException e) {
			Logger.getLogger("fixmycity").log(Level.SEVERE, e.toString());
		}
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

	public void setStartDate(String startDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = formatter.parse(startDate);
			setStartDate(date);
		} catch (ParseException e) {
			Logger.getLogger("fixmycity").log(Level.SEVERE, e.toString());
		}
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
