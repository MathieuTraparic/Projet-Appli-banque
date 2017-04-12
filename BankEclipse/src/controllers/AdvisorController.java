package controllers;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Advisor;
import util.Utils;

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
	public Label invalidDate;
	
	@FXML
	void applyAdvisorChange(ActionEvent event) {
		
		Utils.settingLabelsInvisible(invalidDate,invalidEmail,invalidFirstName,invalidName,invalidNumber);

		
		if (!Advisor.isValidName(name.getText())){
			invalidName.setVisible(true);
		}
		else if (!Advisor.isValidFirstName(firstName.getText())){
			invalidFirstName.setVisible(true);
		}
		else if (!Advisor.isValidPhoneNumber(phoneNumber.getText())){
			invalidNumber.setVisible(true);
		}
		else if (!Advisor.isValidEmail(email.getText())){
			invalidEmail.setVisible(true);
		}
		else if (!Advisor.isValidAssignmentDate(cal.getTime())){
			invalidDate.setVisible(true);
		}
		else{		
		Calendar cal = new GregorianCalendar(assignmentDate.getValue().getYear(),
					assignmentDate.getValue().getMonthValue(), 
					assignmentDate.getValue().getDayOfMonth(), 0, 0, 0);
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
