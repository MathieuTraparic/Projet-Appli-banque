package model;

public class TargetTransaction {
	
	private String summary;
	private String iban;
	
	public TargetTransaction(String summary, String iban) {
		
		checkIban(iban);
		checkSummary(summary);
		
		this.summary = summary;
		this.iban = iban;
	}
	
	public void checkIban(String iban){
		if (iban == null){
			throw new NullPointerException("IBAN cannot be null");
		}
		
		if (iban.isEmpty()){
			throw new IllegalArgumentException("IBAN must be filled with valid number");
		}
		
		/*
		 * TODO: check if IBAN.length is at least > of 10 characters? 
		 */
		if (iban.length()<10){
			throw new IllegalArgumentException("IBAN must be filled with valid number");
		}
	}
	
	public void checkSummary(String summary){
		if (summary == null){
			throw new NullPointerException("Summary cannot be null");
		}
		if (summary.isEmpty()){
			throw new IllegalArgumentException("Summary must be filled with the name of the target transaction");
		}
	}
}
