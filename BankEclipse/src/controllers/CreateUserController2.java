package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Address;
import model.CpVille;
import model.Owner;

public class CreateUserController2 implements Initializable{

	
	public void initOwner(Owner owner2) {
		this.owner=owner2;
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
	void secondStepValid(ActionEvent event) {
		if (name.getText() != null && firstName.getText() != null 
				&& addressLine1.getText() != null && cityName.getText() != null 
				&& zipCode.getText() != null && phoneNumber.getText() != null
				&& birthday.getValue() != null) {
			this.signIn.setDisable(false);
		}
	}
	
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		labels2 = new ArrayList<Label>() {
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
			this.owner.setSalt("dfsgdhf");
			
			
			em.getTransaction().begin();
			em.persist(cpville);
			em.persist(address);
			em.persist(owner); 
			em.getTransaction().commit();

			em.close();
			VistaNavigator.loadVista(VistaNavigator.TEMPLATE);
		}
		else {
			signIn.setDisable(true);
		}

	}
	
	private List<Label> labels2;
	private Owner owner;
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

}
