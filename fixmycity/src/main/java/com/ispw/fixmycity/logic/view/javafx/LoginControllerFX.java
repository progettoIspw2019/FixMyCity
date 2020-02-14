package com.ispw.fixmycity.logic.view.javafx;

import com.ispw.fixmycity.logic.app.App;
import com.ispw.fixmycity.logic.bean.BaseUserBean;
import com.ispw.fixmycity.logic.controller.SystemFacade;
import com.ispw.fixmycity.logic.util.UserMode;
import com.ispw.fixmycity.logic.view.SessionView;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class LoginControllerFX {
	@FXML
	private TextField usernameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Button submitButton;

	@FXML
	private void submitLogin() {
		loginUser(usernameField.getText(), passwordField.getText());
	}
	
	public void loginUser(String username, String password) {
		BaseUserBean userBean = new BaseUserBean();
		userBean.setUsername(username);
		userBean.setPassword(password);
		
		BaseUserBean response = new SystemFacade().isSignedUp(userBean);
		
		SessionView.setUsername(username);
		SessionView.setImageProfile(response.getImage());

		if (response.getMode() == UserMode.CITIZEN) {
			SessionView.setMode(UserMode.CITIZEN);
			SessionView.setCityEnum(response.getCity());
			App.setRoot("home_citizen");
		} else if (response.getMode() == UserMode.COMPANY) {
			SessionView.setMode(UserMode.COMPANY);
			SessionView.setCityEnum(response.getCity());
			App.setRoot("home_company");
		}
		else {
			Alert alert = new Alert(AlertType.ERROR, "Log in failed!", ButtonType.OK);
			alert.showAndWait();
		}
	}

	@FXML
	private void showSignupForm() {
		App.setRoot("signup");
	}

}
