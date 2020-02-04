package com.ispw.fixmycity.logic.view;

import java.io.IOException;

import com.ispw.fixmycity.logic.app.App;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ReportProblemControllerFX {
	
	ObservableList<String> categoryList = FXCollections.observableArrayList("Garbage", "Leak", "Pothole");
	
	@FXML
	private TextField titleField;
	
	@FXML
	private TextArea descriptionField;
	
	@FXML
	private ChoiceBox<String> category;
	
	@FXML
	private void initialize() {
		category.setItems(categoryList);
		category.setValue("Leak");
	}
	
	@FXML
	private void cancel() throws IOException {
		App.setRoot("home");
	}
	
}
