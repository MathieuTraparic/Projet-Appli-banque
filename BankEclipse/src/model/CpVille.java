package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import util.Formater;

/**
 * The persistent class for the cpville database table.
 * 
 */
@Entity
@Table(name="cpville")
@NamedQuery(name = "CpVille.findAll", query = "SELECT t FROM CpVille t")
public class CpVille implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String zip;
	private String city;

	private CpVille() {
	}

	public CpVille(int id, String zip, String city) {

		check_city(city);
		check_zip(zip);

		this.id = id;
		this.zip = Formater.removeUsualSeparators(zip);
		this.city = Formater.formatNameCase(city);
	}

	private static void check_zip(String zip) throws IllegalArgumentException {
		if (zip.isEmpty() || !isValidZip(zip)) {
			throw new IllegalArgumentException("zip must be valid");
		}
	}

	private static void check_city(String city) throws IllegalArgumentException {
		if (city.isEmpty()) {
			throw new IllegalArgumentException("city cannot be empty");
		}
	}

	public static boolean isValidZip(String zip) {
		return zip.length() <= 50;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}