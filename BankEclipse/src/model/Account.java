package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleImmutableEntry;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	private double interestRate;
	private Double alertThreshold;	
	private CountryCode countryCode;
	private Date creationDate;
	private Agency agency;
	private AccountType accountType;
	private List<Transaction> transactions;
	
	
	private Account(){
	}
	
	public Account(String number, String description, double initialBalance,
			 double overdraft, Double threshold){
		
		check_number(number);
		check_description(description);
		check_overdraft(overdraft);
		
		this.number = Formater.removeUsualSeparators(number);
		this.description = description;
		this.initialBalance = initialBalance;
		this.overdraft = overdraft;
		this.alertThreshold = threshold;

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



	public void setInitialBalance(double initialBalance) {
		this.initialBalance = initialBalance;
	}


	public void setOverdraft(double overdraft) {
		this.overdraft = overdraft;
	}


	public void setAlertThreshold(Double alertThreshold) {
		this.alertThreshold = alertThreshold;
	}
	@Temporal(TemporalType.DATE)
	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public double getInterestRate() {
		return this.interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	@ManyToOne
	@JoinColumn(name ="idCountryCode")
	public CountryCode getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(CountryCode countryCode) {
		this.countryCode = countryCode;
	}
	@ManyToOne
	@JoinColumn(name ="idAgency")
	public Agency getAgency() {
		return this.agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	
	@ManyToOne
	@JoinColumn(name ="idAccountType")
	public AccountType getAccountType() {
		return this.accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	@Override
	public String toString() {
		return this.description;
	}
	
	//bi-directional many-to-one association to transaction
	@OneToMany(mappedBy="Account")
	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	private void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	/**
	 * @return the current balance calculated from the initial
	 * and all transaction values
	 * TODO cache the value calculated
	 */
	public double getBalance() {
		double balance = this.initialBalance ;
		for (Transaction t : this.transactions) {
			balance+=t.getValue();
		}
		return balance;
	}
	
	/**
	 * @return a list of couples logging the balance evolution at 
	 * every transaction date
	 * 
	 */
	public List<Entry<Double,Date>> getBalanceHistory() {
		double balance = this.initialBalance ;
		ArrayList<Transaction> sortedTransactions = new ArrayList<>(this.transactions);
		//sort transaction by chronological order ?
		//sortedTransactions.sort(Transaction.CHRONOLOGICAL_COMPARATOR);
		 List<Entry<Double,Date>> result = new ArrayList<>();
		 //add a first entry at account creation
		 result.add(new SimpleImmutableEntry<Double, Date>(balance, this.creationDate));
		 
		 //add all balance evolutions
		 for (Transaction t : sortedTransactions) {
			 balance+=t.getValue();
			result.add(new SimpleImmutableEntry<Double, Date>(balance,t.getDate()));
		}
		return result;
	}


	
	
	
}
