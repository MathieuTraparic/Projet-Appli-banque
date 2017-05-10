/**
 *@Author: David Riviere 
 *3 mai 2017
 */
package controllers.popups;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
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
public class ChangeLoginController extends PopupController<Owner> implements Initializable {

	@FXML
	private Button cancelButton, submitButton;
	@FXML
	public TextField newLogin, confirmLogin;
	@FXML
	public Label confirmError;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ChangeListener<? super String> onChange = (observable, oldValue, newValue) -> {
			boolean fieldsEquals = this.newLogin.getText().equals(this.confirmLogin.getText());
			this.submitButton.setDisable(
					this.newLogin.getText().isEmpty() || this.confirmLogin.getText().isEmpty() || !fieldsEquals);
			if (newValue != null) {
				this.confirmError.setVisible(!fieldsEquals);
			}
		};
		
		newLogin.textProperty().addListener(onChange);
		confirmLogin.textProperty().addListener(onChange);
	}

	@Override
	protected void initializePopupFields(Owner data) {
		// TODO Auto-generated method stub
	}

	@FXML
	void handleChangeCancel(ActionEvent event) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	void handleChangeLogin(ActionEvent event) {

		String login = newLogin.getText();
		this.getData().setLogin(login);
		this.setAsValidated();
		Stage stage = (Stage) submitButton.getScene().getWindow();
		stage.close();
	}
}
