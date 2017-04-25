package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Account;
import model.AccountType;
import model.Agency;
import model.CountryCode;
import util.DateConverter;

public class AddAccountController extends PopupController<Account> implements Initializable {

	@FXML
	public Button addAccountCancel, addAccountSubmit;
	@FXML
	public TextField addAccountNumber, addAccountDescription;
	@FXML
	public TextField addAccountInitBalance, addAccountOverdraft;
	@FXML
	public TextField addAccountThreshold;
	@FXML
	public ComboBox<String> addAccountType;
	@FXML
	public ComboBox<String> addAccountCountryCode;
	@FXML
	public ComboBox<String> addAgency;
	@FXML
	public DatePicker creationDate;
	@FXML
	public Label accountNumberError, accountDescriptionError, accountBalanceError;
	@FXML
	public Label accountOverdraftError, accountAlertError, accountCountryCodeError;
	@FXML
	public Label accountTypeError, accountAgencyError, accountDateError;
	private List<Label> errorLabels;
	private List<Account> list;
	private List<Agency> a = null;
	private List<CountryCode> l = null;
	private List<AccountType> ac = null;

	@FXML
	void handleAddAccountCancel(ActionEvent event) {
		Stage stage = (Stage) addAccountCancel.getScene().getWindow();
		stage.close();
	}

	@FXML
	void handleAddAccountSubmit(ActionEvent event) {

		errorLabels.forEach(label -> label.setVisible(false));

		String number = addAccountNumber.getText();
		String description = addAccountDescription.getText();
		String balance = addAccountInitBalance.getText();
		String overdraft = addAccountOverdraft.getText();
		String threshold = addAccountThreshold.getText();
		String accountType = addAccountType.getValue();
		String countryCode = addAccountCountryCode.getValue();
		String linkedAgency = addAgency.getValue();

		if (number.isEmpty()) {
			accountNumberError.setVisible(true);
		}
		if (description.isEmpty()) {
			accountDescriptionError.setVisible(true);
		}
		if (balance.isEmpty()) {
			accountBalanceError.setVisible(true);
		}
		if (overdraft.isEmpty()) {
			accountOverdraftError.setVisible(true);
		}
		if (threshold.isEmpty()) {
			accountAlertError.setVisible(true);
		}
		if (accountType.isEmpty()) {
			accountTypeError.setVisible(true);
		}
		if (countryCode.isEmpty()) {
			accountTypeError.setVisible(true);
		}
		if (linkedAgency.isEmpty()) {
			accountAgencyError.setVisible(true);
		}
		if (creationDate == null) {
			accountDateError.setVisible(true);
		}
		if (errorLabels.stream().allMatch(label -> !label.isVisible())) {
			EntityManager em = VistaNavigator.getEmf().createEntityManager();
			TypedQuery<Account> q = em.createQuery("SELECT num FROM Account num WHERE num.number=:number",
					Account.class);
			list = q.setParameter("number", number).getResultList();
			if (!list.isEmpty()) {
				accountNumberError.setVisible(true);
			} else {
				Stage stage = (Stage) addAccountSubmit.getScene().getWindow();
				
				int ind = 0;
				for (CountryCode countrycode : l) {
					if (countrycode.getCode() == countryCode) {
						CountryCode code = countrycode;
						this.getData().setCountryCode(code);
					}
					ind++;
				}
				if (ind == l.size()) {
					CountryCode code = new CountryCode(countryCode);
					em.getTransaction().begin();
					em.persist(code);
					em.getTransaction().commit();
				}

				ind = 0;
				for (AccountType accountype : ac) {
					if (accountype.getType() == accountType) {
						AccountType type = accountype;
						this.getData().setAccountType(type);
					}
					ind++;
				}
				if (ind == l.size()) {
					AccountType type = new AccountType(accountType);
					this.getData().setAccountType(type);
					em.getTransaction().begin();
					em.persist(type);
					em.getTransaction().commit();
				}
				
				AccountType type = new AccountType(accountType);

				em.persist(type);

				Agency currentAgency = null;
				for (Agency agency : a) {
					if (agency.getName().equals(this.addAgency.getValue())) {
						currentAgency = agency;
					}
				}

				Date date = DateConverter.LocalDate2Date(creationDate.getValue());

				this.getData().setNumber(number);
				this.getData().setDescription(description);
				this.getData().setInitialBalance(Double.parseDouble(balance));
				this.getData().setOverdraft(Double.parseDouble(overdraft));
				this.getData().setAlertThreshold(Double.parseDouble(threshold));
				this.getData().setAgency(currentAgency);
				this.getData().setCreationDate(date);
				this.setAsValidated();

				stage.close();
			}
			em.close();
		}
	}

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		EntityManager em = VistaNavigator.getEmf().createEntityManager();
		this.l = em.createNamedQuery("CountryCode.findAll").getResultList();
		this.ac = em.createNamedQuery("AccountType.findAll").getResultList();
		this.a = em.createNamedQuery("Agency.findAll").getResultList();
		em.close();

		for (CountryCode countrycode : l) {
			addAccountCountryCode.getItems().add(countrycode.getCode());
		}
		addAccountCountryCode.getItems().add("OTHER");

		for (AccountType type : ac) {
			addAccountType.getItems().add(type.getType());
		}
		addAccountType.getItems().add("OTHER");

		for (Agency agencyName : a) {
			addAgency.getItems().add(agencyName.getName());
		}
		addAgency.getItems().add("OTHER");

		this.errorLabels = new ArrayList<Label>() {
			private static final long serialVersionUID = 6275258056275001066L;
			{
				add(accountNumberError);
				add(accountDescriptionError);
				add(accountBalanceError);
				add(accountOverdraftError);
				add(accountAlertError);
				add(accountCountryCodeError);
				add(accountTypeError);
				add(accountAgencyError);
				add(accountDateError);
			}
		};
		errorLabels.forEach(label -> label.setVisible(false));
	}

	@Override
	protected void initializePopupFields(Account data) {
		// TODO Auto-generated method stub
	}
}
