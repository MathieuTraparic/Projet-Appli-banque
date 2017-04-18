package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import util.PopWindow;

public class TransactionController {
	
	@FXML public TableView tableTransaction; 
	
	@FXML
	void handleAddTransaction(ActionEvent event) throws IOException {
		PopWindow addTransactionPop = new PopWindow("/viewFxml/addTransaction.fxml",true, 
				this.getClass());
		((AddTransactionController)getController).getTransaction();
	}
	
}
