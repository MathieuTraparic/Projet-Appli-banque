package controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Owner;

public class CreateUserController {
	@FXML
	void nextStepButton(ActionEvent event){
		VistaNavigator.loadVista(VistaNavigator.CREATE_USER_2);
	}
	@FXML
	void previousButton(ActionEvent event){
		EntityManager em = VistaNavigator.getEmf().createEntityManager();
		List<Owner> l = em.createNamedQuery("Owner.findAll").getResultList();
		VistaNavigator.loadVista(VistaNavigator.CREATE_USER_1);
		newLogin.setText(l.get(0).getLogin());
	}
	@FXML
	void cancelButton(ActionEvent event){
		VistaNavigator.loadVista(VistaNavigator.LOGIN);
	}
	
	@FXML
	void signInButton(ActionEvent event){
		VistaNavigator.loadVista(VistaNavigator.TEMPLATE);
	}
	
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
	
}
