package controllers.popups;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import controllers.VistaNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Address;
import model.Agency;
import model.Bank;
import model.CpVille;

/**
 * @author Groupe
 * This popup allows the creation of a new agency in the database.  
 */
public class AddAgencyController extends PopupController<Agency> implements Initializable {

	/*
	 * Variables of the class
	 */
	@FXML
	public Button addAgencyCancel, addAgencySubmit;
	@FXML
	public TextField agencyName, agencyCode, addressLine1, addressLine2, cityName, zipCode;
	@FXML
	public Label agencyNameError, agencyCodeError, agencyBankError, addressLine1Error, cityNameError, zipCodeError;
	@FXML
	public ComboBox<String> linkedBank;
	private List<Bank> l = null;
	private List<CpVille> cp = null;
	private List<Address> ad = null;
	private List<Label> errorLabels;
	private List<Agency> list;
	
	/*
	 * (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		// Initialization the list by data extracted from the data base
		EntityManager em = VistaNavigator.getEmf().createEntityManager();
		this.l = em.createNamedQuery("Bank.findAll").getResultList();
		this.cp = em.createNamedQuery("CpVille.findAll").getResultList();
		this.ad = em.createNamedQuery("Address.findAll").getResultList();
		em.close();
		//ComboBox filled
		for (Bank b : l) {
			linkedBank.getItems().add(b.getName());
		}
		// Initialization of the error Labels : by default these are not visible
		this.errorLabels = new ArrayList<Label>() {
			private static final long serialVersionUID = 6275258056275001066L;
			{
				add(agencyNameError);
				add(agencyCodeError);
				add(agencyBankError);
				add(addressLine1Error);
				add(cityNameError);
				add(zipCodeError);
			}
		};
		errorLabels.forEach(label -> label.setVisible(false));
	}

	@Override
	protected void initializePopupFields(Agency data) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * @param event : managenment of the event on Close button
	 */
	@FXML
	void handleAddAgencyCancel(ActionEvent event) {
		Stage stage = (Stage) addAgencyCancel.getScene().getWindow();
		stage.close();
	}

	/**
	 * @param event : By clicking on Submit the event saves the new agency. The commit is set on the
	 * HomeController
	 */
	@FXML
	void handleAddAgencySubmit(ActionEvent event) {

		errorLabels.forEach(label -> label.setVisible(false));

		String name = agencyName.getText();
		String code = agencyCode.getText();
		String line1 = addressLine1.getText();
		String line2 = addressLine2.getText();
		String city = cityName.getText();
		String zip = zipCode.getText();

		if (name.isEmpty()) {
			agencyNameError.setVisible(true);
		}
		if (code.isEmpty()) {
			agencyNameError.setVisible(true);
		}
		if (line1.isEmpty()) {
			addressLine1Error.setVisible(true);
		}
		if (city.isEmpty()) {
			cityNameError.setVisible(true);
		}
		if (zip.isEmpty()) {
			zipCodeError.setVisible(true);
		}

		if (errorLabels.stream().allMatch(label -> !label.isVisible())) {
			EntityManager em = VistaNavigator.getEmf().createEntityManager();
			TypedQuery<Agency> q = em.createQuery("SELECT n FROM Agency n " + "WHERE n.name=:name", Agency.class);
			list = q.setParameter("name", name).getResultList();
			if (!list.isEmpty()) {
				agencyNameError.setVisible(true);
			}

			else {
				Bank currentBank = null;
				for (Bank bank : l) {
					if (bank.getName().equals(this.linkedBank.getValue())) {
						currentBank = bank;
					}
				}
				
				Address currentAddress = null;
				for(Address address : ad){
					if(address.getLine1().equals(line1)){
						currentAddress = address;
					}
				}
				int ind;
				// There is a new instance of Address only if the address doesn't exist in the database
				if(currentAddress!=null){
					this.getData().setAdress(currentAddress);
					ind = 1;
				} else {
					currentAddress = new Address(line1, line2);
					this.getData().setAdress(currentAddress);
					ind = 0;
				}				
				
				CpVille currentCpVille = null;
				for(CpVille cpville : cp){
					if(cpville.getZip().equals(zip)){
						currentCpVille = cpville;
					}
				}
				/*
				 * To respesct the link between CpVille and Address table in the database
				 * we commit the new instances of the object CpVille and then Addresse in 
				 * the database. This syntaxe allows to respect the uniqueness of the data 
				 * in the database
				 */
				if(currentCpVille!=null){
					currentAddress.setCpVille(currentCpVille);
					this.getData().setAdress(currentAddress);
					if(ind==0){
						em.getTransaction().begin();
						em.persist(currentAddress);
						em.getTransaction().commit();
					}
				} else{
					currentCpVille = new CpVille(zip, city);
					currentAddress.setCpVille(currentCpVille);
					em.getTransaction().begin();
					em.persist(currentCpVille);
					em.persist(currentAddress);
					em.getTransaction().commit();
				}
				// Saving of the data. The commit is set in the HomeController
				this.getData().setBank(currentBank);
				this.getData().setName(name);
				this.getData().setCounterCode(code);
				this.setAsValidated();
				
				Stage stage = (Stage) addAgencySubmit.getScene().getWindow();
				stage.close();
			}
			em.close();
		}
	}
}
