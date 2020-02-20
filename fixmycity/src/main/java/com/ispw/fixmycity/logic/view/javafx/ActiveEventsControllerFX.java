package com.ispw.fixmycity.logic.view.javafx;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.ispw.fixmycity.logic.app.App;
import com.ispw.fixmycity.logic.bean.VolunteeringEventBean;
import com.ispw.fixmycity.logic.bean.VolunteeringEventListElementBean;
import com.ispw.fixmycity.logic.controller.SystemFacade;
import com.ispw.fixmycity.logic.exceptions.EmptyResultListException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ActiveEventsControllerFX {
	@FXML
	private Text usernameText;
	
	@FXML
	private ImageView profileImg;
	
	@FXML
	private VBox eventsContainer;
	
	Logger logger;
	
	@FXML
	private void initialize() {
		NavbarManager.setNavbarData(usernameText, profileImg);
		logger = Logger.getLogger("fixmycity");
		List<VolunteeringEventListElementBean> events = new ArrayList<>();
		
		try {
			events = new SystemFacade().getActiveVolunteeringEvents();
		} catch (EmptyResultListException e) {
			Alerter.alert("No events!", "There are no active events at the moment.", AlertType.INFORMATION);
			logger.info("Could not load any events!");
			return;
		}
		if(events.isEmpty()) return;
		URL urlEventUIElement = App.class.getResource("single_event.fxml");
		
		this.fillWithEvents(events, urlEventUIElement);
	}
	
	private void fillWithEvents(List<VolunteeringEventListElementBean> events, URL urlEventUI) {
		events.stream().forEach(ev -> {
			AnchorPane singleEvent;
			try {
				HBox parent = FXMLLoader.load(urlEventUI);
				singleEvent = (AnchorPane) parent.getChildren().get(0);
				for (Node node : singleEvent.getChildren()) {
					switch (node.getId()) {
					case "imageView":
						ImageView imgView = (ImageView) node;
						imgView.setImage(new Image(new ByteArrayInputStream(ev.getImage())));
						break;
					case "textEventTitle":
						Text evTitle = (Text) node;
						evTitle.setText(ev.getTitle());
						break;
					case "jfxTextAreaDescription":
						JFXTextArea description = (JFXTextArea) node;
						description.setText(ev.getFullDescription());
						break;
					case "textEventAddress":
						Text evAddr = (Text) node;
						evAddr.setText(ev.getAddress());
						break;
					case "textEventDate":
						Text evDate = (Text) node;
						evDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(ev.getEventDate()));
						break;
					case "jfxButtonJoin":
						JFXButton buttJoin = (JFXButton) node;
						buttJoin.setOnAction(e -> joinEvent(ev.getEventId()) );
						if (ev.hasUserJoined())
							buttJoin.setVisible(false);
						break;

					case "jfxButtonQuit":
						JFXButton buttQuit = (JFXButton) node;
						buttQuit.setOnAction( e -> quitEvent(ev.getEventId()));
						if(!ev.hasUserJoined())
							buttQuit.setVisible(false);
						break;

					case "textNumParticipants":
						Text numPart = (Text) node;
						numPart.setText(ev.getParticipantsNumber().toString());
						break;

					default:
						continue;

					}
				}
				eventsContainer.getChildren().add(singleEvent);
			} catch (IOException e) {
				Alerter.alert("File not found!", "There are missing files, you might want to reinstall your app...", AlertType.ERROR);
			}
		});
	}
	

	@FXML
	private void toMyReports() {
		App.setRoot("my_reports");
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
	
	private void joinEvent(int eventId) {
		VolunteeringEventBean eventBean = new VolunteeringEventBean();
		eventBean.setEventId(eventId);
		new SystemFacade().joinVolunteeringEvent(eventBean);
		App.setRoot("my_events");
	}
	
	private void quitEvent(int evId) {
		VolunteeringEventBean evBean = new VolunteeringEventBean();
		evBean.setEventId(evId);
		new SystemFacade().quitVolunteeringEvent(evBean);
		App.setRoot("my_events");
	}
}
