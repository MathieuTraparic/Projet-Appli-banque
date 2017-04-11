package testModel;

import org.junit.Test;

import model.Agency;

public class TestAgency {

	@Test(expected = NullPointerException.class)
	public void test_NullAgencyName() {
		Agency agency = new Agency(null,"bar");
	}
	
	@Test(expected = NullPointerException.class)
	public void test_NullCounterCodes() {
		Agency agency = new Agency("bar",null);
	}

}
