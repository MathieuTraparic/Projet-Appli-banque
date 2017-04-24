package model;

import java.io.Serializable;
import java.util.ArrayList;

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
@Table(name="agency")
@NamedQuery(name = "Agency.findAll", query = "SELECT t FROM Agency t")
public class Agency  implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String counterCode;
	private Address adress;
	private Bank bank;
	
	
	private Agency(){
		
	}
	
	public Agency(String agencyName){
		checkName(agencyName);
		this.name = Formater.formatNameCase(agencyName);
	}

	public Agency(String agencyName, String counterCode) {

		chekCounterCode(counterCode);
		checkName(agencyName);

		this.counterCode = Formater.removeUsualSeparators(counterCode);
		this.name = Formater.formatNameCase(agencyName);
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public static void checkName(String name) throws IllegalArgumentException {
		if (name.isEmpty()) {
			throw new IllegalArgumentException("The name of the agency cannot be empty");
		}
	}

	public static void chekCounterCode(String counterCode) throws IllegalArgumentException {
		if (counterCode.isEmpty()) {
			throw new IllegalArgumentException("The countercode cannot be empty");
		}
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCounterCode() {
		return this.counterCode;
	}

	public void setCounterCode(String counterCode) {
		this.counterCode = counterCode;
	}
	
	@ManyToOne
	@JoinColumn(name="idAddress")
	private Address getAdress() {
		return this.adress;
	}

	public void setAdress(Address adress) {
		this.adress = adress;
	}
	@ManyToOne
	@JoinColumn(name="idBank")
	public Bank getBank() {
		return this.bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}
	
}
