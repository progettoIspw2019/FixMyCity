package com.ispw.fixmycity.logic.view.javafx;

import java.util.ArrayList;
import java.util.List;

import com.ispw.fixmycity.logic.app.App;
import com.ispw.fixmycity.logic.controller.SystemFacade;
import com.ispw.fixmycity.logic.util.ReportFilter;
import com.ispw.fixmycity.logic.view.SessionView;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;

public class MapBoundary {
	@FXML
	private WebView mapContainerWebViewHome;

	@FXML
	private AnchorPane mapNavbarPaneHome;

	@FXML
	private Text usernameTextHome;

	@FXML
	private ImageView profileImgHome;

	@FXML
	public void initialize() {
		NavbarManager.setNavbarData(usernameTextHome, profileImgHome);

		List<ReportFilter> reportFilters = new ArrayList<>();
		reportFilters.add(ReportFilter.ALL_COMMUNITY_REPORT);
		reportFilters.add(ReportFilter.ALL_COMPANY_REPORT);
		
		MapController mapController = new MapController(mapContainerWebViewHome);
		mapController.loadMap(reportFilters);
	}

	public WebView getMapContainerWebView() {
		return mapContainerWebViewHome;
	}

	public void setMapContainerWebView(WebView mapContainerWebView) {
		this.mapContainerWebViewHome = mapContainerWebView;
	}

	@FXML
	private void createReport() {
		if (SessionView.getAddressSetOnMap() != null)
			App.setRoot("reportProblemForm");
		else {
			Alert alert = new Alert(AlertType.INFORMATION, "First pick a position on the map!", ButtonType.OK);
			alert.showAndWait();
		}
	}

	@FXML
	private void createEvent() {
		App.setRoot("create_event");
	}
	
	@FXML
	private void toMyReports() {
		App.setRoot("my_reports");
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
}
