package model;

import java.util.Date;

import jdk.internal.org.objectweb.asm.util.CheckFieldAdapter;
import util.Formater;
import util.Validator;

public class Advisor {
	private String name;
	private String firstName;
	private String phoneNumber;
	private String email;
	private Date assignmentDate;

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
		}
		else if (!isValidName(name)){
			throw new IllegalArgumentException("Name is incorrect, must contains only letters and/or spaces, dashes, apostrophe");
		}
	}
	
	public static boolean isValidName(String name){
		return Validator.isValidName(name);
	}

	private static void chekFirstName(String firstname) throws IllegalArgumentException {
		if (firstname.isEmpty()) {
			throw new IllegalArgumentException("First name cannot be empty");
		}
		else if (!isValidFirstName(firstname)){
			throw new IllegalArgumentException("First name is incorrect, must contains only letters and/or spaces, dashes, apostrophe");
		}
	}
	
	public static boolean isValidFirstName(String firstname){
		return Validator.isValidName(firstname);
	}

	private static void check_assignmentDate(Date assignmentDate) throws IllegalArgumentException {
		if (!isValidAssignmentDate(assignmentDate)) {
			throw new IllegalArgumentException("Assignment date cannot be in the future");
		}
	}
	
	public static boolean isValidAssignmentDate(Date assignmentDate){
		return (assignmentDate.before(new Date()));
	}

	private static void check_email(String email) throws IllegalArgumentException {
		if (email.isEmpty()) {
			throw new IllegalArgumentException("Email cannot be empty");
		} else if (!isValidEmail(email)) {
			throw new IllegalArgumentException("Email must be a valid email address");
		}
	}
	
	public static boolean isValidEmail(String email){
		return Validator.isValidEmailAddress(email);
	}

	private static void check_phoneNumber(String phoneNumber) throws IllegalArgumentException {
		if (phoneNumber.isEmpty()) {
			throw new IllegalArgumentException("Phone number cannot be empty");
		}else if(isValidPhoneNumber(phoneNumber)){
			throw new IllegalArgumentException("Phone number must be a valid French phone number composed of 10 digits");
		}
	}
	
	public static boolean isValidPhoneNumber(String phoneNumber){
		return Validator.isValidPhoneNumber(phoneNumber);
	}
}
