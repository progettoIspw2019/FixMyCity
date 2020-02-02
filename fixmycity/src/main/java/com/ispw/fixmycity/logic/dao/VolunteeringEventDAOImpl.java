package com.ispw.fixmycity.logic.dao;

import javax.persistence.EntityManager;

import com.ispw.fixmycity.logic.bean.VolunteeringEventBean;
import com.ispw.fixmycity.logic.model.CommunityReport;
import com.ispw.fixmycity.logic.model.VolunteeringEvent;

public class VolunteeringEventDAOImpl extends BaseDAOImpl implements VolunteeringEventDAO {

	@Override
	public VolunteeringEvent getAllVolunteeringEvents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addVolunteeringEvent(VolunteeringEventBean bean) {

		EntityManager entityManager = getEntityManager();

		VolunteeringEvent volunteeringEvent = new VolunteeringEvent();
		volunteeringEvent.setCreationDate(bean.getCreationDate());
		volunteeringEvent.setEventDate(bean.getEventDate());

		// Setting foreign key
		CommunityReport communityReport = entityManager.getReference(CommunityReport.class,
				bean.getCommunityReport().getIdReport());
		volunteeringEvent.setCommunityReport(communityReport);

		entityManager.getTransaction().begin();
		entityManager.persist(volunteeringEvent);
		entityManager.getTransaction().commit();
	}

}
