package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.WindowEvent;
import model.Account;
import model.Transaction;

public class TemplateController implements Initializable{
	
	private List<Account> account = null;
	private List<Transaction> transaction = null;
	
	@FXML
	void handleLogoutButton(ActionEvent event){
		VistaNavigator.getInstance().setLoggedOwner(null);
		VistaNavigator.loadVista(VistaNavigator.LOGIN);
	}
	
	@FXML
	void handleCancel(ActionEvent event){
		//TODO
	}
	
	@FXML
	void handleSubmit(ActionEvent event){
		//TODO
	}
	
	
	// Event on menu bar
	@FXML
	void handleMenuFileOpen(ActionEvent event){
		// TODO
	}
	
	@FXML
	void handleMenuFileExport(ActionEvent event) throws IOException{
		PopupController<Account> controller = PopupController.load(VistaNavigator.EXPORT, true);
		controller.show(new Account("0000","description",0d,0d,0d), new EventHandler<WindowEvent>(){
			@Override
			public void handle(WindowEvent event){
				Account a = controller.getValidatedData();
			}
		});
	}
	
	@FXML
	void handleMenuFileQuit(ActionEvent event){
		Alert alert = new Alert(
				AlertType.CONFIRMATION,
				"Do you want to quit the application ?",
				ButtonType.OK,
				ButtonType.CANCEL
		);
		Optional<ButtonType> result = alert.showAndWait();
		
		if(result.isPresent() && result.get() == ButtonType.OK) {
			Platform.exit();
		}
	}
	
	@FXML
	void handleMenuWindowPreference(ActionEvent event){
		// TODO
	}
	
	@FXML
	void handleMenuHelpTutorial(ActionEvent event){
		//TODO
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		EntityManager em = VistaNavigator.getEmf().createEntityManager();
		this.account = em.createNamedQuery("Account.findAll").getResultList();
		this.transaction = em.createNamedQuery("Transaction.findAll").getResultList();
		em.close();		
	}
}
