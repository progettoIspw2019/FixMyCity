package com.ispw.fixmycity.logic.view;

import java.io.IOException;
import java.util.Date;

import com.ispw.fixmycity.logic.app.App;
import com.ispw.fixmycity.logic.bean.BaseUserBean;
import com.ispw.fixmycity.logic.bean.VolunteeringEventBean;
import com.ispw.fixmycity.logic.controller.LoginController;
import com.ispw.fixmycity.logic.dao.CommunityReportDAO;
import com.ispw.fixmycity.logic.dao.VolunteeringEventDAO;
import com.ispw.fixmycity.logic.model.CommunityReport;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginForm {

	@FXML
	private TextField usernameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Button submitButton;

	@FXML
	private void submitLogin() throws IOException {

		VolunteeringEventDAO daoEvent = new VolunteeringEventDAO();
		VolunteeringEventBean bean = new VolunteeringEventBean();
		
		CommunityReportDAO daoCommRep = new CommunityReportDAO();
		CommunityReport commReport = daoCommRep.findByPrimaryKey(1);
		
		bean.setCommunityReport(commReport);
		bean.setCreationDate(new Date());
		bean.setEventDate(new Date());

		daoEvent.addVolunteeringEvent(bean);

		BaseUserBean baseUser = new BaseUserBean();
		baseUser.setUsername(usernameField.getText());
		baseUser.setPassword(passwordField.getText());

		// TODO dove deve stare questo if? Nel controller?
		if (LoginController.checkCredentials(baseUser))
			App.setRoot("home");
	}

}
