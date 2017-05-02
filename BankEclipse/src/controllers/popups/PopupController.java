package controllers.popups;

import java.io.IOException;
import java.net.URL;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @author Groupe
 *
 * @param <T> this a generic type for PopupController. That allow to create a typed popup
 * So every popup must extends PopupController
 */
public abstract class PopupController<T> {
	
	Image icon = new Image("file:..\\ressource\\images\\icon2.png");

	public PopupController() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param data : take the type of the popup
	 */
	public void initData(T data) {
		this.data = data;
	}
	protected abstract void initializePopupFields(T data);
	
	/**
	 * @return these permit to get <T>data set in the popup. 
	 * This method is essential to save data after the closing of the popup
	 */
	public T getValidatedData() {
		return this.validated ? this.data : null;
	}
	
	protected T getData() { return this.data; }
	
	/**
	 * Saving the data before the closing of the popup
	 */
	protected void setAsValidated() {
		this.validated = true;
	}
	
	/**
	 * @param fxmlLocation name of the fxml define in the class VistaNavigator
	 * @param combo : boolean the set the presence of a combobox in the popup this syntaxe is no ideal
	 * @return return a controller<T>
	 * @throws IOException
	 */
	public static<T> PopupController<T> load(String fxmlLocation, boolean combo) throws IOException {
		FXMLLoader fxmlLoader;
		if(combo){
			fxmlLoader = new FXMLLoader(
					new URL(PopupController.class.getResource(fxmlLocation).toExternalForm()));
		}
		else{
			fxmlLoader = new FXMLLoader(PopupController.class.getResource(fxmlLocation));
		}
		Parent root = (Parent) fxmlLoader.load();
		PopupController<T> controller = fxmlLoader.getController();
		
		controller.root = root;
		return controller;
	}
	/**
	 * @param data 
	 * @param handler event wich allow use getValidatedData() in order to commit this.data in
	 * the database
	 */
	public void show(T data, EventHandler<WindowEvent> handler) {
		Stage stage = new Stage();
		
		this.initData(data);
		stage.setScene(new Scene(this.root));
		stage.getIcons().add(icon);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
		stage.setOnHidden(handler);
	}
	private Parent root;
	private T data;
	private boolean validated = false;
}
