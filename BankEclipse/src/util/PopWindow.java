package util;

import java.io.IOException;
import java.net.URL;

import controllers.popups.PopupController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
	
	public<T> void show(T data, EventHandler<WindowEvent> handler) throws IOException{
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
		stage.setOnCloseRequest(handler);
	}

}
