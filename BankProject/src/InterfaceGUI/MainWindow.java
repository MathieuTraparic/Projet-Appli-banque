package InterfaceGUI;

import java.awt.event.ActionListener;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import java.util.Scanner;

public class MainWindow extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage logPage){
		// Definition of the grid
		GridPane grid = gridDefinition(10,10);
		
		// Definition of the Start page
		startPage(grid, logPage);
	}
	

	public GridPane gridDefinition(int hgap, int vgap){
		GridPane grid = new GridPane();
		grid.setHgap(hgap);
		grid.setVgap(vgap);
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(25,25,25,25));;
		
		BackgroundFill myBF = new BackgroundFill(Color.BEIGE, new CornerRadii(1),
                new Insets(0.0,0.0,0.0,0.0));
		grid.setBackground(new Background(myBF));
		return grid;
	}
	
	public void startPage(GridPane grid, Stage logPage){
		Scene scene = new Scene(grid, 1500, 775);
		Text sceneTitle = new Text("Welcome to MyBank !");
		sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(sceneTitle,0,0,2,1);
		Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1,2 );
        
        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);
        
        
        Button btn1 = new Button("New account");
        HBox hbBtn1 = new HBox(10);
        hbBtn1.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn1.getChildren().add(btn1);
        grid.add(hbBtn1, 1, 4);
        
        btn1.setOnAction(new EventHandler<ActionEvent>(){
        	@Override public void handle(ActionEvent e){
        		Stage createUser = new Stage();
        		createUser.setTitle("Create user");
        		
        		Text scenetitle = new Text("Creation of a new User");
                scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                 
                GridPane grid2 = gridDefinition(10,10);
                
                Scene scene = new Scene(grid2,500,300);
                Label userName = new Label("Enter a user name ");
                TextField tempUserNameKey = new TextField();
                grid2.add(userName, 0, 0);
                grid2.add(tempUserNameKey,1,0);
                                
                Label pass1 = new Label("Enter a password ");
                PasswordField tempPassKey1 = new PasswordField();
                String passKey1 = tempPassKey1.getText();
                grid2.add(pass1, 0, 1);
                grid2.add(tempPassKey1, 1, 1);
                
                Label pass2 = new Label("Confirm your password ");
                PasswordField tempPassKey2 = new PasswordField();
                String passKey2 = tempPassKey2.getText();
                grid2.add(pass2, 0, 2);
                grid2.add(tempPassKey2, 1, 2);
                
                Label mail = new Label("Enter a valid e-mail ");
                TextField mailKey = new TextField();
                grid2.add(mail, 0, 3);
                grid2.add(mailKey, 1, 3);
                
                Button btnCreate = new Button("Create");
                HBox btnCreate2 = new HBox(10);
                btnCreate2.setAlignment(Pos.BOTTOM_RIGHT);
                btnCreate2.getChildren().add(btnCreate);
                grid2.add(btnCreate2, 1, 4);
                btnCreate.setOnAction(new EventHandler<ActionEvent>(){
                	@Override public void handle(ActionEvent e){                		
                		if(!(passKey2.equals(passKey1))){
                        	Alert alert = new Alert(AlertType.ERROR, "The passwords are different ! ", ButtonType.OK);
                        	alert.showAndWait();
                        	tempPassKey2.clear();
                        }
                	}
                });
                
                createUser.setScene(scene);
                createUser.show();
        		
        	}
        });
		
        Text author = new Text("@Developped by David, Lucien and Mathieu. All right reserved, 2017.");
        author.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        grid.add(author, 1, 10);
        
        logPage.getIcons().add(new Image("file:ressource/images/icon2.png"));

        logPage.setTitle("MaBanque!");
        logPage.setScene(scene);
        logPage.show();
	}
}
