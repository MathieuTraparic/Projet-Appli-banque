package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.stage.WindowEvent;
import model.Bank;
import util.PopWindow;

public class HomeController {

	@FXML
	void handleAddBankHome(ActionEvent event) throws IOException {
		PopupController<Bank> controller = PopupController.load(
				VistaNavigator.ADD_BANK,false);
		controller.show(new Bank("name","code"),
			new EventHandler<WindowEvent>(){
				@Override
				public void handle(WindowEvent event){
					Bank b = controller.getValidatedData();
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
		PopWindow addAgencyPop = new PopWindow(VistaNavigator.ADD_AGENCY, false);
	}

	@FXML
	void handleAddAccountHome(ActionEvent event) throws IOException {
		PopWindow addAccountPop = new PopWindow(VistaNavigator.ADD_ACCOUNT, true);
	}

	@FXML
	void handleBankChoiceHome(ActionEvent event) {
		EntityManager em = VistaNavigator.getEmf().createEntityManager();
		em.createNamedQuery("Bank.findAll").getResultList();
	}
}
