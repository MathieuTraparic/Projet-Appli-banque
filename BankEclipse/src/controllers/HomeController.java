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
		PopWindow addBankPop = new PopWindow(VistaNavigator.ADD_BANK,false,this.getClass());
	}

	@FXML
	void handleAddAgencyHome(ActionEvent event) throws IOException {
		PopWindow addAgencyPop = new PopWindow(VistaNavigator.ADD_AGENCY,false,this.getClass());
	}

	@FXML
	void handleAddAccountHome(ActionEvent event) throws IOException{
		PopWindow addAccountPop = new PopWindow(VistaNavigator.ADD_ACCOUNT,true,this.getClass());
	}

	@FXML
	void handleBankChoiceHome(ActionEvent event) {
		// TODO
	}
}
