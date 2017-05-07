package model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="transaction")
@NamedQuery(name = "Transaction.findAll", query = "SELECT t FROM Transaction t")
public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final Comparator<Transaction> CHRONOLOGICAL_COMPARATOR =new Comparator<Transaction>() {

		@Override
		public int compare(Transaction o1, Transaction o2) {
			return o1.date.compareTo(o2.date);
		}
	};
	public static final Comparator<Transaction> VALUE_COMPARATOR =new Comparator<Transaction>() {

		@Override
		public int compare(Transaction o1, Transaction o2) {
			return (int) (o1.getValue()-o2.getValue());
		}
	};
	

	private int id;

	private String description;
	private double value;
	private Date date;
	private Account account;
	private TransactionType transactionType;
	private Category category;
	private TargetTransaction targetTransaction;
	private PeriodicTransaction periodicTransaction;
    
	@SuppressWarnings("unused")
	private Transaction(){}
  
    /**
     * @param description
     * @param date
     */
    public Transaction(String description, Date date) {
        this.setDescription(description);
        this.setDate(date);;
    }

	/**
	 * @param description
	 * @param value
	 * @param date
	 * @param transactionType
	 */
	public Transaction(String description, double value, Date date, TransactionType transactionType) {
		this.setDescription(description);
		this.setValue(value);
		this.setDate(date);
		this.setTransactionType(transactionType);
	}
	
	/**
	 * @param description
	 * @param value
	 * @param date
	 * @param transactionType
	 * @param periodicTransaction
	 */
	public Transaction(String description, double value, Date date, TransactionType transactionType, PeriodicTransaction periodicTransaction ) {
		this.setDescription(description);
		this.setValue(value);
		this.setDate(date);
		this.setTransactionType(transactionType);
		this.setPeriodicTransaction(periodicTransaction);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		checkDescription(description);
		this.description = description;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		checkValue(value);
		this.value = value;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="dateTransaction")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		checkDate(date);
		this.date = date;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	@SuppressWarnings("unused")
	private void setId(int id) {
		this.id = id;
	}	
	
	@ManyToOne
	@JoinColumn(name="idAccount")
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	@ManyToOne
	@JoinColumn(name="idTransactionType")
	public TransactionType getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		
		checkTransactionType(transactionType);
		this.transactionType = transactionType;
	}
	
	@ManyToOne
	@JoinColumn(name="idCategory")
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		
		this.category = category;
	}
	
	@ManyToOne
	@JoinColumn(name="idTargetTransaction")
	public TargetTransaction getTargetTransaction() {
		return this.targetTransaction;
	}

	public void setTargetTransaction(TargetTransaction targetTransaction) {
		this.targetTransaction = targetTransaction;
	}
	
	@ManyToOne
	@JoinColumn(name="idPeriodicTransaction")
	public PeriodicTransaction getPeriodicTransaction() {
		return this.periodicTransaction;
	}
	
	public String getTextPeriodicTransaction(){
		if (this.periodicTransaction!=null){
			return "Yes";
		}
		else{
			return "nope";
		}
	}

	public void setPeriodicTransaction(PeriodicTransaction periodicTransaction) {
		if (periodicTransaction != null){
			checkPeriodicTransaction(periodicTransaction);
		}
		this.periodicTransaction = periodicTransaction;
	}

	public String formatString(){
		return this.description + ";" + this.value + ";" 
				+ this.date.toString() + ";" + transactionType.toString() + ";\r\n"; 
	}
	
	@Override
	public String toString() {
		return description;
	}
	
	private static void checkDescription(String description) throws IllegalArgumentException {
		if (description.isEmpty()) {
			throw new IllegalArgumentException("The description of the transaction cannot be empty");
		}
	}

	private static void checkValue(double value) throws IllegalArgumentException {
		if (!isValidValue(value)) {
			throw new IllegalArgumentException(
					"The value of the transaction cannot be 0, it must be positive or negative");
		}
	}

	private static boolean isValidValue(double value) {
		return (0 != Double.compare(value, 0));
	}

	private static void checkDate(Date transactionDate) throws NullPointerException {
		if (transactionDate == null) {
			throw new NullPointerException("The date of the transaction cannot be null");
		}
	}
	
	private static void checkTransactionType(TransactionType transactionType) throws NullPointerException{
		if(transactionType == null){
			throw new NullPointerException("The transaction type can't be null");
		}
	}
	
	private static void checkPeriodicTransaction(PeriodicTransaction periodicTransaction) throws NullPointerException{
		if(periodicTransaction == null){
			throw new NullPointerException("The transaction type can't be null");
		}
	}
	
}
