	package model;

import java.io.Serializable;
import java.util.Date;

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
	private Integer id;
	private String description;
	private double value;
	private Date date;
	private Account account;
	private TransactionType transactionType;
	private Category category;
	private TargetTransaction targetTransaction;
	private PeriodicTransaction periodicTransaction;
	
	
	private Transaction(){
		
	}

	public Transaction(String description, double value, Date date) {

		checkValue(value);
		checkDate(date);
		checkDescription(description);

		this.description = description;
		this.value = value;
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
	@Temporal(TemporalType.TIME)
	@Column(name="dateTransaction")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public static boolean isValidValue(double value) {
		return (0 != Double.compare(value, 0));
	}

	private static void checkDate(Date transactionDate) throws NullPointerException {
		if (transactionDate == null) {
			throw new NullPointerException("The date of the transaction cannot be null");
		}

	}
	@ManyToOne
	@JoinColumn(name="idAccount")
	private Account getAccount() {
		return this.account;
	}

	private void setAccount(Account account) {
		this.account = account;
	}
	@ManyToOne
	@JoinColumn(name="idTransactionType")
	public TransactionType getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	
	@ManyToOne
	@JoinColumn(name="idCategory")
	private Category getCategory() {
		return this.category;
	}

	private void setCategory(Category category) {
		this.category = category;
	}
	
	@ManyToOne
	@JoinColumn(name="idTargetTransaction")
	private TargetTransaction getTargetTransaction() {
		return this.targetTransaction;
	}

	private void setTargetTransaction(TargetTransaction targetTransaction) {
		this.targetTransaction = targetTransaction;
	}
	
	@ManyToOne
	@JoinColumn(name="idPeriodicTransaction")
	public PeriodicTransaction getPeriodicTransaction() {
		return this.periodicTransaction;
	}

	public void setPeriodicTransaction(PeriodicTransaction periodicTransaction) {
		this.periodicTransaction = periodicTransaction;
	}

//	private static void checkType(String type) throws IllegalArgumentException {
//		if (type == null) {
//			throw new NullPointerException("The type of the transaction cannot be null");
//		}
//	}

}
