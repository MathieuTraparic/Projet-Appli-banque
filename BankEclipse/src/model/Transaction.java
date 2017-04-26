package model;

import java.io.Serializable;
import java.util.Comparator;
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
	
//
//    private SimpleObjectProperty<Date> dateP;
//    private StringProperty name;
    
	private Transaction(){
		
	}
  

//    public Transaction(String name, Date dateP) {
//        this.name = new SimpleStringProperty(name);
//        this.dateP = new SimpleObjectProperty(dateP);
//    }
	
    public Transaction(String description, Date date) {
        this.description = description;
        this.date = date;
    }


//    public StringProperty nameProperty() {
//        return description;
//    }
//
//    public String getDateAsString() {
//        SimpleDateFormat smp = new SimpleDateFormat("dd MMMMM yyyy");
//        String strDate = (null == date|| null == date.get())
//                ? "" : smp.format(date.get());
//       
//        return strDate;
//    }


	@Override
	public String toString() {
		return description;
	}

	public Transaction(String description, double value, Date date, TransactionType transactionType) {

		checkValue(value);
		checkDate(date);
		checkDescription(description);

		this.description = description;
		this.value = value;
		this.date = date;
		this.transactionType = transactionType;
	}
	
	// TODO avoid repetion with the previous controller
	public Transaction(String description, double value, Date date, TransactionType transactionType, PeriodicTransaction periodicTransaction ) {

		checkValue(value);
		checkDate(date);
		checkDescription(description);
		// TODO chek the periodic transaction

		this.description = description;
		this.value = value;
		this.date = date;
		this.transactionType = transactionType;
		this.periodicTransaction = periodicTransaction;
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
	
	@Temporal(TemporalType.DATE)
	@Column(name="dateTransaction")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	private void setId(int id) {
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
		this.periodicTransaction = periodicTransaction;
	}

//	private static void checkType(String type) throws IllegalArgumentException {
//		if (type == null) {
//			throw new NullPointerException("The type of the transaction cannot be null");
//		}
//	}

	public String formatString(){
		return this.description + ";" + this.value + ";" 
				+ this.date.toString() + ";" + transactionType.toString() + ";\n"; 
	}
}
