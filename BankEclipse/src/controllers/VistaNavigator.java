package controllers;



import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import java.io.IOException;

/**
 * Utility class for controlling navigation between vistas.
 *
 * All methods on the navigator are static to facilitate
 * simple access from anywhere in the application.
 */
public class VistaNavigator {

    /**
     * Convenience constants for fxml layouts managed by the navigator.
     */
	public static final String LOGIN = "/viewFxml/login.fxml";
    public static final String MAIN  = "/viewFxml/main.fxml";
	public static final String CREATE_USER_1 = "/viewFxml/createUser_1.fxml";
	public static final String CREATE_USER_2 = "/viewFxml/createUser_2.fxml";
	public static final String HOME = "/viewFxml/home.fxml";
    public static final String TEMPLATE = "/viewFxml/template.fxml";
    public static final String ADD_BANK = "/viewFxml/addBank.fxml";
    

    /** The main application layout controller. */
    private static MainController mainController;




	/**
     * Stores the main controller for later use in navigation tasks.
     *
     * @param mainController the main application layout controller.
     */
    public static void setMainController(MainController mainController) {
        VistaNavigator.mainController = mainController;
    }

    /**
     * Loads the vista specified by the fxml file into the
     * vistaHolder pane of the main application layout.
     *
     * Previously loaded vista for the same fxml file are not cached.
     * The fxml is loaded anew and a new vista node hierarchy generated
     * every time this method is invoked.
     *
     * A more sophisticated load function could potentially add some
     * enhancements or optimizations, for example:
     *   cache FXMLLoaders
     *   cache loaded vista nodes, so they can be recalled or reused
     *   allow a user to specify vista node reuse or new creation
     *   allow back and forward history like a browser
     *
     * @param fxml the fxml file to be loaded.
     */
    public static void loadVista(String fxml) {
        try {
            mainController.setVista(
                (Node) FXMLLoader.load(
                    VistaNavigator.class.getResource(
                        fxml
                    )
                )
            );
        } catch (IOException e) {
            System.err.println("Vista loading failed : "+ e);
        }
    }
    
    
}