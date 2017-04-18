package testModel;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import model.Frequency;
import model.PeriodicTransaction;

public class TestPeriodicTransaction {

	Date date = new Date();
	Frequency f;
	@Before
	public void setUp(){
		f=new Frequency();
	}

	/**
	 * testing if the date of the periodic transaction is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullPeriodicTransactionEndDateTransaction() {
		PeriodicTransaction periodTransaction = 
				new PeriodicTransaction(null, 1254, f);
	}
	
	/**
	 * testing if the periodic transaction unit is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullPeriodicTransactionUnit() {
		PeriodicTransaction periodTransaction = 
				new PeriodicTransaction(date, 1254, null);
	}
	
	/**
	 * testing if the periodic transaction unit is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_PeriodicTransactionUnit() {
		PeriodicTransaction periodTransaction = 
				new PeriodicTransaction(date, 1254, f);
	}
	
	/**
	 * testing if the periodic transaction unit is not in the ArrayList FREQUENCIES 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_ValueOfPeriodicTransactionUnit() {
		PeriodicTransaction periodTransaction = 
				new PeriodicTransaction(date, 1254, f);
	}
	
	/**
	 * testing if the periodic transaction is a negative number
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_NumberDefiningPeriodicity() {
		PeriodicTransaction periodTransaction = 
				new PeriodicTransaction(date, -1254, f);
	}

}
