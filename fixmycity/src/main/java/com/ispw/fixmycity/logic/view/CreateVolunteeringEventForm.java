package com.ispw.fixmycity.logic.view;

import java.util.Date;

import com.ispw.fixmycity.logic.bean.VolunteeringEventBean;
import com.ispw.fixmycity.logic.controller.VolunteeringEventController;
import com.ispw.fixmycity.logic.dao.CommunityReportDAO;
import com.ispw.fixmycity.logic.model.CommunityReport;
import com.ispw.fixmycity.logic.util.DateUtil;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

public class CreateVolunteeringEventForm {

	@FXML
	private DatePicker eventDatePicker;

	@FXML
	public void handleCreateVolunteeringEvent() {

		// TODO poi il report dovra essere selezionato
		
		CommunityReportDAO daoCompRep = new CommunityReportDAO();
		CommunityReport compReport = daoCompRep.findByPrimaryKey(1);
		
		VolunteeringEventBean volunteeringEventBean = new VolunteeringEventBean();

		volunteeringEventBean.setCommunityReport(compReport);
		volunteeringEventBean.setCreationDate(new Date());
		volunteeringEventBean.setEventDate(DateUtil.dateFromDatePicker(eventDatePicker));

		VolunteeringEventController controller = new VolunteeringEventController(volunteeringEventBean);

		// forse la bean è meglio passarla qui
		controller.createVolunteeringEvent();

	}

}
