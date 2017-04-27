package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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


@Entity
@Table(name="periodictransaction")
@NamedQuery(name = "PeriodicTransaction.findAll", query = "SELECT t FROM PeriodicTransaction t")
public class PeriodicTransaction implements Serializable {

	private static final long serialVersionUID = -7446323535530912419L;
	private int id;
	private Date endDate;
	private int numberDefiningPeriodicity;
	private Frequency frequency;
	private List<Transaction> transactions;

	@SuppressWarnings("unused")
	private PeriodicTransaction() {}

	/**
	 * @param endDate
	 * @param numberDefiningPeriodicity
	 * @param frequency
	 */
	public PeriodicTransaction(Date endDate, int numberDefiningPeriodicity, Frequency f) {
		this.setEndDate(endDate);
		this.setNumberDefiningPeriodicity(numberDefiningPeriodicity);
		this.setFrequency(f);
	}

	public void setFrequency(Frequency frequency) {
		this.frequency =frequency;	
	}
	
	@ManyToOne
	@JoinColumn(name="idFreq")
	public Frequency getFrequency() {
		return this.frequency;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		checkEndDateTransaction(endDate);
		this.endDate = endDate;
	}

	public int getNumberDefiningPeriodicity() {
		return this.numberDefiningPeriodicity;
	}

	public void setNumberDefiningPeriodicity(int numberDefiningPeriodicity) {
		checkdayNumberDefiningPeriodicity(numberDefiningPeriodicity);
		this.numberDefiningPeriodicity = numberDefiningPeriodicity;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	@SuppressWarnings("unused")
	private void setId(Integer id) {
		this.id = id;
	}
	
	@OneToMany(mappedBy="PeriodicTransaction")
	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		checkTransaction(transactions);
		this.transactions = transactions;
	}

	public Transaction addPeriodictransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setPeriodicTransaction(this);
		return transaction;
	}

	public Transaction removePeriodictransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setPeriodicTransaction(null);
		return transaction;
	}
	
	public String getDescription(){
		if(this.transactions.isEmpty()){
			return "empty transaction list";
		}else{
			return this.transactions.get(0).getDescription();
		}
	}
	
	private static void checkEndDateTransaction(Date endDateTransaction) throws IllegalArgumentException{
		if (endDateTransaction == null) {
			throw new NullPointerException("The date cannot be null");
		}
	}
	
	private static void checkdayNumberDefiningPeriodicity(int numberDefiningPeriodicity) throws IllegalArgumentException {
		if (!isValidNumberDefiningPeriodicity(numberDefiningPeriodicity)) {
			throw new IllegalArgumentException(
					"The number which defines the periodicity between each periodic transaction must be superior to 0");
		}
	}
	
	private static void checkTransaction(List<Transaction> transactions2) throws NullPointerException{
		if(transactions2 == null){
			throw new NullPointerException("The transaction can't be null");
		}
	}
	
	public static boolean isValidNumberDefiningPeriodicity(int numberDefiningPeriodicity){
		return (numberDefiningPeriodicity >= 0);
	}
}
