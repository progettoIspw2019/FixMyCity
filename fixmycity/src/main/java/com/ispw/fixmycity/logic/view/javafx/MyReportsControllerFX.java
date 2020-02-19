package com.ispw.fixmycity.logic.view.javafx;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.ispw.fixmycity.logic.app.App;
import com.ispw.fixmycity.logic.bean.CommunityReportBeanView;
import com.ispw.fixmycity.logic.bean.CompanyReportBeanView;
import com.ispw.fixmycity.logic.controller.SystemFacade;
import com.ispw.fixmycity.logic.exceptions.EmptyResultListException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MyReportsControllerFX {
	private Logger logger;
	@FXML
	private Text usernameText;

	@FXML
	private ImageView profileImg;

	@FXML
	private VBox reportsContainer;

	@FXML
	private void initialize() {
		NavbarManager.setNavbarData(usernameText, profileImg);
		logger = Logger.getLogger("fixmycity");
		List<CommunityReportBeanView> commReports = new ArrayList<>();
		List<CompanyReportBeanView> compReports = new ArrayList<>();
		try {
			commReports = new SystemFacade().getMyCommunityReports();
		} catch (EmptyResultListException e) {
			//
		}
		try {
			compReports = new SystemFacade().getMyCompanyReports();
		} catch (EmptyResultListException e) {
			//
		}

		URL url = App.class.getResource("single_report.fxml");
		logger.info(() -> "Loading...\n" + url);

		logger.info("Calling fillCompanyReports...");
		this.fillCompanyReports(compReports, url);
		this.fillCommunityReports(commReports, url);
	}

	private void fillCompanyReports(List<CompanyReportBeanView> compReports, URL urlSingleReportFXML) {
		if(compReports.isEmpty())
			return;
		compReports.stream().forEach(report -> {
			AnchorPane singleReport;
			try {
				HBox parent = FXMLLoader.load(urlSingleReportFXML);
				singleReport = (AnchorPane) parent.getChildren().get(0);

				for (Node node : singleReport.getChildren()) {
					switch (node.getId()) {
					case "imageView":
						ImageView imgView = (ImageView) node;
						imgView.setImage(new Image(new ByteArrayInputStream(report.getImage())));
						break;
					case "textReportTitle":
						Text repTitle = (Text) node;
						repTitle.setText(report.getTitle());
						break;
					case "jfxTextAreaDescription":
						JFXTextArea description = (JFXTextArea) node;
						description.setText(report.getDescription());
						break;
					case "textAddress":
						Text addr = (Text) node;
						addr.setText(report.getAddress());
						break;
					case "textSubmissionDate":
						Text subDate = (Text) node;
						subDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(report.getDateSubmission()));
						break;
					case "textEventJobCreated":
						Text eventCreated = (Text) node;
						if (report.getJobs() == null || report.getJobs().isEmpty())
							eventCreated.setText("Job not created");
						else
							eventCreated.setText("Job created");
						break;

					case "jfxButtonCompReport":
						JFXButton button = (JFXButton) node;
						button.setText(report.getCompanyRelated());
						break;

					case "jfxButtonCommReport":
						JFXButton butt = (JFXButton) node;
						butt.setVisible(false);
						break;

					default:
						continue;

					}
				}
				reportsContainer.getChildren().add(singleReport);
			} catch (IOException e) {
				Alert alert = new Alert(AlertType.ERROR,
						"There are some missing files, you might want to reinstall your app...", ButtonType.OK);
				alert.setHeaderText("File not found!");
				alert.showAndWait();
			}
		});
	}

	private void fillCommunityReports(List<CommunityReportBeanView> commReports, URL urlSingleReportFXML) {
		if(commReports.isEmpty())
			return;
		commReports.stream().forEach(report -> {
			logger.fine(() -> "Loading report\n\n" + report);
			AnchorPane singleReport;
			try {
				HBox parent = FXMLLoader.load(urlSingleReportFXML);
				singleReport = (AnchorPane) parent.getChildren().get(0);

				for (Node node : singleReport.getChildren()) {
					switch (node.getId()) {
					case "imageView":
						ImageView imgView = (ImageView) node;
						imgView.setImage(new Image(new ByteArrayInputStream(report.getImage())));
						break;
					case "textReportTitle":
						Text repTitle = (Text) node;
						repTitle.setText(report.getTitle());
						break;
					case "jfxTextAreaDescription":
						JFXTextArea description = (JFXTextArea) node;
						description.setText(report.getDescription());
						break;
					case "textAddress":
						Text addr = (Text) node;
						addr.setText(report.getAddress());
						break;
					case "textSubmissionDate":
						Text subDate = (Text) node;
						subDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(report.getDateSubmission()));
						break;
					case "textEventJobCreated":
						Text eventCreated = (Text) node;
						if (report.getEvents() == null || report.getEvents().isEmpty())
							eventCreated.setText("Event not created");
						else
							eventCreated.setText("Event created");
						break;

					case "jfxButtonCompReport":
						JFXButton butt = (JFXButton) node;
						butt.setVisible(false);
						break;

					default:
						continue;

					}
				}
				reportsContainer.getChildren().add(singleReport);
			} catch (IOException e) {
				Alert alert = new Alert(AlertType.ERROR,
						"There are some missing files, you might want to reinstall your app...", ButtonType.OK);
				alert.setHeaderText("File not found!");
				alert.showAndWait();
			}
		});
	}

	@FXML
	private void toActiveEvents() {
		App.setRoot("my_events");
	}

	@FXML
	private void logout() {
		new SystemFacade().logout();
		App.setRoot("login");
	}

	@FXML
	private void toMap() {
		App.setRoot("home_citizen");
	}
}
