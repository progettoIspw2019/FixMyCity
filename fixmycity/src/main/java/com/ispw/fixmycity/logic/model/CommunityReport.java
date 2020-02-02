package com.ispw.fixmycity.logic.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the community_reports database table.
 * 
 */
@Entity
@Table(name="community_reports")
@NamedQuery(name="CommunityReport.findAll", query="SELECT c FROM CommunityReport c")
public class CommunityReport implements Serializable {
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

	//bi-directional many-to-one association to VolunteeringEvent
	@OneToMany(mappedBy="communityReport")
	private List<VolunteeringEvent> volunteeringEvents;

	public CommunityReport() {
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

}