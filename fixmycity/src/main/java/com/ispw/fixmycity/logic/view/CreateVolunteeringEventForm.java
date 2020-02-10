package com.ispw.fixmycity.logic.view;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ispw.fixmycity.logic.app.App;
import com.ispw.fixmycity.logic.bean.CommunityReportBean;
import com.ispw.fixmycity.logic.bean.VolunteeringEventBean;
import com.ispw.fixmycity.logic.controller.SystemFacade;
import com.ispw.fixmycity.logic.util.ConverterUtil;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

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

	private ObservableMap<Integer, String> observableList;

	private Integer selectionId;

	private CommunityReportBean selectedReport;

	@FXML
	public void initialize() {

		secondGrid.setVisible(true);
		secondGrid.setVisible(false);

		observableList = FXCollections.observableMap(new SystemFacade().getMappedCommunityReports());

		List<Integer> keys = new ArrayList<>(observableList.keySet());
		commrepListView.getItems().setAll(observableList.values());

		commrepListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
				selectionId = keys.get(commrepListView.getSelectionModel().getSelectedIndex());
				Logger.getLogger("fixmycity").log(Level.INFO, "SELECTION ID " + selectionId);
		});

	}

	@FXML
	public void handleNextButton() {
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
		 VolunteeringEventBean volunteeringEventBean = new VolunteeringEventBean();
		
		volunteeringEventBean.setCommunityReport(selectedReport);
		volunteeringEventBean.setTitle(eventTitleTextField.getText());
		volunteeringEventBean.setFullDescription(eventDescriptionTextArea.getText());
		volunteeringEventBean.setCreationDate(new Date());
		volunteeringEventBean.setEventDate(ConverterUtil.dateFromDatePicker(eventDatePicker));

		new SystemFacade().createVolunteeringEvent(volunteeringEventBean);

	}

	public DatePicker getEventDatePicker() {
		return eventDatePicker;
	}

	public void setEventDatePicker(DatePicker eventDatePicker) {
		this.eventDatePicker = eventDatePicker;
	}

	public GridPane getFirstGrid() {
		return firstGrid;
	}

	public void setFirstGrid(GridPane firstGrid) {
		this.firstGrid = firstGrid;
	}

	public GridPane getSecondGrid() {
		return secondGrid;
	}

	public void setSecondGrid(GridPane secondGrid) {
		this.secondGrid = secondGrid;
	}

	public Button getNextButton() {
		return nextButton;
	}

	public void setNextButton(Button nextButton) {
		this.nextButton = nextButton;
	}

	public Button getHomeButton() {
		return homeButton;
	}

	public void setHomeButton(Button homeButton) {
		this.homeButton = homeButton;
	}

	public Button getBackButton() {
		return backButton;
	}

	public void setBackButton(Button backButton) {
		this.backButton = backButton;
	}

	public Button getSubmitButton() {
		return submitButton;
	}

	public void setSubmitButton(Button submitButton) {
		this.submitButton = submitButton;
	}

	public ListView<String> getCommrepListView() {
		return commrepListView;
	}

	public void setCommrepListView(ListView<String> commrepListView) {
		this.commrepListView = commrepListView;
	}

	public ImageView getReportImageView() {
		return reportImageView;
	}

	public void setReportImageView(ImageView reportImageView) {
		this.reportImageView = reportImageView;
	}

	public ObservableMap<Integer, String> getObservableList() {
		return observableList;
	}

	public void setObservableList(ObservableMap<Integer, String> observableList) {
		this.observableList = observableList;
	}

}
