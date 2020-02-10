package com.ispw.fixmycity.logic.view;

import java.util.List;

import com.ispw.fixmycity.logic.bean.VolunteeringEventListElementBean;
import com.ispw.fixmycity.logic.controller.VolunteeringEventController;

public class ActiveEventsView {
	public List<VolunteeringEventListElementBean> getActiveVolunteeringEvents() {

		VolunteeringEventController controller = new VolunteeringEventController();
		return controller.getActiveVolunteeringEvents();

	}
}
