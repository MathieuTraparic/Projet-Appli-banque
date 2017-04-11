package model;

import java.util.ArrayList;

public class Account {
	private String number;
	private String description;
	private Double initialBalance;
	private int overdraft;
	private Double alertThreshold;	
	private String countryCode;
	private String type;
	
	/**
	 * @author user
	 *To be fetched from DB before constructor
	 */
	private static ArrayList<String> COUNTRYCODE = new ArrayList<String>(){
		{
			add("FR");
			add("CH");
		}
	};
	
	/**
	 * @author user
	 *To be fetched from DB before constructor
	 */
	private static ArrayList<String> TYPE = new ArrayList<String>(){
		{
			add("EPARGNE");
			add("COMPTE_COURANT");
		}
	};
	
	public Account(String number, String description,Double initialBalance,
			int overdraft, Double threshold, String countryCode, String type){
		if(number.isEmpty()){
			throw new IllegalArgumentException("The account number can't be empty");
		}
		if(description.isEmpty()){
			throw new IllegalArgumentException ("The account description can't be empty");
		}
		if(initialBalance == null){
			throw new NullPointerException ("The initial balance can't be null");
		}
		if(threshold == null){
			throw new NullPointerException ("The threshold can't be null");
		}
		if (countryCode.isEmpty()){
			throw new IllegalArgumentException ("The country code can't be empty");
		}
		if (type.isEmpty()){
			throw new IllegalArgumentException ("The account type can't be empty");
		}
		
		
		this.number = number;
		this.initialBalance = initialBalance;
		this.overdraft = overdraft;
		this.alertThreshold = threshold;
		this.countryCode = countryCode;
		this.type = type;
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
