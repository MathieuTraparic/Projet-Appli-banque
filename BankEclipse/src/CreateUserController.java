import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class CreateUserController {
	@FXML
	void nextStepButton(ActionEvent event){
		VistaNavigator.loadVista(VistaNavigator.CREATE_USER_2);
	}
}
