package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the countrycode database table.
 * 
 */
@Entity
@NamedQuery(name="Countrycode.findAll", query="SELECT c FROM Countrycode c")
public class Countrycode implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String code;

	private Countrycode() {
	}
	
	public Countrycode(String code) {
	this.code=code;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}