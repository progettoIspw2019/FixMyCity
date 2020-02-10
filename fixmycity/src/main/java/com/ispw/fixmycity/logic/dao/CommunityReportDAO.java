package com.ispw.fixmycity.logic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ispw.fixmycity.logic.bean.CommunityReportBean;
import com.ispw.fixmycity.logic.model.CommunityReport;
import com.ispw.fixmycity.logic.model.VolunteeringEvent;

public class CommunityReportDAO {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	public CommunityReportDAO() {
		entityManagerFactory = Persistence.createEntityManagerFactory("fixmycitydb");
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	public List<CommunityReport> findAll() {
		return entityManager.createNamedQuery("CommunityReport.findAll", CommunityReport.class).getResultList();
	}

	public CommunityReport findByPrimaryKey(Integer id) {
		return entityManager.find(CommunityReport.class, id);
	}

	public CommunityReport add(CommunityReportBean commRepBean) {
		CommunityReport commReport = new CommunityReport();
		commReport.setFromBean(commRepBean);
		
		entityManager.getTransaction().begin();
		entityManager.persist(commReport);
		entityManager.getTransaction().commit();

		return commReport; // must return entity, auto-generated id might be useful
	}

	// Versione in cui i controller usano le entity
	public void update(CommunityReport commReport) {
		entityManager.persist(commReport);
	}

	public void delete(Integer id) {
		CommunityReport repRef = entityManager.getReference(CommunityReport.class, id);
		entityManager.remove(repRef);
	}

	public void assignVolunteeringEvent(Integer eventId, Integer communityReportId) {
		CommunityReport repRef = entityManager.getReference(CommunityReport.class, communityReportId);
		VolunteeringEvent eventRef = entityManager.getReference(VolunteeringEvent.class, eventId);

		List<VolunteeringEvent> repEvents = repRef.getVolunteeringEvents();

		repEvents.add(eventRef);
		repRef.setVolunteeringEvents(repEvents);
		update(repRef);
	}
}
