package testModel;

import org.junit.Test;

import model.TargetTransaction;

public class TestTargetTransaction {

	/**
	 * to test if IBAN is empty or null 
	 */

	@Test(expected = NullPointerException.class)
	public void test_NullTargetTransactionIBAN() {
		TargetTransaction target = new TargetTransaction(null,"bar");
		TargetTransaction targets = new TargetTransaction("","bar");
	}
	
	/**
	 * to test if summuray of transactionTarget is null 
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullTargetTransactionDescription() {
		TargetTransaction target = new TargetTransaction("FR1420041010050500013M02606",null);
	}

	/**
	 * to test if incorrect IBAN throws error
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_ValidTargetTransactionIBAN() {
		TargetTransaction target = new TargetTransaction("235qsd423673567","bar");
	}
}

