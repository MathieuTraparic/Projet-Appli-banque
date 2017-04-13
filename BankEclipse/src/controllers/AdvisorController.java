package controllers;

import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Account;
import model.Advisor;
import model.Agency;
import model.Bank;
import util.Utils;

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
	public Label invalidBank;
	@FXML
	public ComboBox<String> agency;
	@FXML
	public ComboBox<String> bank;
	@FXML
	public Button applyButton;

	@FXML
	void applyAdvisorChange(ActionEvent event) {

		Utils.settingLabelsInvisible(invalidAgency, invalidDate, invalidEmail, invalidFirstName, invalidName,
				invalidNumber);

		Calendar cal;

		if (bank.getValue() == null) {
			applyButton.setDisable(true);
			assignmentDate.setDisable(true);
			name.setDisable(true);
			firstName.setDisable(true);
			phoneNumber.setDisable(true);
			email.setDisable(true);
			agency.setDisable(true);

			invalidBank.setVisible(true);
		} else {

			if (agency.getValue() == null) {
				invalidAgency.setVisible(true);
			}

			if (assignmentDate.getValue() == null) {
				invalidDate.setVisible(true);
			} else {
				cal = new GregorianCalendar(assignmentDate.getValue().getYear(),
						assignmentDate.getValue().getMonthValue()-1, assignmentDate.getValue().getDayOfMonth(), 0, 0, 0);
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

			if (!invalidEmail.isVisible() && !invalidDate.isVisible() && !invalidFirstName.isVisible()
					&& !invalidName.isVisible() && !invalidNumber.isVisible() && !invalidAgency.isVisible()
					&& !invalidBank.isVisible()) {
				cal = new GregorianCalendar(assignmentDate.getValue().getYear(),
						assignmentDate.getValue().getMonthValue(), assignmentDate.getValue().getDayOfMonth(), 0, 0, 0);
				Advisor advisor = new Advisor(name.getText(), firstName.getText(), phoneNumber.getText(),
						email.getText(), cal.getTime());
			}
		}

	}

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		for (String l : Agency.getAgency()) {
			agency.getItems().add(l);
		}
		agency.getItems().add("OTHER");
		for (String l : Bank.getBank()) {
			bank.getItems().add(l);
		}
		bank.getItems().add("OTHER");

	}

	@FXML
	void chooseAdvisorBank(ActionEvent event) {
		if (bank.getValue() == null) {
			applyButton.setDisable(true);
			agency.setDisable(true);
			assignmentDate.setDisable(true);
			name.setDisable(true);
			firstName.setDisable(true);
			phoneNumber.setDisable(true);
			email.setDisable(true);
		} else {
			applyButton.setDisable(false);
			agency.setDisable(false);
			assignmentDate.setDisable(false);
			name.setDisable(false);
			firstName.setDisable(false);
			phoneNumber.setDisable(false);
			email.setDisable(false);
		}
		// TODO choose the bank link to an account and advisor
	}

}
