package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.WindowEvent;
import model.Transaction;
import model.TransactionType;
import util.PopWindow;

public class TransactionController implements Initializable{
	
	@FXML public TableView<Transaction> tableTransaction;	
	@FXML public TableColumn<Transaction, String> description;
	@FXML public TableColumn<TransactionType, String> type;
	@FXML public TableColumn<Transaction, Double> value;
	@FXML public TableColumn<Transaction, Date> date;
	


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tableTransaction.setItems(FXCollections.observableList(new ArrayList<Transaction>()));
	}	
	
	@FXML
	void handleAddTransaction(ActionEvent event) throws IOException {
		//PopWindow addTransactionPop = new PopWindow("/viewFxml/addTransaction.fxml",true);
		PopupController<Transaction> controller = PopupController.load(
			"/viewFxml/addTransaction.fxml",true
		);
		
		controller.show(
			new Transaction("Nouvelle transaction", 0.01, Calendar.getInstance().getTime()),
			new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					Transaction t = controller.getValidatedData();
					TransactionType tp = controller.getValidatedData().getTransactionType();
					if(t!=null) {
						tableTransaction.getItems().add(t);
						//tableTransaction.getItems().add(tp);
					}
				}
			}
		);
	}
}
