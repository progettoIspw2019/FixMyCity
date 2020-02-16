package com.ispw.fixmycity.logic.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * JavaFX App
 */
public class App extends Application {

	private static Stage stage;

	@Override
	public void start(Stage initialStage) throws IOException {
		App.setStage(initialStage);
		stage.setScene(new Scene(loadFXML("login"), 800, 480));
		stage.setMinHeight(480);
		stage.setMinWidth(800);
		stage.show();
	}

	private static void setStage(Stage initialStage) {
		App.stage = initialStage;
	}

	public static void setRoot(String fxml) {
		Parent newRoot = loadFXML(fxml);
		if(newRoot == null) {
			System.exit(-1);
		}
		stage.getScene().setRoot(newRoot);
	}

	private static Parent loadFXML(String fxml) {
		
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		try {
			return fxmlLoader.load();
		}
		catch(IOException e) {
			e.printStackTrace();
			Logger.getLogger("fixmycity").log(Level.SEVERE, e.toString());
			return null;
		}
	}

	public static void main(String[] args) {
		launch();
	}

}