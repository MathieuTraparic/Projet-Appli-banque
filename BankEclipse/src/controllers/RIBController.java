/**
 *  @author Mathieu Traparic
 * 	Project : Bank
 * 	Creation date : 2017-04-01
 */
package controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.Account;
import util.IBANHandler;
import javafx.event.ActionEvent;

public class RIBController extends AccountSpecificController {

	
	@FXML
	Text iban;

	@FXML public void accountSelected(ActionEvent event) {
		if(this.accountCombo.getValue()==null){
			this.iban.setText("");
			return;
		}
		Account a = this.accountCombo.getValue();
		this.iban.setText(IBANHandler.genrateIBAN(a.getNumber(), a.getAgency().getCounterCode(), a.getAgency().getBank().getCode(), a.getCountryCode().getCode()));
		
	}


}
