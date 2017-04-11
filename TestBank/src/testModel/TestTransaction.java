package testModel;

import java.util.Date;

import org.junit.Test;

import model.Transaction;;

public class TestTransaction {
	
	Date date = new Date();

	@Test(expected = IllegalArgumentException.class)
	public void test_NullTransactionDescription() {
		Transaction transaction = new Transaction(null,"Virement",1254d,date);
	}
	
	@Test(expected = NullPointerException.class)
	public void test_NullTransactionType() {
		Transaction transaction = new Transaction("bar", null,1254d,date);
	}
	
	@Test(expected = NullPointerException.class)
	public void test_NullTransactionValue() {
		Transaction transaction = new Transaction("bar","Virement", null, date);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_TransactionValue() {
		Transaction transaction = new Transaction("bar","Virement", 0d, date);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_TransactionType() {
		Transaction transaction = new Transaction("bar","qgdfgsdfg", 0d, date);
	}
	

}
