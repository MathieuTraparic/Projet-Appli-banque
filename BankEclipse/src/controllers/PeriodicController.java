package controllers;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import model.PeriodicTransaction;
import model.Transaction;
import javafx.event.ActionEvent;

public class PeriodicController extends AccountSpecificController {

	@FXML
	TableView<PeriodicTransaction> viewPeriodic;
	HashSet<Transaction> transactionsOwned;
	HashSet<PeriodicTransaction> periodicTransactionsOwned;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.initialize(location, resources);

		this.transactionsOwned = new HashSet<>();
		this.accountsOwned.forEach(account -> transactionsOwned.addAll(account.getTransactions()));

		this.periodicTransactionsOwned = new HashSet<>();
		transactionsOwned.forEach(t -> {
			if(t.getPeriodicTransaction()!=null)
				periodicTransactionsOwned.add(t.getPeriodicTransaction());
		});

	}

	@FXML
	public void accountChosen(ActionEvent event) {
		this.viewPeriodic.getItems().clear();
		for (PeriodicTransaction pt : periodicTransactionsOwned) {
				System.out.println(pt.getFrequency());
		}

		this.viewPeriodic.setItems(FXCollections.observableArrayList(
				this.periodicTransactionsOwned.toArray(new PeriodicTransaction[periodicTransactionsOwned.size()])));
	}

}
