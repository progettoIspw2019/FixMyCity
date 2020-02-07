package com.ispw.fixmycity.logic.view;

import java.io.File;

import com.ispw.fixmycity.logic.bean.CitizenUserBean;
import com.ispw.fixmycity.logic.bean.CompanyUserBean;
import com.ispw.fixmycity.logic.controller.LoginController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;

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
	private TextField cityField;

	@FXML
	private TextField categoryField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private GridPane signupGridPane;

	@FXML
	private ImageView selectedImageView;

	@FXML
	private Image defaultImage;

	private Image selectedImage;

	private File imageFile;

	@FXML
	public void initialize() {
		selectedImage = defaultImage;
	}

	public void showCitizenSignupForm() {

		clearValues();
		firstNameField.setVisible(true);
		lastNameField.setVisible(true);
		companyNameField.setVisible(false);
		cityField.setVisible(false);
		categoryField.setVisible(false);
		signupCitizenButton.setVisible(true);
		signupCompanyButton.setVisible(false);
		categoryField.setVisible(false);

	}

	public void showCompanySignupForm() {

		clearValues();
		firstNameField.setVisible(false);
		lastNameField.setVisible(false);
		companyNameField.setVisible(true);
		cityField.setVisible(true);
		signupCitizenButton.setVisible(false);
		signupCompanyButton.setVisible(true);
		categoryField.setVisible(true);
	}

	public void handlePictureUpload() {
		Window window = signupGridPane.getScene().getWindow();
		FileChooser imageFileChooser = new FileChooser();
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
		user.setProfilePicture(imageFile);

		if (LoginController.getInstance().signupCitizenUser(user)) {
			// TODO do something
		} else {
			// Show error message
			clearValues();
		}
	}

	public void signupCompany() {
		CompanyUserBean user = new CompanyUserBean();
		user.setCategory(categoryField.getText());
		user.setCity(cityField.getText());
		user.setCompanyName(companyNameField.getText());
		user.setImage(imageFile);
		user.setPassword(passwordField.getText());
		user.setUsername(userNameField.getText());

		if (LoginController.getInstance().signupCompanyUser(user)) {
			// TODO do something
		} else {
			// Show error message
			clearValues();
		}
	}

	public void clearValues() {
		firstNameField.clear();
		lastNameField.clear();
		userNameField.clear();
		passwordField.clear();
		companyNameField.clear();
		cityField.clear();
		categoryField.clear();
		selectedImageView.setImage(defaultImage);
		selectedImage = defaultImage;
	}

	public RadioButton getCitizenRadioButton() {
		return citizenRadioButton;
	}

	public void setCitizenRadioButton(RadioButton citizenRadioButton) {
		this.citizenRadioButton = citizenRadioButton;
	}

	public RadioButton getCompanyRadioButton() {
		return companyRadioButton;
	}

	public void setCompanyRadioButton(RadioButton companyRadioButton) {
		this.companyRadioButton = companyRadioButton;
	}

	public TextField getFirstNameField() {
		return firstNameField;
	}

	public void setFirstNameField(TextField firstNameField) {
		this.firstNameField = firstNameField;
	}

	public TextField getLastNameField() {
		return lastNameField;
	}

	public void setLastNameField(TextField lastNameField) {
		this.lastNameField = lastNameField;
	}

	public TextField getUserNameField() {
		return userNameField;
	}

	public void setUserNameField(TextField userNameField) {
		this.userNameField = userNameField;
	}

	public PasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(PasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public GridPane getSignupGridPane() {
		return signupGridPane;
	}

	public void setSignupGridPane(GridPane signupGridPane) {
		this.signupGridPane = signupGridPane;
	}

	public ImageView getSelectedImageView() {
		return selectedImageView;
	}

	public void setSelectedImageView(ImageView selectedImageView) {
		this.selectedImageView = selectedImageView;
	}

	public Image getDefaultImage() {
		return defaultImage;
	}

	public void setDefaultImage(Image defaultImage) {
		this.defaultImage = defaultImage;
	}

	public Image getSelectedImage() {
		return selectedImage;
	}

	public void setSelectedImage(Image selectedImage) {
		this.selectedImage = selectedImage;
	}

	public TextField getCompanyNameField() {
		return companyNameField;
	}

	public void setCompanyNameField(TextField companyNameField) {
		this.companyNameField = companyNameField;
	}

	public TextField getCityField() {
		return cityField;
	}

	public void setCityField(TextField cityField) {
		this.cityField = cityField;
	}

	public TextField getCategoryField() {
		return categoryField;
	}

	public void setCategoryField(TextField categoryField) {
		this.categoryField = categoryField;
	}

	public File getImageFile() {
		return imageFile;
	}

	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}

}
