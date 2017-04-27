package model;

import java.io.Serializable;

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



	private static final long serialVersionUID = 458612991376198713L;
	private int id;
	private String name;
	private String counterCode;
	private Address adress;
	private Bank bank;
	
	/*
	 * Used only by the ORM
	 */
	@SuppressWarnings("unused")
	private Agency(){
		
	}
	
	/**Constructor
	 * @param agencyName
	 * @param counterCode
	 */
	@Deprecated
	public Agency(String agencyName, String counterCode) {

		this.setCounterCode(Formater.removeUsualSeparators(counterCode));
		this.setName(Formater.formatNameCase(agencyName));
	}
	
	/**
	 * @param name 
	 * @param counterCode
	 * @param adress
	 * @param bank
	 */
	public Agency(String name, String counterCode, Address adress, Bank bank) {
		this.setCounterCode(Formater.removeUsualSeparators(counterCode));
		this.setName(Formater.formatNameCase(name));
		this.setAdress(adress);
		this.setBank(bank);
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

	private static void checkName(String name) throws IllegalArgumentException {
		if (name.isEmpty()) {
			throw new IllegalArgumentException("The name of the agency cannot be empty");
		}
	}

	private static void checkCounterCode(String counterCode) throws IllegalArgumentException {
		if (counterCode.isEmpty()) {
			throw new IllegalArgumentException("The countercode cannot be empty");
		}
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		checkName(name);
		
		this.name = name;
	}

	public String getCounterCode() {
		return this.counterCode;
	}

	public void setCounterCode(String counterCode) {
		checkCounterCode(counterCode);
		this.counterCode = counterCode;
	}
	
	@ManyToOne
	@JoinColumn(name="idAddress")
	private Address getAdress() {
		return this.adress;
	}

	public void setAdress(Address address) {
		if(address==null){
			throw new NullPointerException("address cannot be null");
		}
		this.adress = address;
	}
	@ManyToOne
	@JoinColumn(name="idBank")
	public Bank getBank() {
		return this.bank;
	}

	public void setBank(Bank bank) {
		if(bank==null){
			throw new NullPointerException("bank cannot be null");
		}
		this.bank = bank;
	}
	
}
