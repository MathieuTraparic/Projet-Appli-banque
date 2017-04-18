
package model;

import java.util.ArrayList;

import util.Formater;
import util.Validator;

public class Bank {
	private String bankName;
	private String bankCode;
	
	private static ArrayList<String> BANK = new ArrayList<String>(){
		{
			add("j");
			add("h");
		}
	};

	
	
	public Bank(String bankName, String bankCode) {
		
		//TODO update methode for the ArrayListes
		
		checkBankCode(bankCode);
		checkBankName(bankName);
		
		this.bankName = Formater.formatNameCase(bankName);
		this.bankCode = Formater.removeUsualSeparators(bankCode);
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
