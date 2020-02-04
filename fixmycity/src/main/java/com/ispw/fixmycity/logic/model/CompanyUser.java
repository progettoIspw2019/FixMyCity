package com.ispw.fixmycity.logic.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the company_users database table.
 * 
 */
@Entity
@Table(name="company_users")
@NamedQuery(name="CompanyUser.findAll", query="SELECT c FROM CompanyUser c")
public class CompanyUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String username;

	private String category;

	@Column(name="interested_area")
	private String interestedArea;

	private String pwd;

	//bi-directional many-to-one association to CompanyReport
	@OneToMany(mappedBy="companyUser")
	private List<CompanyReport> companyReports;

	public CompanyUser() {
		// This is a POJO, and there are no default values
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getInterestedArea() {
		return this.interestedArea;
	}

	public void setInterestedArea(String interestedArea) {
		this.interestedArea = interestedArea;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public List<CompanyReport> getCompanyReports() {
		return this.companyReports;
	}

	public void setCompanyReports(List<CompanyReport> companyReports) {
		this.companyReports = companyReports;
	}

	public CompanyReport addCompanyReport(CompanyReport companyReport) {
		getCompanyReports().add(companyReport);
		companyReport.setCompanyUser(this);

		return companyReport;
	}

	public CompanyReport removeCompanyReport(CompanyReport companyReport) {
		getCompanyReports().remove(companyReport);
		companyReport.setCompanyUser(null);

		return companyReport;
	}

}