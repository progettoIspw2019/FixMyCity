package com.ispw.fixmycity.logic.view.javafx;

import com.ispw.fixmycity.logic.app.App;
import com.ispw.fixmycity.logic.bean.BaseUserBean;
import com.ispw.fixmycity.logic.controller.SystemFacade;
import com.ispw.fixmycity.logic.util.UserMode;
import com.ispw.fixmycity.logic.view.SessionView;

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
	private void submitLogin() {

		BaseUserBean userBean = new BaseUserBean();
		userBean.setUsername(usernameField.getText());
		userBean.setPassword(passwordField.getText());
		SessionView.setUsername(usernameField.getText());
		
		BaseUserBean response = new SystemFacade().isSignedUp(userBean);
		
		SessionView.setImageProfile(response.getImage());

		if (response.getMode() == UserMode.CITIZEN) {
			SessionView.setMode(UserMode.CITIZEN);
			App.setRoot("home_citizen");
		}
		else if (response.getMode() == UserMode.COMPANY) {
			SessionView.setMode(UserMode.COMPANY);
			App.setRoot("home_company");
		}

	}

	@FXML
	private void showSignupForm() {
		App.setRoot("signup");
	}

}
