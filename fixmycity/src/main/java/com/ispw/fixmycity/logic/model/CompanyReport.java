package com.ispw.fixmycity.logic.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.ispw.fixmycity.logic.bean.CompanyReportBean;

/**
 * The persistent class for the company_reports database table.
 * 
 */
@Entity
@Table(name = "company_reports")
@NamedQuery(name = "CompanyReport.findAll", query = "SELECT c FROM CompanyReport c")
@NamedQuery(name = "CompanyReport.findAllFromCompanyUsername", query = "SELECT c FROM CompanyReport c WHERE companyUser = :input_company")
public class CompanyReport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_report")
	private int idReportComp;

	@Column(name = "address")
	private String address;

	@Column(name = "category")
	private String categoryComp;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_submission")
	private Date dateSubmissionRep;

	@Column(name = "full_description")
	private String fullDescription;

	@Lob
	@Column(length = 16777215, name = "image")
	private byte[] imageCompRep;

	private BigDecimal latitude;

	private BigDecimal longitude;

	private String title;

	private String city;

	private String status;

	private int refuseCounter;

	private String refuseDescription;

	// bi-directional many-to-one association to CitizenUser
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "submitter")
	private CitizenUser citizenUser;

	// bi-directional many-to-one association to CompanyUser
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "related_company")
	private CompanyUser companyUser;

	// bi-directional many-to-one association to Job
	@OneToMany(mappedBy = "companyReport", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Job> jobs;

	public CompanyReport() {
		// This is a POJO, and there are no default values
	}

	public int getIdReport() {
		return this.idReportComp;
	}

	public void setIdReport(int idReport) {
		this.idReportComp = idReport;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateSubmission() {
		return this.dateSubmissionRep;
	}

	public void setDateSubmission(Date dateSubmission) {
		this.dateSubmissionRep = dateSubmission;
	}

	public String getFullDescription() {
		return this.fullDescription;
	}

	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}

	public byte[] getImage() {
		return this.imageCompRep;
	}

	public void setImage(byte[] image) {
		this.imageCompRep = image;
	}

	public BigDecimal getLatitude() {
		return this.latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return this.longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public CitizenUser getCitizenUser() {
		return this.citizenUser;
	}

	public void setCitizenUser(CitizenUser citizenUser) {
		this.citizenUser = citizenUser;
	}

	public CompanyUser getCompanyUser() {
		return this.companyUser;
	}

	public void setCompanyUser(CompanyUser companyUser) {
		this.companyUser = companyUser;
	}

	public List<Job> getJobs() {
		return this.jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public void setFromBean(CompanyReportBean compRepBean) {
		this.setAddress(compRepBean.getAddress());
		this.setCitizenUser(compRepBean.getSubmitter());
		this.setCategory(compRepBean.getCategory());
		this.setCompanyUser(compRepBean.getCompany());
		this.setDateSubmission(compRepBean.getDateSubmission());
		this.setFullDescription(compRepBean.getFullDescription());
		this.setImage(compRepBean.getImage());
		this.setLatitude(compRepBean.getLatitude());
		this.setLongitude(compRepBean.getLongitude());
		this.setTitle(compRepBean.getTitle());
		this.setCity(compRepBean.getCity());
	}

	public String getCategory() {
		return categoryComp;
	}

	public void setCategory(String category) {
		this.categoryComp = category;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setRefuseDescription(String description) {
		this.refuseDescription = description;

	}

	public String refuseDescription() {
		return this.refuseDescription;
	}

	public int getRefuseCounter() {
		return this.refuseCounter;

	}

	public void increaseRefuseCounter() {
		this.refuseCounter++;
	}

	public void addJob(Job job) {
		if (this.getJobs() != null) {
			List<Job> jobList = this.getJobs();
			jobList.add(job);
			this.setJobs(jobList);
		} else {
			List<Job> jobList = new ArrayList<>();
			jobList.add(job);
			this.setJobs(jobList);
		}
	}

	public void initRejectCounter() {
		this.refuseCounter = 0;
	}

	public void removeJob(Job job) {
		if (this.jobs.contains(job))
			this.jobs.remove(job);
	}
}