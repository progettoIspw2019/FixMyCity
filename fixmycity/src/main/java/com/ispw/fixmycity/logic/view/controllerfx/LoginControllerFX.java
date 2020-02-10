package com.ispw.fixmycity.logic.view.controllerfx;

import java.io.IOException;

import com.ispw.fixmycity.logic.app.App;
import com.ispw.fixmycity.logic.bean.UserSessionBean;
import com.ispw.fixmycity.logic.util.UserMode;
import com.ispw.fixmycity.logic.view.LoginForm;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginControllerFX {
	@FXML
	private TextField usernameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Button submitButton;

	@FXML
	private void submitLogin() throws IOException {

		LoginForm loginForm = new LoginForm();

		loginForm.setUsername(usernameField.getText());
		loginForm.setPassword(passwordField.getText());

		loginForm.submitLogin();

		UserSessionBean session = UserSessionBean.getInstance();

		if (session.getActiveCitizenUser() != null && session.getUserMode() == UserMode.CITIZEN)
			App.setRoot("home_citizen");
		else if (session.getActiveCompanyUser() != null && session.getUserMode() == UserMode.COMPANY) {
			App.setRoot("home_company");
		}

	}

	@FXML
	private void showSignupForm() throws IOException {
		App.setRoot("signup");
	}

}
