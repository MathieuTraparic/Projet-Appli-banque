package model;


import util.Formater;

public class Bank {
	private String name;
	private String bankCode;

	
	
	public Bank(String name, String bankCode) {
		
		//TODO update methode for the ArrayListes
		
		checkBankCode(bankCode);
		checkName(name);
		
		this.name = Formater.formatNameCase(name);
		this.bankCode = Formater.removeUsualSeparators(bankCode);
	}
	
	public static void checkName(String name) throws IllegalArgumentException {

		if (name.isEmpty()){
			throw new IllegalArgumentException("The name of the bank cannot be empty");
		}
	}
	
	public static void checkBankCode(String bankCode) throws IllegalArgumentException {

		if (bankCode.isEmpty()){
			throw new IllegalArgumentException("The code of the bank cannot be empty");
		}
	}

}
