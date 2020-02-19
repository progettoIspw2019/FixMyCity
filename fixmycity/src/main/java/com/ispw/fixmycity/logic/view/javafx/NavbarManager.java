package com.ispw.fixmycity.logic.view.javafx;

import java.io.ByteArrayInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ispw.fixmycity.logic.view.SessionView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class NavbarManager {
	
	private NavbarManager() {}
	
	public static void setNavbarData(Text usernameText, ImageView profileImg) {
		usernameText.setText(SessionView.getUsername());
		byte[] profilePicByte = SessionView.getImageProfile();
		Image img = new Image(LoginControllerFX.class.getResourceAsStream("placeholder-profile.jpg"));
		profileImg.setImage(img);

		if (profilePicByte != null) {
			Logger.getLogger("fixmycity").log(Level.INFO, "loaded {}", profilePicByte);
			profileImg.setImage(new Image(new ByteArrayInputStream(profilePicByte)));
		}
		double halfWidth = profileImg.getFitWidth() / 2;
		double halfHeight = profileImg.getFitHeight() / 2;
		Circle clip = new Circle(halfWidth - 4, halfHeight - 4, 15);
		profileImg.setClip(clip);
	}
}
