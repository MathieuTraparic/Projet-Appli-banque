package controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Owner;
import util.PasswordHandler;

public class LoginController {

	@FXML
	TextField login;
	@FXML
	PasswordField pswd;
	@FXML
	Label loginError;
	@FXML
	Button signIn;

	@FXML
	void signInButton(ActionEvent event) {
		EntityManager em = VistaNavigator.getEmf().createEntityManager();
		Query q = em.createQuery("SELECT  o FROM Owner o WHERE login =:login");
		q.setParameter("login", this.login.getText());

		q.executeUpdate();
		Owner o = (Owner) q.getSingleResult();

		if (PasswordHandler.hash(o.getSalt() + pswd.getText()).equals(o.getPswd())) {
			VistaNavigator.loadVista(VistaNavigator.TEMPLATE);
		} else {
			loginError.setVisible(true);
		}

	}

	@FXML
	void handleFields(ActionEvent event) {
		signIn.setDisable(login.getText().isEmpty() || pswd.getText().isEmpty());

	}

	@FXML
	void signUpButton(ActionEvent event) {
		VistaNavigator.loadVista(VistaNavigator.CREATE_USER_1);
	}

	@FXML
	void handleByPass(ActionEvent event) {
		VistaNavigator.loadVista(VistaNavigator.TEMPLATE);
	}

}
