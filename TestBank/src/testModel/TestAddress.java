package testModel;

import org.junit.Before;
import org.junit.Test;

import model.Address;
import model.CpVille;

public class TestAddress {
	
	private CpVille cpVille;
	private Address address;
	
	@Before
	public void setUp(){
		this.cpVille=new CpVille("33000", "Bordeaux");
		this.address = new Address("65 rue machin", " boite 2 ", cpVille);
		
	}
	
	/**
	 * testing if address is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullAddressLine1() {
		this.address = new Address(null, " boite 2 ", cpVille);
	}
	
	/**
	 * testing if address is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_EmptyAddressLine1() {
		this.address = new Address("", " boite 2 ", cpVille);
	}
	
	@Test(expected = NullPointerException.class)
	public void test_NullcpVille() {
		this.address = new Address("65 rue machin", " boite 2 ", null);
	}


}
