package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

public class SampleController {
	 @FXML private Text actiontarget;
	 
	 @FXML private PasswordField passwordField;
	 
	 @FXML protected void handleSubmitButtonAction(ActionEvent event) {
	        actiontarget.setText(passwordField.getText());
	    }
	 @FXML protected void handleSignUpButtonAction(ActionEvent event) throws IOException {
	        actiontarget.setText("Sign up button pressed");
	        Node node = (Node) event.getSource();
	        Stage stage =  (Stage) node.getScene().getWindow();
	        stage.setTitle("create user");
	        //stage.set
	        GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("createUser.fxml"));
			Scene scene = new Scene(root,400,300);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			//stage.show();
			
			
			
	    }
	 @FXML protected void handleSubmitNewUserButtonAction(ActionEvent event) {
	       //handle event
	    }
	 @FXML protected void handleBackButtonAction(ActionEvent event) {
	       //handle event
	 }
	 
	 
}
