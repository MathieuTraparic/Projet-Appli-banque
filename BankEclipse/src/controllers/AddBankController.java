package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class AddBankController {
	
	public Button addBankCancel;

	@FXML
	void handleAddBankCancel(ActionEvent event){
		Stage stage = (Stage) addBankCancel.getScene().getWindow();
	    stage.close();
	}

	@FXML
	void handleAddBankSubmit(ActionEvent event){
		//TODO
	}
}
