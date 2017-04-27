package controllers.popups;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import controllers.VistaNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Bank;

public class AddBankController extends PopupController<Bank> {

	@FXML public Button addBankCancel, addBankSubmit;
	@FXML public TextField bankName, bankCode;
	@FXML public Label bankNameError, bankCodeError;
	//private List<Label> labels1;
	@FXML
	void handleAddBankCancel(ActionEvent event) {
		Stage stage = (Stage) addBankCancel.getScene().getWindow();
		stage.close();
	}

	@FXML
	void handleAddBankSubmit(ActionEvent event) {
		//labels1.forEach(label -> label.setVisible(false));	
		String name = bankName.getText();
		String code = bankCode.getText();
		if (name.isEmpty()) {
			bankNameError.setText("The name must be fill");
			bankNameError.setVisible(true);
		} else if (!Bank.isValidName(name)) {
			bankNameError.setText("The name must be valid");
			bankNameError.setVisible(true);
		}
		if (code.isEmpty()) {
			bankCodeError.setText("The code must be fill");
			bankCodeError.setVisible(true);
		} else {
			EntityManager em = VistaNavigator.getEmf().createEntityManager();
			TypedQuery<Bank> q = em.createQuery("SELECT b FROM Bank b WHERE b.name =:name", Bank.class);
			List<Bank> list = q.setParameter("name", name).getResultList();
			TypedQuery<Bank> q2 = em.createQuery("SELECT c FROM Bank c WHERE c.code =:code", Bank.class);
			List<Bank> list2 = q2.setParameter("code", code).getResultList();
			em.close();
			if(!list.isEmpty()){
				bankNameError.setText("This bank name already exists");
				bankNameError.setVisible(true);
			}
			if(!list2.isEmpty()){
				bankCodeError.setText("This bank code already exists");
				bankCodeError.setVisible(true);
			}
			Stage stage = (Stage) addBankSubmit.getScene().getWindow();
			this.getData().setName(name);
			this.getData().setCode(code);
			this.setAsValidated();
			stage.close();
		}
	}

	@Override
	protected void initializePopupFields(Bank data) {
		// TODO Auto-generated method stub
		
	}
}
