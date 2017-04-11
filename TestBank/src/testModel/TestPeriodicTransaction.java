package testModel;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import model.PeriodicTransaction;

public class TestPeriodicTransaction {

	Date date = new Date();

	@Test(expected = NullPointerException.class)
	public void test_NullPeriodicTransactionEndDateTransaction() {
		PeriodicTransaction periodTransaction = 
				new PeriodicTransaction(null, 1254, "foo");
	}
	
	@Test(expected = NullPointerException.class)
	public void test_NullPeriodicTransactionUnit() {
		PeriodicTransaction periodTransaction = 
				new PeriodicTransaction(date, 1254, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_PeriodicTransactionUnit() {
		PeriodicTransaction periodTransaction = 
				new PeriodicTransaction(date, 1254, "");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_NumberDefiningPeriodicity() {
		PeriodicTransaction periodTransaction = 
				new PeriodicTransaction(date, -1254, "foo");
	}

}
