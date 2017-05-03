package controllers;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import controllers.popups.PopupController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.WindowEvent;
import javafx.util.converter.DoubleStringConverter;
import model.Account;
import model.AccountType;
import model.Agency;
import model.Bank;
import model.Category;
import model.CountryCode;
import model.Owner;
import model.TargetTransaction;
import model.TransactionType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;

public class HomeController extends BankSelector implements Initializable {

	@FXML
	TableView<Account> accountView;
	@FXML
	LineChart<String, Number> chart;
	@FXML
	CategoryAxis xDateAxis;
	@FXML
	NumberAxis yBalanceAxis;
	@FXML Button removeButton;
	//@FXML Button editButton;
	@FXML TableColumn<Account, String> descriptionCol;
	@FXML TableColumn<Account, Double> interestCol;
	@FXML TableColumn<Account, Double> alertCol;
	@FXML TableColumn<Account, CountryCode> countryCol;
	@FXML TableColumn<Account, AccountType> typeCol;
	@FXML TableColumn<Account, Agency> agencyCol;

	@FXML
	void handleAddBankHome(ActionEvent event) throws IOException {
		PopupController<Bank> controller = PopupController.load(VistaNavigator.ADD_BANK, false);
		controller.show(new Bank("name", "code"), new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				Bank addedBank = controller.getValidatedData();
				if (addedBank != null) {
					EntityManager em = VistaNavigator.getEmf().createEntityManager();
					em.getTransaction().begin();
					em.persist(addedBank);
					em.getTransaction().commit();
					//em.getEntityManagerFactory().getCache().evictAll();
					em.close();
				}
			}
		});
	}

	@FXML
	void handleAddAgencyHome(ActionEvent event) throws IOException {
		PopupController<Agency> controller = PopupController.load(VistaNavigator.ADD_AGENCY, false);
		controller.show(new Agency("name", "counterCode"), new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				Agency a = controller.getValidatedData();
				if (a != null) {
					EntityManager em = VistaNavigator.getEmf().createEntityManager();

					em.getTransaction().begin();
					em.persist(a);
					em.getTransaction().commit();
					em.close();
				}
			}
		});
	}

	@FXML
	void handleAddAccountHome(ActionEvent event) throws IOException {
		PopupController<Account> controller = PopupController.load(VistaNavigator.ADD_ACCOUNT, true);
		controller.show(new Account("number", "description", 0d, -150d, 0d), new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				Account account = controller.getValidatedData();
				if (account != null) {
					List<Owner> owners =new ArrayList<>();
					owners.add(VistaNavigator.getInstance().getLoggedOwner());
					account.setOwners(owners);
					EntityManager em = VistaNavigator.getEmf().createEntityManager();
					em.getTransaction().begin();
					em.persist(account);
					em.getTransaction().commit();
					//accountCo.getItems().add(bankCombo.getItems().size() - 1, addedBank);
					em.close();
				}
			}
		});
	}

	@FXML
	void handleBankChoiceHome(ActionEvent event) {
		// get a bank specific subset of all the account from the owner
		List<Account> accountFromCurrentBank = new ArrayList<Account>();
		for (Account account : accountsOwned) {
			if (account.getAgency().getBank().equals(bankCombo.getValue())) {
				accountFromCurrentBank.add(account);
			}
		}
		
		this.accountView.setItems(FXCollections.observableList(accountFromCurrentBank));
	}

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		super.initialize(fxmlFileLocation, resources);

		//fetch lists of possible edit for account attributes
		EntityManager em = VistaNavigator.getEmf().createEntityManager();
		ObservableList<CountryCode> countryCodes = FXCollections.observableList(em.createNamedQuery("CountryCode.findAll", CountryCode.class).getResultList());
		ObservableList<AccountType> types = FXCollections.observableList(em.createNamedQuery("AccountType.findAll", AccountType.class).getResultList());
		ObservableList<Agency> agencies = FXCollections.observableList(em.createNamedQuery("Agency.findAll", Agency.class).getResultList());
		em.close();
		
		this.descriptionCol.setCellFactory(TextFieldTableCell.forTableColumn());
		this.interestCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		this.alertCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		this.countryCol.setCellFactory(ComboBoxTableCell.forTableColumn(countryCodes));
		this.typeCol.setCellFactory(ComboBoxTableCell.forTableColumn(types));
		this.agencyCol.setCellFactory(ComboBoxTableCell.forTableColumn(agencies));
		
		//TODO find a way to refactor the setOnEditCommit
		this.descriptionCol.setOnEditCommit(
				t ->{
					EntityManager eman = VistaNavigator.getEmf().createEntityManager();
					Account a = t.getTableView().getItems().get(t.getTablePosition().getRow());
					a.setDescription(t.getNewValue());
					eman.getTransaction().begin();
					eman.merge(a);
					eman.getTransaction().commit();
					eman.close();
				});		
		this.interestCol.setOnEditCommit(
				t ->{
					EntityManager eman = VistaNavigator.getEmf().createEntityManager();
					Account a = t.getTableView().getItems().get(t.getTablePosition().getRow());
					a.setInterestRate(t.getNewValue());
					eman.getTransaction().begin();
					eman.merge(a);
					eman.getTransaction().commit();
					eman.close();
				});		
		this.alertCol.setOnEditCommit(
				t ->{
					EntityManager eman = VistaNavigator.getEmf().createEntityManager();
					Account a = t.getTableView().getItems().get(t.getTablePosition().getRow());
					a.setAlertThreshold(t.getNewValue());
					eman.getTransaction().begin();
					eman.merge(a);
					eman.getTransaction().commit();
					eman.close();
				});		
				
		this.countryCol.setOnEditCommit(
				t ->{
					EntityManager eman = VistaNavigator.getEmf().createEntityManager();
					Account a = t.getTableView().getItems().get(t.getTablePosition().getRow());
					a.setCountryCode(t.getNewValue());
					eman.getTransaction().begin();
					eman.merge(a);
					eman.getTransaction().commit();
					eman.close();
				});		
				
		this.typeCol.setOnEditCommit(
				t ->{
					EntityManager eman = VistaNavigator.getEmf().createEntityManager();
					Account a = t.getTableView().getItems().get(t.getTablePosition().getRow());
					a.setAccountType(t.getNewValue());
					eman.getTransaction().begin();
					eman.merge(a);
					eman.getTransaction().commit();
					eman.close();
				});		
		this.agencyCol.setOnEditCommit(
				t ->{
					EntityManager eman = VistaNavigator.getEmf().createEntityManager();
					Account a = t.getTableView().getItems().get(t.getTablePosition().getRow());
					a.setAgency(t.getNewValue());
					eman.getTransaction().begin();
					eman.merge(a);
					eman.getTransaction().commit();
					eman.close();
				});		
		
		
		// on selectedAccount, draw a lineChart
		accountView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
			removeButton.setDisable(newValue == null);
			removeButton.setVisible(newValue != null);
			if (newValue != null) {
				
				Account selectedAccount = this.accountView.getSelectionModel().getSelectedItem();
				

				// populate the lineChart

				Series<String, Number> series = new Series<>();
				series.setName(selectedAccount.getDescription());

				// convert from the model List of couples to serie of Data
				List<Entry<Double, Date>> couples = selectedAccount.getBalanceHistory();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				for (Entry<Double, Date> entry : couples) {
					series.getData().add(new Data<String, Number>(format.format(entry.getValue()), entry.getKey()));
				}

				// if the series is not already in the chart, add it
				if (!this.chart.getData().stream()
						.anyMatch(s -> s.getName().equals(selectedAccount.getDescription()))) {
					this.chart.setData(FXCollections.observableArrayList(series));
					// this.chart.getData().add(series);
				}

			}
		}));
	}



	@FXML public void removeAccount(ActionEvent event) {
		Account selectedAccount = this.accountView.getSelectionModel().getSelectedItem();
		this.accountView.getSelectionModel().clearSelection();
		this.accountView.getItems().remove(selectedAccount);
		this.chart.getData().clear();
		EntityManager em = VistaNavigator.getEmf().createEntityManager();
		//not sure if necessary
		Account a =em.find(Account.class, selectedAccount.getId());
		em.getTransaction().begin();
		em.remove(a);
		em.getTransaction().commit();
		
	}

}
