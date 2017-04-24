package testModel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Frequency;

public class TestFrequency {

	Frequency frequency;

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
	 * testing if the frequency unit is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullFrequencyunit() {
		frequency = new Frequency(null);
	}

	/**
	 * testing if the frequency unit is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_FrequencyunitEmpty() {
		frequency = new Frequency("");
	}

}
