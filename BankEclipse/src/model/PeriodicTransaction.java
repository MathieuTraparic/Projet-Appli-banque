package model;

import java.util.Date;

public class PeriodicTransaction {
	private Date endDateTransaction;
	private int numberDefiningPeriodicity; 
	private String frequencyUnit;

	public PeriodicTransaction(Date endDateTransaction, int numberDefiningPeriodicity, String frequencyUnit) {

		checkdayNumberDefiningPeriodicity(numberDefiningPeriodicity);
		checkEndDateTransaction(endDateTransaction);
		checkFrequencyUnit(frequencyUnit);

		this.endDateTransaction = endDateTransaction;
		this.numberDefiningPeriodicity = numberDefiningPeriodicity;
		this.frequencyUnit = frequencyUnit;
	}

	public static void checkEndDateTransaction(Date endDateTransaction) {
		if (endDateTransaction == null) {
			throw new NullPointerException("The date cannot be null");
		}
	}

	public static void checkdayNumberDefiningPeriodicity(int numberDefiningPeriodicity) {
		if (numberDefiningPeriodicity <= 0) {
			throw new IllegalArgumentException(
					"The number which defines the periodicity between each periodic transaction must be superior to 0");
		}
	}

	public static void checkFrequencyUnit(String frequencyUnit) {
		if (frequencyUnit.isEmpty()) {
			throw new IllegalArgumentException("The unit of periodicity cannot be empty");
		}
	}

}
