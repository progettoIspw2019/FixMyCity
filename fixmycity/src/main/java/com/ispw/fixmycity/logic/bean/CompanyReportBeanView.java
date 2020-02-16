package com.ispw.fixmycity.logic.bean;

import java.text.SimpleDateFormat;
import java.util.List;

public class CompanyReportBeanView extends ReportBeanView {
	private String companyRelated;
	private List<String> jobs;

	public String getCompanyRelated() {
		return companyRelated;
	}

	public void setCompanyRelated(String companyRelated) {
		this.companyRelated = companyRelated;
	}

	public List<String> getJobs() {
		return jobs;
	}

	public void setJobs(List<String> jobs) {
		this.jobs = jobs;
	}
	
	public String toString() {
		return "Title: " + this.getTitle() + "\nDescription: " + this.getDescription() 
		+ "\nSubmission date: " + new SimpleDateFormat("dd-MM-yyyy").format(this.getDateSubmission())
		+" by " + this.getSubmitter() + "\nHas Job: " + !this.getJobs().isEmpty();
	}

}
