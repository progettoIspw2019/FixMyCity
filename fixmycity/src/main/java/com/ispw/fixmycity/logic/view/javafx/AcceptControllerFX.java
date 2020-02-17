package com.ispw.fixmycity.logic.view.javafx;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.util.logging.Logger;

import com.ispw.fixmycity.logic.bean.JobBeanView;
import com.jfoenix.controls.JFXDatePicker;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;

public class AcceptControllerFX {
	private File document;
	private AcceptOrRefuseJobForm masterController;
	private JobBeanView currJob;

	@FXML
	private VBox form;

	@FXML
	private JFXDatePicker startDatePicker;

	@FXML
	private JFXDatePicker endDatePicker;

	public void setMasterControllerAndJob(AcceptOrRefuseJobForm masterController, JobBeanView jBean) {
		this.masterController = masterController;
		currJob = jBean;
	}

	@FXML
	private void submitJob() {
		if(document == null) {
			Alert alert = new Alert(AlertType.INFORMATION,
					"First, we need a document that explains how works will get done.",
					ButtonType.OK);
			alert.setHeaderText("No document uploaded!");
			alert.showAndWait();
			return;
		}
		
		try {
			currJob.setStartDate(Date.valueOf(startDatePicker.getValue()));

			currJob.setEndDate(Date.valueOf(endDatePicker.getValue()));

			currJob.setJobInfo(Files.readAllBytes(document.toPath()));
			masterController.submitJob(currJob);
			form.getScene().getWindow().hide();
			
		} catch (IOException e) {
			Logger.getLogger("fixmycity").severe(e.toString());
		}
	}

	@FXML
	private void cancel() {
		form.getScene().getWindow().hide();
	}

	@FXML
	private void uploadDocument() {
		Window window = form.getScene().getWindow();
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters()
				.add((new ExtensionFilter("JPG files (*.jpg, *.jpeg)", "*.JPG", "*.jpg", "*.JPEG", "*.jpeg")));
		document = fileChooser.showOpenDialog(window);
	}
}
