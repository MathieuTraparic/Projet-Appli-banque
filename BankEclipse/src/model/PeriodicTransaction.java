package model;

import java.util.Date;

public class PeriodicTransaction {
	private Date endDateTransaction;
	private int dayNumberperiodicity;
	private String frequencyUnit;
	
	public PeriodicTransaction(Date endDateTransaction, int dayNumberperiodicity, String frequencyUnit) {

		checkdayNumberperiodicity(dayNumberperiodicity);
		checkEndDateTransaction(endDateTransaction);
		checkFrequencyUnit(frequencyUnit);
		
		this.endDateTransaction = endDateTransaction;
		this.dayNumberperiodicity = dayNumberperiodicity;
		this.frequencyUnit = frequencyUnit;
	}
	
	public void checkEndDateTransaction (Date endDateTransaction) {
		if (endDateTransaction == null){
			throw new NullPointerException("The date cannot be null");
		}
	}
	
	public void checkdayNumberperiodicity (int dayNumberperiodicity) {
		if (dayNumberperiodicity <= 0){
			throw new IllegalArgumentException("The number of day between each periodic transaction must be superior to 0");
		}
	}
	
	public void checkFrequencyUnit (String frequencyUnit) {
		if (frequencyUnit == null){
			throw new NullPointerException("The unit of frequency (day / month / year) cannot be null");
		}
	}
	
	
}
