package controllers;

import java.util.Optional;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;

public class TemplateController{
	
	//TODO add all action on the menu buttons 
	
	@FXML
	void handleLogoutButton(ActionEvent event){
		VistaNavigator.getInstance().setLoggedOwner(null);
		VistaNavigator.loadVista(VistaNavigator.LOGIN);
	}
	
	@FXML
	void handleCancel(ActionEvent event){
		//TODO
	}
	
	@FXML
	void handleSubmit(ActionEvent event){
		//TODO
	}
	
	
	// Event on menu bar
	@FXML
	void handleMenuFileOpen(ActionEvent event){
		// TODO
	}
	
	@FXML
	void handleMenuFileExport(ActionEvent event){
		// TODO
	}
	
	@FXML
	void handleMenuFileQuit(ActionEvent event){
		Alert alert = new Alert(
				AlertType.CONFIRMATION,
				"Do you want to quit the application ?",
				ButtonType.OK,
				ButtonType.CANCEL
		);
		Optional<ButtonType> result = alert.showAndWait();
		
		if(result.isPresent() && result.get() == ButtonType.OK) {
			Platform.exit();
		}
	}
	
	@FXML
	void handleMenuWindowPreference(ActionEvent event){
		// TODO
	}
	
	@FXML
	void handleMenuHelpTutorial(ActionEvent event){
		//TODO
	}
}
