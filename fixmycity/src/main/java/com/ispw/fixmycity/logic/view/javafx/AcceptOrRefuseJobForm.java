package com.ispw.fixmycity.logic.view.javafx;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Observer;
import java.util.logging.Logger;

import com.ispw.fixmycity.logic.app.App;
import com.ispw.fixmycity.logic.bean.AcceptOrRefuseJobBean;
import com.ispw.fixmycity.logic.controller.AcceptOrRefuseAJobController;
import com.ispw.fixmycity.logic.controller.LoginController;
import com.ispw.fixmycity.logic.dao.CompanyReportDAO;
import com.ispw.fixmycity.logic.model.CompanyReport;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AcceptOrRefuseJobForm {

	Stage stage;

	@FXML
	private ImageView reportImageView;
	@FXML
	public static Button backButton;
	@FXML

	public static Button submitAcceptButton;
	@FXML
	public static Button submitRefuseButton;

	@FXML
	private Text reportTitleText;

	@FXML
	private DatePicker startDatePicker;

	@FXML
	private DatePicker endDatePicker;

	@FXML
	private TextArea refuseDescription;

	@FXML
	private TreeTableView<CompanyReport> reportTable;

	@FXML
	private TreeTableColumn<CompanyReport, String> statusColumn;

	@FXML
	private TreeTableColumn<CompanyReport, String> titleColumn;

	@FXML
	private TextArea description;
	@FXML
	private TextArea city;

	@FXML
	private TextArea submissionDate;

	@FXML
	private TextArea status;

	private CompanyReport reportSelected;

	private Logger logger;

	AcceptOrRefuseJobBean bean = new AcceptOrRefuseJobBean();
	AcceptOrRefuseAJobController controller = new AcceptOrRefuseAJobController();
	CompanyReportDAO dao = new CompanyReportDAO();
	// List<CompanyReport> reports = dao.findAll();
	// public List<Integer> observableReportId = new ArrayList<>();

	@FXML
	public void initialize() {

		logger = Logger.getLogger("fixmycity");

		List<CompanyReport> reports = dao.findAll();
		if (reports.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION, "No problems were reported for this company.");
			alert.setHeaderText("No reports to show!");
			alert.showAndWait();
			return;
		}
		final TreeItem<CompanyReport> root = new TreeItem<>();
		root.setValue(new CompanyReport());
		reportTable.setRoot(root);
		reportTable.setShowRoot(false);
		root.setExpanded(true);
		reports.stream().forEach(report -> root.getChildren().add(new TreeItem<>(report)));

		reportTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				reportSelected = newSelection.getValue();
				logger.info(() -> "Loading...\n" + reportSelected.getTitle() + "\n");

				reportTitleText.setText(reportSelected.getTitle());
				Image image = new Image(new ByteArrayInputStream(reportSelected.getImage()));
				reportImageView.setImage(image);
				description.setText(reportSelected.getFullDescription());
				city.setText(reportSelected.getCity());
				submissionDate.setText(new SimpleDateFormat("dd-MM-yyyy").format(reportSelected.getDateSubmission()));
			}
		});

		statusColumn.setCellValueFactory(
				(TreeTableColumn.CellDataFeatures<CompanyReport, String> param) -> new SimpleStringProperty(
						param.getValue().getValue().getStatus()));

		titleColumn.setCellValueFactory(
				(TreeTableColumn.CellDataFeatures<CompanyReport, String> param) -> new SimpleStringProperty(
						param.getValue().getValue().getTitle()));

	}

	@FXML
	public void acceptJob() throws IOException {
		CompanyReport report = this.reportSelected;
		bean.setIdJob(report.getIdReport());
		bean.setRelatedReport(report);
		bean.setRelatedCompany(report.getCompanyUser());
		Stage primaryStage = null;
		Parent root = FXMLLoader.load(getClass().getResource("accept.fxml"));
		Scene scene = new Scene(root, 400, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	@FXML
	public void uploadDocButton() {
		bean.setDocument(controller.uploadDocument());
	}

	@FXML
	public void refuseJob() throws IOException {
		CompanyReport report = this.reportSelected;
		bean.setIdJob(report.getIdReport());
		bean.setRelatedReport(report);
		bean.setRelatedCompany(report.getCompanyUser());
		Stage primaryStage = null;
		Parent root = FXMLLoader.load(getClass().getResource("refuse.fxml"));
		Scene scene = new Scene(root, 400, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@FXML
	public void submitRefuse() {
		bean.setRefuseMotivation(refuseDescription);

		controller.refuseReport(bean);

	}

	@FXML
	public void submitAcceptJob() {

		bean.setStartDate(startDatePicker);
		bean.setEndDate(endDatePicker);

		controller.jobCreation(bean);

	}

	@FXML
	public void backButton(ActionEvent event) {

		Stage stage = (Stage) backButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void logout() {
		new LoginController().logout();
		App.setRoot("login");
	}
}
