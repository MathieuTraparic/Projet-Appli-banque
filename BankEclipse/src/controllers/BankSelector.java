/**
 *  @author Mathieu Traparic
 * 	Project : Bank
 * 	Creation date : 2017-04-25
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
 *	Handle the initializing of a bank ComboBox specific to the logged Owner
 */
public abstract class BankSelector implements Initializable{
	@FXML
	ComboBox<Bank> bankCombo;
	protected HashSet<Bank> banks;
	protected HashSet<Account> accounts;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
		
	}

}
