package com.ispw.fixmycity.logic.view.javafx;

import com.ispw.fixmycity.logic.bean.JobBeanView;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;

public class RejectControllerFX {
	private AcceptOrRefuseJobForm masterController;
	private JobBeanView currJob;
	
	@FXML
	TextArea rejectDescription;
	
	@FXML
	private void cancel() {
		rejectDescription.getScene().getWindow().hide();
	}
	
	@FXML
	private void submit() {
		if(rejectDescription.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION, "We want to communicate "
					+ "to citizens the reason why no works will be done for this issue.", ButtonType.OK);
			alert.setHeaderText("Missing description!");
			alert.showAndWait();
			return;
		}
		currJob.setRejectingMotivation(rejectDescription.getText());
		masterController.submitReject(currJob);
		rejectDescription.getScene().getWindow().hide();
	}

	public void setMasterControllerAndJob(AcceptOrRefuseJobForm masterController, JobBeanView jBean) {
		this.masterController = masterController;
		this.currJob = jBean;
	}
}
