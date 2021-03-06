package com.ispw.fixmycity.logic.view.javafx;

import java.io.File;

import com.ispw.fixmycity.logic.app.App;
import com.ispw.fixmycity.logic.bean.CitizenUserBean;
import com.ispw.fixmycity.logic.bean.CompanyUserBean;
import com.ispw.fixmycity.logic.controller.SystemFacade;
import com.ispw.fixmycity.logic.model.CityFactory;
import com.ispw.fixmycity.logic.util.CityEnum;
import com.ispw.fixmycity.logic.util.ConverterUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;

public class SignupForm {

	@FXML
	private Button signupCitizenButton;

	@FXML
	private Button signupCompanyButton;

	@FXML
	private RadioButton citizenRadioButton;

	@FXML
	private RadioButton companyRadioButton;

	@FXML
	private TextField firstNameField;

	@FXML
	private TextField lastNameField;

	@FXML
	private TextField userNameField;

	@FXML
	private TextField companyNameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private GridPane signupGridPane;

	@FXML
	private ImageView selectedImageView;

	@FXML
	private ChoiceBox<String> cityChoiceBox;

	@FXML
	private ChoiceBox<String> categoryChoiceBox;

	@FXML
	private Image defaultImage;

	private Image selectedImage;

	private File imageFile;

	ObservableList<String> cityList = FXCollections.observableArrayList("Roma", "Fiuggi");
	ObservableList<String> categoryList = FXCollections.observableArrayList();

	@FXML
	public void initialize() {
		selectedImage = defaultImage;
		categoryChoiceBox.setItems(categoryList);
		cityChoiceBox.setItems(cityList);
		cityChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			categoryList.clear();
			new CityFactory().getCity(CityEnum.valueOf(cityChoiceBox.getValue().toUpperCase())).getCompaniesCategories()
					.forEach(cat -> categoryList.add(cat));
			categoryChoiceBox.setValue(categoryList.get(0));
		});
		cityChoiceBox.setValue(cityList.get(0));
	}

	public void showCitizenSignupForm() {

		clearValues();
		firstNameField.setVisible(true);
		lastNameField.setVisible(true);
		companyNameField.setVisible(false);
		categoryChoiceBox.setVisible(false);
		signupCitizenButton.setVisible(true);
		signupCompanyButton.setVisible(false);

	}

	public void showCompanySignupForm() {

		clearValues();
		firstNameField.setVisible(false);
		lastNameField.setVisible(false);
		companyNameField.setVisible(true);
		signupCitizenButton.setVisible(false);
		signupCompanyButton.setVisible(true);
		categoryChoiceBox.setVisible(true);
	}

	public void handlePictureUpload() {
		Window window = signupGridPane.getScene().getWindow();
		FileChooser imageFileChooser = new FileChooser();
		imageFileChooser.getExtensionFilters()
				.add((new ExtensionFilter("JPG files (*.jpg, *.jpeg)", "*.JPG", "*.jpg", "*.JPEG", "*.jpeg")));
		imageFile = imageFileChooser.showOpenDialog(window);

		if (imageFile != null) {
			selectedImage = new Image(imageFile.toURI().toString());
			selectedImageView.setImage(selectedImage);
		}

	}

	public void signupCitizen() {
		CitizenUserBean user = new CitizenUserBean();
		user.setFirstName(firstNameField.getText());
		user.setLastName(lastNameField.getText());
		user.setUsername(userNameField.getText());
		user.setPassword(passwordField.getText());
		byte[] imByte = ConverterUtil.byteArrayFromImage(imageFile);
		if(imByte == null) return;
		user.setProfilePicture(imByte);
		user.setCity(CityEnum.valueOf(cityChoiceBox.getValue().toUpperCase()));

		if (new SystemFacade().signupCitizenUser(user)) {
			new LoginControllerFX().loginUser(user.getUsername(), user.getPassword());
		} else {
			Alerter.alert("Could not sign you up!", "This username is already in use.", AlertType.ERROR);
		}
	}

	public void signupCompany() {
		CompanyUserBean user = new CompanyUserBean();
		user.setCategory(categoryChoiceBox.getValue());
		user.setCompanyName(companyNameField.getText());
		byte[] imByte = ConverterUtil.byteArrayFromImage(imageFile);
		if(imByte == null) return;
		user.setImage(imByte);
		user.setPassword(passwordField.getText());
		user.setUsername(userNameField.getText());
		user.setCity(CityEnum.valueOf(cityChoiceBox.getValue().toUpperCase()));

		if (new SystemFacade().signupCompanyUser(user)) {
			new LoginControllerFX().loginUser(user.getUsername(), user.getPassword());
		} else {
			Alerter.alert("Could not sign you up!", "This username is already in use.", AlertType.ERROR);
		}
	}

	public void clearValues() {
		firstNameField.clear();
		lastNameField.clear();
		userNameField.clear();
		passwordField.clear();
		companyNameField.clear();
		selectedImageView.setImage(defaultImage);
		selectedImage = defaultImage;
	}
	
	@FXML
	private void toLogin() {
		App.setRoot("login");
	}

}
