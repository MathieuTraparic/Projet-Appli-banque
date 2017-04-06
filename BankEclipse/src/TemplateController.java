import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class TemplateController{
	
	//TODO add all action on the menu buttons 
	
	@FXML
	void logoutButton(ActionEvent event){
		VistaNavigator.loadVista(VistaNavigator.LOGIN);
	}
	
	
	
}
