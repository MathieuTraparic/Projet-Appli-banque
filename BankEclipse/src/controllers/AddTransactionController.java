package controllers;

import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Bank;
import model.Category;
import model.TargetTransaction;
import model.Transaction;
import model.TransactionType;
import util.DateConverter;

public class AddTransactionController extends PopupController<Transaction> implements Initializable {

	@FXML
	public Button transactionCancel, transactionSubmit;
	@FXML
	public TextField descriptionTextField, valueTextField, newTargetIBANTextField, newTargetSummaryTextField,
			newCatgoryTextField;
	@FXML
	public DatePicker datePicker;
	@FXML
	public Label descriptionError, typeError, valueError, categoryOther, targetOther, dateError, IBANTargetError,
			descriptionTargetError, categoryNameError;
	@FXML
	public ComboBox<TransactionType> typeCombo;
	@FXML
	public ComboBox<Category> categoryCombo;
	@FXML
	public ComboBox<Category> categoryParentCombo;
	@FXML
	public ComboBox<TargetTransaction> targetCombo;

	// @FXML
	// private ObservableList<Transaction> data;

	private List<TransactionType> transactionType;
	private List<Category> categoryList;
	private List<TargetTransaction> targetList;
	private List<Label> errorLabels;
	private List<TextField> newTextfields;

	private static final String NEW_TARGET = "New target";
	private static final String NEW_CATEGORY = "New category";
	private static final String NEW_TYPE = "New type";

	@FXML
	void handleTransactionCancel(ActionEvent event) {
		Stage stage = (Stage) transactionCancel.getScene().getWindow();
		stage.close();
	}

	@FXML
	void handleTransactionSubmit(ActionEvent event) throws ParseException {

		errorLabels.forEach(label -> label.setVisible(false));

		Category cat;
		TargetTransaction tar;

		String des = descriptionTextField.getText();
		Double val = Double.parseDouble(valueTextField.getText());
		Date date = new Date();
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
		
		//if (isANewAdvisor == false && errorLabels.stream().allMatch(label -> !label.isVisible())) {

		else {

			Stage stage = (Stage) transactionCancel.getScene().getWindow();

			TransactionType transactionType = typeCombo.getValue();

			cat = categoryCombo.getValue();

			tar = targetCombo.getValue();
			
			if (cat!=null){
				if (categoryCombo.getValue().getDescription().equals(NEW_CATEGORY)) {
					Category newCat = new Category(newCatgoryTextField.getText());

					EntityManager em = VistaNavigator.getEmf().createEntityManager();

					List<Category> catList = em.createNamedQuery("Category.findAll").getResultList();

					for (Category ca : catList) {
						if (ca.getDescription().equals(newCat.getDescription())) {
							categoryNameError.setVisible(true);
						} else {
							if (categoryParentCombo.getValue() != null) {

								newCat.setParentCategory(categoryParentCombo.getValue());
							}

							em.getTransaction().begin();
							em.persist(newCat);
							em.getTransaction().commit();

							cat = newCat;

						}
					}
					this.getData().setCategory(cat);
					em.close();

				}else{
					this.getData().setCategory(cat);
				}
			}
			
			if (tar!=null){
				if (targetCombo.getValue().getSummary().equals(NEW_TARGET)) {
					TargetTransaction newTar= new TargetTransaction(newTargetSummaryTextField.getText(), newTargetIBANTextField.getText());

					EntityManager em = VistaNavigator.getEmf().createEntityManager();

					List<TargetTransaction> tarList = em.createNamedQuery("TargetTransaction.findAll").getResultList();

					for (TargetTransaction ta : tarList) {
						if (ta.getIban().equals(newTar.getIban())) {
							IBANTargetError.setVisible(true);
						} else {

							em.getTransaction().begin();
							em.persist(newTar);
							em.getTransaction().commit();

							tar = newTar;

						}
					}
					this.getData().setTargetTransaction(tar);
					em.close();
				}
				else {
					this.getData().setTargetTransaction(tar);
				}
			}


			this.getData().setDate(date);
			this.getData().setValue(val);
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

		TransactionType otherType = new TransactionType(NEW_TYPE);
		Category otherCategory = new Category(NEW_CATEGORY);
		TargetTransaction otherTarget = new TargetTransaction(NEW_TARGET);

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

		this.newTextfields = new ArrayList<TextField>() {

			{
				add(newTargetIBANTextField);
				add(newTargetSummaryTextField);
				add(newCatgoryTextField);
			}
		};

		newTextfields.forEach(textfield -> textfield.setDisable(true));

		categoryParentCombo.setDisable(true);

		typeCombo.valueProperty().addListener((obs, oldV, newV) -> {

			boolean b = !newV.getDescription().equals(NEW_TYPE);

		});

		targetCombo.valueProperty().addListener((obs, oldV, newV) -> {

			boolean b = !newV.getSummary().equals(NEW_TARGET);

			newTargetIBANTextField.setDisable(b);
			newTargetSummaryTextField.setDisable(b);

		});

		categoryCombo.valueProperty().addListener((obs, oldV, newV) -> {

			boolean b = !newV.getDescription().equals(NEW_CATEGORY);

			newCatgoryTextField.setDisable(b);
			categoryParentCombo.setDisable(b);

		});

	}

	@Override
	protected void initializePopupFields(Transaction data) {

	}

}
