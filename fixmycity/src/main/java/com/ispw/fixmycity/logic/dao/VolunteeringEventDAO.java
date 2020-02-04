package com.ispw.fixmycity.logic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ispw.fixmycity.logic.bean.VolunteeringEventBean;
import com.ispw.fixmycity.logic.model.CommunityReport;
import com.ispw.fixmycity.logic.model.VolunteeringEvent;

public class VolunteeringEventDAO {
	
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	public VolunteeringEventDAO() {
		entityManagerFactory = Persistence.createEntityManagerFactory("fixmycitydb");
		entityManager = entityManagerFactory.createEntityManager();
	}

	public List<VolunteeringEvent> getAllVolunteeringEvents() {
		return entityManager.createNamedQuery("VolunteeringEvent.findAll").getResultList();
	}

	public void addVolunteeringEvent(VolunteeringEventBean eventBean) {
		VolunteeringEvent volunteeringEvent = new VolunteeringEvent();
		volunteeringEvent.setFromBean(eventBean);

		// Setting foreign key
		Integer idCommReport = eventBean.getCommunityReport().getIdReport();
		CommunityReport communityReport = entityManager
										.getReference(CommunityReport.class, idCommReport);
		volunteeringEvent.setCommunityReport(communityReport);
		
		entityManager.persist(volunteeringEvent);
	}

}
