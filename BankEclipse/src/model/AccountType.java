package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the accounttype database table.
 * 
 */
@Entity
@Table(name = "accounttype")
@NamedQuery(name = "AccountType.findAll", query = "SELECT b FROM AccountType b")
public class AccountType implements Serializable {

	private static final long serialVersionUID = 6668423498399671975L;
	private int id;
	private String type;
	
	/*
	 * Used only by the ORM
	 */
	@SuppressWarnings("unused")
	private AccountType() {
	}

	/**Constructor
	 * @param type
	 */
	public AccountType(String type) {

		this.setType(type);
	}
	
	private static void checkType(String type) throws IllegalArgumentException {
		if (type.isEmpty()) {
			throw new IllegalArgumentException("The account type can't be empty");
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

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		
		checkType(type);
		
		this.type = type;
	}

	@Override
	public String toString() {
		return this.getType();
	}
	
	

}