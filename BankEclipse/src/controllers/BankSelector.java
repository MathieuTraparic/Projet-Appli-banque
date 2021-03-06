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
import javafx.scene.input.MouseEvent;
import model.Account;
import model.Agency;
import model.Bank;
import model.Owner;

/**
 *	Handle the initializing of a bank ComboBox specific to the logged Owner
 */
public abstract class BankSelector implements Initializable, Refreshable{
	@FXML
	ComboBox<Bank> bankCombo;
	protected HashSet<Bank> banksOwned;
	protected HashSet<Account> accountsOwned;
	protected HashSet<Agency> agencyOwned;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.refresh();
		
	}

	@Override
	public void refresh() {
		this.bankCombo.getItems().clear();
		Owner loggedOwner = VistaNavigator.getInstance().getLoggedOwner();

		EntityManager em = VistaNavigator.getEmf().createEntityManager();

		// get the accounts from logged owner
		this.accountsOwned = new HashSet<>();
		TypedQuery<List> q = em.createQuery("SELECT o.accounts FROM Owner o WHERE o=:loggedOwner", List.class);
		accountsOwned.addAll((Collection<? extends Account>) q.setParameter("loggedOwner", loggedOwner).getResultList());
		
		// getResultist return null if no rows are found
		if(accountsOwned.contains(null) && accountsOwned.size()==1){
			accountsOwned.clear();
		}
		//get a set of agencies from the accounts
		this.agencyOwned = new HashSet<>();
		//accountsOwned.forEach(account -> agencies.add(account.getAgency()));
		for (Account account: accountsOwned) {
			this.agencyOwned.add(account.getAgency());
		}
		
		//get a set of banks from the agencies
		this.banksOwned = new HashSet<>();
		this.agencyOwned.forEach(agency -> banksOwned.add(agency.getBank()));

		this.bankCombo.getItems().addAll(banksOwned);
		this.bankCombo.getItems().sort(Bank.ALPHABETICAL_COMPARATOR);
		
	}
	
	@FXML public void bankComboClicked(MouseEvent event) {
		this.refresh();
	}
	

}
