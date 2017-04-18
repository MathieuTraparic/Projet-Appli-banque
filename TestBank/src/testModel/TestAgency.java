package testModel;

import org.junit.Test;

import model.Agency;

public class TestAgency {

	/**
	 * testing if the agency name is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullAgencyName() {
		Agency agency = new Agency(null,"bar");
	}
	

	/**
	 * testing if the agency name is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_EmptyAgencyName() {
		Agency agency = new Agency("","bar");
	}
	
	/**
	 * testing if the agency counter code is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullCounterCodes() {
		Agency agency = new Agency("bar",null);
	}
	
	/**
	 * testing if the agency counter code is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_EmptyCounterCodes() {
		Agency agency = new Agency("bar","");
	}

}
