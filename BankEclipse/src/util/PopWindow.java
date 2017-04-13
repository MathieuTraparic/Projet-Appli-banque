package util;

import java.io.IOException;
import java.net.URL;

import controllers.TransactionController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopWindow {

	private String fxmlLocation;
	private boolean combo;
	
	public PopWindow(String fxmlLocation, boolean combo) throws IOException{
		this.fxmlLocation = fxmlLocation;
		this.combo = combo;
		popWindow(fxmlLocation, combo);
	}
	
	/**
	 * 
	 * @param fxmlLocation : acces of the fxml file
	 * @param combo : boolean for the presence of combo box in popup
	 * @throws IOException
	 */
	
	private void popWindow(String fxmlLocation, boolean combo) throws IOException{
		FXMLLoader fxmlLoader;
		if(combo){
			fxmlLoader = new FXMLLoader(
					new URL(TransactionController.class.getResource(fxmlLocation).toExternalForm()));
		}
		else{
			fxmlLoader = new FXMLLoader(getClass().getResource(fxmlLocation));
		}
		Parent root1 = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
	}

}
