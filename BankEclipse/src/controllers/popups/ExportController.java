package controllers.popups;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import controllers.VistaNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import model.Account;
import model.Agency;
import model.Bank;
import model.Owner;
import model.Transaction;

public class ExportController extends PopupController<Account> implements Initializable {

	@FXML
	public ComboBox<Account> selectedAccount;
	@FXML
	public ComboBox<Bank> selectedBank;
	@FXML
	public Button exportCancel, export;
	private HashSet<Bank> banksOwned;
	private HashSet<Account> accountsOwned;
	private List<Transaction> transactions = null;

	@FXML
	void handleExportCancel(ActionEvent event) {
		Stage stage = (Stage) exportCancel.getScene().getWindow();
		stage.close();
	}

	@FXML
	void handleExport(ActionEvent event) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		File selectedDirectory = 
				directoryChooser.showDialog((Stage) export.getScene().getWindow());
		transactions = selectedAccount.getValue().getTransactions();
		transactions.size();
		try {
			FileWriter fw = new FileWriter(new File(selectedDirectory.getAbsolutePath(), selectedAccount.getValue().getDescription() + ".csv"));
			for(Transaction l : transactions){
				//oos.writeChars(l.formatString());
				fw.write(l.formatString());
				fw.flush();
			}
			fw.close();
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}
		Stage stage = (Stage) exportCancel.getScene().getWindow();
		stage.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Owner loggedOwner = VistaNavigator.getInstance().getLoggedOwner();

		EntityManager em = VistaNavigator.getEmf().createEntityManager();

		// get the accounts from logged owner
		this.accountsOwned = new HashSet<>();
		TypedQuery<List> q = em.createQuery("SELECT o.accounts FROM Owner o WHERE o=:loggedOwner", List.class);
		accountsOwned
				.addAll((Collection<? extends Account>) q.setParameter("loggedOwner", loggedOwner).getResultList());

		// get a set of agencies from the accounts
		HashSet<Agency> agencies = new HashSet<>();
		accountsOwned.forEach(account -> agencies.add(account.getAgency()));

		// get a set of banks from the agencies
		this.banksOwned = new HashSet<>();
		agencies.forEach(agency -> banksOwned.add(agency.getBank()));

		this.selectedBank.getItems().addAll(banksOwned);

		this.selectedAccount.setDisable(true);

		this.selectedBank.valueProperty().addListener((observable, oldValue, newValue) -> {
			// get a bank specific subset of all the account from the owner
			HashSet<Account> accountFromCurrentBank = new HashSet<>();
			this.accountsOwned.forEach(account -> {
				if (account.getAgency().getBank().equals(selectedBank.getValue())) {
					accountFromCurrentBank.add(account);
				}
			});

			// update the account comboBox and reset its current value
			this.selectedAccount.setDisable(false);
			this.selectedAccount.getItems().setAll(accountFromCurrentBank);
			this.selectedAccount.setValue(null);
		});
	}

	@Override
	protected void initializePopupFields(Account data) {
		// TODO Auto-generated method stub

	}

}
