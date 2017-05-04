package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleImmutableEntry;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

	private static final long serialVersionUID = 4046352721505678179L;
	private int id;
	private String number;
	private String description;
	private double initialBalance;
	private double overdraft;
	private double interestRate;
	private double agioRate;
	private double alertThreshold;
	private CountryCode countryCode;
	private Date creationDate;
	private Agency agency;
	private AccountType accountType;
	private List<Transaction> transactions;
	private List<Owner> owners;

	private double interestTransaction;
	private double agioTransaction;

	public static final Comparator<Account> ALPHABETICAL_COMPARATOR = new Comparator<Account>() {

		@Override
		public int compare(Account o1, Account o2) {
			return o1.getDescription().compareTo(o2.getDescription());
		}
	};

	// for ORM use
	@SuppressWarnings("unused")
	private Account() {
	}

	/**
	 * @param number
	 *            the account Number ex: 1234 1234 1234
	 * @param description
	 * @param initialBalance
	 * @param overdraft
	 *            the balance value under which penalties are due
	 * @param alertThreshold
	 *            the balance value under which an alert is sent to the owner
	 * 
	 *            This constructor doesn't initialize all required members since
	 *            we connected the Database
	 */
	@Deprecated
	public Account(String number, String description, double initialBalance, double overdraft, double alertThreshold) {

		this.setNumber(Formater.removeUsualSeparators(number));
		this.setDescription(description);
		this.setInitialBalance(initialBalance);
		this.setOverdraft(overdraft);
		this.setAlertThreshold(alertThreshold);

	}

	/**
	 * @param number
	 *            the account Number ex: 1234 1234 1234
	 * @param description
	 * @param initialBalance
	 * @param overdraft
	 *            the balance value under which penalties are due
	 * @param interestRate
	 * @param alertThreshold
	 *            the balance value under which an alert is sent to the owner
	 * @param countryCode
	 * @param creationDate
	 * @param agency
	 * @param accountType
	 */
	public Account(String number, String description, double initialBalance, double overdraft, double interestRate,
			double alertThreshold, CountryCode countryCode, Date creationDate, Agency agency, AccountType accountType) {

		this.setNumber(Formater.removeUsualSeparators(number));
		this.setDescription(description);
		this.setInitialBalance(initialBalance);
		this.setOverdraft(overdraft);
		this.setInterestRate(interestRate);
		this.setAlertThreshold(alertThreshold);
		this.setCountryCode(countryCode);
		this.setCreationDate(creationDate);
		this.setAgency(agency);
		this.setAccountType(accountType);
	}

	public double getInitialBalance() {
		return this.initialBalance;
	}

	public double getOverdraft() {
		return this.overdraft;
	}

	public double getAlertThreshold() {
		return this.alertThreshold;
	}

	private static void check_number(String number) throws IllegalArgumentException {
		if (number.isEmpty()) {
			throw new IllegalArgumentException("The account number cannot be empty");
		}
	}

	private static void check_description(String description) throws IllegalArgumentException {
		if (description.isEmpty()) {
			throw new IllegalArgumentException("The account description can't be empty");
		}
	}

	private static void check_overdraft(double overdraft) throws IllegalArgumentException {
		if (!isValidOverdraft(overdraft)) {
			throw new IllegalArgumentException("Authorised overdraft cannot be positive");
		}
	}

	/**
	 * @param overdraft
	 * @return false if the overdraft is negative
	 */
	public static boolean isValidOverdraft(double overdraft) {
		return overdraft <= 0;
	}

	/**
	 * @return the primary key ID from the Database
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	// for ORM use
	@SuppressWarnings("unused")
	private void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the account number
	 */
	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		check_number(number);
		this.number = number;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		check_description(description);
		this.description = description;
	}

	public void setInitialBalance(double initialBalance) {
		this.initialBalance = initialBalance;
	}

	public void setOverdraft(double overdraft) {
		check_overdraft(overdraft);
		this.overdraft = overdraft;
	}

	public void setAlertThreshold(double alertThreshold) {
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
	@JoinColumn(name = "idCountryCode")
	public CountryCode getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(CountryCode countryCode) {
		if (countryCode == null) {
			throw new NullPointerException("The countryCode cannot be null");
		}
		this.countryCode = countryCode;
	}

	@ManyToOne
	@JoinColumn(name = "idAgency")
	public Agency getAgency() {
		return this.agency;
	}

	public void setAgency(Agency agency) {
		if (agency == null) {
			throw new NullPointerException("The agency cannot be null");
		}
		this.agency = agency;
	}

	@ManyToOne
	@JoinColumn(name = "idAccountType")
	public AccountType getAccountType() {
		return this.accountType;
	}

	public void setAccountType(AccountType accountType) {
		if (accountType == null) {
			throw new NullPointerException("The accountType cannot be null");
		}
		this.accountType = accountType;
	}

	@Override
	public String toString() {
		return this.description;
	}

	// bi-directional many-to-one association to transaction
	@OneToMany(mappedBy = "Account")
	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	// for ORM use
	@SuppressWarnings("unused")
	private void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	// @ManyToMany(mappedBy="accounts")
	@ManyToMany
	@JoinTable(name = "Assign", joinColumns = { @JoinColumn(name = "idAccount") }, inverseJoinColumns = {
			@JoinColumn(name = "idOwner") })
	public List<Owner> getOwners() {
		return this.owners;
	}

	public void setOwners(List<Owner> owners) {
		this.owners = owners;
	}

	public void addOwner(Owner owner) {
		this.owners.add(owner);
	}

	/**
	 * @return the agioRate
	 */
	public double getAgioRate() {
		return agioRate;
	}

	/**
	 * @param agioRate
	 *            the agioRate to set
	 */
	public void setAgioRate(double agioRate) {
		this.agioRate = agioRate;
	}

	/**
	 * @return the current balance calculated from the initial and all
	 *         transaction values TODO cache the value calculated and consider
	 *         interestRate
	 */
	public double getBalance() {
		double balance = this.initialBalance;
		for (Transaction t : this.transactions) {
			balance += t.getValue();
		}
		return balance;
	}

	/**
	 * @return a list of couples logging the balance evolution at every
	 *         transaction date
	 * 
	 */
	public List<Entry<Double, Date>> getBalanceHistory() {
		double balance = this.initialBalance;
		ArrayList<Transaction> sortedTransactions = new ArrayList<>(this.transactions);
		// sort transaction by chronological order ?
		// sortedTransactions.sort(Transaction.CHRONOLOGICAL_COMPARATOR);
		List<Entry<Double, Date>> result = new ArrayList<>();
		// add a first entry at account creation
		result.add(new SimpleImmutableEntry<Double, Date>(balance, this.creationDate));

		// add all balance evolutions
		for (Transaction t : sortedTransactions) {
			balance += t.getValue();
			result.add(new SimpleImmutableEntry<Double, Date>(balance, t.getDate()));
		}

		// to sort the balanceHystory by date !
		result.sort(new Comparator<Entry<Double, Date>>() {

			@Override
			public int compare(Entry<Double, Date> o1, Entry<Double, Date> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
		return result;
	}

	/*	
		*//**
			 * 
			 * @return a list of couples logging the balance evolution at each
			 *         day since the account creation until today
			 * 
			 *//*
			 * public List<Entry<Double,Date>> getDailyBalanceHistory() { double
			 * balance = this.initialBalance; Calendar currentCal = new
			 * GregorianCalendar(this.creationDate.getYear(),
			 * this.creationDate.getMonth(), this.creationDate.getDate());
			 * 
			 * ArrayList<Transaction> sortedTransactions = new
			 * ArrayList<>(this.transactions); //sort transaction by
			 * chronological order ?
			 * sortedTransactions.sort(Transaction.CHRONOLOGICAL_COMPARATOR);
			 * List<Entry<Double,Date>> result = new ArrayList<>(); //add a
			 * first entry at account creation result.add(new
			 * SimpleImmutableEntry<Double, Date>(balance,
			 * currentCal.getTime()));
			 * 
			 * while(!currentDate.equals()){ for (Transaction t :
			 * sortedTransactions) { if(t.getDate().ge==currentDate){
			 * currentDate. } } } //add all balance evolutions for (Transaction
			 * t : sortedTransactions) { balance+=t.getValue(); result.add(new
			 * SimpleImmutableEntry<Double, Date>(balance,t.getDate())); }
			 * return result; }
			 */

	public String getInterestAccountPerYear() {
		double interestRate = this.getInterestRate() / 100;

		List<Entry<Double, Date>> balanceHistory = getBalanceHistory();
		
		int coef = 0;
		int previousCoef = 0;

		this.interestTransaction = 0;
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		for (Entry<Double, Date> entry : balanceHistory) {
			if (entry.getKey() > 0 && entry.getValue().getYear() == cal.getTime().getYear()) {
				if (entry.getValue().getDate() <= 15) {
					coef = (24 - (entry.getValue().getMonth() * 2)) - previousCoef;
					this.interestTransaction += ((entry.getKey()) * interestRate * coef) / 24;
					previousCoef = (24 - (entry.getValue().getMonth() * 2));
				} else {
					coef = (24 - (entry.getValue().getMonth() * 2) - 1) - previousCoef;
					this.interestTransaction += ((entry.getKey()) * interestRate * coef) / 24;
					previousCoef = (24 - (entry.getValue().getMonth() * 2) - 1);
					;
				}
			}
		}

		return String.format(Locale.US, "%.2f", this.interestTransaction);

	}

	public String getAgioAccountPerYear() {

		double agioRate = this.getAgioRate() / 100;
		double agioTotal = 0;
		long numOfDayPerAgio = 1;

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int numOfDays = cal.getActualMaximum(Calendar.DAY_OF_YEAR);

		List<Entry<Double, Date>> balanceHistory = getBalanceHistory();

		for (Entry<Double, Date> entry : balanceHistory) {
			
			if (entry.getKey() < 0 && entry.getValue().getYear() == cal.getTime().getYear()) {
				if (balanceHistory.indexOf(entry)+1 < balanceHistory.size()) {
					Date newBalanceDate = balanceHistory.get(balanceHistory.indexOf(entry) + 1).getValue();
	
					numOfDayPerAgio = (newBalanceDate.getTime() - entry.getValue().getTime())/(1000*60*60*24);
				} else {
	
					numOfDayPerAgio = (cal.getTimeInMillis() - entry.getValue().getTime())/(1000*60*60*24);
					
				}
				System.out.println(numOfDayPerAgio);
				
				agioTotal += entry.getKey() * agioRate * numOfDayPerAgio;
			}
		}

		agioTotal /= numOfDays;
		
		if (agioTotal!=0){
			agioTotal*=-1;
		}

		return String.format(Locale.US, "%.2f", agioTotal);
	}
}
