package testUtil;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import util.IBANHandler;
import util.Validator;

public class TestIbanHandler {

	@Before
	public void setUp() throws Exception {
	}



	@Test
	public final void testIsValidIban() {
		assertTrue(IBANHandler.ibanTest("FR1420041010050500013M02606"));
		assertTrue(IBANHandler.ibanTest(" FR14 2004 1010 0505 0001 3M02 606 "));
		assertTrue(IBANHandler.ibanTest("GB29 NWBK 6016 1331 9268 19"));

		assertFalse(IBANHandler.ibanTest("FR13200410100440013M00006"));

	}

	@Test
	public final void testGenerateIban() {

		assertTrue(IBANHandler.genrateIBAN("05 0001 3M02 606", "010 05", "2004 1", "FR")
				.equals("FR142004 1010 0505 0001 3M02 606"));
		
		assertTrue(IBANHandler.genrateIBAN("0500013M02606", "01005", "20041", "FR")
				.equals("FR1420041010050500013M02606"));

	}

}
