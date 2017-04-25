package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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

public class AddAgencyController extends PopupController<Agency> implements Initializable {

	@FXML
	public Button addAgencyCancel, addAgencySubmit;
	@FXML
	public TextField agencyName, agencyCode, addressLine1, addressLine2, cityName, zipCode;
	@FXML
	public Label agencyNameError, agencyCodeError, agencyBankError, addressLine1Error, cityNameError, zipCodeError;
	@FXML
	public ComboBox<String> linkedBank;
	private List<Bank> l = null;
	private List<Label> errorLabels;
	private List<Agency> list;

	@FXML
	void handleAddAgencyCancel(ActionEvent event) {
		Stage stage = (Stage) addAgencyCancel.getScene().getWindow();
		stage.close();
	}

	@FXML
	void handleAddAgencySubmit(ActionEvent event) {

		errorLabels.forEach(label -> label.setVisible(false));

		String name = agencyName.getText();
		String code = agencyCode.getText();
		String line1 = addressLine1.getText();
		String line2 = addressLine2.getText();
		String city = cityName.getText();
		String zip = zipCode.getText();
		String agencyBank = linkedBank.getValue();

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

			// I don't think it is necessary to check both name and code but if
			// yes, then we need to check everything I would say
			// list = q.setParameter("code", code).getResultList();

			if (!list.isEmpty()) {
				agencyNameError.setVisible(true);
			}

			else {

				Stage stage = (Stage) addAgencySubmit.getScene().getWindow();

				Bank currentBank = null;
				for (Bank bank : l) {
					if (bank.getName().equals(this.linkedBank.getValue())) {
						currentBank = bank;
					}
				}

				CpVille zipCityAgency = new CpVille(zip, city);
				Address agencyAddress = new Address(line1, line2);

				agencyAddress.setCpVille(zipCityAgency);

				em.getTransaction().begin();
				em.persist(zipCityAgency);
				em.persist(agencyAddress);
				em.getTransaction().commit();

				this.getData().setBank(currentBank);
				this.getData().setAdress(agencyAddress);

				this.getData().setName(name);
				this.getData().setCounterCode(code);
				this.setAsValidated();
				stage.close();
			}
			em.close();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		EntityManager em = VistaNavigator.getEmf().createEntityManager();
		this.l = em.createNamedQuery("Bank.findAll").getResultList();
		em.close();
		for (Bank b : l) {
			linkedBank.getItems().add(b.getName());
		}

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
}
