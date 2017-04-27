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



	private static final long serialVersionUID = -882488167799880777L;
	private int id;
	private String name;
	private String firstName;
	private String phoneNumber;
	private String email;
	private Date assignmentDate;
	private Agency agency;

	/*
	 * Used only by the ORM
	 */
	@SuppressWarnings("unused")
	private Advisor() {

	}

	/**Constructor
	 * @param name contains only letters, spaces, apostrophe and dashes
	 * @param firstName contains only letters, spaces, apostrophe and dashes
	 * @param phoneNumber only accept French number ex: +33610203010 or 0033610203040 or 0610203040
	 * @param email ex : azer@aze.azeaze or aze@ze.zeee
	 * @param assignment date not in the future
	 */
	@Deprecated
	public Advisor(String name, String firstName, String phoneNumber, String email, 
			Date assignmentDate) {

		this.setName(Formater.formatNameCase(name));
		this.setFirstName(Formater.formatNameCase(firstName));
		this.setPhoneNumber(Formater.removeUsualSeparators(phoneNumber));
		this.setEmail(Formater.removeUsualSeparators(email));
		this.setAssignmentDate(assignmentDate);
		
	}
	
	/**Constructor
	 * @param name contains only letters, spaces, apostrophe and dashes
	 * @param firstName contains only letters, spaces, apostrophe and dashes
	 * @param phoneNumber only accept French number ex: +33610203010 or 0033610203040 or 0610203040
	 * @param email ex : azer@aze.azeaze or aze@ze.zeee
	 * @param assignment date not in the future
	 * @param agency 
	 */
	public Advisor(String name, String firstName, String phoneNumber, String email, Date assignmentDate,
			Agency agency) {
		this.setName(Formater.formatNameCase(name));
		this.setFirstName(Formater.formatNameCase(firstName));
		this.setPhoneNumber(Formater.removeUsualSeparators(phoneNumber));
		this.setEmail(Formater.removeUsualSeparators(email));
		this.setAssignmentDate(assignmentDate);
		this.setAgency(agency);
	}

	private static void checkName(String name) throws IllegalArgumentException {
		if (name.isEmpty()) {
			throw new IllegalArgumentException("Name cannot be empty");
		} else if (!isValidName(name)) {
			throw new IllegalArgumentException(
					"Name is incorrect, must contains only letters and/or spaces, dashes, apostrophe");
		}
	}

	/**
	 * @param  name
	 * @return true if name contains only letters, spaces, apostrophe and dashes
	 */
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

	/**
	 * @param first name
	 * @return true if first  name contains only letters, spaces, apostrophe and dashes
	 */
	public static boolean isValidFirstName(String firstname) {
		return Validator.isValidName(firstname);
	}

	private static void checkAssignmentDate(Date assignmentDate) throws IllegalArgumentException {
		if (!isValidAssignmentDate(assignmentDate)) {
			throw new IllegalArgumentException("Assignment date cannot be in the future");
		}
	}

	/**
	 * @param assignmentDate
	 * @return boolean true if the assignment date is not in the future
	 */
	public static boolean isValidAssignmentDate(Date assignmentDate) {
		Calendar cal = Calendar.getInstance();
		return (assignmentDate.before(cal.getTime()));
	}

	private static void checkEmail(String email) throws IllegalArgumentException {
		if (email.isEmpty()|| !isValidEmail(email)) {
			throw new IllegalArgumentException("Email must be a valid email address");
		}
	}

	/**
	 * @param email ex : azer@aze.azeaze or aze@ze.zeee
	 * @return true if the email is valid
	 */
	public static boolean isValidEmail(String email) {
		return Validator.isValidEmailAddress(email);
	}

	private static void checkPhoneNumber(String phoneNumber) throws IllegalArgumentException {
		if (phoneNumber.isEmpty()|| !isValidPhoneNumber(phoneNumber)) {
			throw new IllegalArgumentException(
					"Phone number must be a valid French phone number composed of 10 digits");
		}
	}

	/**
	 * @param phoneNumber only accept French number ex: +33610203010 or 0033610203040 or 0610203040
	 * @return true if the phone number doesn't contain any unwanted character
	 */
	public static boolean isValidPhoneNumber(String phoneNumber) {
		if (phoneNumber!=null){
			return Validator.isValidPhoneNumber(phoneNumber);
		}
		else{
			return false;
		}
		
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		
		checkName(name);
		this.name = name;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		chekFirstName(firstName);
		this.firstName = firstName;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		checkPhoneNumber(phoneNumber);
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		
		checkEmail(email);
		this.email = email;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getAssignmentDate() {
		return this.assignmentDate;
	}

	public void setAssignmentDate(Date assignmentDate) {
		checkAssignmentDate(assignmentDate);
		this.assignmentDate = assignmentDate;
	}

	@ManyToOne
	@JoinColumn(name = "idAgency")
	public Agency getAgency() {
		return this.agency;
	}

	public void setAgency(Agency agency) {
		if(agency !=null){
			this.agency = agency;
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}
	
	/*
	 * Used only by the ORM
	 */
	@SuppressWarnings("unused")
	private void setId(int id) {
		this.id = id;
	}


}
