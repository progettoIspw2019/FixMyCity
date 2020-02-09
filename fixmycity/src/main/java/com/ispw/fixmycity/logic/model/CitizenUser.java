package com.ispw.fixmycity.logic.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ispw.fixmycity.logic.bean.CitizenUserBean;
import com.ispw.fixmycity.logic.util.ConverterUtil;

/**
 * The persistent class for the citizen_users database table.
 * 
 */
@Entity
@Table(name = "citizen_users")
@NamedQuery(name = "CitizenUser.findAll", query = "SELECT c FROM CitizenUser c")
@NamedQuery(name = "CitizenUser.findAllFromCredentials", query = "SELECT c FROM CitizenUser c WHERE c.username = :input_username AND c.pwd = MD5(:input_pwd)")
@NamedQuery(name = "CitizenUser.countFromUsername", query = "SELECT count(c.username) FROM CitizenUser c WHERE c.username = :input_username")

public class CitizenUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String username;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "profile_picture")
	@Lob
	private byte[] profilePicture;

	private String pwd;

	private String surname;

	// bi-directional many-to-one association to CommunityReport
	@OneToMany(mappedBy = "citizenUser")
	private List<CommunityReport> communityReports;

	// bi-directional many-to-one association to CompanyReport
	@OneToMany(mappedBy = "citizenUser")
	private List<CompanyReport> companyReports;

	// bi-directional many-to-many association to VolunteeringEvent
	@ManyToMany(mappedBy = "citizenUsers")
	private List<VolunteeringEvent> volunteeringEvents;

	public CitizenUser() {
		// This is a POJO, and there are no default values
	}

	public void setFromBean(CitizenUserBean citizenUserBean) {
		setUsername(citizenUserBean.getUsername());
		setPwd(citizenUserBean.getPassword());
		setSurname(citizenUserBean.getLastName());
		setFirstName(citizenUserBean.getFirstName());
		setProfilePicture(ConverterUtil.byteArrayFromImage(citizenUserBean.getProfilePicture()));
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public byte[] getProfilePicture() {
		return this.profilePicture;
	}

	public void setProfilePicture(byte[] profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<CommunityReport> getCommunityReports() {
		return this.communityReports;
	}

	public void setCommunityReports(List<CommunityReport> communityReports) {
		this.communityReports = communityReports;
	}

	public CommunityReport addCommunityReport(CommunityReport communityReport) {
		getCommunityReports().add(communityReport);
		communityReport.setCitizenUser(this);

		return communityReport;
	}

	public CommunityReport removeCommunityReport(CommunityReport communityReport) {
		getCommunityReports().remove(communityReport);
		communityReport.setCitizenUser(null);

		return communityReport;
	}

	public List<CompanyReport> getCompanyReports() {
		return this.companyReports;
	}

	public void setCompanyReports(List<CompanyReport> companyReports) {
		this.companyReports = companyReports;
	}

	public CompanyReport addCompanyReport(CompanyReport companyReport) {
		getCompanyReports().add(companyReport);
		companyReport.setCitizenUser(this);

		return companyReport;
	}

	public CompanyReport removeCompanyReport(CompanyReport companyReport) {
		getCompanyReports().remove(companyReport);
		companyReport.setCitizenUser(null);

		return companyReport;
	}

	public List<VolunteeringEvent> getVolunteeringEvents() {
		return this.volunteeringEvents;
	}

	public void setVolunteeringEvents(List<VolunteeringEvent> volunteeringEvents) {
		this.volunteeringEvents = volunteeringEvents;
	}

}