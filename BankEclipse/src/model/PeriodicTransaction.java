package model;

import java.util.Date;

import util.Formater;

public class PeriodicTransaction {
	private Date endDate;
	private int numberDefiningPeriodicity;
	private Frequency frequency;
	
	

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

	public Frequency getFrequency() {
		return this.frequency;
	}

}
