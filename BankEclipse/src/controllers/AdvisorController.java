package controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Account;
import model.Advisor;

public class AdvisorController {

	@FXML
	void applyAdvisorChange(ActionEvent event){
		Advisor advisor = new Advisor(name.getText(), 
				firstName.getText(),
				phoneNumber.getText(),
				email.getText(),
				Date.from(assignmentDate.getValue().toInstant()));
	}
	
	@FXML
	void chooseAdvisorBank(ActionEvent event){
		//TODO choose the bank link to an account and advisor
	}
	

	public TextField name;
	public TextField firstName;
	public TextField phoneNumber;
	public TextField email;
	public DatePicker assignmentDate;
	
}
