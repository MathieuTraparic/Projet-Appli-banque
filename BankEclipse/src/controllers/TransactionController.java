package controllers;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.PopWindow;

public class TransactionController {
	
	@FXML
	void handleAddTransaction(ActionEvent event) throws IOException {
		PopWindow addTransactionPop = new PopWindow("/viewFxml/addTransaction.fxml",true);
	}
	
}
