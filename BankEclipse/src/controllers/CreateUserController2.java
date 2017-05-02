package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import model.Address;
import model.CpVille;
import model.Owner;
import util.SendEmail;

public class CreateUserController2 implements Initializable{
	
	@FXML
	public Label nameError;
	@FXML
	public Label firstNameError;
	@FXML
	public Label addressError;
	@FXML
	public Label cityNameError;
	@FXML
	public Label zipCodeError;
	@FXML
	public Label birthdayError;
	@FXML
	public DatePicker birthday;
	@FXML
	public TextField phoneNumber;
	@FXML
	public Label phoneNumberError;
	
	@FXML
	private TextField name;
	@FXML
	private TextField firstName;
	@FXML
	private TextField addressLine1;
	@FXML
	private TextField addressLine2;
	@FXML
	private TextField cityName;
	@FXML
	private TextField zipCode;
	
	@FXML
	private Button signIn;
	
	private List<Label> labels2;
	private Owner owner;

	/**get the data from create user controller 1 
	 * @param owner2
	 */
	public void initOwner(Owner owner2) {
		this.owner=owner2;
	}
	
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		
		/*
		 * set all Label in ArrayList to be initialized
		 */
		
		labels2 = new ArrayList<Label>() {

			private static final long serialVersionUID = 2567784150210394600L;

			{
				add(nameError);
				add(firstNameError);
				add(birthdayError);
				add(addressError);
				add(cityNameError);
				add(zipCodeError);
				add(phoneNumberError);
			}
		};
		
		/*
		 * if all text fields are filled with data, the listener set the sign in
		 * button to not disabled
		 */

		ChangeListener<? super String> onChange = (observable, oldValue, newValue) -> {
			signIn.setDisable(name.getText().isEmpty() 
					|| firstName.getText().isEmpty()
					|| addressLine1.getText().isEmpty()
					|| cityName.getText().isEmpty()
					|| zipCode.getText().isEmpty()
					|| phoneNumber.getText().isEmpty()
					|| birthday.getValue()==null);
		};
		
		
		name.textProperty().addListener(onChange);
		firstName.textProperty().addListener(onChange);
		addressLine1.textProperty().addListener(onChange);
		cityName.textProperty().addListener(onChange);
		zipCode.textProperty().addListener(onChange);
		phoneNumber.textProperty().addListener(onChange);
		
		/*
		 * if the date is chosen and , the listener set the sign in
		 * button to not disabled
		 */
		birthday.valueProperty().addListener((observable, oldValue, newValue) -> {
			signIn.setDisable(name.getText().isEmpty() 
					|| firstName.getText().isEmpty()
					|| addressLine1.getText().isEmpty()
					|| cityName.getText().isEmpty()
					|| zipCode.getText().isEmpty()
					|| phoneNumber.getText().isEmpty()
					|| birthday.getValue()==null);
		});
		
		/*
		 * modify the date picker in a way that all date in the future are disable and with a red background
		 */
		final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
			
			@Override
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);

						if (item.isAfter(LocalDate.now())) {
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb;");
						}
					}
				};
			}
		};
		birthday.setDayCellFactory(dayCellFactory);
	}
	
	@FXML
	void previousButton(ActionEvent event) {
		VistaNavigator.loadVista(VistaNavigator.CREATE_USER_1);
	}
	
	@FXML
	void cancelButton(ActionEvent event) {
		VistaNavigator.loadVista(VistaNavigator.LOGIN);
	}
	
	
	
	@FXML
	void signInButton(ActionEvent event) {


		labels2.forEach(label -> label.setVisible(false));

		Calendar birth = Calendar.getInstance();

		if (name.getText().isEmpty() || !Owner.isValidName(firstName.getText())) {
			nameError.setVisible(true);
		}

		if (firstName.getText().isEmpty() || !Owner.isValidName(firstName.getText())) {
			firstNameError.setVisible(true);
		}

		if (addressLine1.getText().isEmpty()) {
			addressError.setVisible(true);
		}
		if (cityName.getText().isEmpty()|| !Owner.isValidName(cityName.getText())) {
			cityNameError.setVisible(true);
		}
		if (zipCode.getText().isEmpty()) {
			zipCodeError.setVisible(true);
		}
		if (phoneNumber.getText().isEmpty()|| !Owner.isValidPhoneNumber(phoneNumber.getText())) {
			phoneNumberError.setVisible(true);
		}
		if (birthday.getValue() == null) {
			birthdayError.setVisible(true);
		} else {
			birth = new GregorianCalendar(birthday.getValue().getYear(), birthday.getValue().getMonthValue() - 1,
					birthday.getValue().getDayOfMonth(), 0, 0, 0);
			if (!Owner.isValidBirthday(birth.getTime())) {
				birthdayError.setVisible(true);
			}
		}

		if (labels2.stream().allMatch(label -> label.isVisible() == false)) {

			EntityManager em = VistaNavigator.getEmf().createEntityManager();
			
			CpVille cpville = new CpVille(zipCode.getText(),cityName.getText());
			Address address = new Address(addressLine1.getText(), addressLine2.getText());
			
			address.setCpVille(cpville);
			
			this.owner.setName(name.getText());
			this.owner.setFirstName(firstName.getText());
			this.owner.setPhoneNumber(phoneNumber.getText());
			this.owner.setBirthday(birth.getTime());
			this.owner.setAddress(address);
			
			
			em.getTransaction().begin();
			em.persist(cpville);
			em.persist(address);
			em.persist(owner); 
			em.getTransaction().commit();

			em.close();
			VistaNavigator.getInstance().setLoggedOwner(owner);
			VistaNavigator.loadVista(VistaNavigator.TEMPLATE);
			SendEmail.SendEmails();
		}
		else {
			signIn.setDisable(true);
		}

	}

}
