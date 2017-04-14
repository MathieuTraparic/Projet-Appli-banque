package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Bank;

public class AddBankController {

	@FXML public Button addBankCancel, addBankSubmit;
	@FXML public TextField bankName, bankCode;
	@FXML public Label bankNameError, bankCodeError;

	@FXML
	void handleAddBankCancel(ActionEvent event) {
		Stage stage = (Stage) addBankCancel.getScene().getWindow();
		stage.close();
	}

	@FXML
	void handleAddBankSubmit(ActionEvent event) {
		String name = bankName.getText();
		String code = bankCode.getText();
		if (!Bank.isValidName(name)) {
			bankNameError.setText("The name is incorrect");
		} else if (name.isEmpty()) {
			bankNameError.setText("The name can't be empty");
		}
		if (!Bank.isValidName(code)) {
			bankCodeError.setText("The code is incorrect");
		} else if (code.isEmpty()) {
			bankCodeError.setText("The code can't be empty");
		} else {
			Bank bank = new Bank(name, code);
			Stage stage = (Stage) addBankSubmit.getScene().getWindow();
			stage.close();
		}
	}
}
