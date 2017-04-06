

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * Main controller class for the entire layout.
 */
public class TemplateController {

    /** Holder of a switchable vista. */
    @FXML
    private Pane vistaHolder;
    private Pane vistaLogHolder;

    /**
     * Replaces the vista displayed in the vista holder with a new vista.
     *
     * @param node the vista node to be swapped in.
     */
    public void setVista(Node node) {
        vistaHolder.getChildren().setAll(node);
    }
    
    @FXML
	void logoutButton(ActionEvent event){
		VistaNavigator.loadVista(VistaNavigator.LOGIN);
	}

}