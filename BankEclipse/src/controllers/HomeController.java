package controllers;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.PopWindow;

public class HomeController {

	@FXML
	void handleAddBankHome(ActionEvent event) throws IOException {
		PopWindow addBankPop = new PopWindow("/viewFxml/addBank.fxml",false);
	}

	@FXML
	void handleAddAgencyHome(ActionEvent event) throws IOException {
		PopWindow addAgencyPop = new PopWindow("/viewFxml/addAgency.fxml",false);
	}

	@FXML
	void handleAddAccountHome(ActionEvent event) throws IOException{
		PopWindow addAccountPop = new PopWindow("/viewFxml/addAccount.fxml",true);
	}

	@FXML
	void handleBankChoiceHome(ActionEvent event) {
		// TODO
	}
}
