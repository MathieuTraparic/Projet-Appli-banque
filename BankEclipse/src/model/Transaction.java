package model;

import java.util.Date;

public class Transaction {
	
	private String wording;
	private Double transactionValue;
	private Date transactionDate;
	private String transactionType;

	public Transaction(String wording, Double transactionValue, Date transactionDate, String transactionType) {

		checkWording(wording);
		checkTransactionValue(transactionValue);
		
		this.wording = wording;
		this.transactionValue = transactionValue;
		this.transactionDate=transactionDate;
		this.transactionType=transactionType;
	}
	
	public void checkWording(String wording){
		if (wording == null){
			throw new NullPointerException("Description cannot be null");
		}
	}
	
	public void checkTransactionValue(Double transactionValue){
		if (transactionValue == null){
			throw new NullPointerException("The value of the transaction cannot be null");
		}
	}
	
	public void checkTransactionDate(Date transactionDate){
		if (transactionDate == null){
			throw new NullPointerException("The date of the transaction cannot be null");
		}
	}
	
	public void checkTransactionType (String transactionType){
		if (transactionType == null){
			throw new NullPointerException("The type of the transaction cannot be null");
		}
	}

}
