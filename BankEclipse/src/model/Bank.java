package model;

import java.util.ArrayList;

public class Bank {
	private String bankName;
	private String bankCode;

	
	
	public Bank(String bankName, String bankCode) {
		
		//TODO update methode for the ArrayListes
		
		checkBankCode(bankCode);
		checkBankName(bankName);
		
		this.bankName = bankName;
		this.bankCode = bankCode;
	}
	
	public static void checkBankName(String bankName) throws IllegalArgumentException {

		if (bankName.isEmpty()){
			throw new IllegalArgumentException("The name of the bank cannot be empty");
		}
	}
	
	public static void checkBankCode(String bankCode) throws IllegalArgumentException {

		if (bankCode.isEmpty()){
			throw new IllegalArgumentException("The code of the bank cannot be empty");
		}
	}

}
