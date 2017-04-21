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


public abstract class AccountSpecificController implements Initializable {
	@FXML
	ComboBox<Bank> bankCombo;
	@FXML
	ComboBox<Account> accountCombo;
	protected HashSet<Bank> banks;
	protected HashSet<Account> accounts;

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.accountCombo.setDisable(true);

		Owner loggedOwner = VistaNavigator.getInstance().getLoggedOwner();

		EntityManager em = VistaNavigator.getEmf().createEntityManager();

		// get the accounts from logged owner
		this.accounts = new HashSet<>();
		TypedQuery<List> q = em.createQuery("SELECT o.accounts FROM Owner o WHERE o=:loggedOwner", List.class);
		accounts.addAll((Collection<? extends Account>) q.setParameter("loggedOwner", loggedOwner).getResultList());
		
		//get a set of agencies from the accounts
		HashSet<Agency> agencies = new HashSet<>();
		accounts.forEach(account -> agencies.add(account.getAgency()));
		
		//get a set of banks from the agencies
		this.banks = new HashSet<>();
		agencies.forEach(agency -> banks.add(agency.getBank()));

		this.bankCombo.getItems().addAll(banks);

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
