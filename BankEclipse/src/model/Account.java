package model;

public class Account {
	private String number;
	private String description;
	private Double initialBalance;
	private int overdraft;
	private Double alertThreshold;
	
	public Account(String number, String description,Double initialBalance, int overdraft, Double threshold){
		if(number.equals(null)){
			throw new NullPointerException ("The account number can't be null");
		}
		if(description.equals(null)){
			throw new NullPointerException ("The account description can't be null");
		}
		if(initialBalance == null){
			throw new NullPointerException ("The initial balance can't be null");
		}
		if(threshold == null){
			throw new NullPointerException ("The initial balance can't be null");
		}
		
		this.number = number;
		this.initialBalance = initialBalance;
		this.overdraft = overdraft;
		this.alertThreshold = threshold;
	}
	
	public String getAccountNumber(){
		if(this.number.length()==0){
			throw new IllegalArgumentException("Account number can't be empty");
		}
		return this.number;
	}
	
	public String getAccountDescription(){
		if(this.description.length()==0){
			throw new IllegalArgumentException("Account number can't be empty");
		}
		return this.description;
	}
}
