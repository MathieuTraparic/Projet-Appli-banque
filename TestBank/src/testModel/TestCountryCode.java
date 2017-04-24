package testModel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.CountryCode;

public class TestCountryCode {
	
	private CountryCode countrycode;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	* testing if the account country code is null
	*/
	@Test(expected = NullPointerException.class)
	public void test_NullCountryCode() {
		countrycode = new CountryCode(null);
	}
	
	/**
	* testing if the account country code is empty
	*/
	@Test(expected = IllegalArgumentException.class)
	public void test_EmptyCountryCode() {
		countrycode = new CountryCode("");
	}
	/**
	* testing if the account type is not from the ArrayList TYPES
	*/
	@Test(expected = IllegalArgumentException.class)
	public void test_ValueOfAccountCountryCode() {
		countrycode = new CountryCode("qsd");
	}

}
