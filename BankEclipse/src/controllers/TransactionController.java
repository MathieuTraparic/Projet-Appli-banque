package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.stage.WindowEvent;
import javafx.util.StringConverter;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.Account;
import model.PeriodicTransaction;
import model.Transaction;
import model.TransactionType;

public class TransactionController extends AccountSpecificController {

	@FXML
	public TableView<Transaction> tableTransaction;
	@FXML
	public TableColumn<Transaction, String> descriptionCol;
	@FXML
	public TableColumn<Transaction, TransactionType> typeCol;
	@FXML
	public TableColumn<Transaction, Double> valueCol;
	@FXML
	public TableColumn<Transaction, Date> dateCol;
	@FXML
	public TableColumn<Transaction, PeriodicTransaction> periodicCol;

	@FXML
	private Button addTransaction;
	
	@FXML
	private Button editTransaction;

	private ObservableList<Transaction> dataTransactionRow;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.initialize(location, resources);
		tableTransaction.setItems(FXCollections.observableList(new ArrayList<Transaction>()));

		this.addTransaction.setDisable(true);

		tableTransaction.getColumns().forEach(col -> {
			col.setEditable(true);
		});
		
		descriptionCol.setCellFactory(TextFieldTableCell.forTableColumn());

		valueCol.setCellFactory(
			    TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		dateCol.setCellFactory(
				TextFieldTableCell.forTableColumn(new DateStringConverter()));

		dateCol.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow())
				.setDate(t.getNewValue()));
		
		valueCol.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow())
				.setValue(t.getNewValue()));
		
		descriptionCol.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow())
				.setDescription(t.getNewValue()));
		
		tableTransaction.setItems(dataTransactionRow);
	}

	@FXML
	void accountCombo(ActionEvent event) throws IOException {
		
		this.addTransaction.setDisable(this.accountCombo.getValue() == null);
		if (this.accountCombo.getValue() != null) {
			this.tableTransaction
					.setItems(FXCollections.observableList(this.accountCombo.getValue().getTransactions()));
		}

	}

	@FXML
	void handleAddTransaction(ActionEvent event) throws IOException {

		PopupController<Transaction> controller = PopupController.load("/viewFxml/addTransaction.fxml", true);

		controller.show(new Transaction("Nouvelle transaction", 0.01, Calendar.getInstance().getTime(),
				new TransactionType("sd")), new EventHandler<WindowEvent>() {
					@Override
					public void handle(WindowEvent event) {
						Transaction transaction = controller.getValidatedData();

						if (transaction != null) {

							Account account = accountCombo.getValue();

							System.out.println(transaction.getDate());

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

	@FXML
	void removeTransaction(ActionEvent event) {

	}

	@FXML
	void editTransaction(ActionEvent event) {


		if (tableTransaction.getSelectionModel().getSelectedItem() != null) {
			
			editTransaction.setDisable(false);
			
			
			EntityManager em = VistaNavigator.getEmf().createEntityManager();

			em.close();
		}

	}
}
