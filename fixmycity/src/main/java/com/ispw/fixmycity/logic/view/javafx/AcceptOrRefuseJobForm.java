package com.ispw.fixmycity.logic.view.javafx;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ispw.fixmycity.logic.app.App;
import com.ispw.fixmycity.logic.bean.JobBeanView;
import com.ispw.fixmycity.logic.bean.CompanyReportBeanView;
import com.ispw.fixmycity.logic.controller.LoginController;
import com.ispw.fixmycity.logic.controller.SystemFacade;
import com.ispw.fixmycity.logic.exceptions.CompanyReportIsAcceptedException;
import com.ispw.fixmycity.logic.exceptions.InvalidDateIntervalException;
import com.ispw.fixmycity.logic.view.SessionView;
import com.jfoenix.controls.JFXTextArea;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AcceptOrRefuseJobForm {

	@FXML
	private ImageView reportImageView;

	@FXML
	private Text reportTitleText;

	@FXML
	private TreeTableView<CompanyReportBeanView> reportTable;

	@FXML
	private TreeTableColumn<CompanyReportBeanView, String> statusColumn;

	@FXML
	private TreeTableColumn<CompanyReportBeanView, String> titleColumn;

	@FXML
	private JFXTextArea description;
	@FXML
	private JFXTextArea city;
	@FXML
	private JFXTextArea submissionDate;
	
	@FXML
	private Text usernameText;
	
	@FXML
	private ImageView profileImg;

	@FXML
	private TextArea status;

	private CompanyReportBeanView reportSelected;

	@FXML
	public void initialize() {
		usernameText.setText(SessionView.getUsername());
		byte[] profilePicByte = SessionView.getImageProfile();
		Image img = new Image(getClass().getResourceAsStream("placeholder-profile.jpg"));
		profileImg.setImage(img);

		if (profilePicByte != null) {
			Logger.getLogger("fixmycity").log(Level.INFO, "loaded {}", profilePicByte);
			profileImg.setImage(new Image(new ByteArrayInputStream(profilePicByte)));
		}
		double halfWidth = profileImg.getFitWidth() / 2;
		double halfHeight = profileImg.getFitHeight() / 2;
		Circle clip = new Circle(halfWidth - 4, halfHeight - 4, 15);
		profileImg.setClip(clip);
		
		Logger logger = Logger.getLogger("fixmycity");
		
		List<CompanyReportBeanView> reports = new SystemFacade().loadMyCompanyReports();
		
		if (reports.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION, "No problems were reported for this company.");
			alert.setHeaderText("No reports to show!");
			alert.showAndWait();
			return;
		}
		
		final TreeItem<CompanyReportBeanView> root = new TreeItem<>();
		root.setValue(new CompanyReportBeanView());
		reportTable.setRoot(root);
		reportTable.setShowRoot(false);
		root.setExpanded(true);
		reports.stream().forEach(report -> root.getChildren().add(new TreeItem<>(report)));
		
		reportTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if(newSelection != null) {
				reportSelected = newSelection.getValue();
				logger.info(() -> "Loading...\n" + reportSelected.getTitle() +"\n");
				
				reportTitleText.setText(reportSelected.getTitle());
				Image image = new Image(new ByteArrayInputStream(reportSelected.getImage()));
				reportImageView.setImage(image);
				description.setText(reportSelected.getDescription());
				city.setText(reportSelected.getCity());
				submissionDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(reportSelected.getDateSubmission()));
			}
		});


		statusColumn.setCellValueFactory(
				(TreeTableColumn.CellDataFeatures<CompanyReportBeanView, String> param) -> new SimpleStringProperty(
						param.getValue().getValue().getStatus()));

		titleColumn.setCellValueFactory(
				(TreeTableColumn.CellDataFeatures<CompanyReportBeanView, String> param) -> new SimpleStringProperty(
						param.getValue().getValue().getTitle()));

	}

	@FXML
	public void acceptJob() throws IOException {
		if(reportSelected == null) {
			Alert alert = new Alert(AlertType.INFORMATION, "First pick a report!", ButtonType.OK);
			alert.setHeaderText("No report selected!");
			alert.showAndWait();
			return;
		}
		JobBeanView bean = new JobBeanView();
		bean.setRelatedReport(reportSelected.getId());
		bean.setRelatedCompany(SessionView.getUsername());
		FXMLLoader loader = new FXMLLoader(App.class.getResource("accept.fxml"));
		Parent root = loader.load();
        AcceptControllerFX controller = loader.getController();
        controller.setMasterControllerAndJob(this, bean);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(reportTable.getScene().getWindow());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	
	public void submitJob(JobBeanView jBean) {
		try {
			new SystemFacade().jobCreation(jBean);
			
		} catch (CompanyReportIsAcceptedException e) {
			Alert alert = new Alert(AlertType.WARNING, "This report was already accepted.", ButtonType.OK);
			alert.setHeaderText("Cannot accept!");
			alert.showAndWait();
			return;
		}
		
		catch(InvalidDateIntervalException e) {
			Alert alert = new Alert(AlertType.WARNING, "Starting and ending date are invalid.", ButtonType.OK);
			alert.setHeaderText("Cannot submit");
			alert.showAndWait();
			
		}
		App.setRoot("home_company");
	}

	@FXML
	public void rejectJob() throws IOException {
		if(reportSelected == null) {
			Alert alert = new Alert(AlertType.INFORMATION, "First pick a report!", ButtonType.OK);
			alert.setHeaderText("No report selected!");
			alert.showAndWait();
			return;
		}
		JobBeanView bean = new JobBeanView();
		bean.setRelatedReport(reportSelected.getId());
		bean.setRelatedCompany(SessionView.getUsername());
		FXMLLoader loader = new FXMLLoader(App.class.getResource("reject.fxml"));
		Parent root = loader.load();
        RejectControllerFX controller = loader.getController();
        controller.setMasterControllerAndJob(this, bean);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(reportTable.getScene().getWindow());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	
	@FXML
	private void logout() {
		new LoginController().logout();
		App.setRoot("login");
	}

	public void submitReject(JobBeanView currJob) {
		try {
			new SystemFacade().rejectReport(currJob);
		} catch (CompanyReportIsAcceptedException e) {
			Alert alert = new Alert(AlertType.WARNING, "This report was accepted.", ButtonType.OK);
			alert.setHeaderText("Cannot reject!");
			alert.showAndWait();
			return;
		}
		App.setRoot("home_company");
	}
}
