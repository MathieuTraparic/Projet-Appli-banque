package controllers.popups;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import controllers.VistaNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Bank;

public class AddBankController extends PopupController<Bank> implements Initializable {

	
	/*
	 * Class variables
	 */
	@FXML public Button addBankCancel, addBankSubmit;
	@FXML public TextField bankName, bankCode;
	@FXML public Label bankNameError, bankCodeError;
	private List<Label> errorLabels;
	
	/*
	 * (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources){
		/*
		 * Initialization of the error labels
		 * The messages are defined in fxml of this controller AddAccount.fxml
		 */
		this.errorLabels = new ArrayList<Label>() {
			private static final long serialVersionUID = 6275258056275001066L;
			{
				add(bankNameError);
				add(bankCodeError);
			}
		};
		errorLabels.forEach(label -> label.setVisible(false));
	}
	
	@Override
	protected void initializePopupFields(Bank data) {
		// TODO Auto-generated method stub
	}
	
	/*
	 * Event on Cancel button : that closes the popup.
	 */
	@FXML 
	void handleAddBankCancel(ActionEvent event) {
		Stage stage = (Stage) addBankCancel.getScene().getWindow();
		stage.close();
	}
	
	/*
	 * Event on submit button. In this event, the user enter the description of the bank.
	 * The data are saved and the commit in the data base is set in HomeController 
	 */
	@FXML
	void handleAddBankSubmit(ActionEvent event) {
		// Lambda expression for the error message
		errorLabels.forEach(label -> label.setVisible(false));	
		String name = bankName.getText();
		String code = bankCode.getText();
		// Tests to verify if the fields are filled
		if (name.isEmpty()) {
			bankNameError.setVisible(true);
		}
		if (code.isEmpty()) {
			bankCodeError.setVisible(true);
		} 
		if(errorLabels.stream().allMatch(label -> !label.isVisible())){
			// Query select the existed bank in the data base
			EntityManager em = VistaNavigator.getEmf().createEntityManager();
			TypedQuery<Bank> q = em.createQuery("SELECT b FROM Bank b WHERE b.name =:name", Bank.class);
			List<Bank> list = q.setParameter("name", name).getResultList();
			TypedQuery<Bank> q2 = em.createQuery("SELECT c FROM Bank c WHERE c.code =:code", Bank.class);
			List<Bank> list2 = q2.setParameter("code", code).getResultList();
			em.close();
			// Tests to verify if the bank already exists in the data base
			if(!list.isEmpty()){
				bankNameError.setText("This bank name already exists");
				bankNameError.setVisible(true);
			}
			if(!list2.isEmpty()){
				bankCodeError.setText("This bank code already exists");
				bankCodeError.setVisible(true);
			}
			// Data saving
			this.getData().setName(name);
			this.getData().setCode(code);
			this.setAsValidated();
			// Code to close the current popup
			Stage stage = (Stage) addBankSubmit.getScene().getWindow();
			stage.close();
		}
	}
}
