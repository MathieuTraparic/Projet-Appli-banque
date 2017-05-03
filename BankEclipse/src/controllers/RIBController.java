/**
 *  @author Mathieu Traparic
 * 	Project : Bank
 * 	Creation date : 2017-04-01
 */
package controllers;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
		DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = 
                directoryChooser.showDialog((Stage) exportRib.getScene().getWindow());         
		List<Owner> listOwner = accountCombo.getValue().getOwners();
		String owner = null; int ind = 0;
		for (Owner o : listOwner) {
			if(listOwner.size()==1){
				owner = o.getName();
			}
			else if(ind==0){
				owner = o.getName() + " ";
				ind = ind + 1;
			}
			else {
				owner += o.getName() + " ";
			}	
		}
		CreatePDF ribPDF = new CreatePDF("RIB.pdf", String.format("%s", iban.getText()), bankCombo.getValue().getName(),
				accountCombo.getValue().getAgency().getName(), owner, selectedDirectory.getAbsolutePath());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		super.initialize(arg0, arg1);
		exportRib.setDisable(true);
	}
}
