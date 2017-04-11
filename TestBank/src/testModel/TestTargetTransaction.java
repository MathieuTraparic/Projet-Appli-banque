package testModel;

import org.junit.Test;

import model.TargetTransaction;

public class TestTargetTransaction {

	@Test(expected = IllegalArgumentException.class)
	public void test_NullTargetTransactionIBAN() {
		TargetTransaction target = new TargetTransaction(null,"bar");
	}
	
	@Test(expected = NullPointerException.class)
	public void test_NullTargetTransactionDescription() {
		TargetTransaction target = new TargetTransaction("bar",null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_ValidTargetTransactionIBAN() {
		TargetTransaction target = new TargetTransaction("235qsd423673567","bar");
	}
}

