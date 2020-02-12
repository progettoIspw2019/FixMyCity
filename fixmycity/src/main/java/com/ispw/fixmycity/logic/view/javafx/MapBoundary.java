package com.ispw.fixmycity.logic.view.javafx;

import java.util.ArrayList;
import java.util.List;

import com.ispw.fixmycity.logic.app.App;
import com.ispw.fixmycity.logic.util.ReportFilter;
import com.ispw.fixmycity.logic.view.MapController;
import com.ispw.fixmycity.logic.view.SessionView;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.web.WebView;

public class MapBoundary {
	@FXML
	private WebView mapContainerWebView;

	@FXML
	public void initialize() {

		List<ReportFilter> reportFilters = new ArrayList<>();
		reportFilters.add(ReportFilter.ALL_COMMUNITY_REPORT);
		reportFilters.add(ReportFilter.ALL_COMPANY_REPORT);

		MapController mapController = new MapController(mapContainerWebView);
		mapController.loadMap(reportFilters);
	}

	public WebView getMapContainerWebView() {
		return mapContainerWebView;
	}

	public void setMapContainerWebView(WebView mapContainerWebView) {
		this.mapContainerWebView = mapContainerWebView;
	}

	@FXML
	private void createReport() {
		if(SessionView.getLatitudeSetOnMap() != null && SessionView.getLongitudeSetOnMap() != null)
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
}
