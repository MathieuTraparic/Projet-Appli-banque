package model;

import util.Formater;
import util.Validator;

public class TargetTransaction {
	
	private String summary;
	private String iban;
	
	public TargetTransaction(String summary, String iban) {
		
		checkIban(iban);
		checkSummary(summary);
		
		this.summary = Formater.formatNameCase(summary);
		this.iban = Formater.removeUsualSeparators(iban);
	}
	
	private static void checkIban(String iban) throws IllegalArgumentException{
		if (iban.isEmpty()){
			throw new IllegalArgumentException("IBAN must be filled with valid number");
		}
		if (!isValid(iban)){
			throw new IllegalArgumentException("IBAN must be filled with valid number");
		}
	}
	
	public static boolean isValid(String iban){
		return (Validator.isValidIban(iban));
	}
	
	private static void checkSummary(String summary) throws IllegalArgumentException{

		if (summary.isEmpty()){
			throw new IllegalArgumentException("Summary must be filled with the name of the target transaction");
		}
	}
}
