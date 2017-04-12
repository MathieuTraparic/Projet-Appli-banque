package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Account;

public class AddAccountController {

	public Button addAccountCancel, addAccountSubmit;
	public TextField addAccountNumber, addAccountDescription, addAccountCountryCode, addAccountType;
	public TextField addAccountInitBalance, addAccountOverdraft;
	public TextField addAccountThreshold;
	@FXML
	void handleAddAccountCancel(ActionEvent event){
		Stage stage = (Stage) addAccountCancel.getScene().getWindow();
	    stage.close();
	}

	@FXML
	void handleAddAccountSubmit(ActionEvent event){
		Account account = new Account(addAccountNumber.getText(), addAccountDescription.getText(),
				Double.parseDouble(addAccountInitBalance.getText()),
				Double.parseDouble(addAccountOverdraft.getText()),
				Double.parseDouble(addAccountThreshold.getText()),
				addAccountCountryCode.getText(),
				addAccountType.getText());
		Stage stage = (Stage) addAccountCancel.getScene().getWindow();
	    stage.close();
	}
}
