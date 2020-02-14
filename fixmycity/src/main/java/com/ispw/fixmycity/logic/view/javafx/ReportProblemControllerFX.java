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
import com.ispw.fixmycity.logic.exceptions.NoMatchingCompanyFound;
import com.ispw.fixmycity.logic.bean.AddressBean;
import com.ispw.fixmycity.logic.bean.ReportBeanView;
import com.ispw.fixmycity.logic.controller.SystemFacade;
import com.ispw.fixmycity.logic.model.CityFactory;
import com.ispw.fixmycity.logic.view.SessionView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

public class ReportProblemControllerFX {

	ObservableList<String> categoryList = FXCollections.observableArrayList(new CityFactory().getCity(SessionView.getCityEnum()).getAllCategories());

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
		imageFileChooser.getExtensionFilters()
				.add((new ExtensionFilter("JPG files (*.jpg, *.jpeg)", "*.JPG", "*.jpg", "*.JPEG", "*.jpeg")));
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
		if (titleField.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION, "A title summarises the description"
					+ " of the issue and it allows better filtering for users on the platform.", ButtonType.OK);
			alert.setHeaderText("Missing title!");
			alert.showAndWait();
			return;
		}

		if (descriptionField.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION, "A description is needed so that"
					+ " there is enough information to understand what the problem is.", ButtonType.OK);
			alert.setHeaderText("Missing description!");
			alert.showAndWait();
			return;
		}

		if (selectedImage == null) {
			Alert alert = new Alert(AlertType.INFORMATION,
					"A picture greatly improves" + " efficiency and allows us to detect the severity of the issue.",
					ButtonType.OK);
			alert.setHeaderText("No image uploaded!");
			alert.showAndWait();
			return;
		}

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

		try {
			new SystemFacade().reportProblem(repBean);
		} catch (NoMatchingCompanyFound e) {
			Alert alert = new Alert(AlertType.INFORMATION,
					"The issue might not get addressed until the public company that is responsible signs up, "
							+ "we'd be grateful if you could try to get them to contact us so that everyone can benefit from our platform!",
					ButtonType.OK);
			alert.setHeaderText("No matching company found!");
			alert.showAndWait();
			return;
		}

		App.setRoot("home_citizen");
	}

}
