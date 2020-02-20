package com.ispw.fixmycity.logic.model;

import java.io.Serializable;
import java.math.BigDecimal;
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

import com.ispw.fixmycity.logic.bean.CommunityReportBean;

/**
 * The persistent class for the community_reports database table.
 * 
 */
@Entity
@Table(name = "community_reports")
@NamedQuery(name = "CommunityReport.findAll", query = "SELECT c FROM CommunityReport c")
public class CommunityReport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_report")
	private int idReport;

	private String address;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_submission")
	private Date dateSubmission;

	@Column(name = "full_description")
	private String fullDescription;

	@Lob
	private byte[] image;

	private BigDecimal latitudeComm;

	private BigDecimal longitudeComm;

	private String titleComm;

	private String categoryComm;

	private String cityComm;

	// bi-directional many-to-one association to CitizenUser
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "submitter")
	private CitizenUser citizenUser;

	// bi-directional many-to-one association to VolunteeringEvent
	@OneToMany(mappedBy = "communityReport", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<VolunteeringEvent> volunteeringEvents;

	public CommunityReport() {
		// This is a POJO, and there are no default values
	}

	public void setFromBean(CommunityReportBean commRepBean) {
		this.setAddress(commRepBean.getAddress());
		this.setDateSubmission(commRepBean.getDateSubmission());
		this.setFullDescription(commRepBean.getFullDescription());
		this.setImage(commRepBean.getImage());
		this.setLatitude(commRepBean.getLatitude());
		this.setLongitude(commRepBean.getLongitude());
		this.setCategory(commRepBean.getCategory());
		this.setCitizenUser(commRepBean.getSubmitter());
		this.setTitle(commRepBean.getTitle());
		this.setCity(commRepBean.getCity());
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
		return this.latitudeComm;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitudeComm = latitude;
	}

	public BigDecimal getLongitude() {
		return this.longitudeComm;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitudeComm = longitude;
	}

	public String getTitle() {
		return this.titleComm;
	}

	public void setTitle(String title) {
		this.titleComm = title;
	}

	public CitizenUser getCitizenUser() {
		return this.citizenUser;
	}

	public void setCitizenUser(CitizenUser citizenUser) {
		this.citizenUser = citizenUser;
	}

	public List<VolunteeringEvent> getVolunteeringEvents() {
		return this.volunteeringEvents;
	}

	public void setVolunteeringEvents(List<VolunteeringEvent> volunteeringEvents) {
		this.volunteeringEvents = volunteeringEvents;
	}

	public VolunteeringEvent addVolunteeringEvent(VolunteeringEvent volunteeringEvent) {
		getVolunteeringEvents().add(volunteeringEvent);
		volunteeringEvent.setCommunityReport(this);

		return volunteeringEvent;
	}

	public VolunteeringEvent removeVolunteeringEvent(VolunteeringEvent volunteeringEvent) {
		getVolunteeringEvents().remove(volunteeringEvent);
		volunteeringEvent.setCommunityReport(null);

		return volunteeringEvent;
	}

	public String getCategory() {
		return categoryComm;
	}

	public void setCategory(String category) {
		this.categoryComm = category;
	}

	public String getCity() {
		return cityComm;
	}

	public void setCity(String city) {
		this.cityComm = city;
	}

}