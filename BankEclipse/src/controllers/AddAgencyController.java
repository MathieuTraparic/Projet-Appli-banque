package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AddAgencyController {
	
	public Button addAgencyCancel; 
	
	@FXML
	void handleAddAgencyCancel(ActionEvent event) {
		Stage stage = (Stage) addAgencyCancel.getScene().getWindow();
	    stage.close();
	}

	@FXML
	void handleAddAgencySubmit(ActionEvent event){
		//TODO
	}
}
