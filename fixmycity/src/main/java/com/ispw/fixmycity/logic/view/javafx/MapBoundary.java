package com.ispw.fixmycity.logic.view.javafx;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ispw.fixmycity.logic.app.App;
import com.ispw.fixmycity.logic.util.ReportFilter;
import com.ispw.fixmycity.logic.view.MapController;
import com.ispw.fixmycity.logic.view.SessionView;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;

public class MapBoundary {
	@FXML
	private WebView mapContainerWebView;

	@FXML
	private AnchorPane mapNavbarPane;

	@FXML
	private Text usernameText;

	@FXML
	private ImageView profileImg;

	@FXML
	public void initialize() {
		usernameText.setText(SessionView.getUsername());
		byte[] profilePicByte = SessionView.getImageProfile();
		Image img = new Image(getClass().getResourceAsStream("placeholder-profile.jpg"));
		profileImg.setImage(img);

		if (profilePicByte != null) {
			Logger.getLogger("fixmycity").log(Level.INFO, "loaded " + profilePicByte);
			profileImg.setImage(new Image(new ByteArrayInputStream(profilePicByte)));
		}
		double halfWidth = profileImg.getFitWidth() / 2;
		double halfHeight = profileImg.getFitHeight() / 2;
		Circle clip = new Circle(halfWidth - 4, halfHeight - 4, 15);
		profileImg.setClip(clip);

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
		if (SessionView.getLatitudeSetOnMap() != null && SessionView.getLongitudeSetOnMap() != null)
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
