package testModel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.AccountType;

public class TestAccountType {
	
	AccountType accountType;

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
	* testing if the account type is null
	*/
	@Test(expected = NullPointerException.class)
	public void test_NullAccountType() {
		accountType = new AccountType(null);
	}
	
	/**
	* testing if the account type is empty
	*/
	@Test(expected = IllegalArgumentException.class)
	public void test_EmptyAccountType() {
		accountType = new AccountType("");
	}

}
