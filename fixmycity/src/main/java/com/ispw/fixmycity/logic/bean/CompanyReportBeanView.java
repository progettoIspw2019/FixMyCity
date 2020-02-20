package com.ispw.fixmycity.logic.bean;

import java.text.SimpleDateFormat;
import java.util.List;

public class CompanyReportBeanView extends ReportBeanView {
	private String companyRelatedBean;
	private String statusBean;
	private List<String> jobsBean;
	private int idBean;

	public String getCompanyRelated() {
		return companyRelatedBean;
	}

	public void setCompanyRelated(String companyRelated) {
		this.companyRelatedBean = companyRelated;
	}

	public List<String> getJobs() {
		return jobsBean;
	}

	public void setJobs(List<String> jobs) {
		this.jobsBean = jobs;
	}
	
	public String toString() {
		return "Title: " + this.getTitle() + "\nDescription: " + this.getDescription() 
		+ "\nSubmission date: " + new SimpleDateFormat("dd-MM-yyyy").format(this.getDateSubmission())
		+" by " + this.getSubmitter() + "\nHas Job: " + (this.getJobs()!=null && !this.getJobs().isEmpty());
	}

	public String getStatus() {
		return statusBean;
	}

	public void setStatus(String status) {
		this.statusBean = status;
	}

	public int getId() {
		return idBean;
	}

	public void setId(int id) {
		this.idBean = id;
	}

}
