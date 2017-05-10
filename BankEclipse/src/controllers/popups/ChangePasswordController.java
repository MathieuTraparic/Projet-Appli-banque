/**
 *@Author: David Riviere 
 *4 mai 2017
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
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import model.Owner;
import util.PasswordHandler;

/**
 * @author Beltharion
 *
 */
public class ChangePasswordController extends PopupController<Owner> implements Initializable {

	@FXML
	private Button cancelButton, submitButton;
	@FXML
	private PasswordField newPswd, confirmPswd;
	@FXML
	private Label unequalFieldLabel;

	@Override
	protected void initializePopupFields(Owner data) {
		// TODO Auto-generated method stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ChangeListener<? super String> onChange = (observable, oldValue, newValue) -> {
			boolean fieldsEquals = this.newPswd.getText().equals(this.confirmPswd.getText());
			this.submitButton.setDisable(this.newPswd.getText().isEmpty() || this.confirmPswd.getText().isEmpty()
					|| !fieldsEquals);
			if(newValue!=null){
				this.unequalFieldLabel.setVisible(!fieldsEquals);
			}
		};
		this.newPswd.textProperty().addListener(onChange);
		this.confirmPswd.textProperty().addListener(onChange);
	}

	@FXML
	void handleChangeCancel(ActionEvent event) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	void handleChangePswd(ActionEvent event) {

		String password = newPswd.getText();
		String confirm = confirmPswd.getText();
		String salt = PasswordHandler.getNewSalt();
		password = PasswordHandler.hash(salt + confirm);
		this.getData().setSalt(salt);
		this.getData().setPswd(password);
		this.setAsValidated();
		Stage stage = (Stage) submitButton.getScene().getWindow();
		stage.close();
	}
}
