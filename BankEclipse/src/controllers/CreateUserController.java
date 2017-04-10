package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

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
}
