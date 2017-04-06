
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LoginFrameController {

	@FXML
	void signInButton(ActionEvent event){
		VistaNavigator.loadVista(VistaNavigator.HOME);
	}
}
