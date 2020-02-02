package com.ispw.fixmycity.logic.view;

import java.util.Date;

import com.ispw.fixmycity.logic.bean.CommunityReportBean;
import com.ispw.fixmycity.logic.bean.VolunteeringEventBean;
import com.ispw.fixmycity.logic.controller.VolunteeringEventController;
import com.ispw.fixmycity.logic.util.DateUtil;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

public class CreateVolunteeringEventForm {

	@FXML
	private DatePicker eventDatePicker;

	@FXML
	public void handleCreateVolunteeringEvent() {

		// TODO poi il report dovra essere selezionato
		CommunityReportBean reportBean = new CommunityReportBean();
		reportBean.setIdReport(1);
		VolunteeringEventBean volunteeringEvent = new VolunteeringEventBean();

		volunteeringEvent.setCommunityReport(reportBean);
		volunteeringEvent.setCreationDate(new Date());
		volunteeringEvent.setEventDate(DateUtil.dateFromDatePicker(eventDatePicker));

		VolunteeringEventController controller = new VolunteeringEventController(volunteeringEvent);

		controller.createVolunteeringEvent();

	}

}
