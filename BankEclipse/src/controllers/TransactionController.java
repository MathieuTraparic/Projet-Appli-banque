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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.DefaultStringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.Account;
import model.PeriodicTransaction;
import model.Transaction;
import model.TransactionType;
import util.DatePickerCell;


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
	
	@FXML
	private Button removeTransaction;

	private ObservableList<Transaction> dataTransactionRow=null;
	private ArrayList<TransactionType> typeList;
	private ObservableList<TransactionType> typeStringList;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.initialize(location, resources);
		tableTransaction.setItems(FXCollections.observableList(new ArrayList<Transaction>()));
		
		typeList = new ArrayList<>();

		EntityManager em = VistaNavigator.getEmf().createEntityManager();
		List<TransactionType> l = em.createNamedQuery("TransactionType.findAll").getResultList();
		em.close();



		if (l!=null){
			l.forEach(tr -> typeList.add(tr));
		}	
		
		typeStringList = FXCollections.observableList(typeList);
		
		this.addTransaction.setDisable(true);

		valueCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		dateCol.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
		typeCol.setCellFactory(ComboBoxTableCell.forTableColumn(typeStringList));
		

		dataTransactionRow = FXCollections.observableArrayList();
		
		/*
		 * AUTHOR : http://blog.physalix.com/javafx8-render-a-datepicker-cell-in-a-tableview/
		 */
		
		/*
		 * There still problem with the DatePickerCell class to fix
		 */
		dateCol.setCellFactory(new Callback<TableColumn<Transaction, Date>, TableCell<Transaction, Date>>() {
            @Override
            public TableCell call(TableColumn p) {
                DatePickerCell datePick = new DatePickerCell(dataTransactionRow);
                return datePick;
            }
        });

		
		tableTransaction.getSelectionModel().selectedItemProperty().addListener((obs, old, obschanged)-> {
				editTransaction.setDisable(obschanged == null);
				removeTransaction.setDisable(obschanged == null);
		});
		
		//Getting the new date value is still problematic
		dateCol.setOnEditCommit(
				t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setDate(t.getNewValue()));
		
		typeCol.setOnEditCommit(
				t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setTransactionType(t.getNewValue()));


		valueCol.setOnEditCommit(
				t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setValue(t.getNewValue()));

		descriptionCol.setOnEditCommit(
				t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setDescription(t.getNewValue()));

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
							
							tableTransaction.setItems(FXCollections.observableList(new ArrayList<Transaction>()));
							
						}

					}
				});

	}

	@FXML
	void removeTransaction(ActionEvent event) {
		
		Transaction transactionUpdate = tableTransaction.getSelectionModel().getSelectedItem();

		EntityManager em = VistaNavigator.getEmf().createEntityManager();
		
		Transaction t = em.find(Transaction.class,transactionUpdate.getId());
		
		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();

		em.close();
		
		tableTransaction.getItems().remove(transactionUpdate);
		
		tableTransaction.getSelectionModel().clearSelection();

		removeTransaction.setDisable(true);
		editTransaction.setDisable(true);

	}

	@FXML
	void editTransaction(ActionEvent event) {
		
			Transaction transactionUpdate = tableTransaction.getSelectionModel().getSelectedItem();

			EntityManager em = VistaNavigator.getEmf().createEntityManager();
			
			em.getTransaction().begin();
			
			//String description, double value, Date date, TransactionType transactionType, PeriodicTransaction periodicTransaction 
			
			Query q = em.createQuery(
					"UPDATE Transaction a SET a.description=:description, a.value=:value, a.date=:date, "
							+ "a.transactionType=:transactionType WHERE a.id=:id");
			q.setParameter("id", transactionUpdate.getId());
			q.setParameter("description", transactionUpdate.getDescription());
			q.setParameter("value", transactionUpdate.getValue());
			q.setParameter("date", transactionUpdate.getDate());
			q.setParameter("transactionType", transactionUpdate.getTransactionType());

			q.executeUpdate();
			
			em.getTransaction().commit();

			em.close();
			
			tableTransaction.getSelectionModel().clearSelection();
			
			editTransaction.setDisable(true);

	}
}
