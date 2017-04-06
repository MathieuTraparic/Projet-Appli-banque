package model;

import java.sql.Date;

public class Owner {
	private String name;
	private String firstName;
	
	
	//java.sql or java.util ?
	private Date birthday;
	private String login;
	private String pswd;
	
	//probably useless ?
	private String phoneNumber;
	
	private Address address;
	

	public Owner(String name, String firstName, String phoneNumber, Date birthday, String login, String pswd,  Address address) {
		check_firstName(firstName);
		check_login(login);
		check_name(name);
		check_phoneNumber(phoneNumber);
		check_pswd(pswd);
		
		this.name = name;
		this.firstName = firstName;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;
		this.login = login;
		this.pswd = pswd;
		this.address = address;
	}

	public static void check_name(String name) throws IllegalArgumentException {
		if (name.length()==0) {
			throw new IllegalArgumentException("name cannot be empty");
		}
	}

	public static void check_firstName(String firstName) throws IllegalArgumentException {
			if (firstName.length()==0) {
				throw new IllegalArgumentException("firstName cannot be empty");
			}
	}

	public static void check_login(String login) throws IllegalArgumentException {
		if (login.length() == 0) {
			throw new IllegalArgumentException("login cannot be empty");
		}
	}
	
	public static void check_pswd(String pswd) throws IllegalArgumentException {
		if (pswd.length() == 0) {
			throw new IllegalArgumentException("pswd cannot be empty");
		}
	}
	
	public static void check_phoneNumber(String phoneNumber) throws IllegalArgumentException {
		if (phoneNumber.length() == 0) {
			throw new IllegalArgumentException("phoneNumber cannot be empty");
		}
	}


}
