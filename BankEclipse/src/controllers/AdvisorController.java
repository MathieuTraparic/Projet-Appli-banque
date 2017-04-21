package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.jpa.jpql.parser.OnClause;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;
import model.Advisor;
import model.Agency;
import model.Bank;
import util.PopWindow;
import util.DateConverter;

public class AdvisorController implements Initializable {

	@FXML
	public TextField name;
	@FXML
	public TextField firstName;
	@FXML
	public TextField phoneNumber;
	@FXML
	public TextField email;
	@FXML
	public DatePicker assignmentDate;
	@FXML
	public Label invalidName;
	@FXML
	public Label invalidFirstName;
	@FXML
	public Label invalidNumber;
	@FXML
	public Label invalidEmail;
	@FXML
	public Label invalidDate;
	@FXML
	public Label invalidAgency;
	@FXML
	public ComboBox<String> agency;
	@FXML
	public ComboBox<String> bank;
	@FXML
	public Button applyButton;

	private List<Label> labels;

	private List<Node> secondaryFields;
	private List<Node> thirdFields;

	private List<Agency> listAgency;
	private List<Advisor> listAdvisor;
	private ChangeListener<? super String> onChange = null;
	private ChangeListener<? super LocalDate> timeChange = null;

	@FXML
	void applyAdvisorChange(ActionEvent event) {

		// Lambda expression
		labels.forEach(label -> label.setVisible(false));

		Calendar cal = Calendar.getInstance();

		if (agency.getValue() == null) {
			invalidAgency.setVisible(true);
		}

		if (assignmentDate.getValue() == null) {
			invalidDate.setVisible(true);
		} else {
			cal = new GregorianCalendar(assignmentDate.getValue().getYear(),
					assignmentDate.getValue().getMonthValue() - 1, assignmentDate.getValue().getDayOfMonth(), 0, 0, 0);
			if (!Advisor.isValidAssignmentDate(cal.getTime())) {
				invalidDate.setVisible(true);
			}
		}

		if (name.getText().isEmpty() || !Advisor.isValidName(name.getText())) {
			invalidName.setVisible(true);
		}
		if (firstName.getText().isEmpty() || !Advisor.isValidFirstName(firstName.getText())) {
			invalidFirstName.setVisible(true);
		}
		if (phoneNumber.getText().isEmpty() || !Advisor.isValidPhoneNumber(phoneNumber.getText())) {
			invalidNumber.setVisible(true);
		}
		if (email.getText().isEmpty() || !Advisor.isValidEmail(email.getText())) {
			invalidEmail.setVisible(true);
		}

		if (labels.stream().allMatch(label -> label.isVisible() == false)) {
			Agency currentAgency = null;
			for (Agency a : this.listAgency) {
				if (agency.getValue() == a.getName()) {
					currentAgency = a;
				}
			}

			EntityManager em = VistaNavigator.getEmf().createEntityManager();
			em.getTransaction().begin();

			Query q = em.createQuery("UPDATE Advisor a SET a.name=:name WHERE a.agency=:agency");
			q.setParameter("agency", currentAgency);
			q.setParameter("name", name.getText());
			q.executeUpdate();

			Query s = em.createQuery("UPDATE Advisor a SET a.firstName=:firstName WHERE a.agency=:agency");
			s.setParameter("agency", currentAgency);
			s.setParameter("firstName", firstName.getText());
			s.executeUpdate();

			Query d = em.createQuery("UPDATE Advisor a SET a.phoneNumber=:phoneNumber WHERE a.agency=:agency");
			d.setParameter("agency", currentAgency);
			d.setParameter("phoneNumber", phoneNumber.getText());
			d.executeUpdate();

			Query f = em.createQuery("UPDATE Advisor a SET a.email=:email WHERE a.agency=:agency");
			f.setParameter("agency", currentAgency);
			f.setParameter("email", email.getText());
			f.executeUpdate();

			Query g = em.createQuery("UPDATE Advisor a SET a.assignmentDate=:assignmentDate WHERE a.agency=:agency");
			g.setParameter("agency", currentAgency);
			g.setParameter("assignmentDate", cal.getTime());
			g.executeUpdate();

			em.getTransaction().commit();

			em.close();

		}

	}

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		labels = new ArrayList<Label>() {

			private static final long serialVersionUID = 1L;

			{
				add(invalidAgency);
				add(invalidDate);
				add(invalidEmail);
				add(invalidFirstName);
				add(invalidName);
				add(invalidNumber);
			}
		};
		secondaryFields = new ArrayList<Node>() {

			private static final long serialVersionUID = 1L;

			{
				add(assignmentDate);
				add(name);
				add(firstName);
				add(phoneNumber);
				add(email);
				add(agency);
			}
		};

		thirdFields = new ArrayList<Node>() {

			private static final long serialVersionUID = 1L;

			{
				add(assignmentDate);
				add(name);
				add(firstName);
				add(phoneNumber);
				add(email);
			}
		};

