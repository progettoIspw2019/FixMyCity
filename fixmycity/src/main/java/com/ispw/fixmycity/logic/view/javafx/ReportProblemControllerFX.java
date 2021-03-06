package com.ispw.fixmycity.logic.view.javafx;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.ispw.fixmycity.logic.app.App;
import com.ispw.fixmycity.logic.exceptions.InvalidReportException;
import com.ispw.fixmycity.logic.exceptions.NoMatchingCompanyFound;
import com.ispw.fixmycity.logic.bean.AddressBean;
import com.ispw.fixmycity.logic.bean.ReportBeanView;
import com.ispw.fixmycity.logic.controller.SystemFacade;
import com.ispw.fixmycity.logic.model.CityFactory;
import com.ispw.fixmycity.logic.util.ConverterUtil;
import com.ispw.fixmycity.logic.view.SessionView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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

	ObservableList<String> categoryList = FXCollections
			.observableArrayList(new CityFactory().getCity(SessionView.getCityEnum()).getAllCategories());

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
		category.setValue(category.getItems().get(0));
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
			Image selImage = new Image(imageFile.toURI().toString());

			imgView.setImage(selImage);
			selectedImage = ConverterUtil.byteArrayFromImage(imageFile);

		}

	}

	public void handleSubmit() {
		if (titleField.getText().isEmpty()) {
			Alerter.alert("Missing title!", "A title summarises the description"
					+ " of the issue and it allows better filtering for users on the platform.", AlertType.INFORMATION);
			return;
		}

		if (descriptionField.getText().isEmpty()) {
			Alerter.alert("Missing description", "A description is needed so that"
					+ " there is enough information to understand what the problem is.", AlertType.INFORMATION);
		}

		if (selectedImage == null) {
			Alerter.alert("No image uploaded!", "A picture greatly improves\" + \" efficiency and allows us to detect the severity of the issue.", AlertType.INFORMATION);
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
		repBean.setCity(SessionView.getCityEnum().toString());
		repBean.setSubmitter(SessionView.getUsername());
		repBean.setImage(selectedImage);
		repBean.setTitle(titleField.getText());

		try {
			new SystemFacade().reportProblem(repBean);
		} catch (NoMatchingCompanyFound e) {
			Alerter.alert("No matching company found!", "The issue might not get addressed until the public company that is responsible signs up, "
					+ "we'd be grateful if you could try to get them to contact us so that everyone can benefit from our platform!", AlertType.INFORMATION);
			return;
		} catch (InvalidReportException e) {
			Alerter.alert("Cannot submit!", "Cannot submit your report at the moment, try again at a later time or contact our support team.", AlertType.INFORMATION);
		}

		App.setRoot("home_citizen");
	}

}
