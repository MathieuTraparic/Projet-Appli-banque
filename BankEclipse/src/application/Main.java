package application;

import java.io.IOException;

import javax.persistence.Persistence;

import controllers.MainController;
import controllers.VistaNavigator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Main application class.
 */
public class Main extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		/*
		 * Parent root = FXMLLoader.load(getClass().getResource(
		 * VistaNavigator.LOGIN));
		 * stage.initModality(Modality.APPLICATION_MODAL); Scene scene = new
		 * Scene(root); stage.setScene(scene);
		 * stage.initOwner(stage.getScene().getWindow());
		 */
		VistaNavigator.setEmf(Persistence.createEntityManagerFactory("Bank"));
		stage.setTitle("Personnal Bank Account Manager");
		stage.getIcons().add(new Image("file:..\\ressource\\images\\icon2.png"));
		stage.setScene(createScene(loadMainPane()));

		stage.show();
	}

	/**
	 * Loads the main fxml layout. Sets up the vista switching VistaNavigator.
	 * Loads the first vista into the fxml layout.
	 *
	 * @return the loaded pane.
	 * @throws IOException
	 *             if the pane could not be loaded.
	 */
	private Pane loadMainPane() throws IOException {
		FXMLLoader loader = new FXMLLoader();

		Pane mainPane = (Pane) loader.load(getClass().getResourceAsStream(VistaNavigator.MAIN));
		MainController mainController = loader.getController();

		VistaNavigator.setMainController(mainController);
		VistaNavigator.loadVista(VistaNavigator.LOGIN);
		return mainPane;
	}

	/**
	 * Creates the main application scene.
	 *
	 * @param mainPane
	 *            the main application layout.
	 *
	 * @return the created scene.
	 */
	private Scene createScene(Pane mainPane) {
		Scene scene = new Scene(mainPane);
		scene.getStylesheets().setAll(getClass().getResource("application.css").toExternalForm());
		return scene;
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void stop() throws Exception {
		VistaNavigator.getEmf().close();
		super.stop();
	}
}