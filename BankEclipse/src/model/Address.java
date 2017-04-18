package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import util.Formater;

@Entity
@Table(name = "Address")
@NamedQuery(name = "Address.findAll", query = "SELECT t FROM Address t")
public class Address implements Serializable{
	private static final long serialVersionUID = 1L;
	private String line1;
	private String line2 = null;
	private String zip;
	private String city;
	private Integer id;
	
	private Address(){
		
	}
	public Address(String line1, String line2, String zip, String city) {
		check_city(city);
		check_line1(line1);
		check_zip(zip);

		this.id = null;
		this.line1 = line1;
		this.line2 = line2;
		this.zip = Formater.removeUsualSeparators(zip);
		this.city = Formater.formatNameCase(city);
	}

	private static void check_line1(String line1) throws IllegalArgumentException {
		if (line1.isEmpty()) {
			throw new IllegalArgumentException("line1 cannot be empty");
		}
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

	@Column(name = "lign1")
	public String getLine1() {
		return this.line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	@Column(name = "lign2")
	public String getLine2() {
		return this.line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}
	
	@ManyToOne
	@JoinColumn(name = "idcpville")
	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	@ManyToOne
	@JoinColumn(name = "idcpville")
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
