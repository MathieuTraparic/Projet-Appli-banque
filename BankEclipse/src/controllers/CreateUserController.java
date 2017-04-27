package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Owner;
import util.PasswordHandler;

public class CreateUserController implements Initializable {

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

	private List<Label> errorLabels;
	
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

		/*
		 * set all Label in ArrayList  to be initialized
		 */

		errorLabels = new ArrayList<Label>() {

			private static final long serialVersionUID = -4858852416598365832L;

			{
				add(emailError);
				add(loginError);
				add(passwordConfirmationError);
				add(passwordError);
			}
		};

		/*
		 * if all text fields are filled with data, the listener set the next
		 * step button to not disabled
		 */

		ChangeListener<? super String> onChange = (observable, oldValue, newValue) -> {
			nextStepButton.setDisable(newLogin.getText().isEmpty() || newPassword.getText().isEmpty()
					|| newPasswordConfirmation.getText().isEmpty() || email.getText().isEmpty());
		};
		newLogin.textProperty().addListener(onChange);
		newPassword.textProperty().addListener(onChange);
		newPasswordConfirmation.textProperty().addListener(onChange);
		email.textProperty().addListener(onChange);
	}

	/**
	 * @param event
	 *            click
	 * @return load next page to create a user if the new login, password and
	 *         email were correctly created
	 */
	@FXML
	void nextStepButton(ActionEvent event) {

		// hide all error labels
		errorLabels.forEach(label -> label.setVisible(false));

		if (newLogin.getText().isEmpty()) {
			loginError.setText("This login must be filled");
			loginError.setVisible(true);
		}

		// looking in the database if the newLogin is already used
		EntityManager em = VistaNavigator.getEmf().createEntityManager();
		TypedQuery<Owner> q = em.createQuery("SELECT  o FROM Owner o WHERE o.login =:login", Owner.class);
		List<Owner> list = q.setParameter("login", this.newLogin.getText()).getResultList();
		em.close();

		if (!list.isEmpty()) {
			loginError.setText("This login is already used");
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

		/*
		 * if no error label activated
		 */
		if (errorLabels.stream().allMatch(label -> label.isVisible() == false)) {

			String salt = PasswordHandler.getNewSalt();

			Owner owner = new Owner(newLogin.getText(), PasswordHandler.hash(salt + newPasswordConfirmation.getText()),
					email.getText(), salt);

			// allow us to pass on data from this controller to the next
			// controller
			CreateUserController2 r = (CreateUserController2) VistaNavigator.loadVista(VistaNavigator.CREATE_USER_2);

			// data to pass to the next controller
			r.initOwner(owner);

		} else {
			// if error label activated
			this.nextStepButton.setDisable(true);
		}
	}

	@FXML
	void cancelButton(ActionEvent event) {
		VistaNavigator.loadVista(VistaNavigator.LOGIN);
	}
}
