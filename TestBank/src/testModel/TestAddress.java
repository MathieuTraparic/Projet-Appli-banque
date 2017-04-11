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
	
	@Test(expected = NullPointerException.class)
	public void test_NullAddressLine1() {
		Address address = new Address(null,"bar","bar","bar");
	}
	
	@Test(expected = NullPointerException.class)
	public void test_NullAddressZip() {
		Address address = new Address("bar",null,null,"bar");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_AddressZipSize() {
		Address address = new Address("bar",null,"000000000000000000000000000000000000000000000000000000000000","bar");
	}
	
	@Test(expected = NullPointerException.class)
	public void test_NullAddressCity() {
		Address address = new Address("bar","bar","bar",null);
	}

}
