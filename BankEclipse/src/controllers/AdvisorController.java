package controllers;

import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Account;
import model.Advisor;
import model.Agency;
import model.Bank;
import util.Utils;

public class AdvisorController implements Initializable{
	
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
	public ComboBox<String> agency;
	public ComboBox<String> bank;
	
	@FXML
	void applyAdvisorChange(ActionEvent event) {
		
		Utils.settingLabelsInvisible(invalidDate,invalidEmail,invalidFirstName,invalidName,invalidNumber);
		
		Calendar cal;
		
		if (assignmentDate!=null ){
			cal = new GregorianCalendar(assignmentDate.getValue().getYear(),
					assignmentDate.getValue().getMonthValue(), 
					assignmentDate.getValue().getDayOfMonth(), 0, 0, 0);
			if (!Advisor.isValidAssignmentDate(cal.getTime())){
				invalidDate.setVisible(true);
			}
		}
		else {
			invalidDate.setVisible(true);

		}
		if (!Advisor.isValidName(name.getText())){
			invalidName.setVisible(true);
		}
		if (!Advisor.isValidFirstName(firstName.getText())){
			invalidFirstName.setVisible(true);
		}
		if (!Advisor.isValidPhoneNumber(phoneNumber.getText())){
			invalidNumber.setVisible(true);
		}
		if (!Advisor.isValidEmail(email.getText())){
			invalidEmail.setVisible(true);
		}
		
		else{		
			cal = new GregorianCalendar(assignmentDate.getValue().getYear(),
					assignmentDate.getValue().getMonthValue(), 
					assignmentDate.getValue().getDayOfMonth(), 0, 0, 0);
			Advisor advisor = new Advisor(name.getText(), 
							firstName.getText(), phoneNumber.getText(), email.getText(),
							cal.getTime());
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
		// TODO choose the bank link to an account and advisor
	}



}
