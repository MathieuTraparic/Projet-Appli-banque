package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the accounttype database table.
 * 
 */
@Entity
@Table(name = "accounttype")
@NamedQuery(name = "accounttype.findAll", query = "SELECT a FROM accounttype a")
public class AccountType implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String type;

	private AccountType() {
	}

	public AccountType(String type) {
		this.type = type;
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