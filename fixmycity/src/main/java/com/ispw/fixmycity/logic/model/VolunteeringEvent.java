package com.ispw.fixmycity.logic.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.ispw.fixmycity.logic.bean.VolunteeringEventBean;

/**
 * The persistent class for the volunteering_events database table.
 * 
 */
@Entity
@Table(name = "volunteering_events")
@NamedQuery(name = "VolunteeringEvent.findAll", query = "SELECT v FROM VolunteeringEvent v")
@NamedQuery(name = "VolunteeringEvent.findActiveEvents", query = "SELECT v FROM VolunteeringEvent v WHERE v.eventDate >= NOW()")

public class VolunteeringEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_event")
	private int idEvent;

	private String title;

	@Column(name = "full_description")
	private String fullDescription;

	@Temporal(TemporalType.DATE)
	@Column(name = "creation_date")
	private Date creationDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "event_date")
	private Date eventDate;

	@Temporal(TemporalType.TIME)
	@Column(name = "event_time")
	private Date eventTime;

	// bi-directional many-to-many association to CitizenUser
	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "participations", joinColumns = { @JoinColumn(name = "id_event") }, inverseJoinColumns = {
			@JoinColumn(name = "username") })
	private List<CitizenUser> citizenUsers;

	// bi-directional many-to-one association to CommunityReport
	@ManyToOne(fetch = FetchType.EAGER)
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
		this.setFullDescription(volEventBean.getFullDescription());
		this.setTitle(volEventBean.getTitle());
		this.setCreationDate(volEventBean.getCreationDate());
		this.setEventTime(volEventBean.getEventTime());
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFullDescription() {
		return fullDescription;
	}

	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}

	public Date getEventTime() {
		return eventTime;
	}

	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}

}