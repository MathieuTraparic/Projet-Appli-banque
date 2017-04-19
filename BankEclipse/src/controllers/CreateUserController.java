package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Address;
import model.CpVille;
import model.Owner;

public class CreateUserController implements Initializable {

	@FXML
	void firstStepValid(ActionEvent event) {
		if (newLogin.getText() != null && newPassword.getText() != null && newPasswordConfirmation.getText() != null
				&& email.getText() != null) {
			this.nextStepButton.setDisable(false);
		}
	}

	@FXML
	void nextStepButton(ActionEvent event) {

		labels1.forEach(label -> label.setVisible(false));

		if (newLogin.getText().isEmpty()) {
			loginError.setVisible(true);
		}

		if (newPassword.getText().isEmpty() || !Owner.isValidPswd(newPassword.getText())) {
			passwordError.setVisible(true);

		}
		if (newPasswordConfirmation.getText().isEmpty()
				|| !newPassword.getText().equals(newPasswordConfirmation.getText())) {
			passwordConfirmationError.setVisible(true);
		}

		if (email.getText().isEmpty() || !Owner.isValidEmail(email.getText())) {
			emailError.setVisible(true);
		}

		if (labels1.stream().allMatch(label -> label.isVisible() == false)) {
			
			Owner owner = new Owner(newLogin.getText(),newPasswordConfirmation.getText(),email.getText());

			/*owner.setLogin(newLogin.getText());
			owner.setPswd(newPasswordConfirmation.getText());
			owner.setEmail(email.getText());*/
			
	
			CreateUserController r = 
					(CreateUserController) VistaNavigator.loadVista(VistaNavigator.CREATE_USER_2);
			
			r.initOwner(owner);
			

			
		} else {
			this.nextStepButton.setDisable(true);
		}
	}

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

	@FXML
	void signInButton(ActionEvent event) {

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

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		labels1 = new ArrayList<Label>() {
			{

				add(emailError);
				add(loginError);
				add(passwordConfirmationError);
				add(passwordError);

			}
		};
	}

	private List<Label> labels1;
	private List<Label> labels2;
/*	private String loginO = null;
	private String pwsdO = null;
	private String emailO = null;*/
	private Owner owner;

	@FXML
	private Button nextStepButton;
	@FXML
	private Button signIn;
	@FXML
	private TextField newLogin;
	@FXML
	private TextField email;
	@FXML
	private PasswordField newPassword;
	@FXML
	private PasswordField newPasswordConfirmation;
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
	public Label loginError;
	@FXML
	public Label passwordError;
	@FXML
	public Label passwordConfirmationError;
	@FXML
	public Label emailError;
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

}
