package com.ispw.fixmycity.logic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ispw.fixmycity.logic.bean.VolunteeringEventBean;
import com.ispw.fixmycity.logic.model.CitizenUser;
import com.ispw.fixmycity.logic.model.CommunityReport;
import com.ispw.fixmycity.logic.model.VolunteeringEvent;

public class VolunteeringEventDAO {

	private EntityManagerFactory entityManagerFactory;

	public VolunteeringEventDAO() {
		entityManagerFactory = Persistence.createEntityManagerFactory("fixmycitydb");
	}

	public List<VolunteeringEvent> getAllVolunteeringEvents() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<VolunteeringEvent> result = entityManager
				.createNamedQuery("VolunteeringEvent.findAll", VolunteeringEvent.class).getResultList();
		entityManager.close();
		return result;
	}

	public VolunteeringEvent addVolunteeringEvent(VolunteeringEventBean eventBean) {
		VolunteeringEvent volunteeringEvent = new VolunteeringEvent();
		volunteeringEvent.setFromBean(eventBean);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		// Setting foreign key
		Integer idCommReport = eventBean.getCommunityReport().getIdReport();
		CommunityReport communityReport = entityManager.getReference(CommunityReport.class, idCommReport);
		volunteeringEvent.setCommunityReport(communityReport);

		entityManager.getTransaction().begin();
		entityManager.persist(volunteeringEvent);
		entityManager.getTransaction().commit();
		entityManager.close();
		return volunteeringEvent;
	}

	public boolean joinVolunteeringEvent(String username, Integer eventId) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		CitizenUser citizenUser = entityManager.find(CitizenUser.class, username);
		VolunteeringEvent tempEvent = entityManager.find(VolunteeringEvent.class, eventId);

		citizenUser.getVolunteeringEvents().add(tempEvent);
		tempEvent.getCitizenUsers().add(citizenUser);

		entityManager.getTransaction().begin();
		entityManager.persist(citizenUser);
		entityManager.persist(tempEvent);

		entityManager.getTransaction().commit();
		entityManager.close();
		
		return tempEvent.getCitizenUsers().contains(citizenUser);
	}

	public boolean quitVolunteeringEvent(String username, Integer eventId) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		CitizenUser citizenUser = entityManager.find(CitizenUser.class, username);
		VolunteeringEvent tempEvent = entityManager.find(VolunteeringEvent.class, eventId);

		tempEvent.getCitizenUsers().remove(citizenUser);
		citizenUser.getVolunteeringEvents().remove(tempEvent);

		entityManager.getTransaction().begin();
		entityManager.persist(citizenUser);
		entityManager.persist(tempEvent);

		entityManager.getTransaction().commit();
		entityManager.close();
		
		return !tempEvent.getCitizenUsers().contains(citizenUser);

	}

	public List<VolunteeringEvent> findActiveEvents() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<VolunteeringEvent> result = entityManager
				.createNamedQuery("VolunteeringEvent.findActiveEvents", VolunteeringEvent.class).getResultList();
		entityManager.close();
		return result;
	}

}
