
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LoginController {

	@FXML
	void signInButton(ActionEvent event){
		VistaNavigator.loadVista(VistaNavigator.TEMPLATE);
	}
}
