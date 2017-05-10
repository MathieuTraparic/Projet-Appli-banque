/**
 *@Author: David Riviere 
 *3 mai 2017
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Owner;

/**
 * @author Beltharion
 *
 */
public class ChangeLoginController extends PopupController<Owner> implements Initializable{

	@FXML
	public Button changeCancel, changeLogin;
	@FXML
	public TextField newLogin, confirmLogin;
	@FXML
	public Label loginError, confirmError;
	private List<Label> errorLabels = null;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.errorLabels = new ArrayList<Label>() {
			{
				add(loginError);
				add(confirmError);
			}
		};
		errorLabels.forEach(label -> label.setVisible(false));
	}

	@Override
	protected void initializePopupFields(Owner data) {
		// TODO Auto-generated method stub		
	}

	@FXML
	void handleChangeCancel(ActionEvent event){
		Stage stage = (Stage) changeCancel.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	void handleChangeLogin(ActionEvent event){
		if(newLogin.getText().isEmpty()){
			loginError.setVisible(false);
		}
		if(!confirmLogin.getText().equals(newLogin.getText())){
			confirmError.setVisible(false);
		}
		String login = newLogin.getText();
		if (errorLabels.stream().allMatch(label -> label.isVisible() == false)) {
			this.getData().setLogin(login);
			this.setAsValidated();
			Stage stage = (Stage) changeLogin.getScene().getWindow();
			stage.close();
		}
	}
}
