package com.ispw.fixmycity.logic.view.javafx;

import java.util.ArrayList;
import java.util.List;

import com.ispw.fixmycity.logic.app.App;
import com.ispw.fixmycity.logic.controller.SystemFacade;
import com.ispw.fixmycity.logic.util.ReportFilter;

import javafx.fxml.FXML;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;

public class MapCompany {
	@FXML
	private WebView mapContainer;

	@FXML
	private AnchorPane mapNavbarPane;

	@FXML
	private Text usernameText;

	@FXML
	private ImageView profileImg;

	@FXML
	public void initialize() {
		NavbarManager.setNavbarData(usernameText, profileImg);

		List<ReportFilter> reportFilters = new ArrayList<>();
		reportFilters.add(ReportFilter.ALL_MY_COMPANY_REPORT);
		MapController mapController = new MapController(mapContainer);
		mapController.loadMap(reportFilters);
	}
	
	@FXML
	private void toCompanyReports() {
		App.setRoot("home_company");
	}
	
	@FXML
	private void logout() {
		new SystemFacade().logout();
		App.setRoot("login");
	}
}
