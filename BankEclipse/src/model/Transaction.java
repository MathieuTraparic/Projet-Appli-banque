package model;

import java.util.Date;

public class Transaction {
	
	private String transactionTypeDescription;
	private String transactionDescription;
	private Double transactionValue;
	private Date transactionDate;
	private String transactionType;

	public Transaction(String transactionDescription, String transactionTypeDescription,
						Double transactionValue, Date transactionDate, String transactionType) {

		checkTransactionTypeDescription(transactionTypeDescription);
		checkTransactionValue(transactionValue);
		checkTransactionDate(transactionDate);
		checkTransactionType(transactionType);
		checkTransactionDescription(transactionDescription);
		
		
		this.transactionDescription = transactionDescription;
		this.transactionTypeDescription = transactionTypeDescription;
		this.transactionValue = transactionValue;
		this.transactionDate=transactionDate;
		this.transactionType=transactionType;
	}
	
	public void checkTransactionTypeDescription(String transactionTypeDescription){
		if (transactionTypeDescription == null){
			throw new NullPointerException("The Description of the transaction type cannot be null");
		}
	}
	
	public void checkTransactionDescription(String transactionDescription){
		if (transactionDescription == null){
			throw new NullPointerException("The description of the transaction cannot be null");
		}
	}
	
	public void checkTransactionValue(Double transactionValue){
		if (transactionValue == null){
			throw new NullPointerException("The value of the transaction cannot be null");
		}
		if (transactionValue == 0){
			throw new IllegalArgumentException("The value of the transaction cannot be 0, it must be positive or negative");
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
