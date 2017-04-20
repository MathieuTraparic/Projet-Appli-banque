package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Agency;
import model.Bank;

public class AddAgencyController extends PopupController<Agency> implements Initializable {
	
	@FXML public Button addAgencyCancel, addAgencySubmit;
	@FXML public TextField agencyName, agencyCode;
	@FXML public Label agencyNameError, agencyCodeError, agencyBankError;
	@FXML public ComboBox<String> linkedBank;
	
	@FXML
	void handleAddAgencyCancel(ActionEvent event) {
		Stage stage = (Stage) addAgencyCancel.getScene().getWindow();
	    stage.close();
	}

	@FXML
	void handleAddAgencySubmit(ActionEvent event){
		String name = agencyName.getText();
		String code = agencyCode.getText();
		String bankName = linkedBank.getValue();
		agencyNameError.setVisible(false);
		agencyCodeError.setVisible(false);
		if(name.isEmpty()){
			agencyNameError.setText("The name must be fill");
			agencyNameError.setVisible(true);
		}
		if(code.isEmpty()){
			agencyNameError.setText("The code must be fill");
			agencyNameError.setVisible(true);
		}else{
			EntityManager em = VistaNavigator.getEmf().createEntityManager();
			TypedQuery<Agency> q = em.createQuery("SELECT n FROM Agency n WHERE n.name=:name",Agency.class);
			List<Agency> list = q.setParameter("name", name).getResultList();
			TypedQuery<Agency> q2 = em.createQuery("SELECT c FROM Agency c WHERE c.counterCode=:code",Agency.class);
			List<Agency> list2 = q2.setParameter("code", code).getResultList();
			em.close();
			if(!list.isEmpty()){
				agencyNameError.setText("This agency already exists");
				agencyNameError.setVisible(true);
			}
			if(!list2.isEmpty()){
				agencyCodeError.setText("This counter code already exists");
				agencyCodeError.setVisible(true);
			}
			Stage stage = (Stage) addAgencySubmit.getScene().getWindow();
			this.getData().getBank();
			this.getData().setName(name);
			this.getData().setCounterCode(code);
			this.setAsValidated();
		    stage.close();
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		EntityManager em = VistaNavigator.getEmf().createEntityManager();
		List<Bank> l = em.createNamedQuery("Bank.findAll").getResultList();
		em.close();
		for(Bank b : l){
			linkedBank.getItems().add(b.getName());
		}
	}
	
	@Override
	protected void initializePopupFields(Agency data) {
		// TODO Auto-generated method stub
		
	}
}
