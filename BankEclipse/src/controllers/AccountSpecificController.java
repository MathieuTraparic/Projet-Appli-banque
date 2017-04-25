/**
 *  @author Mathieu Traparic
 * 	Project : Bank
 * 	Creation date : 2017-04-20
 */
package controllers;

import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import model.Account;
import model.Agency;
import model.Bank;
import model.Owner;


/**
 *	Handle the initialization of a account ComboBox specific to the logged Owner
 *	TODO Wrapper design pattern ?
 */
public abstract class AccountSpecificController extends BankSelector {
	
	@FXML
	ComboBox<Account> accountCombo;
	
	

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.initialize(location, resources);
		this.accountCombo.setDisable(true);

		this.bankCombo.valueProperty().addListener((observable, oldValue, newValue) -> {
			//get a bank specific subset of all the account from the owner 
			HashSet<Account> accountFromCurrentBank = new HashSet<>();
			this.accounts.forEach(account-> {
				if(account.getAgency().getBank().equals(bankCombo.getValue())){
					accountFromCurrentBank.add(account);
				}
			});
			
			//update the account comboBox and reset its current value
			this.accountCombo.setDisable(false);
			this.accountCombo.getItems().setAll(accountFromCurrentBank);
			this.accountCombo.setValue(null);
		});

	}

}
