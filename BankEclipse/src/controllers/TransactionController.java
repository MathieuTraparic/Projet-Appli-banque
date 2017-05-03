package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import controllers.popups.PopupController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.DoubleStringConverter;
import model.Account;
import model.Category;
import model.PeriodicTransaction;
import model.TargetTransaction;
import model.Transaction;
import model.TransactionType;
import util.DatePickerCell;

public class TransactionController extends AccountSelector {

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
	public TableColumn<Transaction, TargetTransaction> targetCol;
	@FXML
	public TableColumn<Transaction, Category> categoryCol;

	@FXML
	private Button addTransaction;

	@FXML
	private Button editTransaction;

	@FXML
	private Button removeTransaction;
	
	@FXML
	private Label balanceLabel, balanceNumberLabel, alertLabel;

	private ObservableList<Transaction> dataTransactionRow = null;
	
	private String overAndTresholdAlert = "The balance is below the overdraft limit \n and the threshold! Carefull!";
	private String overAlert = "The balance is below the overdraft limit! \n U gonna pay!";
	private String thresholdAlert ="The balance is below the threshold!";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.initialize(location, resources);
		
		balanceLabel.setVisible(false);
		balanceNumberLabel.setVisible(false);
		alertLabel.setVisible(false);
		
		tableTransaction.setItems(FXCollections.observableList(new ArrayList<Transaction>()));

		EntityManager em = VistaNavigator.getEmf().createEntityManager();
		List<TransactionType> transactionTypeList = em.createNamedQuery("TransactionType.findAll").getResultList();
		List<TargetTransaction> targetList = em.createNamedQuery("TargetTransaction.findAll").getResultList();
		List<Category> categoryList = em.createNamedQuery("Category.findAll").getResultList();
		em.close();
		
		/*
		 * Necessary because target and category can be null in the db
		 */
		targetList.add(null);
		categoryList.add(null);

		ObservableList<TransactionType> typeStringList = FXCollections.observableList(transactionTypeList);
		ObservableList<TargetTransaction> targetStringList = FXCollections.observableList(targetList);
		ObservableList<Category> categoryStringList = FXCollections.observableList(categoryList);

		this.addTransaction.setDisable(true);

		valueCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		dateCol.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
		typeCol.setCellFactory(ComboBoxTableCell.forTableColumn(typeStringList));
		targetCol.setCellFactory(ComboBoxTableCell.forTableColumn(targetStringList));
		categoryCol.setCellFactory(ComboBoxTableCell.forTableColumn(categoryStringList));

		dataTransactionRow = FXCollections.observableArrayList();
		
		/*
		 * AUTHOR :
		 * http://blog.physalix.com/javafx8-render-a-datepicker-cell-in-a-
		 * tableview/
		 */

		/*
		 * There still problem with the DatePickerCell class to fix // in worst
		 * case, the date picker will be changed to just a string column
		 */
		dateCol.setCellFactory(new Callback<TableColumn<Transaction, Date>, TableCell<Transaction, Date>>() {
			@Override
			public TableCell call(TableColumn p) {
				DatePickerCell datePick = new DatePickerCell(dataTransactionRow);
				return datePick;
			}
		});

		tableTransaction.getSelectionModel().selectedItemProperty().addListener((obs, old, obschanged) -> {
			editTransaction.setDisable(obschanged == null);
			removeTransaction.setDisable(obschanged == null);
		});

		// Getting the new date value is still problematic
		dateCol.setOnEditCommit(
				t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setDate(t.getNewValue()));

