package testModel;

import org.junit.Test;

import model.Account;

public class TestAccount {
	private Account account;
	
	/*
	 * (String number, String description, double initialBalance,
			 double overdraft, Double threshold, String countryCode, String type)
	 */
	
	/**
	 * testing if the account number is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullAccountNumber() {
		account = new Account(null, "Comment",1000d, -150d, 0d);
	}
	
	/**
	 * testing if the account number is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_AccountNumberEmpty(){
		account = new Account("", "Comment",1000d, -150d, 0d);
	}
	
	/**
	 * testing if the account description is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullDescription() {
		account = new Account("NA", null,1000d, -150d, 0d);
	}
	
	/**
	 * testing if the account description is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_AccountDescriptionEmpty(){
		account = new Account("NA","",1000d, -150d, 0d);
	}
	
	/**
	* testing if the account overdraft is positive
	*/
	@Test(expected = IllegalArgumentException.class)
	public void test_ValueOfAccountOverdraft() {
		account = new Account("NA", "Comment",1000d, 150.0, 0d);
	}
	
	
}
