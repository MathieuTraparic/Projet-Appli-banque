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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Owner;

public class CreateUserController implements Initializable {
	
	@FXML
	void firstStepValid(ActionEvent event){
		if (newLogin.getText() != null && newPassword.getText() != null 
				&& newPasswordConfirmation.getText() != null 
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

			if (newPasswordConfirmation.getText().isEmpty()
					|| !newPassword.getText().equals(newPasswordConfirmation.getText())) {
				passwordConfirmationError.setVisible(true);
			}
		}

		if (email.getText().isEmpty() || !Owner.isValidEmail(email.getText())) {
			emailError.setVisible(true);
		}

		if (labels1.stream().allMatch(label -> label.isVisible() == false)) {
			loginO = newLogin.getText();
			pwsdO = newPasswordConfirmation.getText();
			emailO = email.getText();

			VistaNavigator.loadVista(VistaNavigator.CREATE_USER_2);
		}
		else {
			this.nextStepButton.setDisable(true);
		}
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

		Calendar cal = Calendar.getInstance();

		if (name.getText().isEmpty() || !Owner.isValidName(firstName.getText())) {
			nameError.setVisible(true);
		}

		if (firstName.getText().isEmpty() || !Owner.isValidName(firstName.getText())) {
			firstNameError.setVisible(true);
		}

		if (addressLine1.getText().isEmpty()) {
			addressError.setVisible(true);
		}
		if (cityName.getText().isEmpty()) {
			cityNameError.setVisible(true);
		}
		if (zipCode.getText().isEmpty()) {
			zipCodeError.setVisible(true);
		}
		if (phoneNumber.getText().isEmpty()) {
			phoneNumberError.setVisible(true);
		}
		if (birthday.getValue() == null) {
			birthdayError.setVisible(true);
		} else {
			cal = new GregorianCalendar(birthday.getValue().getYear(), birthday.getValue().getMonthValue() - 1,
					birthday.getValue().getDayOfMonth(), 0, 0, 0);
			if (!Owner.isValidBirthday(cal.getTime())) {
				birthdayError.setVisible(true);
			}
		}
		

		if (labels2.stream().allMatch(label -> label.isVisible() == false)) {
			loginO = newLogin.getText();
			pwsdO = newPasswordConfirmation.getText();
			emailO = email.getText();

			signIn.setDisable(false);

			EntityManager em = VistaNavigator.getEmf().createEntityManager();

			em.close();
			VistaNavigator.loadVista(VistaNavigator.TEMPLATE);
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

	private String loginO = null;
	private String pwsdO = null;
	private String emailO = null;

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

	private List<Label> labels1;
	private List<Label> labels2;
	private List<Node> secondaryFields;

}