		typeCol.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow())
				.setTransactionType(t.getNewValue()));

		valueCol.setOnEditCommit(
				t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setValue(t.getNewValue()));

		descriptionCol.setOnEditCommit(
				t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setDescription(t.getNewValue()));

		targetCol.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow())
				.setTargetTransaction(t.getNewValue()));
		
		categoryCol.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow())
				.setCategory(t.getNewValue()));

	}

	@FXML
	void accountCombo(ActionEvent event) throws IOException {

		this.addTransaction.setDisable(this.accountCombo.getValue() == null);
		
		balanceLabel.setVisible(false);
		balanceNumberLabel.setVisible(false);
		alertLabel.setVisible(false);
		
		if (this.accountCombo.getValue() != null) {
			
			balanceLabel.setVisible(true);
			balanceNumberLabel.setVisible(true);
			
			this.tableTransaction
					.setItems(FXCollections.observableList(this.accountCombo.getValue().getTransactions()));
			
			Double balance = this.accountCombo.getValue().getBalance();			
			balanceNumberLabel.setText(balance.toString());
			
			Double alert = this.accountCombo.getValue().getAlertThreshold();
			
			Double overdraft = this.accountCombo.getValue().getOverdraft();
			

			if (alert != null && balance <= alert) {
				alertLabel.setText(thresholdAlert);
				alertLabel.setVisible(true);
			}
			if (overdraft != null  && balance <= overdraft){
				alertLabel.setText(overAlert);
				alertLabel.setVisible(true);
			}
			if (alert != null  && overdraft != null  && balance <= overdraft && balance <= alert){
				alertLabel.setText(overAndTresholdAlert);
				alertLabel.setVisible(true);
			}
		}
		

	}

	@FXML
	void handleAddTransaction(ActionEvent event) throws IOException {
		
		alertLabel.setVisible(false);

		PopupController<Transaction> controller = PopupController.load("/viewFxml/addTransaction.fxml", true);

		controller.show(new Transaction("Nouvelle transaction", 0.01, Calendar.getInstance().getTime(),
				new TransactionType("sd")), new EventHandler<WindowEvent>() {
					@Override
					public void handle(WindowEvent event) {
						Transaction transaction = controller.getValidatedData();

						if (transaction != null) {

							Account account = accountCombo.getValue();

							transaction.setAccount(account);

							transaction.setPeriodicTransaction(null);


							EntityManager em = VistaNavigator.getEmf().createEntityManager();
							em.getTransaction().begin();
							em.persist(transaction);
							em.getTransaction().commit();
							em.close();

							tableTransaction.getItems().add(transaction);

							tableTransaction.getSelectionModel().clearSelection();

							tableTransaction.getColumns().forEach(col -> {
								col.setVisible(false);
								col.setVisible(true);
								
								
							});

							Double balance = accountCombo.getValue().getBalance();			
							balanceNumberLabel.setText(balance.toString());
							
							Double alert = accountCombo.getValue().getAlertThreshold();
							
							Double overdraft = accountCombo.getValue().getOverdraft();
							

							if (alert != null && balance <= alert) {
								alertLabel.setText(thresholdAlert);
								alertLabel.setVisible(true);
							}
							if (overdraft != null  && balance <= overdraft){
								alertLabel.setText(overAlert);
								alertLabel.setVisible(true);
							}
							if (alert != null  && overdraft != null  && balance <= overdraft && balance <= alert){
								alertLabel.setText(overAndTresholdAlert);
								alertLabel.setVisible(true);
							}

						}

					}
				});

	}

	@FXML
	void removeTransaction(ActionEvent event) {
		
		alertLabel.setVisible(false);

		Transaction transactionUpdate = tableTransaction.getSelectionModel().getSelectedItem();

		EntityManager em = VistaNavigator.getEmf().createEntityManager();

		Transaction t = em.find(Transaction.class, transactionUpdate.getId());

		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();

		em.close();

		tableTransaction.getItems().remove(transactionUpdate);

		tableTransaction.getSelectionModel().clearSelection();

		tableTransaction.getColumns().forEach(col -> {
			col.setVisible(false);
			col.setVisible(true);
		});

		removeTransaction.setDisable(true);
		editTransaction.setDisable(true);
		
		Double balance = this.accountCombo.getValue().getBalance();			
		balanceNumberLabel.setText(balance.toString());
		
		Double alert = this.accountCombo.getValue().getAlertThreshold();
		
		Double overdraft = this.accountCombo.getValue().getOverdraft();
		

		if (alert != null && balance <= alert) {
			alertLabel.setText(thresholdAlert);
			alertLabel.setVisible(true);
		}
		if (overdraft != null  && balance <= overdraft){
			alertLabel.setText(overAlert);
			alertLabel.setVisible(true);
		}
		if (alert != null  && overdraft != null  && balance <= overdraft && balance <= alert){
			alertLabel.setText(overAndTresholdAlert);
			alertLabel.setVisible(true);
		}

	}

	@FXML
	void editTransaction(ActionEvent event) {
		
		alertLabel.setVisible(false);

		Transaction transactionUpdate = tableTransaction.getSelectionModel().getSelectedItem();

		EntityManager em = VistaNavigator.getEmf().createEntityManager();

		Query q = em.createQuery("UPDATE Transaction a SET a.description=:description, a.value=:value, a.date=:date, "
				+ "a.transactionType=:transactionType, a.category=:category, a.targetTransaction=:targetTransaction WHERE a.id=:id");
		
		em.getTransaction().begin();

		q.setParameter("id", transactionUpdate.getId());
		q.setParameter("description", transactionUpdate.getDescription());
		q.setParameter("value", transactionUpdate.getValue());
		q.setParameter("date", transactionUpdate.getDate());
		q.setParameter("transactionType", transactionUpdate.getTransactionType());
		q.setParameter("category", transactionUpdate.getCategory());
		q.setParameter("targetTransaction", transactionUpdate.getTargetTransaction());

		q.executeUpdate();

		em.getTransaction().commit();

		em.close();

		tableTransaction.getSelectionModel().clearSelection();

		tableTransaction.getColumns().forEach(col -> {
			col.setVisible(false);
			col.setVisible(true);
		});

		editTransaction.setDisable(true);
		
		Double balance = this.accountCombo.getValue().getBalance();			
		balanceNumberLabel.setText(balance.toString());
		
		Double alert = this.accountCombo.getValue().getAlertThreshold();
		
		Double overdraft = this.accountCombo.getValue().getOverdraft();
		

		if (alert != null && balance <= alert) {
			alertLabel.setText(thresholdAlert);
			alertLabel.setVisible(true);
		}
		if (overdraft != null  && balance <= overdraft){
			alertLabel.setText(overAlert);
			alertLabel.setVisible(true);
		}
		if (alert != null  && overdraft != null  && balance <= overdraft && balance <= alert){
			alertLabel.setText(overAndTresholdAlert);
			alertLabel.setVisible(true);
		}

	}

}
