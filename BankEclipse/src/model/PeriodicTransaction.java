package model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name="periodictransaction")
@NamedQuery(name = "PeriodicTransaction.findAll", query = "SELECT t FROM PeriodicTransaction t")
public class PeriodicTransaction implements Serializable {

	private static final long serialVersionUID = 1L;
	private Date endDate;
	private int numberDefiningPeriodicity;
	private Frequency frequency;
	private Integer id;
	
	

	private PeriodicTransaction() {
	}

	public PeriodicTransaction(Date endDate, int numberDefiningPeriodicity, Frequency f) {
		//TODO update FREQENCIES from DB

		checkdayNumberDefiningPeriodicity(numberDefiningPeriodicity);
		checkEndDateTransaction(endDate);

		this.endDate = endDate;
		this.numberDefiningPeriodicity = numberDefiningPeriodicity;
		this.frequency = f;
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
	
	public static boolean isValidNumberDefiningPeriodicity(int numberDefiningPeriodicity){
		return (numberDefiningPeriodicity >= 0);
	}

	/*private static void checkFrequencyUnit(String frequencyUnit) throws IllegalArgumentException{
		if (frequencyUnit.isEmpty()) {
			throw new IllegalArgumentException("The unit of periodicity cannot be empty");
		}
		else if(!isValidFrequencyUnit(frequencyUnit)){
			throw new IllegalArgumentException("The frequency of the periodic transaction must be an existing type");
		}
	}*/
	
//	public static boolean isValidFrequencyUnit(String frequencyUnit){
//		return (FREQUENCIES.contains(frequencyUnit));
//	}

	public void setFrequency(Frequency frequency) {
		// TODO Auto-generated method stub
		
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
		this.endDate = endDate;
	}

	public int getNumberDefiningPeriodicity() {
		return this.numberDefiningPeriodicity;
	}

	public void setNumberDefiningPeriodicity(int numberDefiningPeriodicity) {
		this.numberDefiningPeriodicity = numberDefiningPeriodicity;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
