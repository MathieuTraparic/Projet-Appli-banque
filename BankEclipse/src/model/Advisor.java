package model;

import java.util.Date;

public class Advisor {
	private String name;
	private String firstName;
	private String telNumber;
	private String email;
	private Date assigmentDate;
	
	public Advisor(String name, String firstName, String telNumber,
			String email, Date assigmentDate) {
		
		checkName(name);
		checkName(firstName);
		
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
	}
	
	public void chekFirstName(String firstname){
		if (firstname==null){
			throw new NullPointerException("First Name cannot be null");
		}
	}
	
	/*public void chekTelNumber(String telNumber){
		if (telNumber==null){
			throw new NullPointerException("First Name cannot be null");
		}
	}*/
}
