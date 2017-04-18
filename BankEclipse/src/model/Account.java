package model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import util.Formater;


@Entity
@Table(name = "Account")
@NamedQuery(name = "Account.findAll", query = "SELECT t FROM Account t")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String number;
	private String description;
	private double initialBalance;
	private double overdraft;
	private Double alertThreshold;	
	private CountryCode countryCode;
	private String type;
	
	
	private Account(){
		
	}
	
	public Account(String number, String description, double initialBalance,
			 double overdraft, Double threshold, String type){
		
		//TODO update methode for the ArrayListes
		
		check_number(number);
		check_description(description);
		
		check_type(type);
		check_overdraft(overdraft);
		
		this.number = Formater.removeUsualSeparators(number);
		this.initialBalance = initialBalance;
		this.overdraft = overdraft;
		this.alertThreshold = threshold;

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
	
	
	private static void check_number(String number) throws IllegalArgumentException {
		if(number.isEmpty()){
			throw new IllegalArgumentException("The account number cannot be empty");
		}
	}
	
	private static void check_description(String description) throws IllegalArgumentException {
		if(description.isEmpty()){
			throw new IllegalArgumentException ("The account description can't be empty");
		}
	}
	

	
	private static void check_type(String type) throws IllegalArgumentException {
		if (type.isEmpty()){
			throw new IllegalArgumentException ("The account type can't be empty");
		}
	}
	
	private static void check_overdraft(double overdraft) throws IllegalArgumentException {
		if(!isValidOverdraft(overdraft)){
			throw new IllegalArgumentException ("Authorised overdraft cannot be positive");
		}
	}
	
	public static boolean isValidOverdraft(double overdraft){
		return overdraft<=0;
	}
	
	public static boolean isValidType(double overdraft){
		return overdraft<=0;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNumber() {
		return this.number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public String getDescription() {
		return this.description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getType() {
		return this.type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public void setInitialBalance(double initialBalance) {
		this.initialBalance = initialBalance;
	}


	public void setOverdraft(double overdraft) {
		this.overdraft = overdraft;
	}


	public void setAlertThreshold(Double alertThreshold) {
		this.alertThreshold = alertThreshold;
	}

	@ManyToOne
	@JoinColumn(name ="idcountrycode")
	public CountryCode getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(CountryCode countryCode) {
		this.countryCode = countryCode;
	}
	
	
}
