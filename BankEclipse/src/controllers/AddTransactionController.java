package controllers;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Account;
import model.Transaction;

public class AddTransactionController implements Initializable{
	
	@FXML public Button transactionCancel, transactionSubmit;
	@FXML public static TextField description;
	@FXML public static TextField value;
	@FXML public static TextField date;
	@FXML public Label descriptionError, typeError, valueError, dateError;
	@FXML public static ComboBox<String> type;
	@FXML private ObservableList<Transaction> data;
	
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
				Stage stage = (Stage) transactionCancel.getScene().getWindow();
				stage.close();
			} catch ( ParseException e ) {
			    // Error
			}
		}
	}
	
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		for (String l : Transaction.getTypes()) {
			type.getItems().add(l);	
		}
		type.getItems().add("OTHER");
	}
	
	public static Transaction getTransaction(){
		SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");
		Date dt = null;
		try {
			dt = dateParser.parse(date.getText());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Transaction(description.getText(),type.getValue(),Double.parseDouble(value.getText()),dt);
	}
}
