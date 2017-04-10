package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class TemplateController{
	
	//TODO add all action on the menu buttons 
	
	@FXML
	void handleLogoutButton(ActionEvent event){
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
	
	
	
	
}
