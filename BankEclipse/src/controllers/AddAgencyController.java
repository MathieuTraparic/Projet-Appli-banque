package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Agency;

public class AddAgencyController {
	
	@FXML public Button addAgencyCancel, addAgencySubmit;
	@FXML public TextField agencyName, agencyCode;
	
	@FXML
	void handleAddAgencyCancel(ActionEvent event) {
		Stage stage = (Stage) addAgencyCancel.getScene().getWindow();
	    stage.close();
	}

	@FXML
	void handleAddAgencySubmit(ActionEvent event){
		Agency agency = new Agency(agencyName.getText(),agencyCode.getText());
		Stage stage = (Stage) addAgencySubmit.getScene().getWindow();
	    stage.close();
	}
}
