package controllers;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Advisor;

public class AdvisorController {
	
	public TextField name;
	public TextField firstName;
	public TextField phoneNumber;
	public TextField email;
	public DatePicker assignmentDate;
	public Label invalidName;
	public Label invalidFirstName;
	public Label invalidNumber;
	public Label invalidEmail;
	
	@FXML
	void applyAdvisorChange(ActionEvent event) {
		
		Calendar cal = new GregorianCalendar(assignmentDate.getValue().getYear(),
				assignmentDate.getValue().getMonthValue(), 
				assignmentDate.getValue().getDayOfMonth(), 0, 0);
		
		if (!Advisor.isValidName(name.getText())){
			invalidName.setText("Name is incorrect, must contains only letters and/or spaces, dashes, apostrophe");
		}
		else if (!Advisor.isValidFirstName(firstName.getText())){
			invalidFirstName.setText("First name is incorrect, must contains only letters and/or spaces, dashes, apostrophe");
		}
		else if (!Advisor.isValidPhoneNumber(phoneNumber.getText())){
			invalidNumber.setText("Phone number must be a valid French phone number composed of 10 digits");
		}
		else if (!Advisor.isValidEmail(email.getText())){
			invalidEmail.setText("Email must be a valid email address");
		}
		else if (!Advisor.isValidAssignmentDate(cal.getTime())){
			invalidEmail.setText("Email must be a valid email address");
		}
		else{		
		Advisor advisor = new Advisor(name.getText(), 
						firstName.getText(), phoneNumber.getText(), email.getText(),
						cal.getTime());
		}
	}

	@FXML
	void chooseAdvisorBank(ActionEvent event) {
		// TODO choose the bank link to an account and advisor
	}



}
