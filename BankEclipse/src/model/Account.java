package model;

import java.util.ArrayList;

import util.Formater;



public class Account {
	private String number;
	private String description;
	private double initialBalance;
	private double overdraft;
	private Double alertThreshold;	
	private String countryCode;
	private String type;
	
	/**
	 * @author user
	 *To be fetched from DB before constructor
	 */
	private static ArrayList<String> COUNTRYCODES = new ArrayList<String>(){
		{
			add("FR");
			add("CH");
		}
	};
	
	/**
	 * @author user
	 *To be fetched from DB before constructor
	 */
	private static ArrayList<String> TYPES = new ArrayList<String>(){
		{
			add("EPARGNE");
			add("COMPTE_COURANT");
		}
	};
	
	public Account(String number, String description, double initialBalance,
			 double overdraft, Double threshold, String countryCode, String type){
		
		//TODO update methode for the ArrayListes
		
		check_number(number);
		check_description(description);
		check_countryCode(countryCode);
		check_type(type);
		check_overdraft(overdraft);
		
		this.number = Formater.removeUsualSeparators(number);
		this.initialBalance = initialBalance;
		this.overdraft = overdraft;
		this.alertThreshold = threshold;
		this.countryCode = countryCode;
		this.type = type;
	}
	
	
	public String getAccountNumber(){
		return this.number;
	}

	public String getAccountDescription(){
		return this.description;
	}
	
	public double getInitialBalance(){
		return this.initialBalance;
	}
	
	public double getOverdraft(){
		return this.overdraft;
	}
	
	public double getAlertThreshold(){
		return this.alertThreshold.doubleValue();
	}
	
	public static Iterable<String> getTypes() {
		return TYPES;
	}
	
	public static Iterable<String> getCountryCode() {
		return COUNTRYCODES;
	}
	
	public static void check_number(String number) throws IllegalArgumentException {
		if(number.isEmpty()){
			throw new IllegalArgumentException("The account number cannot be empty");
		}
	}
	
	public static void check_description(String description) throws IllegalArgumentException {
		if(description.isEmpty()){
			throw new IllegalArgumentException ("The account description can't be empty");
		}
	}
	
	public static void check_countryCode(String countryCode) throws IllegalArgumentException {
		if (countryCode.isEmpty()){
			throw new IllegalArgumentException ("The country code can't be empty");
		}else if(!COUNTRYCODES.contains(countryCode)){
			throw new IllegalArgumentException ("The country code must be an existing country code");
		}
	}
	
	public static void check_type(String type) throws IllegalArgumentException {
		if (type.isEmpty()){
			throw new IllegalArgumentException ("The account type can't be empty");
		}else if(!TYPES.contains(type)){
			throw new IllegalArgumentException ("The account type must be an existing account type");
		}
	}
	
	public static void check_overdraft(double overdraft) throws IllegalArgumentException {
		if(overdraft>0){
			throw new IllegalArgumentException ("Authorised overdraft cannot be positive");
		}
	}
	
	
}
