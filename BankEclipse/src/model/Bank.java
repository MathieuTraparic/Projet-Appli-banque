
package model;

import java.io.Serializable;
import java.util.ArrayList;

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
	private Integer id;
	private String bankName;
	private String bankCode;
	
	private static ArrayList<String> BANK = new ArrayList<String>(){
		{
			add("j");
			add("h");
		}
	};
	
	private Bank(){
		
	}
	
	
	public Bank(String bankName, String bankCode) {
		
		//TODO update methode for the ArrayListes
		
		checkBankCode(bankCode);
		checkBankName(bankName);
		
		this.bankName = Formater.formatNameCase(bankName);
		this.bankCode = Formater.removeUsualSeparators(bankCode);
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
	
	
	public static Iterable<String> getBank() {
		return BANK;
	}
}
