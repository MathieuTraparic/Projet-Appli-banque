package testModel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.TransactionType;

public class TestTransactionType {

	TransactionType transactionType;

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
	 * testing if the transaction type is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullTransactionType() {
		transactionType = new TransactionType(null);
	}

	/**
	 * testing if the transaction type is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_TransactionTypeEmpty() {
		transactionType = new TransactionType("");
	}

}
