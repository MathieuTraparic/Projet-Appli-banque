package util;

import java.io.IOException;
import java.net.URL;

import controllers.PopupController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopWindow {

	private String fxmlLocation;
	private boolean combo;
	
	/**
	 * 
	 * @param fxmlLocation : path of the fxml file
	 * @param combo : a boolean for the presence or not of combobox in the popup
	 * @throws IOException
	 */
	
	public PopWindow(String fxmlLocation, boolean combo) throws IOException{
		this.fxmlLocation = fxmlLocation;
		this.combo = combo;
	}
	
	public<T> T showAndWait(T data) throws IOException{
		FXMLLoader fxmlLoader;
		if(combo){
			fxmlLoader = new FXMLLoader(
					new URL(this.getClass().getResource(fxmlLocation).toExternalForm()));
		}
		else{
			fxmlLoader = new FXMLLoader(getClass().getResource(fxmlLocation));
		}
		Parent root1 = (Parent) fxmlLoader.load();
		PopupController<T> controller = fxmlLoader.getController();
		Stage stage = new Stage();
		
		controller.initData(data);
		stage.setScene(new Scene(root1));
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
		return controller.getValidatedData();
	}

}
