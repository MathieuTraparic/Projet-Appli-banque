package controllers;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Transaction;
import model.TransactionType;

public class AddTransactionController extends PopupController<Transaction> implements Initializable {

	@FXML
	public Button transactionCancel, transactionSubmit;
	@FXML
	public TextField description, value, date;
	@FXML
	public Label descriptionError, typeError, valueError, dateError;
	@FXML
	public ComboBox<String> type;
	@FXML
	private ObservableList<Transaction> data;
	TransactionType transactionType;
	
	@FXML
	void handleTransactionCancel(ActionEvent event) {
		Stage stage = (Stage) transactionCancel.getScene().getWindow();
		stage.close();
	}

	@FXML
	void handleTransactionSubmit(ActionEvent event) throws ParseException {
		String des = description.getText();
		String val = value.getText();
		String d = date.getText();
		String typ = type.getValue();
		SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");

		if (des.isEmpty()) {
			descriptionError.setText("Description can't be empty");
		}
		if (val.isEmpty()) {
			valueError.setText("Description can't be empty");
		}
		if (d.isEmpty()) {
			dateError.setText("Description can't be empty");
		} else {
			Date dt = dateParser.parse(d);
			Stage stage = (Stage) transactionCancel.getScene().getWindow();
			this.getData().setDescription(des);
			this.getData().setDate(dt);
			this.getData().setValue(Double.parseDouble(val));
			transactionType = new TransactionType(typ);
			this.getData().setTransactionType(transactionType);
			this.setAsValidated();
			stage.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		EntityManager em = VistaNavigator.getEmf().createEntityManager();
		List<TransactionType> l = em.createNamedQuery("TransactionType.findAll").getResultList();
		em.close();
		for(TransactionType t : l){
			type.getItems().add(t.getDescription());
		}
		type.getItems().add("OTHER");
	}

	@Override
	protected void initializePopupFields(Transaction data) {
		
	}
	
}
