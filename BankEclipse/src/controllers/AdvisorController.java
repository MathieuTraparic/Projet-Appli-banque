package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import controllers.popups.PopupController;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import model.Advisor;
import model.Agency;
import model.Bank;
import util.DateConverter;

public class AdvisorController implements Initializable {

	@FXML
	public TextField nameField;
	@FXML
	public TextField firstNameField;
	@FXML
	public TextField phoneNumberField;
	@FXML
	public TextField emailField;
	@FXML
	public DatePicker assignmentDatePicker;
	@FXML
	public Label nameErrorLabel;
	@FXML
	public Label firstNameErrorLabel;
	@FXML
	public Label phoneNumberErrorLabel;
	@FXML
	public Label emailErrorLabel;
	@FXML
	public Label dateErrorLabel;
	@FXML
	public Label agencyErrorLabel;
	@FXML
	public ComboBox<String> agencyCombo;
	@FXML
	public ComboBox<String> bankCombo;
	@FXML
	public Button applyButton;

	private List<Label> errorLabels;
	private List<Node> secondaryFields;
	private List<Agency> listAgency;
	private ChangeListener<? super LocalDate> timeChange = null;
	private List<Bank> allBanks;
	private Boolean isANewAdvisor = false;

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		
		/*
		 * set all Label and fields in ArrayList
		 */
		this.errorLabels = new ArrayList<Label>() {

			private static final long serialVersionUID = 428581192054721416L;

			{
				add(agencyErrorLabel);
				add(dateErrorLabel);
				add(emailErrorLabel);
				add(firstNameErrorLabel);
				add(nameErrorLabel);
				add(phoneNumberErrorLabel);
			}
		};
		this.secondaryFields = new ArrayList<Node>() {

			private static final long serialVersionUID = 3970639145907080465L;

			{
				add(assignmentDatePicker);
				add(nameField);
				add(firstNameField);
				add(phoneNumberField);
				add(emailField);
				add(agencyCombo);
			}
		};

		// fetch all banks from DB
		EntityManager em = VistaNavigator.getEmf().createEntityManager();
		this.allBanks = em.createNamedQuery("Bank.findAll").getResultList();

		em.close();

		// insert all banks in bank comboBox
		allBanks.forEach(bank -> bankCombo.getItems().add(bank.getName()));

		bankCombo.getItems().add("OTHER");
		
