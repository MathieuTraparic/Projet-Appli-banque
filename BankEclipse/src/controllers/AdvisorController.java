package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AdvisorController {

	@FXML
	void applyAdvisorChange(ActionEvent event){
		
	}
	
	@FXML
	void chooseAdvisorBank(ActionEvent event){
		//TODO choose the bank link to an account and advisor
	}
	
	
	@FXML
	private TextField name;
	
	@FXML
	private TextField firstName;
	
	@FXML
	private TextField phoneNumber;
	
	@FXML
	private TextField email;
	
	@FXML
	private DatePicker assignmentDate;
	
}
