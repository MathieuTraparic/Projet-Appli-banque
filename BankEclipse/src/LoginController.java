
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class LoginController {

	@FXML
	void signInButton(ActionEvent event){
		VistaNavigator.loadVista(VistaNavigator.TEMPLATE);
		//load the template fxml and get the templateController associated
        FXMLLoader loader2 = new FXMLLoader();
        try {
			loader2.load( getClass().getResourceAsStream(
			        VistaNavigator.TEMPLATE
			    ));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        TemplateController templateController = loader2.getController();
        VistaNavigator.setTemplateController(templateController);
        
		VistaNavigator.loadSubVista(VistaNavigator.VISTA_1);
	}
}
