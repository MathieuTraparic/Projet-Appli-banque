package model;

import java.util.Date;

public class Advisor {
	private String name;
	private String firstName;
	private String phoneNumber;
	private String email;
	private Date assignmentDate;

	public Advisor(String name, String firstName, String phoneNumber, String email, Date assignmentDate) {

		checkName(name);
		checkName(firstName);
		check_email(email);
		check_assignmentDate(assignmentDate);
		check_phoneNumber(phoneNumber);

		this.name = name;
		this.firstName = firstName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.assignmentDate = assignmentDate;
	}

	public static void checkName(String name) throws IllegalArgumentException {
		if (name.isEmpty()) {
			throw new IllegalArgumentException("Name cannot be empty");
		}
	}

	public static void chekFirstName(String firstname) throws IllegalArgumentException {
		if (firstname.isEmpty()) {
			throw new IllegalArgumentException("First name cannot be empty");
		}
	}

	public static void check_assignmentDate(Date assignmentDate) throws IllegalArgumentException {
		if (assignmentDate.after(new Date())) {
			throw new IllegalArgumentException("Assignment date cannot be in the future");
		}
	}

	public static void check_email(String email) throws IllegalArgumentException {
		if (email.isEmpty()) {
			throw new IllegalArgumentException("Email cannot be empty");
		} else if (!isValidEmailAddress(email)) {
			throw new IllegalArgumentException("Email must be a valid email address");
		}

	}

	public static void check_phoneNumber(String phoneNumber) throws IllegalArgumentException {
		if (phoneNumber.isEmpty()) {
			throw new IllegalArgumentException("Phone number cannot be empty");
		}else if(isValidPhoneNumber(phoneNumber)){
			throw new IllegalArgumentException("Phone number must be a valid French phone number composed of 10 digits");
		}
	}

	public static boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}
	
	/**
	 * @param phoneNumber
	 * @return true is the phoneNumber is valid
	 * 
	 * match the phone number against a regex, only accept french number for now
	 */
	public static boolean isValidPhoneNumber(String phoneNumber){
		String ePattern = "^0([0-9] ?){9}$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(phoneNumber);
		return m.matches();
	}

}
