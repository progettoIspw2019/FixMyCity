package com.ispw.fixmycity.logic.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the company_reports database table.
 * 
 */
@Entity
@Table(name="company_reports")
@NamedQuery(name="CompanyReport.findAll", query="SELECT c FROM CompanyReport c")
public class CompanyReport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_report")
	private int idReport;

	private String address;

	@Temporal(TemporalType.DATE)
	@Column(name="date_submission")
	private Date dateSubmission;

	@Column(name="full_description")
	private String fullDescription;

	@Lob
	private byte[] image;

	private BigDecimal latitude;

	private BigDecimal longitude;

	private String title;

	//bi-directional many-to-one association to CitizenUser
	@ManyToOne
	@JoinColumn(name="submitter")
	private CitizenUser citizenUser;

	//bi-directional many-to-one association to CompanyUser
	@ManyToOne
	@JoinColumn(name="related_company")
	private CompanyUser companyUser;

	//bi-directional many-to-one association to Job
	@OneToMany(mappedBy="companyReport")
	private List<Job> jobs;

	public CompanyReport() {
		// This is a POJO, and there are no default values
	}

	public int getIdReport() {
		return this.idReport;
	}

	public void setIdReport(int idReport) {
		this.idReport = idReport;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateSubmission() {
		return this.dateSubmission;
	}

	public void setDateSubmission(Date dateSubmission) {
		this.dateSubmission = dateSubmission;
	}

	public String getFullDescription() {
		return this.fullDescription;
	}

	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}

	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
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

	public Job addJob(Job job) {
		getJobs().add(job);
		job.setCompanyReport(this);

		return job;
	}

	public Job removeJob(Job job) {
		getJobs().remove(job);
		job.setCompanyReport(null);

		return job;
	}

}