		//set secondary label as disable
		this.secondaryFields.forEach(item -> item.setDisable(true));
		this.applyButton.setDisable(true);

	}

	@FXML
	void chooseAdvisorBank(ActionEvent event) throws IOException {
		
		/*
		 * if bank not chosen yet, submit is disable
		 * all textfields are cleared when another bank is chosen
		 */
		applyButton.setDisable(true);
		nameField.clear();
		firstNameField.clear();
		phoneNumberField.clear();
		emailField.clear();
		assignmentDatePicker.setValue(null);

		if (bankCombo.getValue() != null) {
			this.secondaryFields.forEach(item -> item.setDisable(true));
			agencyCombo.setDisable(false);
		}
		if (bankCombo.getValue().toString() == "OTHER") {
			// call an AddBank popup
			PopupController<Bank> controller = PopupController.load(VistaNavigator.ADD_BANK, false);
			controller.show(new Bank("name", "code"), new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					//get the new bank data from the popup
					Bank b = controller.getValidatedData();
					if (b != null) {
						EntityManager em = VistaNavigator.getEmf().createEntityManager();
						em.getTransaction().begin();
						em.persist(b);
						em.getTransaction().commit();
						em.close();
						bankCombo.getItems().add(bankCombo.getItems().size() - 1, b.getName());
					}
				}
			});
			// TODO add the bank in this.allBanks or update it ?
		} else {
			EntityManager em = VistaNavigator.getEmf().createEntityManager();

			Bank currentBank = null;
			// could be avoided if comboBox<Bank> instead of string
			for (Bank bank : allBanks) {
				if (bank.getName().equals(this.bankCombo.getValue())) {
					currentBank = bank;
				}
			}

			// check could be removed as we are selecting from the list of all
			// banks
			if (currentBank != null) {
				// fetch the agencies for this bank from DB
				// TODO maybe fetch them all at initialisation along with
				// this.allbanks ?
				this.agencyCombo.getItems().clear();
				TypedQuery<Agency> a = em.createQuery("SELECT  a FROM Agency a WHERE a.bank =:bank", Agency.class);
				this.listAgency = a.setParameter("bank", currentBank).getResultList();

				// put the agencies obtained in the comboBox
				for (Agency agency : listAgency) {
					agencyCombo.getItems().add(agency.getName());
				}
				agencyCombo.getItems().add("OTHER");
			}
			em.close();
		}
	}

	@FXML
	void chooseAdvisorAgency(ActionEvent event) throws IOException {
		
		/*
		 * when a new agency is chosen, all error labels are hidden
		 *  all textfields are cleared when another bank is chosen
		 */
		errorLabels.forEach(label -> label.setVisible(false));

		applyButton.setDisable(true);
		nameField.clear();
		firstNameField.clear();
		phoneNumberField.clear();
		emailField.clear();
		assignmentDatePicker.setValue(LocalDate.now());

		isANewAdvisor = false;

		if (agencyCombo.getValue() == null) {
			return;
		}

		if (agencyCombo.getValue().toString() == "OTHER") {
			// call popup to create an agency
			PopupController<Agency> controller = PopupController.load(VistaNavigator.ADD_AGENCY, false);
			controller.show(new Agency("name", "counterCode"), new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					//get the new bank data from the popup
					// could be avoid but need a major refactoring
					Agency a = controller.getValidatedData();

					if (a != null) {
						EntityManager em = VistaNavigator.getEmf().createEntityManager();

						em.getTransaction().begin();
						em.persist(a);
						em.getTransaction().commit();
						em.close();
						//update the agency combobox with the newly added agency
						agencyCombo.getItems().add(agencyCombo.getItems().size() - 1, a.getName());
					}
				}
			});

		} else {

			// enable all fields
			this.secondaryFields.forEach(item -> item.setDisable(false));

			//used to compare if there is changes compare to the database
			Agency currentAgency = null;
			for (Agency j : this.listAgency) {
				if (agencyCombo.getValue().toString().equals(j.getName())) {
					currentAgency = j;
				}
			}

			EntityManager em = VistaNavigator.getEmf().createEntityManager();
			TypedQuery<Advisor> a = em.createQuery("SELECT a FROM Advisor a WHERE a.agency=:agency", Advisor.class);

			List<Advisor> l = a.setParameter("agency", currentAgency).getResultList();
			em.close();

			if (isANewAdvisor == false && !l.isEmpty()) {
				Advisor currentAdvisor = l.get(0);

				nameField.setText(currentAdvisor.getName());
				firstNameField.setText(currentAdvisor.getFirstName());
				phoneNumberField.setText(currentAdvisor.getPhoneNumber());
				emailField.setText(currentAdvisor.getEmail());
				assignmentDatePicker.setValue(DateConverter.DateToLocalDate(currentAdvisor.getAssignmentDate()));

				ChangeListener<? super String> onChange = (observable, oldValue, newValue) -> {
					applyButton.setDisable(nameField.getText() == currentAdvisor.getName()
							|| firstNameField.getText() == currentAdvisor.getFirstName()
							|| phoneNumberField.getText() == currentAdvisor.getPhoneNumber()
							|| emailField.getText() == currentAdvisor.getEmail());
				};
				nameField.textProperty().addListener(onChange);
				firstNameField.textProperty().addListener(onChange);
				phoneNumberField.textProperty().addListener(onChange);
				emailField.textProperty().addListener(onChange);

				timeChange = (observable, oldValue, newValue) -> {
					applyButton.setDisable(assignmentDatePicker.getValue() == DateConverter
							.DateToLocalDate(currentAdvisor.getAssignmentDate()));
				};

				assignmentDatePicker.valueProperty().addListener(timeChange);
				
				//could be refactored in a new util class since it is used with other datepickers

				final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
					@Override
					public DateCell call(final DatePicker datePicker) {
						return new DateCell() {
							@Override
							public void updateItem(LocalDate item, boolean empty) {
								super.updateItem(item, empty);

								if (item.isAfter(LocalDate.now())) {
									setDisable(true);
									setStyle("-fx-background-color: #ffc0cb;");
								}
							}
						};
					}
				};
				assignmentDatePicker.setDayCellFactory(dayCellFactory);

			}

			else {
				isANewAdvisor = true;
				applyButton.setDisable(false);
			}

		}
	}

	@FXML
	void applyAdvisorChange(ActionEvent event) {

		// hide all error labels
		errorLabels.forEach(label -> label.setVisible(false));

		Calendar cal = Calendar.getInstance();

		EntityManager em = VistaNavigator.getEmf().createEntityManager();

		Agency currentAgency = null;
		for (Agency a : this.listAgency) {
			if (agencyCombo.getValue() == a.getName()) {
				currentAgency = a;
			}
		}

		if (agencyCombo.getValue() == null) {
			agencyErrorLabel.setVisible(true);
		}

		if (assignmentDatePicker.getValue() == null) {
			dateErrorLabel.setVisible(true);
		} else {
			cal = new GregorianCalendar(assignmentDatePicker.getValue().getYear(),
					assignmentDatePicker.getValue().getMonthValue() - 1,
					assignmentDatePicker.getValue().getDayOfMonth(), 0, 0, 0);
			if (!Advisor.isValidAssignmentDate(cal.getTime())) {
				dateErrorLabel.setVisible(true);
			}
		}

		if (nameField.getText().isEmpty() || !Advisor.isValidName(nameField.getText())) {
			nameErrorLabel.setVisible(true);
		}
		if (firstNameField.getText().isEmpty() || !Advisor.isValidFirstName(firstNameField.getText())) {
			firstNameErrorLabel.setVisible(true);
		}
		if (phoneNumberField.getText().isEmpty() || !Advisor.isValidPhoneNumber(phoneNumberField.getText())) {
			phoneNumberErrorLabel.setVisible(true);
		}
		if (emailField.getText().isEmpty() || !Advisor.isValidEmail(emailField.getText())) {
			emailErrorLabel.setVisible(true);
		}

		if (isANewAdvisor == false && errorLabels.stream().allMatch(label -> !label.isVisible())) {

			em.getTransaction().begin();

			Query q = em.createQuery(
					"UPDATE Advisor a SET a.name=:name, a.firstName=:firstName, a.phoneNumber=:phoneNumber, "
							+ "a.email=:email, a.assignmentDate=:assignmentDate WHERE a.agency=:agency");
			q.setParameter("agency", currentAgency);
			q.setParameter("name", nameField.getText());
			q.setParameter("firstName", firstNameField.getText());
			q.setParameter("phoneNumber", phoneNumberField.getText());
			q.setParameter("email", emailField.getText());
			q.setParameter("assignmentDate", cal.getTime());
			q.executeUpdate();

			em.getTransaction().commit();

			em.close();

			// TODO show a message that indicate the change was successful or
			// not
			// I just added the next line to show it, I can add a label in the
			// view as well but
			// I don't see this as mandatory
			applyButton.setDisable(true);

		}
		if (isANewAdvisor == true && errorLabels.stream().allMatch(label -> !label.isVisible())) {
			Advisor newAdvisor = new Advisor(nameField.getText(), firstNameField.getText(), phoneNumberField.getText(),
					emailField.getText(), cal.getTime());
			newAdvisor.setAgency(currentAgency);

			em.getTransaction().begin();
			em.persist(newAdvisor);
			em.getTransaction().commit();
			em.close();

			isANewAdvisor = false;
			applyButton.setDisable(true);
		}

	}
}
