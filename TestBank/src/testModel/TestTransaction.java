package testModel;

import java.util.Date;

import org.junit.Test;

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
		Transaction transaction = new Transaction(null,"VIREMENT",1254d,date);
	}
	/**
	 * testing if the type is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullTransactionType() {
		Transaction transaction = new Transaction("bar", null,1254d,date);
	}

	
	/**
	 * testing if the description is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_EmptyTransactionDescription() {
		Transaction transaction = new Transaction("","VIREMENT", 1254d, date);
	}
	
	/**
	 * testing if the value is zero
	 */	
	@Test(expected = IllegalArgumentException.class)
	public void test_TransactionValue() {
		Transaction transaction = new Transaction("bar","VIREMENT", 0, date);
	}
	
	/**
	 * testing if the type is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_TransactionTypeEmpty() {
		Transaction transaction = new Transaction("bar","", 1254d, date);
	}
	
	/**
	 * testing if the type is not corresponding of one of the ArrayList TYPES in transaction model
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_TransactionType() {
		Transaction transaction = new Transaction("bar","dfgsdffg", 1230d, date);
	}
	

}
