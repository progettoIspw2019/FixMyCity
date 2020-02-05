package com.ispw.fixmycity.logic.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the jobs database table.
 * 
 */
@Entity
@Table(name = "jobs")
@NamedQuery(name = "Job.findAll", query = "SELECT j FROM Job j")
public class Job implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_job")
	private int idJob;

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "job_info", length = 16777215)
	@Lob
	private byte[] jobInfo;

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;

	private String state;

	// bi-directional many-to-one association to CompanyReport
	@ManyToOne
	@JoinColumn(name = "id_company_report")
	private CompanyReport companyReport;

	public Job() {
		// This is a POJO, and there are no default values
	}

	public int getIdJob() {
		return this.idJob;
	}

	public void setIdJob(int idJob) {
		this.idJob = idJob;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public byte[] getJobInfo() {
		return this.jobInfo;
	}

	public void setJobInfo(byte[] jobInfo) {
		this.jobInfo = jobInfo;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public CompanyReport getCompanyReport() {
		return this.companyReport;
	}

	public void setCompanyReport(CompanyReport companyReport) {
		this.companyReport = companyReport;
	}

}