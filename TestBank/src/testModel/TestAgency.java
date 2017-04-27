package testModel;

import org.junit.Before;
import org.junit.Test;

import model.Address;
import model.Agency;
import model.Bank;
import model.CpVille;

public class TestAgency {

	private Address address;
	private Bank bank;
	private Agency agency;

	@Before
	public void setUp() {
		this.address = new Address("bl", null, new CpVille("132", "awefg"));
		this.bank = new Bank("bankname", "12345");
		this.agency = new Agency("name", "123", address, bank);
	}

	/**
	 * testing if the agency name is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullAgencyName() {
		this.agency = new Agency(null, "123", address, bank);
	}

	/**
	 * testing if the agency name is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_EmptyAgencyName() {
		this.agency = new Agency("", "123", address, bank);
	}

	/**
	 * testing if the agency counter code is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullCounterCodes() {
		this.agency = new Agency("name", null, address, bank);
	}

	/**
	 * testing if the agency counter code is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_EmptyCounterCodes() {
		this.agency = new Agency("name", "", address, bank);
	}

}
