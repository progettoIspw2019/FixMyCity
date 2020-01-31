package com.ispw.fixmycity.logic.view;

import java.io.IOException;

import com.ispw.fixmycity.logic.app.App;
import com.ispw.fixmycity.logic.bean.BaseUserBean;
import com.ispw.fixmycity.logic.controller.LoginController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class LoginForm {

	@FXML
	private TextArea usernameField;

	@FXML
	private TextArea passwordField;

	@FXML
	private Button submitButton;

	@FXML
	private void submitLogin() throws IOException {

		BaseUserBean baseUser = new BaseUserBean();
		baseUser.setUsername(usernameField.getText());
		baseUser.setPassword(passwordField.getText());

		// TODO dove deve stare questo if? Nel controller?
		if (LoginController.checkCredentials(baseUser))
			App.setRoot("home");
	}

}
