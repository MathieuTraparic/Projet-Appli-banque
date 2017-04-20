/**
 *  @author Mathieu Traparic
 * 	Project : Bank
 * 	Creation date : 2017-04-01
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
import javafx.scene.text.Text;
import model.Account;
import model.Agency;
import model.Bank;
import model.Owner;
import util.IBANHandler;
import javafx.event.ActionEvent;

public class RIBController implements Initializable {

	@FXML
	ComboBox<Bank> bankCombo;
	@FXML
	Text iban;
	@FXML
	ComboBox<Account> accountCombo;
	private HashSet<Bank> banks;
	private HashSet<Account> accounts;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.accountCombo.setDisable(true);

		Owner o = VistaNavigator.getInstance().getLoggedOwner();

		EntityManager em = VistaNavigator.getEmf().createEntityManager();

		// get the accounts from logged owner
		this.accounts = new HashSet<>();
		TypedQuery<List> q = em.createQuery("SELECT o.accounts FROM Owner o WHERE o=:loggedOwner", List.class);
		accounts.addAll((Collection<? extends Account>) q.setParameter("loggedOwner", o).getResultList());

		HashSet<Agency> agencies = new HashSet<>();
		accounts.forEach(account -> agencies.add(account.getAgency()));

		this.banks = new HashSet<>();
		agencies.forEach(agency -> banks.add(agency.getBank()));

		this.bankCombo.getItems().addAll(banks);

		this.bankCombo.valueProperty().addListener((observable, oldValue, newValue) -> {
			HashSet<Account> accountFromCurrentBank = new HashSet<>();
			this.accounts.forEach(account-> {
				if(account.getAgency().getBank().equals(bankCombo.getValue())){
					accountFromCurrentBank.add(account);
				}
			});
			this.accountCombo.setDisable(false);
			this.accountCombo.getItems().setAll(accountFromCurrentBank);
		});

	}

	@FXML public void accountSelected(ActionEvent event) {
		if(this.accountCombo.getValue()==null){
			this.iban.setText("");
			return;
		}
		Account a = this.accountCombo.getValue();
		this.iban.setText(IBANHandler.genrateIBAN(a.getAccountNumber(), a.getAgency().getCounterCode(), a.getAgency().getBank().getCode(), a.getCountryCode().getCode()));
		
	}


}
