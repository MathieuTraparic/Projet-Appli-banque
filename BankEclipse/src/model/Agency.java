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
	
	/**Deprecated constructor
	 * @param agencyName
	 */
	@Deprecated
	public Agency(String agencyName){

		this.setName(Formater.formatNameCase(agencyName));
	}

	/**Constructor
	 * @param agencyName
	 * @param counterCode
	 */
	public Agency(String agencyName, String counterCode) {

		this.setCounterCode(Formater.removeUsualSeparators(counterCode));
		this.setName(Formater.formatNameCase(agencyName));
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
