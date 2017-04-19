package model;

import java.io.Serializable;
import java.util.Calendar;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import util.Formater;
import util.Validator;

@Entity
@Table(name = "advisor")
@NamedQuery(name = "Advisor.findAll", query = "SELECT t FROM Advisor t")
public class Advisor implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String firstName;
	private String phoneNumber;
	private String email;
	private Date assignmentDate;
	private Agency agency;

	private Advisor() {

	}

	public Advisor(String name, String firstName, String phoneNumber, String email, Date assignmentDate) {

		checkName(name);
		chekFirstName(firstName);
		check_email(email);
		check_assignmentDate(assignmentDate);
		check_phoneNumber(phoneNumber);

		this.name = Formater.formatNameCase(name);
		this.firstName = Formater.formatNameCase(firstName);
		this.phoneNumber = Formater.removeUsualSeparators(phoneNumber);
		this.email = Formater.removeUsualSeparators(email);
		this.assignmentDate = assignmentDate;
	}



	private static void checkName(String name) throws IllegalArgumentException {
		if (name.isEmpty()) {
			throw new IllegalArgumentException("Name cannot be empty");
		} else if (!isValidName(name)) {
			throw new IllegalArgumentException(
					"Name is incorrect, must contains only letters and/or spaces, dashes, apostrophe");
		}
	}

	public static boolean isValidName(String name) {
		return Validator.isValidName(name);
	}

	private static void chekFirstName(String firstname) throws IllegalArgumentException {
		if (firstname.isEmpty()) {
			throw new IllegalArgumentException("First name cannot be empty");
		} else if (!isValidFirstName(firstname)) {
			throw new IllegalArgumentException(
					"First name is incorrect, must contains only letters and/or spaces, dashes, apostrophe");
		}
	}

	public static boolean isValidFirstName(String firstname) {
		return Validator.isValidName(firstname);
	}

	private static void check_assignmentDate(Date assignmentDate) throws IllegalArgumentException {
		if (!isValidAssignmentDate(assignmentDate)) {
			throw new IllegalArgumentException("Assignment date cannot be in the future");
		}
	}

	public static boolean isValidAssignmentDate(Date assignmentDate) {
		Calendar cal = Calendar.getInstance();
		return (assignmentDate.before(cal.getTime()));
	}

	private static void check_email(String email) throws IllegalArgumentException {
		if (email.isEmpty()) {
			throw new IllegalArgumentException("Email cannot be empty");
		} else if (!isValidEmail(email)) {
			throw new IllegalArgumentException("Email must be a valid email address");
		}
	}

	public static boolean isValidEmail(String email) {
		return Validator.isValidEmailAddress(email);
	}

	private static void check_phoneNumber(String phoneNumber) throws IllegalArgumentException {
		if (phoneNumber.isEmpty()) {
			throw new IllegalArgumentException("Phone number cannot be empty");
		} else if (!isValidPhoneNumber(phoneNumber)) {
			throw new IllegalArgumentException(
					"Phone number must be a valid French phone number composed of 10 digits");
		}
	}

	public static boolean isValidPhoneNumber(String phoneNumber) {
		return Validator.isValidPhoneNumber(phoneNumber);
	}

	private String getName() {
		return this.name;
	}

	private void setName(String name) {
		this.name = name;
	}

	private String getFirstName() {
		return this.firstName;
	}

	private void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	private String getPhoneNumber() {
		return this.phoneNumber;
	}

	private void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	private String getEmail() {
		return this.email;
	}

	private void setEmail(String email) {
		this.email = email;
	}
	
	@Temporal(TemporalType.DATE)
	private Date getAssignmentDate() {
		return this.assignmentDate;
	}

	private void setAssignmentDate(Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}

	@ManyToOne
	@JoinColumn(name = "idAgency")
	private Agency getAgency() {
		return this.agency;
	}

	private void setAgency(Agency agency) {
		this.agency = agency;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


}
