package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import model.Transaction;
import model.TransactionType;
import util.PopWindow;

public class TransactionController implements Initializable {
	
	@FXML public TableView<Transaction> tableTransaction;	
	


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tableTransaction.setItems(FXCollections.observableList(new ArrayList<Transaction>()));
	}	
	
	@FXML
	void handleAddTransaction(ActionEvent event) throws IOException {
		PopWindow addTransactionPop = new PopWindow("/viewFxml/addTransaction.fxml",true);
		
		Transaction t = (Transaction)addTransactionPop.showAndWait(
			new Transaction("Nouvelle transaction", 0, Calendar.getInstance().getTime())
		);

		if(t!=null) {
			tableTransaction.getItems().add(t);
		}
		//this.tableTransaction.setItems(FXCollections.observableList(t));
	}
}
