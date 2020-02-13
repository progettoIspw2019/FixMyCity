package com.ispw.fixmycity.logic.view.javafx;

import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import com.ispw.fixmycity.logic.app.App;
import com.ispw.fixmycity.logic.bean.AddressBean;
import com.ispw.fixmycity.logic.bean.ReportBeanView;
import com.ispw.fixmycity.logic.controller.SystemFacade;
import com.ispw.fixmycity.logic.view.SessionView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class ReportProblemControllerFX {

	ObservableList<String> categoryList = FXCollections.observableArrayList("Garbage", "Leak", "Pothole", "Filth");

	@FXML
	private GridPane reportGridPane;

	@FXML
	private TextField titleField;

	@FXML
	private TextArea descriptionField;

	@FXML
	private ChoiceBox<String> category;

	@FXML
	private ImageView imgView;

	private byte[] selectedImage;

	@FXML
	private void initialize() {
		category.setItems(categoryList);
		category.setValue("Leak");
	}

	@FXML
	private void cancel() {
		App.setRoot("home_citizen");
	}

	public void handlePictureUpload() {
		Window window = reportGridPane.getScene().getWindow();
		FileChooser imageFileChooser = new FileChooser();
		File imageFile = imageFileChooser.showOpenDialog(window);

		if (imageFile != null) {
			try {
				Image selImage = new Image(imageFile.toURI().toString());
	
				imgView.setImage(selImage);
				DataBufferByte data = (DataBufferByte) ImageIO.read(imageFile).getRaster().getDataBuffer();
				selectedImage = data.getData();
				
			} catch (IOException e) {
				Logger.getLogger("fixmycity").log(Level.SEVERE, e.toString());
			}
			
		}

	}

	public void handleSubmit() {
		LocalDateTime localDateTime = LocalDateTime.now();

		ReportBeanView repBean = new ReportBeanView();
		
		BigDecimal latitude = SessionView.getLatitudeSetOnMap();
		BigDecimal longitude = SessionView.getLongitudeSetOnMap();
		AddressBean address = SessionView.getAddressSetOnMap();
		

		repBean.setCategory(category.getValue());
		repBean.setDateSubmission(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
		repBean.setDescription(descriptionField.getText());

		repBean.setTitle(titleField.getText());

		repBean.setLatitude(latitude);
		repBean.setLongitude(longitude);
		repBean.setAddress(address.getRoad());
		repBean.setCity(address.getCity());
		repBean.setSubmitter(SessionView.getUsername());
		repBean.setImage(selectedImage);
		repBean.setTitle(titleField.getText());

		// TODO: try catch exception that should be thrown by the controller
		new SystemFacade().reportProblem(repBean);
		
		App.setRoot("home_citizen");
	}

}
