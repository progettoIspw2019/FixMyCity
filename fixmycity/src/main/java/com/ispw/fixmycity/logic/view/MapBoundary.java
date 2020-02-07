package com.ispw.fixmycity.logic.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ispw.fixmycity.logic.app.App;
import com.ispw.fixmycity.logic.controller.MapController;
import com.ispw.fixmycity.logic.util.ReportFilter;

import javafx.fxml.FXML;
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
	private void createReport() throws IOException {
		App.setRoot("reportProblemForm");
	}

	@FXML
	private void createEvent() throws IOException {
		App.setRoot("create_event");
	}
}
