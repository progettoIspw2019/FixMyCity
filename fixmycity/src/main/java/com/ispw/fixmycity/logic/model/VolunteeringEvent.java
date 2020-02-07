package com.ispw.fixmycity.logic.model;

import java.io.Serializable;
import javax.persistence.*;

import com.ispw.fixmycity.logic.bean.VolunteeringEventBean;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the volunteering_events database table.
 * 
 */
@Entity
@Table(name = "volunteering_events")
@NamedQuery(name = "VolunteeringEvent.findAll", query = "SELECT v FROM VolunteeringEvent v")
public class VolunteeringEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_event")
	private int idEvent;

	@Temporal(TemporalType.DATE)
	@Column(name = "creation_date")
	private Date creationDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "event_date")
	private Date eventDate;

	// bi-directional many-to-many association to CitizenUser
	@ManyToMany
	@JoinTable(name = "participations", joinColumns = { @JoinColumn(name = "id_event") }, inverseJoinColumns = {
			@JoinColumn(name = "username") })
	private List<CitizenUser> citizenUsers;

	// bi-directional many-to-one association to CommunityReport
	@ManyToOne
	@JoinColumn(name = "id_community_report")
	private CommunityReport communityReport;

	public VolunteeringEvent() {
		// This is a POJO, and there are no default values
	}

	public void setFromBean(VolunteeringEventBean volEventBean) {
		CommunityReport cr = new CommunityReport();
		cr.setFromBean(volEventBean.getCommunityReport());
		this.setCitizenUsers(volEventBean.getCitizenUsers());
		this.setCommunityReport(cr);
		this.setCreationDate(volEventBean.getCreationDate());
		this.setEventDate(volEventBean.getEventDate());
	}

	public int getIdEvent() {
		return this.idEvent;
	}

	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getEventDate() {
		return this.eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public List<CitizenUser> getCitizenUsers() {
		return this.citizenUsers;
	}

	public void setCitizenUsers(List<CitizenUser> citizenUsers) {
		this.citizenUsers = citizenUsers;
	}

	public CommunityReport getCommunityReport() {
		return this.communityReport;
	}

	public void setCommunityReport(CommunityReport communityReport) {
		this.communityReport = communityReport;
	}

}