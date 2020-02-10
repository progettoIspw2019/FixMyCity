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
	private EntityManager entityManager;

	public VolunteeringEventDAO() {
		entityManagerFactory = Persistence.createEntityManagerFactory("fixmycitydb");
	}

	public List<VolunteeringEvent> getAllVolunteeringEvents() {
		entityManager = entityManagerFactory.createEntityManager();
		return entityManager.createNamedQuery("VolunteeringEvent.findAll", VolunteeringEvent.class).getResultList();
	}

	public VolunteeringEvent addVolunteeringEvent(VolunteeringEventBean eventBean) {
		VolunteeringEvent volunteeringEvent = new VolunteeringEvent();
		volunteeringEvent.setFromBean(eventBean);
		entityManager = entityManagerFactory.createEntityManager();

		// Setting foreign key
		Integer idCommReport = eventBean.getCommunityReport().getIdReport();
		CommunityReport communityReport = entityManager.getReference(CommunityReport.class, idCommReport);
		volunteeringEvent.setCommunityReport(communityReport);

		entityManager.getTransaction().begin();
		entityManager.persist(volunteeringEvent);
		entityManager.getTransaction().commit();

		return volunteeringEvent;
	}

	public void joinVolunteeringEvent(String username, VolunteeringEvent event) {
		entityManager = entityManagerFactory.createEntityManager();
		CitizenUser citizenUser = entityManager.find(CitizenUser.class, username);
		citizenUser.getVolunteeringEvents().add(event);

		VolunteeringEvent tempEvent = entityManager.getReference(VolunteeringEvent.class, event.getIdEvent());

		tempEvent.getCitizenUsers().add(citizenUser);

		entityManager.getTransaction().begin();
		entityManager.persist(citizenUser);
		entityManager.persist(tempEvent);

		entityManager.getTransaction().commit();
	}

	public List<VolunteeringEvent> findActiveEvents() {
		entityManager = entityManagerFactory.createEntityManager();
		return entityManager.createNamedQuery("VolunteeringEvent.findActiveEvents").getResultList();
	}

}
