package com.ispw.fixmycity.logic.view.javafx;

import com.ispw.fixmycity.logic.app.App;
import com.ispw.fixmycity.logic.controller.LoginController;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ActiveEventsControllerFX {
	@FXML
	private Text usernameText;
	
	@FXML
	private ImageView profileImg;
	
	@FXML
	private VBox reportsContainer;
	
	@FXML
	private void initialize() {
		
	}
	
	@FXML
	private void toMyReports() {
		App.setRoot("my_reports");
	}
	
	@FXML
	private void logout() {
		new LoginController().logout();
		App.setRoot("login");
	}
	
	@FXML
	private void toMap() {
		App.setRoot("home_citizen");
	}
}
