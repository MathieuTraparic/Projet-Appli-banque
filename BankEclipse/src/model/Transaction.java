package model;

import java.util.ArrayList;
import java.util.Date;

public class Transaction {

	private String type;
	/**
	 * To be fetched from DB BEFORE first instance is created
	 */
	@SuppressWarnings("serial")
	private static ArrayList<String> TYPES = new ArrayList<String>() {
		{
			add("VIREMENT");
			add("RETRAIT");
		}
	};

	private String description;
	private Double value;
	private Date date;

	public Transaction(String description, String type, 
			Double value, Date date) {

		checkValue(value);
		checkDate(date);
		checkType(type);
		checkDescription(description);

		this.description = description;
		this.type = type; //pass the test without being in the types arraylist
		this.value = value;
		this.date = date;
	}

	public static void checkDescription(String description) throws IllegalArgumentException {
		if (description.isEmpty()) {
			throw new IllegalArgumentException("The description of the transaction cannot be empty");
		}
	}

	public static void checkValue(Double value) throws IllegalArgumentException {
		if (value == null) {
			throw new NullPointerException("The value of the transaction cannot be null");
		}
		if (value == 0) {
			throw new IllegalArgumentException(
					"The value of the transaction cannot be 0, it must be positive or negative");
		}
	}

	public static void checkDate(Date transactionDate) throws NullPointerException {
		if (transactionDate == null) {
			throw new NullPointerException("The date of the transaction cannot be null");
		}

	}

	public static void checkType (String type)throws IllegalArgumentException{
		if (type == null){
			throw new NullPointerException("The type of the transaction cannot be null");
		}else if(!TYPES.contains(type)){
			throw new IllegalArgumentException("The type of the transaction must be an existing type");
		}
	}

}
