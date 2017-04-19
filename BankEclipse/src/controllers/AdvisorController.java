package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.AccountType;
import model.Advisor;
import model.Agency;
import model.Bank;
import model.CountryCode;
import util.PopWindow;

public class AdvisorController implements Initializable {

	@FXML
	public TextField name;
	@FXML
	public TextField firstName;
	@FXML
	public TextField phoneNumber;
	@FXML
	public TextField email;
	@FXML
	public DatePicker assignmentDate;
	@FXML
	public Label invalidName;
	@FXML
	public Label invalidFirstName;
	@FXML
	public Label invalidNumber;
	@FXML
	public Label invalidEmail;
	@FXML
	public Label invalidDate;
	@FXML
	public Label invalidAgency;
	@FXML
	public ComboBox<String> agency;
	@FXML
	public ComboBox<String> bank;
	@FXML
	public Button applyButton;

	private List<Label> labels;

	private List<Node> secondaryFields;

	@FXML
	void applyAdvisorChange(ActionEvent event) {

		// Lambda expression
		labels.forEach(label -> label.setVisible(false));

		Calendar cal =Calendar.getInstance();

		if (agency.getValue() == null) {
			invalidAgency.setVisible(true);
		}

		if (assignmentDate.getValue() == null) {
			invalidDate.setVisible(true);
		} else {
			cal = new GregorianCalendar(assignmentDate.getValue().getYear(),
					assignmentDate.getValue().getMonthValue() - 1, assignmentDate.getValue().getDayOfMonth(), 0, 0, 0);
			if (!Advisor.isValidAssignmentDate(cal.getTime())) {
				invalidDate.setVisible(true);
			}
		}

		if (name.getText().isEmpty() || !Advisor.isValidName(name.getText())) {
			invalidName.setVisible(true);
		}
		if (firstName.getText().isEmpty() || !Advisor.isValidFirstName(firstName.getText())) {
			invalidFirstName.setVisible(true);
		}
		if (phoneNumber.getText().isEmpty() || !Advisor.isValidPhoneNumber(phoneNumber.getText())) {
			invalidNumber.setVisible(true);
		}
		if (email.getText().isEmpty() || !Advisor.isValidEmail(email.getText())) {
			invalidEmail.setVisible(true);
		}

		if (labels.stream().allMatch(label -> label.isVisible() == false)) {
			Advisor advisor = new Advisor(name.getText(), firstName.getText(), phoneNumber.getText(), email.getText(),
					cal.getTime());

		}

	}

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		labels = new ArrayList<Label>() {
			{
				add(invalidAgency);
				add(invalidDate);
				add(invalidEmail);
				add(invalidFirstName);
				add(invalidName);
				add(invalidNumber);
			}
		};
		secondaryFields = new ArrayList<Node>() {
			{
				// addAll(labels);
				add(applyButton);
				add(assignmentDate);
				add(name);
				add(firstName);
				add(phoneNumber);
				add(email);
				add(agency);
			}
		};
		
		EntityManager em = VistaNavigator.getEmf().createEntityManager();
		List<Agency> a = em.createNamedQuery("Agency.findAll").getResultList();
		List<Bank> b = em.createNamedQuery("Bank.findAll").getResultList();
		em.close();
		
		for (Agency  agensy : a){
			agency.getItems().add(agensy.getName());
		}

		agency.getItems().add("OTHER");
		
		for (Bank  bankk : b){
			agency.getItems().add(bankk.getName());
		}

		bank.getItems().add("OTHER");

		this.secondaryFields.forEach(item -> item.setDisable(true));

	}

	@FXML
	void chooseAdvisorBank(ActionEvent event) throws IOException {
		if (bank.getValue() != null) {
			this.secondaryFields.forEach(item -> item.setDisable(false));
		}
		if (bank.getValue().toString() == "OTHER") {
			//TODO refactor
			PopWindow addBankPop = new PopWindow("/viewFxml/addBank.fxml",false,this.getClass());
			//TODO get the new bank and add it to the ComboBox
		}
	}

}
