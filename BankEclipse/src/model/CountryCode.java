package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the countrycode database table.
 * 
 */
@Entity
@Table(name="countrycode")
@NamedQuery(name = "CountryCode.findAll", query = "SELECT t FROM CountryCode t")
public class CountryCode implements Serializable {

	private static final long serialVersionUID = 2517598819056512842L;
	private int id;
	private String code;

	@SuppressWarnings("unused")
	private CountryCode() {}

	/**
	 * @param code : country code
	 */
	public CountryCode(String code) {
		this.setCode(code);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	@SuppressWarnings("unused")
	private void setId(int id) {
		this.id = id;
	}
	
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		checkCountryCode(code);
		this.code = code;
	}

	@Override
	public String toString() {
		return  this.code ;
	}
	
	private static void checkCountryCode(String countryCode) throws IllegalArgumentException {
		if (countryCode.isEmpty()) {
			throw new IllegalArgumentException("The country code can't be empty");
		}
		if (countryCode.length()>=3){
			throw new IllegalArgumentException("The country code is only two letters");
		}
	}
}