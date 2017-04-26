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
	public TextField descriptionTextField, valueTextField,
			newTargetIBANTextField, newTargetSummaryTextField, newCatgoryTextField;
	@FXML
	public DatePicker datePicker;
	@FXML
	public Label descriptionError, typeError, valueError, categoryOther, targetOther,
			dateError, IBANTargetError, descriptionTargetError,categoryNameError ;
	@FXML
	public ComboBox<TransactionType> typeCombo;
	@FXML
	public ComboBox<Category> categoryCombo;
	@FXML
	public ComboBox<Category> categoryParentCombo;
	@FXML
	public ComboBox<TargetTransaction> targetCombo;
	
	//@FXML
	//private ObservableList<Transaction> data;

	private List<TransactionType> transactionType;
	private List<Category> categoryList;
	private List<TargetTransaction> targetList;
	private List<Label> errorLabels;
	private List<TextField> errorTextfields;
	

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
			
			Category cat = categoryCombo.getValue();
			TargetTransaction tar = targetCombo.getValue();
			
			if (cat!=null){
				this.getData().setCategory(cat);
			}
			if (tar!=null){
				this.getData().setTargetTransaction(tar);
			}
			
			this.getData().setDate(date);
			this.getData().setValue(val);;
			this.getData().setDescription(des);
			this.getData().setTransactionType(transactionType);

			this.setAsValidated();
			stage.close();

		}
	}

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		EntityManager em = VistaNavigator.getEmf().createEntityManager();
		transactionType = em.createNamedQuery("TransactionType.findAll").getResultList();
		categoryList = em.createNamedQuery("Category.findAll").getResultList();
		targetList = em.createNamedQuery("TargetTransaction.findAll").getResultList();
		em.close();
		
		TransactionType otherType= new TransactionType("OTHER");;
		Category otherCategory = new Category("OTHER");
		TargetTransaction otherTarget = new TargetTransaction("OTHER");


		for (TransactionType t : transactionType) {
			typeCombo.getItems().add(t);
		}
		typeCombo.getItems().add(otherType);
		
		for (Category t : categoryList) {
			categoryCombo.getItems().add(t);
		}
		categoryCombo.getItems().add(otherCategory);
		
		for (TargetTransaction t : targetList) {
			targetCombo.getItems().add(t);
		}
		targetCombo.getItems().add(otherTarget);
		
		for (Category t : categoryList) {
			categoryParentCombo.getItems().add(t);
		}
		

		this.errorLabels = new ArrayList<Label>() {

			{
				add(descriptionError);
				add(dateError);
				add(valueError);
				add(typeError);
				add(IBANTargetError);
				add(descriptionTargetError);
				add(categoryNameError);

			}
		};
		errorLabels.forEach(label -> label.setVisible(false));
		
		this.errorTextfields = new ArrayList<TextField>() {

			{
				add(newTargetIBANTextField);
				add(newTargetSummaryTextField);
				add(newCatgoryTextField);
			}
		};
		
		errorTextfields.forEach(textfields -> textfields.setDisable(true));
		
		categoryParentCombo.setDisable(true);
	}

	@Override
	protected void initializePopupFields(Transaction data) {

	}

}
