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
	}
	
	public void checkSummary(String summary){
		if (summary == null){
			throw new NullPointerException("Summary cannot be null");
		}
	}
}
