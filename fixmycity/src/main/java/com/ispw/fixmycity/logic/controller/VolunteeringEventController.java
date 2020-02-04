package com.ispw.fixmycity.logic.controller;

import com.ispw.fixmycity.logic.bean.VolunteeringEventBean;
import com.ispw.fixmycity.logic.dao.VolunteeringEventDAO;

public class VolunteeringEventController {

	VolunteeringEventBean volunteeringEvent;

	public VolunteeringEventController(VolunteeringEventBean bean) {
		this.setVolunteeringEvent(bean);
	}

	public VolunteeringEventBean getVolunteeringEvent() {
		return volunteeringEvent;
	}

	public void setVolunteeringEvent(VolunteeringEventBean volunteeringEvent) {
		this.volunteeringEvent = volunteeringEvent;
	}

	public void createVolunteeringEvent() {

		VolunteeringEventDAO dao = new VolunteeringEventDAO();

		dao.addVolunteeringEvent(volunteeringEvent);

		// Poi:
		// ottieni User dalla sessione
		// assegna User alla sessione
		// dao.assignCitizenToVolunteeringEvent(CitizenUser user);
		
	}

}
