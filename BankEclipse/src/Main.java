

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main application class.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
    	/*
    	Parent root = FXMLLoader.load(getClass().getResource(
                        VistaNavigator.LOGIN));
    	stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initOwner(stage.getScene().getWindow());
        */
        
        stage.setTitle("Personnal Bank Account Manager");
        stage.setScene(
            createScene(
                loadMainPane()
            )
        );
        
        stage.show();
    }

    /**
     * Loads the main fxml layout.
     * Sets up the vista switching VistaNavigator.
     * Loads the first vista into the fxml layout.
     *
     * @return the loaded pane.
     * @throws IOException if the pane could not be loaded.
     */
    private Pane loadMainPane() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = (Pane) loader.load(
            getClass().getResourceAsStream(
                VistaNavigator.MAIN
            )
        );

        MainController mainController = loader.getController();
        //TemplateController templateController = loader.getController();
        
        VistaNavigator.setMainController(mainController);
        //VistaNavigator.setTemplateController(templateController);
        VistaNavigator.loadVista(VistaNavigator.LOGIN);
        return mainPane;
    }

    /**
     * Creates the main application scene.
     *
     * @param mainPane the main application layout.
     *
     * @return the created scene.
     */
    private Scene createScene(Pane mainPane) {
        Scene scene = new Scene(
            mainPane
        );

        scene.getStylesheets().setAll(
            getClass().getResource("vista.css").toExternalForm()
        );

        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}