		EntityManager em = VistaNavigator.getEmf().createEntityManager();
		List<Bank> b = em.createNamedQuery("Bank.findAll").getResultList();

		em.close();

		for (Bank bankk : b) {
			bank.getItems().add(bankk.getName());
		}
		bank.getItems().add("OTHER");

		this.secondaryFields.forEach(item -> item.setDisable(true));
		this.applyButton.setDisable(true);
		
	}

	@FXML
	void chooseAdvisorBank(ActionEvent event) throws IOException {

		applyButton.setDisable(true);
		name.clear();
		firstName.clear();
		phoneNumber.clear();
		email.clear();
		assignmentDate.setValue(null);

		if (bank.getValue() != null) {
			this.secondaryFields.forEach(item -> item.setDisable(false));
			this.thirdFields.forEach(item -> item.setDisable(true));
		}
		if (bank.getValue().toString() == "OTHER") {
			PopupController<Bank> controller = PopupController.load(
					VistaNavigator.ADD_BANK,false);
			controller.show(new Bank("name","code"),
				new EventHandler<WindowEvent>(){
					@Override
					public void handle(WindowEvent event){
						Bank b = controller.getValidatedData();
						if (b!=null){
							EntityManager em = VistaNavigator.getEmf().createEntityManager();
							em.getTransaction().begin();
							em.persist(b);
							em.getTransaction().commit();
							em.close();
							bank.getItems().add(bank.getItems().size()-1,b.getName());
						}
				}	
			});
	
		} else {
			EntityManager em = VistaNavigator.getEmf().createEntityManager();
			TypedQuery<Bank> b = em.createQuery("SELECT b FROM Bank b WHERE b.name=:name", Bank.class);
			List<Bank> bb = b.setParameter("name", this.bank.getValue()).getResultList();

			if (!bb.isEmpty()) {
				this.agency.getItems().clear();
				TypedQuery<Agency> a = em.createQuery("SELECT  a FROM Agency a WHERE a.bank =:bank", Agency.class);
				this.listAgency = a.setParameter("bank", bb.get(0)).getResultList();
				
				for (Agency agensy : listAgency) {
					agency.getItems().add(agensy.getName());
					agency.getItems().add("OTHER");
				}
			}
			em.close();
		}
	}

	@FXML
	void chooseAdvisorAgency(ActionEvent event) throws IOException {


		
		applyButton.setDisable(true);
		name.clear();
		firstName.clear();
		phoneNumber.clear();
		email.clear();
		assignmentDate.setValue(null);


		
		if(agency.getValue()==null){
			return ;
		}

		if (agency.getValue() != null) {
			this.thirdFields.forEach(item -> item.setDisable(false));
		}
		if (agency.getValue().toString() == "OTHER") {
			
			// TODO refactor

			
			
		} else {
			
			Agency currentAgency = null;
			for (Agency j : this.listAgency) {
				if (agency.getValue() == j.getName()) {
					currentAgency = j;
				}
			}
			
			EntityManager em = VistaNavigator.getEmf().createEntityManager();
			TypedQuery<Advisor> a = em.createQuery("SELECT a FROM Advisor a WHERE a.agency=:agency", Advisor.class);

			listAdvisor = a.setParameter("agency", currentAgency).getResultList();


			if (!listAdvisor.isEmpty()) {

				name.setText(listAdvisor.get(0).getName());
				firstName.setText(listAdvisor.get(0).getFirstName());
				phoneNumber.setText(listAdvisor.get(0).getPhoneNumber());
				email.setText(listAdvisor.get(0).getEmail());
				assignmentDate.setValue(DateConverter.DateToLocalDate(listAdvisor.get(0).getAssignmentDate()));

				onChange = (observable, oldValue, newValue) -> {
					applyButton.setDisable(name.getText() == listAdvisor.get(0).getName()
							|| firstName.getText() == listAdvisor.get(0).getFirstName()
							|| phoneNumber.getText() == listAdvisor.get(0).getPhoneNumber()
							|| email.getText() == listAdvisor.get(0).getEmail());
				};
				name.textProperty().addListener(onChange);
				firstName.textProperty().addListener(onChange);
				phoneNumber.textProperty().addListener(onChange);
				email.textProperty().addListener(onChange);
				
				timeChange = (observable, oldValue, newValue) -> {
					applyButton.setDisable(assignmentDate.getValue() == DateConverter
							.DateToLocalDate(listAdvisor.get(0).getAssignmentDate()));};
			
				
				assignmentDate.valueProperty().addListener(timeChange);

				em.close();

			}
		
/*			applyButton.setDisable(false);
			name.textProperty().removeListener(onChange);
			firstName.textProperty().removeListener(onChange);
			phoneNumber.textProperty().removeListener(onChange);
			email.textProperty().removeListener(onChange);
			assignmentDate.valueProperty().removeListener(timeChange);*/


		}
	}
}
