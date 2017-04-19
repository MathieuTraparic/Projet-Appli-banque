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
import util.PasswordHandler;

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
			
			String salt = PasswordHandler.getNewSalt();
			
			Owner owner = new Owner(newLogin.getText(),PasswordHandler.hash(salt+newPasswordConfirmation.getText()),email.getText());
			
			owner.setSalt(salt);
	
			CreateUserController2 r = 
					(CreateUserController2) VistaNavigator.loadVista(VistaNavigator.CREATE_USER_2);
			
			r.initOwner(owner);
	
		} else {
			this.nextStepButton.setDisable(true);
		}
	}

	@FXML
	void cancelButton(ActionEvent event) {
		VistaNavigator.loadVista(VistaNavigator.LOGIN);
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

	@FXML
	private Button nextStepButton;
	@FXML
	private TextField newLogin;
	@FXML
	private TextField email;
	@FXML
	private PasswordField newPassword;
	@FXML
	private PasswordField newPasswordConfirmation;

	@FXML
	public Label loginError;
	@FXML
	public Label passwordError;
	@FXML
	public Label passwordConfirmationError;
	@FXML
	public Label emailError;


}
