package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.WindowEvent;
import model.Account;
import model.Agency;
import model.Bank;

import util.PopWindow;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;


public class HomeController extends BankSelector implements Initializable{


	@FXML TableView<Account> accountView;

	@FXML
	void handleAddBankHome(ActionEvent event) throws IOException {
		PopupController<Bank> controller = PopupController.load(VistaNavigator.ADD_BANK, false);
		controller.show(new Bank("name", "code"), new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				Bank addedBank = controller.getValidatedData();
				if (addedBank != null) {
					EntityManager em = VistaNavigator.getEmf().createEntityManager();
					em.getTransaction().begin();
					em.persist(addedBank);
					em.getTransaction().commit();
					em.close();
				}
			}
		});
	}

	@FXML
	void handleAddAgencyHome(ActionEvent event) throws IOException {
		PopupController<Agency> controller = PopupController.load(VistaNavigator.ADD_AGENCY, false);
		controller.show(new Agency("name", "counterCode"), new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				Agency a = controller.getValidatedData();

				// Actually I don't get the idea why we do this here and not
				// when we submit in the popup window
				if (a != null) {
					EntityManager em = VistaNavigator.getEmf().createEntityManager();

					em.getTransaction().begin();
					em.persist(a);
					em.getTransaction().commit();
					em.close();
				}
			}
		});
	}

	@FXML
	void handleAddAccountHome(ActionEvent event) throws IOException {
		PopupController<Account> controller = PopupController.load(VistaNavigator.ADD_ACCOUNT,true);
		controller.show(new Account("number","description", 0d, -150d, 0d),
			new EventHandler<WindowEvent>(){
				@Override
				public void handle(WindowEvent event){
					Account account = controller.getValidatedData();

					//Actually I don't get the idea why we do this here and not when we submit in the popup window
					if (account!=null){
						EntityManager em = VistaNavigator.getEmf().createEntityManager();
						em.getTransaction().begin();
						em.persist(account);
						em.getTransaction().commit();
						em.close();	
					}
			}	
		});
	}

	@FXML
	void handleBankChoiceHome(ActionEvent event) {
		//get a bank specific subset of all the account from the owner 
		List<Account>accountFromCurrentBank= new ArrayList<Account>();
		
		this.accountsOwned.forEach(account-> {
			if(account.getAgency().getBank().equals(bankCombo.getValue())){
				accountFromCurrentBank.add(account);
			}
		});
		this.accountView.setItems(FXCollections.observableList(accountFromCurrentBank));
		
	}

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		super.initialize(fxmlFileLocation, resources);
	}
}
