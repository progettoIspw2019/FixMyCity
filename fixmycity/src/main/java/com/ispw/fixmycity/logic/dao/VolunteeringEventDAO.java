package com.ispw.fixmycity.logic.dao;

import com.ispw.fixmycity.logic.bean.VolunteeringEventBean;
import com.ispw.fixmycity.logic.model.VolunteeringEvent;

public interface VolunteeringEventDAO {

	VolunteeringEvent getAllVolunteeringEvents();
	
	void addVolunteeringEvent(VolunteeringEventBean bean);
	
}
