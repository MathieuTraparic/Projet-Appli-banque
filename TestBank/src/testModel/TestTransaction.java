package testModel;

import java.util.Date;

import org.junit.Test;
import org.mockito.internal.matchers.Null;

import model.Transaction;;

/**
 * @author user
 *
 */
public class TestTransaction {
	
	Date date = new Date();

	/**
	 * testing if the description is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullTransactionDescription() {
		Transaction transaction = new Transaction(null,1254d,date);
	}
	
	/**
	 * testing if the description is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_EmptyTransactionDescription() {
		Transaction transaction = new Transaction("", 1254d, date);
	}
	
	/**
	 * testing if the value is zero
	 */	
	@Test(expected = IllegalArgumentException.class)
	public void test_TransactionValue() {
		Transaction transaction = new Transaction("bar", 0, date);
	}
	
	/**
	 * testing if the date is null
	 */	
	@Test(expected = NullPointerException.class)
	public void test_NullTransactionDate() {
		Transaction transaction = new Transaction("bar", -1254d, null);
	}
	

	

}
