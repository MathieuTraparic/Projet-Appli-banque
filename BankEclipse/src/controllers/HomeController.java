package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.stage.WindowEvent;
import model.Address;
import model.Agency;
import model.Bank;
import model.CpVille;
import util.PopWindow;

public class HomeController implements Initializable{
	
	@FXML public ComboBox<String> bankChoiceHome;
	
	private Bank b = null;

	@FXML
	void handleAddBankHome(ActionEvent event) throws IOException {
		PopupController<Bank> controller = PopupController.load(
				VistaNavigator.ADD_BANK,false);
		controller.show(new Bank("name","code"),
			new EventHandler<WindowEvent>(){
				@Override
				public void handle(WindowEvent event){
					b = controller.getValidatedData();
					if (b!=null){
						EntityManager em = VistaNavigator.getEmf().createEntityManager();
						em.getTransaction().begin();
						em.persist(b);
						em.getTransaction().commit();
						em.close();
					}
			}	
		});
	}

	@FXML
	void handleAddAgencyHome(ActionEvent event) throws IOException {
		PopupController<Agency> controller = PopupController.load(
				VistaNavigator.ADD_AGENCY,false);
		controller.show(new Agency("name","counterCode"),
			new EventHandler<WindowEvent>(){
				@Override
				public void handle(WindowEvent event){
					Agency a = controller.getValidatedData();
					
					CpVille zipCityAgency = new CpVille("123é", "trifouilli");
					Address agencyAddress = new Address("azeaze", "azeazeaz");
					
					agencyAddress.setCpVille(zipCityAgency);
					
					if (a!=null){
						EntityManager em = VistaNavigator.getEmf().createEntityManager();
						
						
						a.setAdress(agencyAddress);
						
						em.getTransaction().begin();
						em.persist(zipCityAgency);
						em.persist(agencyAddress);
						em.persist(a);
						em.getTransaction().commit();
						em.close();
					}
			}	
		});
	}

	@FXML
	void handleAddAccountHome(ActionEvent event) throws IOException {
		PopWindow addAccountPop = new PopWindow(VistaNavigator.ADD_ACCOUNT, true);
	}

	@FXML
	void handleBankChoiceHome(ActionEvent event) {
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		EntityManager em = VistaNavigator.getEmf().createEntityManager();
		List<Bank> l = em.createNamedQuery("Bank.findAll").getResultList();
		em.close();
		for(Bank b : l){
			bankChoiceHome.getItems().add(b.getName());
		}
	}
}
