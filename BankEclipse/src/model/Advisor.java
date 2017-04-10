package model;

import java.util.Date;

public class Advisor { //in the table no email nor telnumber --> to see if we keep thos guys
	private String name;
	private String firstName;
	private String telNumber;
	private String email; //TODO: need to add a champ in advisor table for the e-mail of the advisor
	private Date assigmentDate;
	
	public Advisor(String name, String firstName, String telNumber,
			String email, Date assigmentDate) {
		
		checkName(name);
		checkName(firstName);
		chekEmail(email);
		
		this.name = name;
		this.firstName = firstName;
		this.telNumber = telNumber;
		this.email = email;
		this.assigmentDate = assigmentDate;
	}

	public void checkName(String name){
		if (name==null){
			throw new NullPointerException("Name cannot be null");
		}
		if (name.isEmpty()){
			throw new IllegalArgumentException("Name cannot be empty");
		}
	}
	
	public void chekFirstName(String firstname){
		if (firstname==null){
			throw new NullPointerException("First Name cannot be null");
		}
		if (firstname.isEmpty()){
			throw new IllegalArgumentException("First name cannot be empty");
		}
	}
	
	public void chekEmail(String email){
		if (email==null){
			throw new NullPointerException("Advisor's email cannot be null");
		}
		if (email.isEmpty()){
			throw new IllegalArgumentException("Advisor's email cannot be empty");
		}
	}
	
}
