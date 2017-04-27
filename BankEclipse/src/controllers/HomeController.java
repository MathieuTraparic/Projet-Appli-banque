package controllers;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Date;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.swing.text.DateFormatter;

import controllers.popups.PopupController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.WindowEvent;
import model.Account;
import model.Agency;
import model.Bank;
import model.Owner;
import javafx.scene.control.TableView;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.chart.XYChart.Data;

public class HomeController extends BankSelector implements Initializable {

	@FXML
	TableView<Account> accountView;
	@FXML
	LineChart<String, Number> chart;
	@FXML
	CategoryAxis xDateAxis;
	@FXML
	NumberAxis yBalanceAxis;

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

				// Actually I don't get the idea why we do this here and not
				// when we submit in the popup window
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
					List<Owner> l =new ArrayList<>();
					l.add(VistaNavigator.getInstance().getLoggedOwner());
					account.setOwners(l);
					EntityManager em = VistaNavigator.getEmf().createEntityManager();
					em.getTransaction().begin();
					em.persist(account);
					em.getTransaction().commit();
					em.close();
				}
			}
		});
	}

	@FXML
	void handleBankChoiceHome(ActionEvent event) {
		// get a bank specific subset of all the account from the owner
		List<Account> accountFromCurrentBank = new ArrayList<Account>();

		this.accountsOwned.forEach(account -> {
			if (account.getAgency().getBank().equals(bankCombo.getValue())) {
				accountFromCurrentBank.add(account);
			}
		});
		this.accountView.setItems(FXCollections.observableList(accountFromCurrentBank));

	}

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		super.initialize(fxmlFileLocation, resources);
		// on selectedAccount
		accountView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
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
}
