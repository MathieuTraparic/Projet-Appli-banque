package model;

import java.util.ArrayList;
import java.util.Date;

import util.Formater;

public class PeriodicTransaction {
	private Date endDate;
	private int numberDefiningPeriodicity;
	private String frequencyUnit;

	/**
	 * To be fetched from DB BEFORE first instance is created
	 */
	@SuppressWarnings("serial")
	private static ArrayList<String> FREQUENCIES = new ArrayList<String>() {
		{
			add("HEBDOMADAIRE");
			add("MENSUELLE");
			add("TRIMESTRIELLE");
			add("ANUELLE");
		}
	};

	public PeriodicTransaction(Date endDate, int numberDefiningPeriodicity, String frequencyUnit) {
		//TODO update FREQENCIES from DB

		checkdayNumberDefiningPeriodicity(numberDefiningPeriodicity);
		checkEndDateTransaction(endDate);
		checkFrequencyUnit(frequencyUnit);

		this.endDate = endDate;
		this.numberDefiningPeriodicity = numberDefiningPeriodicity;
		this.frequencyUnit = Formater.formatNameCase(frequencyUnit);
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

	private static void checkFrequencyUnit(String frequencyUnit) throws IllegalArgumentException{
		if (frequencyUnit.isEmpty()) {
			throw new IllegalArgumentException("The unit of periodicity cannot be empty");
		}
		else if(!isValidFrequencyUnit(frequencyUnit)){
			throw new IllegalArgumentException("The frequency of the periodic transaction must be an existing type");
		}
	}
	
	public static boolean isValidFrequencyUnit(String frequencyUnit){
		return (FREQUENCIES.contains(frequencyUnit));
	}

}
