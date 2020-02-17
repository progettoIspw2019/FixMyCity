package com.ispw.fixmycity.logic.view.javafx;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ispw.fixmycity.logic.app.App;
import com.ispw.fixmycity.logic.exceptions.InvalidFieldException;
import com.ispw.fixmycity.logic.bean.CommunityReportBean;
import com.ispw.fixmycity.logic.bean.VolunteeringEventBean;
import com.ispw.fixmycity.logic.controller.SystemFacade;
import com.ispw.fixmycity.logic.util.ConverterUtil;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class CreateVolunteeringEventForm {

	@FXML
	private DatePicker eventDatePicker;

	@FXML
	private TextField eventTitleTextField;

	@FXML
	private TextArea eventDescriptionTextArea;

	@FXML
	private GridPane firstGrid;

	@FXML
	private GridPane secondGrid;

	@FXML
	private Button nextButton;

	@FXML
	private Button homeButton;

	@FXML
	private Button backButton;

	@FXML
	private Button submitButton;

	@FXML
	private ListView<String> commrepListView;

	@FXML
	private ImageView reportImageView;

	@FXML
	private TextField eventTimeTextField;

	@FXML
	private Text errorText;

	private Integer selectionId = -1;

	private CommunityReportBean selectedReport;

	@FXML
	public void initialize() {

		firstGrid.setVisible(true);
		secondGrid.setVisible(false);
		errorText.setVisible(false);

		ObservableMap<Integer, String> observableList = FXCollections
				.observableMap(new SystemFacade().getMappedCommunityReports());

		List<Integer> keys = new ArrayList<>(observableList.keySet());
		commrepListView.getItems().setAll(observableList.values());

		commrepListView.getSelectionModel().selectedItemProperty()
				.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
					selectionId = keys.get(commrepListView.getSelectionModel().getSelectedIndex());
					Logger.getLogger("fixmycity").log(Level.INFO, "SELECTION ID " + selectionId);
				});

	}

	@FXML
	public void handleNextButton() {

		if (selectionId == -1) {
			Alert alert = new Alert(AlertType.INFORMATION, "Please select a report", ButtonType.OK);
			alert.setHeaderText("No report selected");
			alert.showAndWait();
			return;
		}

		firstGrid.setVisible(false);
		secondGrid.setVisible(true);
		selectedReport = new SystemFacade().getCommunityReportFromId(selectionId);

		byte[] bytes = selectedReport.getImage();
		Image image = new Image(new ByteArrayInputStream(bytes));

		reportImageView.setImage(image);

	}

	@FXML
	public void handleHomeButton() {
		App.setRoot("home_citizen");
	}

	@FXML
	public void handleBackButton() {
		firstGrid.setVisible(true);
		secondGrid.setVisible(false);
	}

	@FXML
	public void handleSubmitButton() {

		errorText.setVisible(false);

		if (eventTitleTextField.getText() == null || eventDescriptionTextArea.getText() == null
				|| eventDatePicker.getValue() == null || eventTimeTextField.getText() == null) {
			Alert alert = new Alert(AlertType.INFORMATION, "Please make sure that every field is filled",
					ButtonType.OK);
			alert.setHeaderText("Invalid field");
			alert.showAndWait();
			return;
		}

		VolunteeringEventBean volunteeringEventBean = new VolunteeringEventBean();
		volunteeringEventBean.setCommunityReport(selectedReport);
		volunteeringEventBean.setTitle(eventTitleTextField.getText());
		volunteeringEventBean.setFullDescription(eventDescriptionTextArea.getText());
		volunteeringEventBean.setCreationDate(new Date());
		volunteeringEventBean.setEventDate(ConverterUtil.dateFromDatePicker(eventDatePicker));
		try {
			volunteeringEventBean.setEventTime(eventTimeTextField.getText());
		} catch (InvalidFieldException e) {
			Alert alert = new Alert(AlertType.INFORMATION, e.getMessage(), ButtonType.OK);
			alert.setHeaderText("Invalid field");
			alert.showAndWait();
			return;
		}

		new SystemFacade().createVolunteeringEvent(volunteeringEventBean);

	}

}
