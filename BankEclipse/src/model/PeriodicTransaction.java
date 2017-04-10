package model;

import java.util.Date;

public class PeriodicTransaction {
	private Date endDateTransaction;
	private int numberDefiningPeriodicity; //TODO: Should we not be able to say that we want an infinite number of transaction repetition ??
	private String frequencyUnit;
	
	public PeriodicTransaction(Date endDateTransaction, int numberDefiningPeriodicity, String frequencyUnit) {

		checkdayNumberDefiningPeriodicity(numberDefiningPeriodicity);
		checkEndDateTransaction(endDateTransaction);
		checkFrequencyUnit(frequencyUnit);
		
		this.endDateTransaction = endDateTransaction;
		this.numberDefiningPeriodicity = numberDefiningPeriodicity;
		this.frequencyUnit = frequencyUnit;
	}
	
	public void checkEndDateTransaction (Date endDateTransaction) {
		if (endDateTransaction == null){
			throw new NullPointerException("The date cannot be null");
		}
	}
	
	public void checkdayNumberDefiningPeriodicity (int numberDefiningPeriodicity) {
		if (numberDefiningPeriodicity <= 0){
			throw new IllegalArgumentException("The number which defines the periodicity between each periodic transaction must be superior to 0");
		}
	}
	
	public void checkFrequencyUnit (String frequencyUnit) {
		if (frequencyUnit == null){
			throw new NullPointerException("The unit of frequency (day / month / year) cannot be null");
		}
		if (frequencyUnit.isEmpty()){
			throw new IllegalArgumentException("The unit of periodicity cannot be empty");
		}
	}
	
	
}
