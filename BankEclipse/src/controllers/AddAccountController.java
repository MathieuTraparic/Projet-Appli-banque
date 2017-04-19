package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Account;
import model.AccountType;
import model.CountryCode;
import model.Owner;

public class AddAccountController implements Initializable {

	@FXML public Button addAccountCancel, addAccountSubmit;
	@FXML public TextField addAccountNumber, addAccountDescription;
	@FXML public TextField addAccountInitBalance, addAccountOverdraft;
	@FXML public TextField addAccountThreshold;
	@FXML public ComboBox<String> addAccountType;
	@FXML public ComboBox<String> addAccountCountryCode;

	
	@FXML
	void handleAddAccountCancel(ActionEvent event){
		Stage stage = (Stage) addAccountCancel.getScene().getWindow();
	    stage.close();
	}

	@FXML
	void handleAddAccountSubmit(ActionEvent event){
		
//		Account account = new Account(addAccountNumber.getText(), addAccountDescription.getText(),
//				Double.parseDouble(addAccountInitBalance.getText()),
//				Double.parseDouble(addAccountOverdraft.getText()),
//				Double.parseDouble(addAccountThreshold.getText()),
//				addAccountType.getAccessibleText());
		CountryCode countryCode = new CountryCode(addAccountCountryCode.getAccessibleText());
		Stage stage = (Stage) addAccountCancel.getScene().getWindow();
	    stage.close();
	}

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		EntityManager em = VistaNavigator.getEmf().createEntityManager();
		List<CountryCode> l = em.createNamedQuery("countrycode.findAll").getResultList();
		List<AccountType> j = em.createNamedQuery("accounttype.findAll").getResultList();
		em.close();
		
		for (CountryCode countrycode : l){
			addAccountCountryCode.getItems().add(countrycode.getCode());
		}
		
		for (AccountType type : j){
			addAccountCountryCode.getItems().add(type.getType());
		}
		
		addAccountCountryCode.getItems().add("OTHER");

		addAccountType.getItems().add("OTHER");
	}
}
