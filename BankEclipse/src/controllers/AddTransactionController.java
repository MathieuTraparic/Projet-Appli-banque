package controllers;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import org.kohsuke.rngom.ast.om.ParsedElementAnnotation;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Account;
import model.Bank;
import model.Category;
import model.PeriodicTransaction;
import model.TargetTransaction;
import model.Transaction;
import model.TransactionType;
import util.DateConverter;

public class AddTransactionController extends PopupController<Transaction> implements Initializable {

	@FXML
	public Button transactionCancel, transactionSubmit;
	@FXML
	public TextField descriptionTextField, valueTextField;
	@FXML
	public DatePicker datePicker;
	@FXML
	public Label descriptionError, typeError, valueError, dateError;
	@FXML
	public ComboBox<TransactionType> typeCombo;
	
	//@FXML
	//private ObservableList<Transaction> data;

	private List<TransactionType> transactionType;
	private List<Label> errorLabels;
	private TransactionType otherType;
	

	@FXML
	void handleTransactionCancel(ActionEvent event) {
		Stage stage = (Stage) transactionCancel.getScene().getWindow();
		stage.close();
	}

	@FXML
	void handleTransactionSubmit(ActionEvent event) throws ParseException {

		errorLabels.forEach(label -> label.setVisible(false));

		String des = descriptionTextField.getText();
		Double val = Double.parseDouble(valueTextField.getText());
		Date date = new Date ();
		date = DateConverter.LocalDate2Date(datePicker.getValue());
		String typ = typeCombo.getValue().getDescription();


		if (des.isEmpty()) {
			descriptionError.setVisible(true);
		}
		if (val == null || val.equals("0")) {
			valueError.setVisible(true);
		}
		if (date == null) {
			dateError.setVisible(true);
		}
		if (typ.isEmpty()) {
			typeError.setVisible(true);
		} 
		if (typeCombo.getValue().toString() == "OTHER") {
			// call an Add transaction type popup	
			
		} else {
			
			Stage stage = (Stage) transactionCancel.getScene().getWindow();
			
			TransactionType transactionType = typeCombo.getValue();
					
			this.getData().setDate(date);
			this.getData().setValue(val);;
			this.getData().setDescription(des);
			this.getData().setTransactionType(transactionType);

			this.setAsValidated();
			stage.close();
			System.out.println(date);
		}
	}

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		EntityManager em = VistaNavigator.getEmf().createEntityManager();
		transactionType = em.createNamedQuery("TransactionType.findAll").getResultList();
		em.close();

		otherType = new TransactionType("OTHER");

		for (TransactionType t : transactionType) {
			typeCombo.getItems().add(t);
		}
		typeCombo.getItems().add(otherType);

		this.errorLabels = new ArrayList<Label>() {

			{
				add(descriptionError);
				add(dateError);
				add(valueError);
				add(typeError);

			}
		};
		errorLabels.forEach(label -> label.setVisible(false));
	}

	@Override
	protected void initializePopupFields(Transaction data) {

	}

}
