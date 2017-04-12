package controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Account;

public class AddAccountController {

	public Button addAccountCancel, addAccountSubmit;
	public TextField addAccountNumber, addAccountDescription;
	public TextField addAccountInitBalance, addAccountOverdraft;
	public TextField addAccountThreshold;
	public ChoiceBox addAccountType,addAccountCountryCode;
	@FXML
	void handleAddAccountCancel(ActionEvent event){
		Stage stage = (Stage) addAccountCancel.getScene().getWindow();
	    stage.close();
	}

	@FXML
	void handleAddAccountSubmit(ActionEvent event){
		
		@SuppressWarnings("unchecked")
		ChoiceBox addAccountType = new ChoiceBox(FXCollections.observableArrayList(
			    Account.getTypes(), "Other"));
		
		@SuppressWarnings("unchecked")
		ChoiceBox addAccountCountryCode = new ChoiceBox(FXCollections.observableArrayList(
			    Account.getCountryCode(), "Other"));
		
		Account account = new Account(addAccountNumber.getText(), addAccountDescription.getText(),
				Double.parseDouble(addAccountInitBalance.getText()),
				Double.parseDouble(addAccountOverdraft.getText()),
				Double.parseDouble(addAccountThreshold.getText()),
				addAccountCountryCode.getAccessibleText(),
				addAccountType.getAccessibleText());
		Stage stage = (Stage) addAccountCancel.getScene().getWindow();
	    stage.close();
	}
}
