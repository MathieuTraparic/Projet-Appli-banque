package controllers.popups;

import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import controllers.VistaNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Account;
import model.Category;
import model.Owner;
import model.TargetTransaction;
import model.Transaction;
import model.TransactionType;
import util.DateConverter;
import util.Validator;

/**
 * @author Group
 * This controller allow to create a new Transaction
 */
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

	private List<TransactionType> transactionType;
	private List<Category> categoryList;
	private List<TargetTransaction> targetList;
	private List<Label> errorLabels;
	private List<TextField> newTextfields;

	private static final String NEW_TARGET = "New target";
	private static final String NEW_CATEGORY = "New category";
	private static final String NEW_TYPE = "New type";

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		// Initialization of the list with data extracted from the database
		EntityManager em = VistaNavigator.getEmf().createEntityManager();
		transactionType = em.createNamedQuery("TransactionType.findAll").getResultList();
		categoryList = em.createNamedQuery("Category.findAll").getResultList();
		targetList = em.createNamedQuery("TargetTransaction.findAll").getResultList();
		em.close();
		
		TransactionType otherType = new TransactionType(NEW_TYPE);
		Category otherCategory = new Category(NEW_CATEGORY);
		TargetTransaction otherTarget = new TargetTransaction(NEW_TARGET);

		/*
		 * Loading the combobox data
		 */
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
		/*
		 * Setting all the errorLabels and the items to create a new target or
		 * category to non-visible
		 */
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
		targetOther.setVisible(false);
		categoryOther.setVisible(false);

		this.newTextfields = new ArrayList<TextField>() {
			{
				add(newTargetIBANTextField);
				add(newTargetSummaryTextField);
				add(newCatgoryTextField);
			}
		};
		newTextfields.forEach(textfield -> textfield.setDisable(true));
		categoryParentCombo.setDisable(true);
		/*
		 * Listener on the combo box to know if a new category or target or type
		 * should be added
		 */

		typeCombo.valueProperty().addListener((obs, oldV, newV) -> {
			boolean b = !newV.getDescription().equals(NEW_TYPE);
		});

		targetCombo.valueProperty().addListener((obs, oldV, newV) -> {
			boolean b = !newV.getSummary().equals(NEW_TARGET);
			targetOther.setVisible(!b);
			newTargetIBANTextField.setDisable(b);
			newTargetSummaryTextField.setDisable(b);
		});

		categoryCombo.valueProperty().addListener((obs, oldV, newV) -> {
			boolean b = !newV.getDescription().equals(NEW_CATEGORY);
			categoryOther.setVisible(!b);
			newCatgoryTextField.setDisable(b);
			categoryParentCombo.setDisable(b);
		});
		
		/*
		 * modify the date picker in a way that all date in the previous to the account creation date are disable and with a red background
		 */
		final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
			
			@Override
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);

						if (item.isBefore(DateConverter.DateToLocalDate(getData().getAccount().getCreationDate()))) {
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb;");
						}
					}
				};
			}
		};
		datePicker.setDayCellFactory(dayCellFactory);
	}

	@Override
	protected void initializePopupFields(Transaction data) {
		// TODO
	}

	/**
	 * @param event : close the popup on cancel
	 */
	@FXML
	void handleTransactionCancel(ActionEvent event) {
		Stage stage = (Stage) transactionCancel.getScene().getWindow();
		stage.close();
	}

	
	@SuppressWarnings("unchecked")
	@FXML
	void handleTransactionSubmit(ActionEvent event) throws ParseException {

		errorLabels.forEach(label -> label.setVisible(false));
		Category cat;
		TargetTransaction tar;
		if (descriptionTextField.getText().isEmpty()) {
			descriptionError.setVisible(true);
		}

		if (valueTextField.getText().isEmpty() || valueTextField.getText().equals("0")) {
			valueError.setVisible(true);
		}

		if (datePicker.getValue() == null) {
			dateError.setVisible(true);
		}

		if (typeCombo.getValue() == null) {
			typeError.setVisible(true);
		}

		if (errorLabels.stream().allMatch(label -> !label.isVisible())) {
			// Get data from the fields
			Date date = DateConverter.LocalDate2Date(datePicker.getValue());
			Double val = Double.parseDouble(valueTextField.getText());
			String des = descriptionTextField.getText();
			TransactionType transactionType = typeCombo.getValue();
			cat = categoryCombo.getValue();
			tar = targetCombo.getValue();

			if (cat != null) {
				if (categoryCombo.getValue().getDescription().equals(NEW_CATEGORY)) {

					if (newCatgoryTextField.getText().isEmpty()) {
						categoryNameError.setVisible(true);
					}
					if (!categoryNameError.isVisible()) {
						// New instance of category
						Category newCat = new Category(newCatgoryTextField.getText());
						EntityManager em = VistaNavigator.getEmf().createEntityManager();
						List<Category> catList = em.createNamedQuery("Category.findAll").getResultList();
						for (Category ca : catList) {
							if (ca.getDescription().equals(newCat.getDescription())) {
								categoryNameError.setVisible(true);
							} else {
								// commit the new category
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
					}
				} else {
					this.getData().setCategory(cat);
				}
			}

			if (tar != null) {
				if (targetCombo.getValue().getSummary().equals(NEW_TARGET)) {
					if (newTargetSummaryTextField.getText().isEmpty()) {
						descriptionTargetError.setVisible(true);
					}
					if (newTargetIBANTextField.getText().isEmpty()
							|| !Validator.isValidIban(newTargetIBANTextField.getText())) {
						IBANTargetError.setVisible(true);
					}
					
					if (!descriptionTargetError.isVisible() && !IBANTargetError.isVisible()) {
						TargetTransaction newTar = new TargetTransaction(newTargetSummaryTextField.getText(),
								newTargetIBANTextField.getText());
						EntityManager em = VistaNavigator.getEmf().createEntityManager();
						List<TargetTransaction> tarList = em.createNamedQuery("TargetTransaction.findAll")
								.getResultList();
						// Commit the new TargetTransaction
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
				}
				if (!targetCombo.getValue().getSummary().equals(NEW_TARGET)) {
					this.getData().setTargetTransaction(tar);
				}
			}

			if (!descriptionTargetError.isVisible() && !IBANTargetError.isVisible() && !categoryNameError.isVisible()) {
				// Saving for the commit in the HomeController
				this.getData().setDate(date);
				this.getData().setValue(val);
				this.getData().setDescription(des);
				this.getData().setTransactionType(transactionType);
				this.setAsValidated();
				Stage stage = (Stage) transactionCancel.getScene().getWindow();
				stage.close();
			}
		}
	}
}
