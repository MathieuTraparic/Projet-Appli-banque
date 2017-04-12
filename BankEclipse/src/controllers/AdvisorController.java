package controllers;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Advisor;

public class AdvisorController {
	
	public TextField name;
	public TextField firstName;
	public TextField phoneNumber;
	public TextField email;
	public DatePicker assignmentDate;	
	
	@FXML
	void applyAdvisorChange(ActionEvent event) {
		
		String err="";
		
		if (!Advisor.isValidName(name.getText())){
			err+="Name is incorrect, must contains only letters and/or spaces, dashes, apostrophe";
		}
		else if (!Advisor.isValidFirstName(firstName.getText())){
			
		}
		
		Calendar cal = new GregorianCalendar(assignmentDate.getValue().getYear(),
				assignmentDate.getValue().getMonthValue(), 
				assignmentDate.getValue().getDayOfMonth(), 0, 0);

				
		Advisor advisor = new Advisor(name.getText(), 
						firstName.getText(), phoneNumber.getText(), email.getText(),
						cal.getTime());

	}

	@FXML
	void chooseAdvisorBank(ActionEvent event) {
		// TODO choose the bank link to an account and advisor
	}



}
