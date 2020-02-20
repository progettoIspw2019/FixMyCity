package com.ispw.fixmycity.logic.view.javafx;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class Alerter {
	private Alerter() {}
	public static void alert(String head, String body, AlertType al) {
		Alert alert = new Alert(al, body, ButtonType.OK);
		alert.setHeaderText(head);
		alert.showAndWait();
	}
}
