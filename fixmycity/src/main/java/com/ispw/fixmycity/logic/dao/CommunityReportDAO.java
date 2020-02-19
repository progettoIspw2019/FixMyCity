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

	public CommunityReportDAO() {
		entityManagerFactory = Persistence.createEntityManagerFactory("fixmycitydb");
	}

	public List<CommunityReport> findAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<CommunityReport> result = entityManager.createNamedQuery("CommunityReport.findAll", CommunityReport.class)
				.getResultList();
		entityManager.close();
		return result;
	}

	public CommunityReport findByPrimaryKey(Integer id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		CommunityReport result = entityManager.find(CommunityReport.class, id);
		entityManager.close();
		return result;
	}

	public CommunityReport add(CommunityReportBean commRepBean) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		CommunityReport commReport = new CommunityReport();
		commReport.setFromBean(commRepBean);

		entityManager.getTransaction().begin();
		entityManager.persist(commReport);
		entityManager.getTransaction().commit();
		entityManager.close();
		return commReport; // must return entity, auto-generated id might be useful
	}

	// Versione in cui i controller usano le entity
	public void update(CommunityReport commReport) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		entityManager.merge(commReport);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public void delete(Integer id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		CommunityReport repRef = entityManager.getReference(CommunityReport.class, id);
		entityManager.remove(repRef);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public void assignVolunteeringEvent(Integer eventId, Integer communityReportId) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		CommunityReport repRef = entityManager.getReference(CommunityReport.class, communityReportId);
		VolunteeringEvent eventRef = entityManager.getReference(VolunteeringEvent.class, eventId);

		List<VolunteeringEvent> repEvents = repRef.getVolunteeringEvents();

		repEvents.add(eventRef);
		repRef.setVolunteeringEvents(repEvents);
		update(repRef);
		entityManager.close();
	}
}
