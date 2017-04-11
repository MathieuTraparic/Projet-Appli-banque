package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestAccount {
	// Account Number
	@Test(expected = NullPointerException.class)
	public void test_NullAccountNumber() {
		Account account = new Account(null, "Comment",1000d, 150, 0d);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_getAccountNumberEmpty(){
		Account account = new Account("", "Comment",1000d, 150, 0d);
	}
	
	// Account description
	@Test(expected = NullPointerException.class)
	public void test_NullDescription() {
		Account account = new Account("NA", null,1000d, 150, 0d);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_getAccountDescriptionEmpty(){
		Account account = new Account("NA", "",1000d, 150, 0d);
	}
	
	// Initial Balance
	@Test(expected = NullPointerException.class)
	public void test_NullInitialBalance() {
		Account account = new Account("NA", "Comment",null, 150, 0d);
	}
	
	@Test(expected = NullPointerException.class)
	public void test_NullAlertThreshold() {
		Account account = new Account("NA", "Comment",1000d, 150, null);
	}
}
