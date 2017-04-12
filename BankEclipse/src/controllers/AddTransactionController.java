package controllers;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Account;
import model.Transaction;

public class AddTransactionController implements Initializable{
	
	public Button transactionCancel, transactionSubmit;
	public TextField description, value, date;
	public Label descriptionError, typeError, valueError, dateError;
	public ComboBox<String> type;
	
	@FXML
	void handleTransactionCancel(ActionEvent event){
		Stage stage = (Stage) transactionCancel.getScene().getWindow();
		stage.close();
	}

	@FXML
	void handleTransactionSubmit(ActionEvent event){
		String des = description.getText();
		String val = value.getText();
		String d = date.getText();
		SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");

		if(des.isEmpty()){
			descriptionError.setText("Description can't be empty");
		}
		if(val.isEmpty()){
			valueError.setText("Description can't be empty");
		}
		if(d.isEmpty()){
			dateError.setText("Description can't be empty");
		}else{
			try {
				Date dt = dateParser.parse(d);
				Transaction transaction = new Transaction(des,type.getAccessibleText(),Double.parseDouble(val),dt);
				Stage stage = (Stage) transactionCancel.getScene().getWindow();
				stage.close();
			} catch ( ParseException e ) {
			    // Error
			}
		}
	}
	
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		type.getItems().setAll("Money", "Card","Bill", "Transaction");
	}
}
