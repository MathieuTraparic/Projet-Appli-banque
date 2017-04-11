package testModel;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Account;

public class TestAccount {
	private Account account;
	
	// Account Number
	@Test(expected = NullPointerException.class)
	public void test_NullAccountNumber() {
		account = new Account(null, "Comment",1000d, 150, 0d, "FR", "EPARGNE");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_getAccountNumberEmpty(){
		account = new Account("", "Comment",1000d, 150, 0d,"FR", "EPARGNE");
	}
	
	// Account description
	@Test(expected = NullPointerException.class)
	public void test_NullDescription() {
		account = new Account("NA", null,1000d, 150, 0d,"FR", "EPARGNE");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_getAccountDescriptionEmpty(){
		account = new Account("NA", "",1000d, 150, 0d,"FR", "EPARGNE");
	}
	

	
}
