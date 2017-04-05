package application;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.*;

public class Old extends Application{

	
	@Override
	public void start(Stage logPage){
		// Definition of the grid
		GridPane grid =new GridPane();
		
		// Definition of the Start page
		startPage(grid, logPage);
	}
	

	
	public void startPage(GridPane grid, Stage logPage){
		Scene scene = new Scene(grid, 1500, 775);
		
        
        
//        btn1.setOnAction(new EventHandler<ActionEvent>(){
//        	@Override public void handle(ActionEvent e){
//        		Stage createUser = new Stage();
//        		createUser.setTitle("Create user");
//        		
//        		Text scenetitle = new Text("Creation of a new User");
//                scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
//                 
//               // GridPane grid2 = gridDefinition(10,10);
//                
//                Scene scene = new Scene(grid2,500,300);
//                Label userName = new Label("Enter a user name ");
//                TextField tempUserNameKey = new TextField();
//                grid2.add(userName, 0, 0);
//                grid2.add(tempUserNameKey,1,0);
//                                
//                Label pass1 = new Label("Enter a password ");
//                PasswordField tempPassKey1 = new PasswordField();
//                String passKey1 = tempPassKey1.getText();
//                grid2.add(pass1, 0, 1);
//                grid2.add(tempPassKey1, 1, 1);
//                
//                Label pass2 = new Label("Confirm your password ");
//                PasswordField tempPassKey2 = new PasswordField();
//                String passKey2 = tempPassKey2.getText();
//                grid2.add(pass2, 0, 2);
//                grid2.add(tempPassKey2, 1, 2);
//                
//                Label mail = new Label("Enter a valid e-mail ");
//                TextField mailKey = new TextField();
//                grid2.add(mail, 0, 3);
//                grid2.add(mailKey, 1, 3);
//                
//                Button btnCreate = new Button("Create");
//                HBox btnCreate2 = new HBox(10);
//                btnCreate2.setAlignment(Pos.BOTTOM_RIGHT);
//                btnCreate2.getChildren().add(btnCreate);
//                grid2.add(btnCreate2, 1, 4);
//               /* btnCreate.setOnAction(new EventHandler<ActionEvent>(){
//                	@Override public void handle(ActionEvent e){                		
//                		if(!(passKey2.equals(passKey1))){
//                        	Alert alert = new Alert(AlertType.ERROR, "The passwords are different ! ", ButtonType.OK);
//                        	alert.showAndWait();
//                        	tempPassKey2.clear();
//                        }
//                	}
//                });*/
//                
//                createUser.setScene(scene);
//                createUser.show();
//        		
//        	}
//        });
//		
        Text author = new Text("@Developped by David, Lucien and Mathieu. All right reserved, 2017.");
        author.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        grid.add(author, 1, 10);
        
        logPage.getIcons().add(new Image("file:ressource/images/icon2.png"));

        logPage.setTitle("MaBanque!");
        logPage.setScene(scene);
        logPage.show();
	}
}
