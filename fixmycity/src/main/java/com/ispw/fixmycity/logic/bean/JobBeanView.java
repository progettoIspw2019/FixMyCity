package com.ispw.fixmycity.logic.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JobBeanView {
	private int idJobView;
	private Date endDateView;
	private byte[] jobInfoView;
	private Date startDateView;
	private String stateView;
	private String rejectingMotivationView;
	private int relatedReportView;
	private String relatedCompanyView;

	public int getIdJob() {
		return idJobView;
	}

	public void setIdJob(int idJob) {
		this.idJobView = idJob;
	}

	public Date getEndDate() {
		return endDateView;
	}

	public void setEndDate(Date endDate) {
		this.endDateView = endDate;
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
		return jobInfoView;
	}

	public void setJobInfo(byte[] jobInfo) {
		this.jobInfoView = jobInfo;
	}

	public Date getStartDate() {
		return startDateView;
	}

	public void setStartDate(Date startDate) {
		this.startDateView = startDate;
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
		return stateView;
	}

	public void setState(String state) {
		this.stateView = state;
	}

	public int getRelatedReport() {
		return relatedReportView;
	}

	public void setRelatedReport(int relatedReport) {
		this.relatedReportView = relatedReport;
	}

	public String getRejectingMotivation() {
		return rejectingMotivationView;
	}

	public void setRejectingMotivation(String rejectingMotivation) {
		this.rejectingMotivationView = rejectingMotivation;
	}

	public String getRelatedCompany() {
		return relatedCompanyView;
	}

	public void setRelatedCompany(String relatedCompany) {
		this.relatedCompanyView = relatedCompany;
	}

}
