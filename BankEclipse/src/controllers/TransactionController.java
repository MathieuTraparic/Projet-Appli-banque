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

public class TransactionController {
	
	@FXML
	void handleAddTransaction(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(
				new URL(TransactionController.class.getResource("/viewFxml/addTransaction.fxml").toExternalForm()));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
	}
	
}
