package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AddAccountController {

	public Button addAccountCancel;

	@FXML
	void handleAddAccountCancel(ActionEvent event){
		Stage stage = (Stage) addAccountCancel.getScene().getWindow();
	    stage.close();
	}

	@FXML
	void handleAddAccountSubmit(ActionEvent event){
		//TODO
	}

}
