package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Bank;


public class AddBankController {
	
	public Button addBankCancel, addBankSubmit;
	public TextField bankName, bankCode;

	@FXML
	void handleAddBankCancel(ActionEvent event){
		Stage stage = (Stage) addBankCancel.getScene().getWindow();
	    stage.close();
	}

	@FXML
	void handleAddBankSubmit(ActionEvent event){
		Bank  bank = new Bank(bankName.getText(), bankCode.getText());
		Stage stage = (Stage) addBankSubmit.getScene().getWindow();
		stage.close();
	}
}
