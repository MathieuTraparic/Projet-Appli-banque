import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.Optional;


public class TemplateController{
	
	//TODO add all action on the menu buttons 
	 /** Holder of a switchable vista. */
    @FXML
    protected Pane subHolder;
    
    @FXML
    protected Text textid;
    
	@FXML
	void logoutButton(ActionEvent event){
		VistaNavigator.loadVista(VistaNavigator.LOGIN);
		/*
		 * Alert alert = new Alert(Alert.Type.CONFIRMATION, "Are you sure ?",ButtonType.OK, ButtonType.CANCEL);
		 
		Optional<ButtonType> result = alert.showAndWait();
		*/
		
		
	}
	
	@FXML
	void hanfdleHomeButton(ActionEvent event){
		textid.setText("doing things");
		//VistaNavigator.loadSubVista(VistaNavigator.VISTA_1);
		VistaNavigator.loadVista(VistaNavigator.VISTA_1);
		
		
	}

	public void setSubVista(Node load) {
		subHolder.getChildren().setAll(load);
	}
	
	
	
}
