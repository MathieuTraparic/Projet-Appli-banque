package testModel;

import org.junit.Test;


import model.Bank;

public class TestBank {

	/**
	 * testing if the bank name is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullBankName() {
		Bank bank = new Bank(null,"bar");
	}
	
	/**
	 * testing if the bank name is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_EmptyBankName() {
		Bank bank = new Bank("","bar");
	}
	
	/**
	 * testing if the bank code is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullBankCodes() {
		Bank bank = new Bank("bar",null);
	}
	
	/**
	 * testing if the bank code is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_EmptyBankCode() {
		Bank bank = new Bank("dffg","");
	}
}
