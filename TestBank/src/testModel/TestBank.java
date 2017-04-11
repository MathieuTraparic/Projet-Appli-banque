package testModel;

import org.junit.Test;


import model.Bank;

public class TestBank {

	@Test(expected = NullPointerException.class)
	public void test_NullBankName() {
		Bank bank = new Bank(null,"bar");
	}
	
	@Test(expected = NullPointerException.class)
	public void test_NullBankCodes() {
		Bank bank = new Bank("bar",null);
	}
}
