package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.WindowEvent;
import model.Account;
import model.Category;
import model.PeriodicTransaction;
import model.TargetTransaction;
import model.Transaction;
import model.TransactionType;
import util.PopWindow;

public class TransactionController extends AccountSpecificController {

	@FXML
	public TableView<Transaction> tableTransaction;
/*	@FXML
	public TableColumn<Transaction, String> description;
	@FXML
	public TableColumn<TransactionType, String> type;
	@FXML
	public TableColumn<Transaction, Double> value;
	@FXML
	public TableColumn<Transaction, Date> date;*/
	@FXML
	private Button addTransaction;
	private List<Transaction> tableTransactionData;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.initialize(location, resources);
		tableTransaction.setItems(FXCollections.observableList(new ArrayList<Transaction>()));
		
		this.addTransaction.setDisable(true);
		
		this.accountCombo.valueProperty().addListener((observable, oldValue, newValue) -> {
			this.addTransaction.setDisable(this.accountCombo.getValue() == null );
			
			if (this.accountCombo.getValue() != null){
			this.tableTransaction.setItems(FXCollections.observableList(
					this.accountCombo.getValue().getTransactions()));
			}
		});

	}
	
	

	@FXML
	void handleAddTransaction(ActionEvent event) throws IOException {
		
		
		
		PopupController<Transaction> controller = PopupController.load("/viewFxml/addTransaction.fxml", true);

		controller.show(new Transaction("Nouvelle transaction", 0.01, Calendar.getInstance().getTime(), new TransactionType("sd")),
				new EventHandler<WindowEvent>() {
					@Override
					public void handle(WindowEvent event) {
						Transaction transaction = controller.getValidatedData();

						if (transaction != null) {

							Account account = accountCombo.getValue();
							
							transaction.setAccount(account);
							transaction.setCategory(null);
							transaction.setPeriodicTransaction(null);
							transaction.setTargetTransaction(null);
							
							EntityManager em = VistaNavigator.getEmf().createEntityManager();
							em.getTransaction().begin();
							em.persist(transaction);
							em.getTransaction().commit();
							em.close();

							tableTransaction.getItems().add(transaction);
						}

					}
				});
	}
}
