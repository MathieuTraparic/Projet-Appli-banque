package controllers;


import java.util.List;

import javax.persistence.EntityManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Owner;

public class LoginController {
	
	@FXML TextField login;
	@FXML PasswordField pswd;
	
	@FXML
	void signInButton(ActionEvent event){
		VistaNavigator.loadVista(VistaNavigator.TEMPLATE);
	}
	
	@FXML
	void signUpButton(ActionEvent event){
		VistaNavigator.loadVista(VistaNavigator.CREATE_USER_1);
		EntityManager em = VistaNavigator.getEmf().createEntityManager();
		List<Owner> l = em.createNamedQuery("Owner.findAll").getResultList();
		em.close();
	}
	

}
