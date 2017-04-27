
package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import util.Formater;
import util.Validator;


@Entity
@Table(name="bank")
@NamedQuery(name = "Bank.findAll", query = "SELECT t FROM Bank t")
public class Bank implements Serializable{

	private static final long serialVersionUID = -5364979069387254961L;
	private int id;
	private String name;
	private String code;
	
	
	/*
	 * Used only by the ORM
	 */
	@SuppressWarnings("unused")
	private Bank(){
		this.setCode("1235");
	}
	
	/**Deprecated constructor
	 * @param agencyName contains only letters, spaces, apostrophe and dashes
	 */
	@Deprecated
	public Bank(String bankName){

		this.setName(bankName);
	}
	
	
	/**Constructor
	 * @param bankName contains only letters, spaces, apostrophe and dashes
	 * @param bankCode contains only letters, spaces, apostrophe and dashes
	 */
	public Bank(String bankName, String bankCode) {
		
		this.setName(bankName);
		this.setCode(Formater.removeUsualSeparators(bankCode));
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
	
	/**
	 * @param  name
	 * @return true if name contains only letters, spaces, apostrophe and dashes
	 */
	public static boolean isValidName(String name){
		return Validator.isValidName(name);
	}
	
	private static void checkBankName(String bankName) throws IllegalArgumentException {
		if (bankName.isEmpty()){
			throw new IllegalArgumentException("The name of the bank cannot be empty");
		}
		else if(!isValidName(bankName)){
			throw new IllegalArgumentException("The bank name is incorrect");
		}
	}
	
	private static void checkBankCode(String bankCode) throws IllegalArgumentException {
		if (bankCode.isEmpty()){
			throw new IllegalArgumentException("The code of the bank cannot be empty");
		}
	}


	public String getName() {
		return this.name;
	}


	public void setName(String name) {
		checkBankName(name);
		this.name = name;
	}


	public String getCode() {
		return this.code;
	}


	public void setCode(String code) {
		checkBankCode(code);
		this.code = code;
	}


	@Override
	public String toString() {	
		return this.name;
	}
	
	
	
}
