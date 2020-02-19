package com.ispw.fixmycity.logic.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.ispw.fixmycity.logic.bean.CompanyUserBean;
import com.ispw.fixmycity.logic.util.ConverterUtil;

/**
 * The persistent class for the company_users database table.
 * 
 */
@Entity
@Table(name = "company_users")
@NamedQuery(name = "CompanyUser.findAll", query = "SELECT c FROM CompanyUser c")
@NamedQuery(name = "CompanyUser.find", query = "SELECT c FROM CompanyUser c WHERE c.username=:input_username")
@NamedQuery(name = "CompanyUser.findAllFromCredentials", query = "SELECT c FROM CompanyUser c WHERE c.username = :input_username AND c.pwd = MD5(:input_pwd)")
@NamedQuery(name = "CompanyUser.countFromUsername", query = "SELECT count(c.username) FROM CompanyUser c WHERE c.username = :input_username")
@NamedQuery(name = "CompanyUser.findByCategoryAndCity", query = "SELECT c FROM CompanyUser c WHERE c.category = :input_category AND c.city = :input_city")

public class CompanyUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String username;

	private String pwd;

	private String companyName;

	private String category;

	private String city;

	@Lob
	private byte[] image;

	// bi-directional many-to-one association to CompanyReport
	@OneToMany(mappedBy = "companyUser", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<CompanyReport> companyReports;

	// bi-directional many-to-one association to CompanyReport
	@OneToMany(mappedBy = "relatedCompany", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Job> jobs;

	public CompanyUser() {
		// This is a POJO, and there are no default values
	}

	public void setFromBean(CompanyUserBean companyUserBean) {
		setUsername(companyUserBean.getUsername());
		setPwd(ConverterUtil.md5FromString((companyUserBean.getPassword())));
		setCategory(companyUserBean.getCategory());
		setCompanyName(companyUserBean.getCompanyName());

		if (companyUserBean.getImage() != null) {
			setImage(companyUserBean.getImage());
		}

		setCity(companyUserBean.getCity().toString());
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

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
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