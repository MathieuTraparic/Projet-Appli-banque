package controllers;

import java.io.IOException;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import model.Transaction;
import util.PopWindow;

public class TransactionController {
	
	@FXML public TableView<Transaction> tableTransaction;
	private String description;
	
	
	@FXML
	void handleAddTransaction(ActionEvent event) throws IOException {
		PopWindow addTransactionPop = new PopWindow("/viewFxml/addTransaction.fxml",true, 
				this.getClass());
		tableTransaction.getItems().add(AddTransactionController.getTransaction());
	}
}
