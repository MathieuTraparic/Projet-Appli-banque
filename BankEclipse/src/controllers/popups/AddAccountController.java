package controllers.popups;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import controllers.VistaNavigator;
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
	
	/*
	 * Class variables
	 */
	@FXML
	public Button addAccountCancel, addAccountSubmit;
	@FXML
	public TextField addAccountNumber, addAccountDescription;
	@FXML
	public TextField addAccountInitBalance, addAccountOverdraft, addAccountInterestRate, addAccountAgioRate;
	@FXML
	public TextField addAccountThreshold;
	@FXML
	public TextField createCountryCode, createAccountType;
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

	/*
	 * (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

		// Disable by default this two textfield. Theese are enable on event
		this.createCountryCode.setDisable(true);
		this.createAccountType.setDisable(true);
		
		// Communication with the data base to initialize lists
		EntityManager em = VistaNavigator.getEmf().createEntityManager();
		this.l = em.createNamedQuery("CountryCode.findAll").getResultList();
		this.ac = em.createNamedQuery("AccountType.findAll").getResultList();
		this.a = em.createNamedQuery("Agency.findAll").getResultList();
		em.close();

		// Fill the combobox with data extracted from the data base
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

		/*
		 * Initialization of the error labels
		 * The messages are defined in fxml of this controller AddAccount.fxml
		 */
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
	
	/**
	 * @param event : Managemnent of the event on the Cancel button
	 */
	@FXML
	void handleAddAccountCancel(ActionEvent event) {
		Stage stage = (Stage) addAccountCancel.getScene().getWindow();
		stage.close();
	}

	/**
	 * @param event : By clicking on Submit, the event saves the new account. The commit in the
	 * data base is set on the HomeController 
	 */
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
		String agio = addAccountAgioRate.getText();
		String interest = addAccountInterestRate.getText();
		
		// Tests to verify if the fields are filled
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
			// Query to test the existence of data in the database
			EntityManager em = VistaNavigator.getEmf().createEntityManager();
			TypedQuery<Account> q = em.createQuery("SELECT num FROM Account num WHERE num.number=:number",
					Account.class);
			list = q.setParameter("number", number).getResultList();
			if (!list.isEmpty()) {
				accountNumberError.setVisible(true);
			} else {
				// Test to create or not a new AccountType during the creation of a new Account
				if (!accountType.equals("OTHER")) {
					AccountType type = null;
					for (AccountType accountype : ac) {
						if (accountype.getType() == accountType) {
							type = accountype;
							this.getData().setAccountType(type);
						}
					}
				} else {
					AccountType type = new AccountType(createAccountType.getText());
					this.getData().setAccountType(type);
					em.getTransaction().begin();
					em.persist(type);
					em.getTransaction().commit();
				}
				// Test to create or not a new CountryCode during the creation of a new Account
				if (!countryCode.equals("OTHER")) {
					CountryCode code = null;
					for (CountryCode countrycode : l) {
						if (countrycode.getCode() == countryCode) {
							code = countrycode;
							this.getData().setCountryCode(code);
						}
					}
				} else {
					CountryCode code = new CountryCode(createCountryCode.getText());
					this.getData().setCountryCode(code);
					em.getTransaction().begin();
					em.persist(code);
					em.getTransaction().commit();
				}
				// Selection of the Agency in the database
				Agency currentAgency = null;
				for (Agency agency : a) {
					if (agency.getName().equals(this.addAgency.getValue())) {
						currentAgency = agency;
					}
				}

				Date date = DateConverter.LocalDate2Date(creationDate.getValue());
				// Data saving. The commit in the database is set in HomeController
				this.getData().setNumber(number);
				this.getData().setDescription(description);
				this.getData().setInitialBalance(Double.parseDouble(balance));
				this.getData().setOverdraft(Double.parseDouble(overdraft));
				this.getData().setAlertThreshold(Double.parseDouble(threshold));
				this.getData().setAgency(currentAgency);
				this.getData().setCreationDate(date);
				this.getData().setAgioRate(Double.parseDouble(agio));
				this.getData().setInterestRate(Double.parseDouble(interest));
				this.setAsValidated();

				Stage stage = (Stage) addAccountSubmit.getScene().getWindow();
				stage.close();
			}
			em.close();
		}
	}

	/*
	 * These two textfields createCountryCode and addAccountType 
	 * are invisible by default. These two events change the status 
	 * of the textfields.
	 */
	@FXML
	void handleOtherCode(ActionEvent event) {
		if (this.addAccountCountryCode.getValue().toString().equals("OTHER")) {
			this.createCountryCode.setDisable(false);
		}
	}

	@FXML
	void handleOtherType(ActionEvent event) {
		if (this.addAccountType.getValue().toString().equals("OTHER")) {
			this.createAccountType.setDisable(false);
		}
	}
}
