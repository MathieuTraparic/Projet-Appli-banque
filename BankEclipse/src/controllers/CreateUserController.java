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
import model.Advisor;
import model.Owner;

public class CreateUserController implements Initializable{
	
	@FXML
	void nextStepButton(ActionEvent event){
		
		labels1.forEach(label -> label.setVisible(false));
		
		if (newLogin.getText().isEmpty()) {
			loginError.setVisible(true);
		}

		if (newPassword.getText().isEmpty()|| !Owner.isValidPswd(newPassword.getText())) {
			passwordError.setVisible(true);

			if (newPasswordConfirmation.getText().isEmpty() || !newPassword.getText().equals(newPasswordConfirmation.getText())) {
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
			 
			 nexStepButton.setDisable(false);
			 
			 VistaNavigator.loadVista(VistaNavigator.CREATE_USER_2);
		}
	}
	
	@FXML
	void previousButton(ActionEvent event){

		VistaNavigator.loadVista(VistaNavigator.CREATE_USER_1);

		
	}
	@FXML
	void cancelButton(ActionEvent event){
		VistaNavigator.loadVista(VistaNavigator.LOGIN);
	}
	
	@FXML
	void signInButton(ActionEvent event){
		
		labels2.forEach(label -> label.setVisible(false));
		
		Calendar cal =Calendar.getInstance();
		
		if (agency.getValue() == null) {
			invalidAgency.setVisible(true);
		}

		if (assignmentDate.getValue() == null) {
			invalidDate.setVisible(true);
		} else {
			cal = new GregorianCalendar(assignmentDate.getValue().getYear(),
					assignmentDate.getValue().getMonthValue() - 1, assignmentDate.getValue().getDayOfMonth(), 0, 0, 0);
			if (!Advisor.isValidAssignmentDate(cal.getTime())) {
				invalidDate.setVisible(true);
			}
		}

		if (name.getText().isEmpty() || !Advisor.isValidName(name.getText())) {
			invalidName.setVisible(true);
		}
		if (firstName.getText().isEmpty() || !Advisor.isValidFirstName(firstName.getText())) {
			invalidFirstName.setVisible(true);
		}
		if (phoneNumber.getText().isEmpty() || !Advisor.isValidPhoneNumber(phoneNumber.getText())) {
			invalidNumber.setVisible(true);
		}
		if (email.getText().isEmpty() || !Advisor.isValidEmail(email.getText())) {
			invalidEmail.setVisible(true);
		}

		if (labels.stream().allMatch(label -> label.isVisible() == false)) {
			Advisor advisor = new Advisor(name.getText(), firstName.getText(), phoneNumber.getText(), email.getText(),
					cal.getTime());

		}
		
		EntityManager em = VistaNavigator.getEmf().createEntityManager();
		
		
		em.close();
		VistaNavigator.loadVista(VistaNavigator.TEMPLATE);
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

		this.secondaryFields.forEach(item -> item.setDisable(true));

	}
	
	private String loginO=null;
	private String pwsdO=null;
	private String emailO=null;
	
	@FXML
	private Button nexStepButton;	
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
