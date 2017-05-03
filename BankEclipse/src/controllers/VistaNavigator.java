package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import model.Owner;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;

/**
 * Utility class for controlling navigation between vistas.
 *
 * All methods on the navigator are static to facilitate simple access from
 * anywhere in the application.
 */
public class VistaNavigator {

	/**
	 * Convenience constants for fxml layouts managed by the navigator.
	 */
	public static final String LOGIN = "/viewFxml/login.fxml";
	public static final String MAIN = "/viewFxml/main.fxml";
	public static final String CREATE_USER_1 = "/viewFxml/createUser_1.fxml";
	public static final String CREATE_USER_2 = "/viewFxml/createUser_2.fxml";
	public static final String HOME = "/viewFxml/home.fxml";
	public static final String TEMPLATE = "/viewFxml/template.fxml";
	public static final String ADD_BANK = "/viewFxml/addBank.fxml";
	public static final String ADD_AGENCY = "/viewFxml/addAgency.fxml";
	public static final String ADD_ACCOUNT = "/viewFxml/addAccount.fxml";
	public static final String NEW_USER_GUIDE = "/viewFxml/newUserGuide.fxml";
	public static final String EXPORT = "/viewFxml/ExportTransaction.fxml";
	public static final String NEW_LOGIN = "/viewFxml/newLogin.fxml";

	/** The main application layout controller. */
	private static MainController mainController;

	private static EntityManagerFactory emf;
	
	private Owner loggedOwner;
	private static VistaNavigator instance =null;

	private VistaNavigator(){
		this.loggedOwner=null;
	}
	public static VistaNavigator getInstance(){
		if(instance==null){
			instance=new VistaNavigator();
		}
		return instance;
			
	}
public static void setEmf(EntityManagerFactory emf) {
		VistaNavigator.emf = emf;
	}

	public static EntityManagerFactory getEmf() {
		return emf;
	}

	/**
	 * Stores the main controller for later use in navigation tasks.
	 *
	 * @param mainController
	 *            the main application layout controller.
	 */
	public static void setMainController(MainController mainController) {
		VistaNavigator.mainController = mainController;
	}

	/**
	 * Loads the vista specified by the fxml file into the vistaHolder pane of
	 * the main application layout.
	 *
	 * Previously loaded vista for the same fxml file are not cached. The fxml
	 * is loaded anew and a new vista node hierarchy generated every time this
	 * method is invoked.
	 *
	 * A more sophisticated load function could potentially add some
	 * enhancements or optimizations, for example: cache FXMLLoaders cache
	 * loaded vista nodes, so they can be recalled or reused allow a user to
	 * specify vista node reuse or new creation allow back and forward history
	 * like a browser
	 *
	 * @param fxml
	 *            the fxml file to be loaded.
	 */

	public static Object loadVista(String fxml) {
		FXMLLoader loader = new FXMLLoader(VistaNavigator.class.getResource(fxml));
		
		try {
			mainController.setVista((Node) loader.load());
			
		} catch (IOException e) {
			System.err.println("Vista loading failed : " + e);
			e.printStackTrace();
		}
		
		return loader.getController();
	}
	public Owner getLoggedOwner() {
		return this.loggedOwner;
	}
	public void setLoggedOwner(Owner loggedOwner) {
		this.loggedOwner = loggedOwner;
	}
	
	

}