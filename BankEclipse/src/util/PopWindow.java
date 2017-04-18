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
	private Class<?> callingclass;
	
	/**
	 * 
	 * @param fxmlLocation : path of the fxml file
	 * @param combo : a boolean for the presence or not of combobox in the popup
	 * @throws IOException
	 */
	
	public PopWindow(String fxmlLocation, boolean combo, Class<?> callingclass) throws IOException{
		this.fxmlLocation = fxmlLocation;
		this.combo = combo;
		this.callingclass = callingclass;
		
		popWindow(fxmlLocation, combo, callingclass);
	}
	
	private void popWindow(String fxmlLocation, boolean combo, Class<?> callingclass) throws IOException{
		FXMLLoader fxmlLoader;
		if(combo){
			fxmlLoader = new FXMLLoader(
					new URL(callingclass.getResource(fxmlLocation).toExternalForm()));
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
