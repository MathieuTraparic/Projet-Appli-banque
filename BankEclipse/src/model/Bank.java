
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
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String code;
	
	private Bank(){
		
	}
	
	
	public Bank(String bankName, String bankCode) {
		
		//TODO update methode for the ArrayListes
		
		checkBankCode(bankCode);
		checkBankName(bankName);
		
		this.name = Formater.formatNameCase(bankName);
		this.code = Formater.removeUsualSeparators(bankCode);
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
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
		else if(!isValidName(bankCode)){
			throw new IllegalArgumentException("The bank name is incorrect");
		}
	}


	public String getName() {
		return this.name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCode() {
		return this.code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	@Override
	public String toString() {	
		return this.name;
	}
	
	
	
}
