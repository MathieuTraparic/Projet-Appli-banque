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
	private static final long serialVersionUID = 1L;
	private int id;
	private String code;

	private CountryCode() {
	}

	public CountryCode(String code) {
		check_countryCode(code);

		this.code = code;
	}

	private static void check_countryCode(String countryCode) throws IllegalArgumentException {
		if (countryCode.isEmpty()) {
			throw new IllegalArgumentException("The country code can't be empty");
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
	
	
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}