package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Account;

public class AddAccountController implements Initializable {

	public Button addAccountCancel, addAccountSubmit;
	public TextField addAccountNumber, addAccountDescription;
	public TextField addAccountInitBalance, addAccountOverdraft;
	public TextField addAccountThreshold;
	public static ComboBox<String> addAccountType;
	public ComboBox<String> addAccountCountryCode;

	
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
				addAccountCountryCode.getAccessibleText(),
				addAccountType.getAccessibleText());
		Stage stage = (Stage) addAccountCancel.getScene().getWindow();
	    stage.close();
	}

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		addAccountType.getItems().setAll("type1","type2");
	}
}
