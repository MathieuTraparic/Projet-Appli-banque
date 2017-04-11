package model;

import java.util.ArrayList;
import java.util.Date;

public class PeriodicTransaction {
	private Date endDateTransaction;
	private int numberDefiningPeriodicity;
	private String frequencyUnit;

	/**
	 * To be fetched from DB BEFORE first instance is created
	 */
	@SuppressWarnings("serial")
	private static ArrayList<String> FREQUENCIES = new ArrayList<String>() {
		{
			add("Hebdomaidaire");
			add("Mensuelle");
			add("Trimestrielle");
			add("Anuelle");
		}
	};

	public PeriodicTransaction(Date endDateTransaction, int numberDefiningPeriodicity, String frequencyUnit) {
		//TODO update FREQENCIES from DB

		checkdayNumberDefiningPeriodicity(numberDefiningPeriodicity);
		checkEndDateTransaction(endDateTransaction);
		checkFrequencyUnit(frequencyUnit);

		this.endDateTransaction = endDateTransaction;
		this.numberDefiningPeriodicity = numberDefiningPeriodicity;
		this.frequencyUnit = frequencyUnit;
	}

	public static void checkEndDateTransaction(Date endDateTransaction) throws IllegalArgumentException{
		if (endDateTransaction == null) {
			throw new NullPointerException("The date cannot be null");
		}
	}

	public static void checkdayNumberDefiningPeriodicity(int numberDefiningPeriodicity) throws IllegalArgumentException {
		if (numberDefiningPeriodicity <= 0) {
			throw new IllegalArgumentException(
					"The number which defines the periodicity between each periodic transaction must be superior to 0");
		}
	}

	public static void checkFrequencyUnit(String frequencyUnit) throws IllegalArgumentException{
		if (frequencyUnit.isEmpty()) {
			throw new IllegalArgumentException("The unit of periodicity cannot be empty");
		}
	}

}
