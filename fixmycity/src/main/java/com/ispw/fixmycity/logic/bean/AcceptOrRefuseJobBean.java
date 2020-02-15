package com.ispw.fixmycity.logic.bean;

import java.io.File;
import java.time.LocalDate;
import java.util.Date;

import com.ispw.fixmycity.logic.model.CompanyReport;
import com.ispw.fixmycity.logic.model.CompanyUser;
import com.ispw.fixmycity.logic.util.ConverterUtil;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AcceptOrRefuseJobBean {
	private int idJob;
	private Date startDate;
	private Date endDate;
	private byte[] document;
	private String refuseMotivation;
	private String reportDescription;
	private CompanyReport relatedReport;
	private CompanyUser relatedUser;
	private String title;
	
	public AcceptOrRefuseJobBean() {
		
	}
	
	public void setIdJob(int id) {
		this.idJob=id;
		
	}
	public int getIdJob() {
		return this.idJob;
		
	}
	public void setStartDate(DatePicker startDate) {
		this.startDate=ConverterUtil.dateFromDatePicker(startDate);
		
	}
	public Date getStartDate(){
		return this.startDate;
		
	}
	
	public void setEndDate(DatePicker endDate) {
		this.endDate=ConverterUtil.dateFromDatePicker(endDate);
	}
	
	public Date getEndDate() {
		return this.endDate;
		
	}
	public void setDocument(byte[] doc) {
		this.document=doc;
		
	}
	public byte[] getDocument() {
		return this.document;
		
	}
	public String getCompanyReportName() {
		return this.title;
		
	}
	public void setCompanyReportName(String name){
		this.title=name;
	}
	
	public String getRefuseMotivation() {
		return refuseMotivation;
		
	}
	public void setRefuseMotivation(TextArea refuseDescription) {
		this.refuseMotivation=refuseDescription.getText();
		
	}
	public void setReportDescription(String description) {
		this.reportDescription=description;
	}
	public String getReportDescription() {
		return reportDescription;
		
	}
	
	public void setRelatedCompany(CompanyUser company) {
		this.relatedUser=company;
	}
	
	public void setRelatedReport(CompanyReport report) {
		this.relatedReport=report;
	}
	
	public CompanyUser getRelatedUser() {
		return this.relatedUser;
		
	}
	
	public CompanyReport getCompanyReport() {
		return this.relatedReport;
	}
	

}
