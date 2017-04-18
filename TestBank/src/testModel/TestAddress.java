package testModel;

import org.junit.Test;

import model.Address;

public class TestAddress {
	
	/*
	 * 	private String line1;
	 *	private String line2=null;
	 *	private String zip;
	 *	private String city;
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
	
	/**
	 * testing if zip code is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullAddressZip() {
		Address address = new Address("bar",null);
	}
	
	/**
	 * testing if zip code is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_EmptyAddressZip() {
		Address address = new Address("bar",null);
	}
	
	/**
	 * testing if zip code is a string with more than 50 character
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_AddressZipSize() {
		Address address = new Address("bar",null);
	}
	
	/**
	 * testing if city is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullAddressCity() {
		Address address = new Address("bar","bar");
	}
	
	/**
	 * testing if city is null
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_EmptyAddressCity() {
		Address address = new Address("bar","bar");
	}

}
