/**
 *@Author: David Riviere 
 *4 mai 2017
 */
package controllers.popups;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import model.Owner;
import util.PasswordHandler;

/**
 * @author Beltharion
 *
 */
public class ChangePasswordController extends PopupController<Owner> implements Initializable{

	@FXML
	private Button changeCancel, changePswd;
	@FXML 
	private PasswordField newPswd, confirmPswd;
	@FXML
	private Label pswdError, confirmError;
	private List<Label> errorLabels = null;
	
	@Override
	protected void initializePopupFields(Owner data) {
		// TODO Auto-generated method stub	
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.errorLabels = new ArrayList<Label>() {
			{
				add(pswdError);
				add(confirmError);
			}
		};
		errorLabels.forEach(label -> label.setVisible(false));
	}
	
	@FXML 
	void handleChangeCancel(ActionEvent event){
		Stage stage = (Stage) changeCancel.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	void handleChangePswd(ActionEvent event){
		if(newPswd.getText().isEmpty()){
			pswdError.setVisible(true);
		}
		if(!confirmPswd.getText().equals(newPswd.getText())){
			confirmError.setVisible(true);
		}
		if (errorLabels.stream().allMatch(label -> label.isVisible() == false)) {
			String password = newPswd.getText();
			String confirm = confirmPswd.getText();
			String salt = PasswordHandler.getNewSalt();
			password = PasswordHandler.hash(salt+confirm);
			this.getData().setSalt(salt);
			this.getData().setPswd(password);
			this.setAsValidated();
			Stage stage = (Stage) changePswd.getScene().getWindow();
			stage.close();
		}
	}
}
