package testModel;

import org.junit.Test;

import model.Address;

public class TestAddress {
	
	/*
	 * 	private String line1;
	 *	private String line2=null;
	 */
	
	/**
	 * testing if address is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullAddressLine1() {
		Address address = new Address(null,"bar");
	}
	
	/**
	 * testing if address is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_EmptyAddressLine1() {
		Address address = new Address("","bar");
	}


}
