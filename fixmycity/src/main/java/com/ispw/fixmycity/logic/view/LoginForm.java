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
import com.ispw.fixmycity.logic.util.UserMode;

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

		BaseUserBean baseUser = new BaseUserBean();
		
		baseUser.setUsername(usernameField.getText());
		baseUser.setPassword(passwordField.getText());

		LoginController controller = LoginController.getInstance();
		Boolean credentialsValid = controller.checkCredentials(baseUser);

		if (credentialsValid && controller.getUserMode() == UserMode.CITIZEN)
			App.setRoot("home_citizen");
		else if (credentialsValid && controller.getUserMode() == UserMode.COMPANY) {
			App.setRoot("home_company");
		}
		
	}

}
