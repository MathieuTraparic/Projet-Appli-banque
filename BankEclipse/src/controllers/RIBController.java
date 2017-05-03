/**
 *  @author Mathieu Traparic
 * 	Project : Bank
 * 	Creation date : 2017-04-01
 */
package controllers;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import model.Account;
import model.Bank;
import model.Owner;
import util.CreatePDF;
import util.IBANHandler;

public class RIBController extends AccountSelector {

	@FXML
	TextField iban;
	@FXML
	Button exportRib;
	@FXML
	ComboBox<Bank> bankCombo;
	@FXML
	ComboBox<Account> accountCombo;

	@FXML
	public void accountSelected(ActionEvent event) {
		if (this.accountCombo.getValue() == null) {
			this.iban.setText("");
			return;
		}
		Account a = this.accountCombo.getValue();
		this.iban.setText(IBANHandler.genrateIBAN(a.getNumber(), a.getAgency().getCounterCode(),
				a.getAgency().getBank().getCode(), a.getCountryCode().getCode()));
		exportRib.setDisable(false);
	}

	@FXML
	void handleButtonExportRib(ActionEvent event) {
		List<Owner> listOwner = accountCombo.getValue().getOwners();
		String owner = null;
		int ind = 0;
		for (Owner o : listOwner) {
			if (listOwner.size() == 1) {
				owner = o.getName();
			} else if (ind == 0) {
				owner = o.getName() + " ";
				ind = ind + 1;
			} else {
				owner += o.getName() + " ";
			}
		}
		
		Stage stage = (Stage) exportRib.getScene().getWindow();
		DirectoryChooser directoryChooser = new DirectoryChooser();
		File selectedDirectory = directoryChooser.showDialog(stage);
		if (selectedDirectory == null) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.initOwner(stage); 
			alert.setTitle("Warning");
			alert.setHeaderText("You have selected any directory.");
			alert.setContentText("Your RIB is not exported."); 
			final Optional<ButtonType> result = alert.showAndWait(); 
			result.ifPresent(button -> { 
			    if (button != ButtonType.OK) { 
			    	alert.close();
			    } else {
			    	alert.close();
			    }
			});
		} else {
			CreatePDF ribPDF = new CreatePDF("RIB.pdf", String.format("%s", iban.getText()),
					bankCombo.getValue().getName(), accountCombo.getValue().getAgency().getName(), owner,
					selectedDirectory.getAbsolutePath());
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.initOwner(stage); 
			alert.setTitle("Confirmation");
			alert.setHeaderText("Your RIB is exported to that location :");
			alert.setContentText(selectedDirectory.getAbsolutePath()); 
			final Optional<ButtonType> result = alert.showAndWait();
			result.ifPresent(button ->{
				if(button == ButtonType.OK){
					alert.close();
				} else {
					alert.close();
				}
			});
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		super.initialize(arg0, arg1);
		exportRib.setDisable(true);
	}
}
