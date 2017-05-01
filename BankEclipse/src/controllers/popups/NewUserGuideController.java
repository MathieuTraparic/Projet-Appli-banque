package controllers.popups;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import controllers.VistaNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Owner;

public class NewUserGuideController extends PopupController<Owner> implements Initializable {

	
	/*
	 * Class variables
	 */
	@FXML public Button okButton;
	@FXML public CheckBox messageCheckBox;
	@FXML public Label welcomeMessage;
	
	/*
	 * (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources){
		String name = VistaNavigator.getInstance().getLoggedOwner().getFirstName();
		welcomeMessage.setText("Welcome "+name +" to your personnal manager account");
	}
	
	@Override
	protected void initializePopupFields(Owner owner) {
		// TODO Auto-generated method stub
	}
	
	/*
	 * Event on Cancel button : that closes the popup.
	 */
	@FXML 
	void handleOkButton(ActionEvent event) {
		Stage stage = (Stage) okButton.getScene().getWindow();
		
		if(messageCheckBox.isSelected()){
			EntityManager em = VistaNavigator.getEmf().createEntityManager();
			em.getTransaction().begin();

			Query q = em.createQuery(
					"UPDATE Owner a SET a.newUser=:newUser WHERE a.id=:id");
			q.setParameter("id", VistaNavigator.getInstance().getLoggedOwner().getId());
			q.setParameter("newUser", 1);
			q.executeUpdate();

			em.getTransaction().commit();

			em.close();
		}
		
		stage.close();
	}
	
}
