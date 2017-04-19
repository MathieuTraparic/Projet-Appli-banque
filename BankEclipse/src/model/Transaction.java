	package model;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import util.Formater;

@Entity
@Table(name="transaction")
@NamedQuery(name = "Transaction.findAll", query = "SELECT t FROM Transaction t")
public class Transaction {

	/**
	 * To be fetched from DB BEFORE first instance is created
	 */
	@SuppressWarnings("serial")
	private static ArrayList<String> TYPES = new ArrayList<String>() {
		{
			add("TRANSACTION");
			add("RETRAIT");
			add("MONEY");
			add("BILL");
		}
	};

	private String type;
	private String description;
	private Double value;
	private Date date;

	public Transaction(String description, String type, double value, Date date) {

		checkValue(value);
		checkDate(date);
		checkType(type);
		checkDescription(description);

		this.description = description;
		this.type = Formater.formatNameCase(type);
		this.value = value;
		this.date = date;
	}

	private static void checkDescription(String description) throws IllegalArgumentException {
		if (description.isEmpty()) {
			throw new IllegalArgumentException("The description of the transaction cannot be empty");
		}
	}

	private static void checkValue(double value) throws IllegalArgumentException {
		if (!isValidValue(value)) {
			throw new IllegalArgumentException(
					"The value of the transaction cannot be 0, it must be positive or negative");
		}
	}

	public static boolean isValidValue(double value) {
		return (0 != Double.compare(value, 0));
	}

	private static void checkDate(Date transactionDate) throws NullPointerException {
		if (transactionDate == null) {
			throw new NullPointerException("The date of the transaction cannot be null");
		}

	}

	private static void checkType(String type) throws IllegalArgumentException {
		if (type == null) {
			throw new NullPointerException("The type of the transaction cannot be null");
		} else if (!isValidType(type)) {
			throw new IllegalArgumentException("The type of the transaction must be an existing type");
		}
	}

	public static boolean isValidType(String type) {
		return (TYPES.contains(type));
	}
	public static Iterable<String> getTypes() {
		return TYPES;
	}

}
