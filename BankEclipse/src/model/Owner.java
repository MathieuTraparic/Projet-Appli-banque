package model;

import java.util.Date;

import util.Formater;
import util.Validator;

public class Owner {
	private String name;
	private String firstName;
	private Date birthday;
	private String login;
	private String pswd;
	private String phoneNumber;	
	private Address address;
	

	public Owner(String name, String firstName, String phoneNumber, Date birthday, String login, String pswd,  Address address) {
		check_firstName(firstName);
		check_login(login);
		check_name(name);
		check_phoneNumber(phoneNumber);
		check_pswd(pswd);
		check_birthday(birthday);
	
		
		this.name = Formater.formatNameCase(name);
		this.firstName = Formater.formatNameCase(firstName);
		this.phoneNumber = Formater.removeUsualSeparators(phoneNumber);
		this.birthday = birthday;
		this.login = login;
		this.pswd = pswd;
		this.address = address;
	}

	public static void check_name(String name) throws IllegalArgumentException {
		if (name.isEmpty()) {
			throw new IllegalArgumentException("name cannot be empty");
		}if (!isValidName(name)){
			throw new IllegalArgumentException("name cannot be empty");
		}
	}

	private static boolean isValidName(String name2) {
		// TODO Auto-generated method stub
		return false;
	}

	public static void check_firstName(String firstName) throws IllegalArgumentException {
			if (firstName.isEmpty()) {
				throw new IllegalArgumentException("firstName cannot be empty");
			}
	}

	public static void check_login(String login) throws IllegalArgumentException {
		if (login.isEmpty()) {
			throw new IllegalArgumentException("login cannot be empty");
		}
	}
	
	public static void check_pswd(String pswd) throws IllegalArgumentException {
		if (pswd.isEmpty()) {
			throw new IllegalArgumentException("pswd cannot be empty");
		}
	}

	
	public static void check_phoneNumber(String phoneNumber) throws IllegalArgumentException {
		if (phoneNumber.isEmpty()) {
			throw new IllegalArgumentException("phoneNumber cannot be empty");
		} if(!Validator.isValidPhoneNumber(phoneNumber)){
			throw new IllegalArgumentException("Phone number must be a valid phone number");
		}
	}
	
	public static void check_birthday(Date birthday) throws IllegalArgumentException {
		if (birthday.after(new Date())) {
			throw new IllegalArgumentException("The birthday date cannot be in the future");
		}
	}


}
