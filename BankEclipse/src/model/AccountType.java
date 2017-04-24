package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the accounttype database table.
 * 
 */
@Entity
@Table(name = "accounttype")
@NamedQuery(name = "AccountType.findAll", query = "SELECT b FROM AccountType b")
public class AccountType implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String type;

	private AccountType() {
	}

	public AccountType(String type) {
		
		check_type(type);
		
		this.type = type;
	}
	
	private static void check_type(String type) throws IllegalArgumentException {
		if (type.isEmpty()) {
			throw new IllegalArgumentException("The account type can't be empty");
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}