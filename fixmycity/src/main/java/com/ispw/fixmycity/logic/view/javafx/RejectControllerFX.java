package com.ispw.fixmycity.logic.view.javafx;

import com.ispw.fixmycity.logic.bean.JobBeanView;

import javafx.fxml.FXML;
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
			Alerter.alert("Missing description!", "Citizens might want to know the reason behind the rejection.", AlertType.INFORMATION);
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
