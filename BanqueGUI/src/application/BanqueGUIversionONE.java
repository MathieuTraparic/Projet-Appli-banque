package application;

//import java.awt.TextField;

//import java.awt.Insets;
//import java.awt.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.Button;
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
 

public class BanqueGUIversionONE extends Application {
    
    @Override
    public void start(Stage primaryStage) {
    	try {
    	GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 1600, 775);
        
        BackgroundFill myBF = new BackgroundFill(Color.BEIGE, new CornerRadii(1),
                new Insets(0.0,0.0,0.0,0.0));// or null for the padding
       //then you set to your node or container or layout
        grid.setBackground(new Background(myBF));

        
        Text scenetitle = new Text("Welcome to MyBank!");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

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
        hbBtn1.setAlignment(Pos.BOTTOM_CENTER);
        hbBtn1.getChildren().add(btn1);
        grid.add(hbBtn1, 1, 4);
    	
        
        Text author = new Text("@Developped by David, Lucien and Mathieu. All right reserved, 2017.");
        author.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        grid.add(author, 1, 10);
        
        primaryStage.getIcons().add(new Image("file:ressource/images/icon2.png"));

        primaryStage.setTitle("MaBanque!");
        primaryStage.setScene(scene);
        primaryStage.show();
    	}
    	catch (Exception e){
    		System.err.println("something's wrong");
    	}
    }
 public static void main(String[] args) {
        launch(args);
    }
}
