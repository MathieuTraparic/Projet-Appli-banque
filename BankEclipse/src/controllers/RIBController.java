/**
 *  @author Mathieu Traparic
 * 	Project : Bank
 * 	Creation date : 2017-04-01
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.sun.xml.internal.txw2.Document;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Account;
import util.IBANHandler;

public class RIBController extends AccountSelector {

	
	@FXML
	TextField iban;
	@FXML
	Button exportRib;

	@FXML public void accountSelected(ActionEvent event) {
		if(this.accountCombo.getValue()==null){
			this.iban.setText("");
			return;
		}
		Account a = this.accountCombo.getValue();
		this.iban.setText(IBANHandler.genrateIBAN(a.getNumber(), a.getAgency().getCounterCode(), a.getAgency().getBank().getCode(), a.getCountryCode().getCode()));
		exportRib.setDisable(false);
	}
	
	@FXML
	void handleButtonExportRib(ActionEvent event){
		 Document document = new Document(PageSize.A4, 0, 0, 0, 0);
         PdfWriter.getInstance(document,response.getOutputStream());
         document.open();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		exportRib.setDisable(true);
	}
}
