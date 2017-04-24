package testModel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.CpVille;

public class TestCpVille {

	private CpVille cpVille;

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
	 * testing if the zip code is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullZipCode() {
		cpVille = new CpVille(null, "foo");
	}

	/**
	 * testing if the zip code is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_ZipCodeEmpty() {
		cpVille = new CpVille("", "foo");
	}

	/**
	 * testing if the zip code is too long
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_ZipCodeLength() {
		cpVille = new CpVille("012345678901234567890123456789012345678901234567890123456789", "foo");
	}

	/**
	 * testing if the city name is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullCityName() {
		cpVille = new CpVille("foo", null);
	}

	/**
	 * testing if the city name is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_CityNameEmpty() {
		cpVille = new CpVille("foo", "");
	}

}
