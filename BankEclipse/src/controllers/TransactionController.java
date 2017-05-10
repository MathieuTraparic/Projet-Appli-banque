package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.opencsv.CSVReader;

import controllers.popups.PopupController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import model.Account;
import model.Category;
import model.PeriodicTransaction;
import model.TargetTransaction;
import model.Transaction;
import model.TransactionType;
import util.DateConverter;
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
	public TableColumn<Transaction, Date> endDateCol;

	@FXML
	private Button addTransaction, importButton, exportButton;

	@FXML
	private Button editTransaction;

	@FXML
	private Button removeTransaction;

	@FXML
	private Label balanceLabel, balanceNumberLabel, alertLabel;

	private ObservableList<Transaction> dataTransactionRow = null;
	private ObservableList<Transaction> endDateTransactionRow = null;
	private String overAndTresholdAlert = "The balance is below the overdraft limit \n and the threshold! Carefull!";
	private String overAlert = "The balance is below the overdraft limit! \n U gonna pay!";
	private String thresholdAlert = "The balance is below the threshold!";

	
	private Locale locale  = new Locale("en", "UK");
	private String pattern = "###.##";

	private DecimalFormat formatter = (DecimalFormat) NumberFormat.getNumberInstance(locale);
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.initialize(location, resources);

		balanceLabel.setVisible(false);
		balanceNumberLabel.setVisible(false);
		alertLabel.setVisible(false);
		
		formatter.applyPattern(pattern);
		
		
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

		typeCol.setCellFactory(ComboBoxTableCell.forTableColumn(typeStringList));
		targetCol.setCellFactory(ComboBoxTableCell.forTableColumn(targetStringList));
		categoryCol.setCellFactory(ComboBoxTableCell.forTableColumn(categoryStringList));

		/*
		 * AUTHOR :
		 * http://blog.physalix.com/javafx8-render-a-datepicker-cell-in-a-
		 * tableview/
		 */

		/*
		 * There still problem with the DatePickerCell class to fix // in worst
		 * case, the date picker will be changed to just a string column
		 */
		dataTransactionRow = dateCol.getTableView().getItems();

		dateCol.setCellValueFactory(new PropertyValueFactory<Transaction, Date>("date"));
		
		dateCol.setCellFactory(new Callback<TableColumn<Transaction, Date>, TableCell<Transaction, Date>>() {
			@Override
			public TableCell call(TableColumn p) {
				DatePickerCell datePick = new DatePickerCell(dataTransactionRow);
				return datePick;
			}
		});
		
		endDateTransactionRow = endDateCol.getTableView().getItems();
		
		endDateCol.setCellValueFactory(new PropertyValueFactory<Transaction, Date>("date"));

		endDateCol.setCellFactory(new Callback<TableColumn<Transaction, Date>, TableCell<Transaction, Date>>() {
			@Override
			public TableCell call(TableColumn d) {
				DatePickerCell datePick2 = new DatePickerCell(endDateTransactionRow);
				return datePick2;
			}
		});
		
		tableTransaction.getSelectionModel().selectedItemProperty().addListener((obs, old, obschanged) -> {
			editTransaction.setDisable(obschanged == null);
			removeTransaction.setDisable(obschanged == null);

			// if ( obschanged != null){
			// String interest =
			// tableTransaction.getSelectionModel().getSelectedItem().interestTransaction();
			//
			// interestLabel.setText(interest);
			// interestLabel.setVisible(true);
			// }
		});

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

		categoryCol.setOnEditCommit(
				t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setCategory(t.getNewValue()));

	}

	@FXML
	void accountCombo(ActionEvent event) throws IOException {

		this.addTransaction.setDisable(this.accountCombo.getValue() == null);
		this.importButton.setDisable(this.accountCombo.getValue() == null);
		this.exportButton.setDisable(this.accountCombo.getValue() == null);

		balanceLabel.setVisible(false);
		balanceNumberLabel.setVisible(false);
		alertLabel.setVisible(false);

		if (this.accountCombo.getValue() != null) {

			balanceLabel.setVisible(true);
			balanceNumberLabel.setVisible(true);

			this.tableTransaction
					.setItems(FXCollections.observableList(this.accountCombo.getValue().getTransactions()));
			this.dataTransactionRow = this.tableTransaction.getItems();
			this.endDateTransactionRow = this.tableTransaction.getItems();

			Double balance = this.accountCombo.getValue().getBalance();

			balanceNumberLabel.setText(formatter.format(balance));

			Double alert = this.accountCombo.getValue().getAlertThreshold();

			Double overdraft = this.accountCombo.getValue().getOverdraft();

			if (alert != null && balance <= alert) {
				alertLabel.setText(thresholdAlert);
				alertLabel.setVisible(true);
			}
			if (overdraft != null && balance <= overdraft) {
				alertLabel.setText(overAlert);
				alertLabel.setVisible(true);
			}
			if (alert != null && overdraft != null && balance <= overdraft && balance <= alert) {
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
				new TransactionType("sd"), this.accountCombo.getValue()), new EventHandler<WindowEvent>() {
					@Override
					public void handle(WindowEvent event) {
						Transaction transaction = controller.getValidatedData();

						if (transaction != null) {

							Account account = accountCombo.getValue();

							transaction.setAccount(account);


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

							balanceNumberLabel.setText(formatter.format(balance));

							Double alert = accountCombo.getValue().getAlertThreshold();

							Double overdraft = accountCombo.getValue().getOverdraft();

							if (alert != null && balance <= alert) {
								alertLabel.setText(thresholdAlert);
								alertLabel.setVisible(true);
							}
							if (overdraft != null && balance <= overdraft) {
								alertLabel.setText(overAlert);
								alertLabel.setVisible(true);
							}
							if (alert != null && overdraft != null && balance <= overdraft && balance <= alert) {
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

		balanceNumberLabel.setText(formatter.format(balance));

		Double alert = this.accountCombo.getValue().getAlertThreshold();

		Double overdraft = this.accountCombo.getValue().getOverdraft();

		if (alert != null && balance <= alert) {
			alertLabel.setText(thresholdAlert);
			alertLabel.setVisible(true);
		}
		if (overdraft != null && balance <= overdraft) {
			alertLabel.setText(overAlert);
			alertLabel.setVisible(true);
		}
		if (alert != null && overdraft != null && balance <= overdraft && balance <= alert) {
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

		balanceNumberLabel.setText(formatter.format(balance));

		Double alert = this.accountCombo.getValue().getAlertThreshold();

		Double overdraft = this.accountCombo.getValue().getOverdraft();

		if (alert != null && balance <= alert) {
			alertLabel.setText(thresholdAlert);
			alertLabel.setVisible(true);
		}
		if (overdraft != null && balance <= overdraft) {
			alertLabel.setText(overAlert);
			alertLabel.setVisible(true);
		}
		if (alert != null && overdraft != null && balance <= overdraft && balance <= alert) {
			alertLabel.setText(overAndTresholdAlert);
			alertLabel.setVisible(true);
		}

	}

	/**
	 * @result Import data from LA BANQUE POSTALE transaction detail with CSV
	 *         file
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void handleImport(ActionEvent event) throws IOException {

		FileChooser fileChooser = new FileChooser();
		File selectedCSV = fileChooser.showOpenDialog((Stage) importButton.getScene().getWindow());
		List<Transaction> transactions;

		try {
			// ; is the separator, ' ' is the quote and 8 means that the first 8
			// lines are not read
			CSVReader reader = new CSVReader(new FileReader(selectedCSV.toString()), ';');

			// List list = reader.readAll();

			String[] nextLine;

			for (int i = 0; i < 8; i++) {
				nextLine = reader.readNext();
			}

			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

			EntityManager em = VistaNavigator.getEmf().createEntityManager();

			while ((nextLine = reader.readNext()) != null) {
				// nextLine[] is an array of values from the line

				String val = nextLine[2];
				val = val.replaceAll(",", ".");
				double value = Double.parseDouble(val);

				Date dateT = Calendar.getInstance().getTime();
				try {
					dateT = formatter.parse(nextLine[0]);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Transaction transaction = new Transaction(nextLine[1], value, dateT, new TransactionType("to define"));

				List<TransactionType> transactionType = em.createNamedQuery("TransactionType.findAll").getResultList();

				for (TransactionType transactionT : transactionType) {
					if (transactionT.getDescription().equals("to define")) {
						transaction.setTransactionType(transactionT);
					}
				}
				transaction.setAccount(accountCombo.getValue());
				transaction.setPeriodicTransaction(null);
				transaction.setCategory(null);
				transaction.setTargetTransaction(null);

				em.getTransaction().begin();

				em.persist(transaction);
				em.getTransaction().commit();

			}

			em.close();

		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		} catch (NullPointerException e) {

		}

	}

	// TODO nullpointerExecption when the window to choose
	@FXML
	void handleExport(ActionEvent event) throws IOException {

		DirectoryChooser directoryChooser = new DirectoryChooser();
		File selectedDirectory = directoryChooser.showDialog((Stage) exportButton.getScene().getWindow());
		List<Transaction> transactions = accountCombo.getValue().getTransactions();

		try {
			FileWriter fw = new FileWriter(
					new File(selectedDirectory.getAbsolutePath(), accountCombo.getValue().getDescription() + ".csv"));
			for (Transaction l : transactions) {
				// oos.writeChars(l.formatString());
				fw.write(l.formatString());
				fw.flush();
			}
			fw.close();
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		} catch (NullPointerException e) {

		}

	}

}
