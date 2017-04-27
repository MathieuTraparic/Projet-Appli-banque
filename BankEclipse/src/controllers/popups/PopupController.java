package controllers.popups;

import java.io.IOException;
import java.net.URL;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public abstract class PopupController<T> {

	public PopupController() {
		// TODO Auto-generated constructor stub
	}
	public void initData(T data) {
		this.data = data;
	}
	protected abstract void initializePopupFields(T data);
	
	public T getValidatedData() {
		return this.validated ? this.data : null;
	}
	
	protected T getData() { return this.data; }
	
	protected void setAsValidated() {
		this.validated = true;
	}
	
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
	public void show(T data, EventHandler<WindowEvent> handler) {
		Stage stage = new Stage();
		
		this.initData(data);
		stage.setScene(new Scene(this.root));
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
		stage.setOnHidden(handler);
	}
	private Parent root;
	private T data;
	private boolean validated = false;
}
