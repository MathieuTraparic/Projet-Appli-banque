package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
import util.SendEmail;
import javafx.scene.input.InputMethodEvent;

public class LoginController implements Initializable {
	private static final String DEFAULT_PASSWORD ="password";
	private static final String DEFAULT_LOGIN ="defaultUser";

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
		TypedQuery<Owner> q = em.createQuery("SELECT  o FROM Owner o WHERE o.login =:login", Owner.class);
		List<Owner> list = q.setParameter("login", this.login.getText()).getResultList();

		em.close();
		
		if (!list.isEmpty()) {
			Owner o = list.get(0);
			if (PasswordHandler.hash(o.getSalt() + pswd.getText()).equals(o.getPswd())) {
				VistaNavigator.getInstance().setLoggedOwner(o);
				VistaNavigator.loadVista(VistaNavigator.TEMPLATE);
			}

		}
		loginError.setVisible(true);

	}

	@FXML
	void signUpButton(ActionEvent event) {
		VistaNavigator.loadVista(VistaNavigator.CREATE_USER_1);
	}

//	@FXML
//	void handleByPass(ActionEvent event) {
//		VistaNavigator.loadVista(VistaNavigator.TEMPLATE);
//	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//set a default user
		this.signIn.setDisable(false);
		this.login.setText(DEFAULT_LOGIN);
		this.pswd.setText(DEFAULT_PASSWORD);
		//First ping of the DB, dirty fix to connect ealier
		VistaNavigator.getEmf().createEntityManager().close();
		
		//disable the sign in button if one of the fields is empty
		ChangeListener<? super String> onChange = (observable, oldValue, newValue) -> {
			this.signIn.setDisable(this.login.getText().isEmpty() || this.pswd.getText().isEmpty());
		};
		this.login.textProperty().addListener(onChange);
		this.pswd.textProperty().addListener(onChange);

	}


}
