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

	private static void check_name(String name) throws IllegalArgumentException {
		if (name.isEmpty()) {
			throw new IllegalArgumentException("name cannot be empty");
		}if (!isValidName(name)){
			throw new IllegalArgumentException("name cannot be empty");
		}
	}

	public static boolean isValidName(String name) {
		return Validator.isValidName(name);
	}

	private static void check_firstName(String firstName) throws IllegalArgumentException {
			if (firstName.isEmpty()) {
				throw new IllegalArgumentException("firstName cannot be empty");
			}
	}

	private static void check_login(String login) throws IllegalArgumentException {
		if (login.isEmpty()) {
			throw new IllegalArgumentException("login cannot be empty");
		}
	}
	
	private static void check_pswd(String pswd) throws IllegalArgumentException {
		if (pswd.isEmpty()) {
			throw new IllegalArgumentException("pswd cannot be empty");
		}else if(!isValidPswd(pswd)){
			throw new IllegalArgumentException("pswd must be valid");
		}
	}

	
	public static boolean isValidPswd(String pswd) {
		//TODO must not be in clear at that point !
		return true;
	}

	private static void check_phoneNumber(String phoneNumber) throws IllegalArgumentException {
		if (phoneNumber.isEmpty()) {
			throw new IllegalArgumentException("phoneNumber cannot be empty");
		} if(!isValidPhoneNumber(phoneNumber)){
			throw new IllegalArgumentException("Phone number must be a valid phone number");
		}
	}
	
	public static boolean isValidPhoneNumber(String phoneNumber){
		return Validator.isValidPhoneNumber(phoneNumber);
	}
	
	private static void check_birthday(Date birthday) throws IllegalArgumentException {
		if (!isValidBirthday(birthday)) {
			throw new IllegalArgumentException("The birthday date cannot be in the future");
		}
	}
	
	public static boolean isValidBirthday(Date date){
		return date.before(new Date());
	}


}
