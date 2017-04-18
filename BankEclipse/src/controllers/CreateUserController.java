package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CreateUserController {
	@FXML
	void nextStepButton(ActionEvent event){
		VistaNavigator.loadVista(VistaNavigator.CREATE_USER_2);
	}
	@FXML
	void previousButton(ActionEvent event){
		VistaNavigator.loadVista(VistaNavigator.CREATE_USER_1);
	}
	@FXML
	void cancelButton(ActionEvent event){
		VistaNavigator.loadVista(VistaNavigator.LOGIN);
	}
	
	@FXML
	void signInButton(ActionEvent event){
		VistaNavigator.loadVista(VistaNavigator.TEMPLATE);
	}
	
	@FXML
	private TextField newLogin;	
	@FXML
	private TextField email;	
	@FXML
	private PasswordField newPassword;	
	@FXML
	private PasswordField newPasswordConfirmation;
	@FXML
	private TextField name;	
	@FXML
	private TextField firstName;
	@FXML
	private TextField addressLine1;
	@FXML
	private TextField addressLine2;
	@FXML
	private TextField cityName;
	@FXML
	private TextField zipCode;
	
}
