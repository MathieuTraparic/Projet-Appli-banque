/**
 *  @author Mathieu Traparic
 * 	Project : TestBank
 * 	Creation date : 2017-04-11
 */
package testUtil;

import static org.junit.Assert.*;
import util.Validator;

import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class TestValidator {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link util.Validator#isValidEmailAddress(java.lang.String)}.
	 */
	@Test
	public final void testIsValidEmailAddress() {
		assertTrue(Validator.isValidEmailAddress("machin@truc.com"));
		assertTrue(Validator.isValidEmailAddress("machin@google.fr"));
		assertTrue(Validator.isValidEmailAddress("ma--rc.erchin@tr.cdfg"));
		
		assertFalse(Validator.isValidEmailAddress("@truc.com"));
		assertFalse(Validator.isValidEmailAddress("ert@truccom"));
		assertFalse(Validator.isValidEmailAddress("awd@.com"));

	}

	/**
	 * Test method for {@link util.Validator#isValidPhoneNumber(java.lang.String)}.
	 */
	@Test
	public final void testIsValidPhoneNumber() {
		assertTrue(Validator.isValidPhoneNumber("0511223344"));
		assertTrue(Validator.isValidPhoneNumber("05 11 22 33 44"));
		assertTrue(Validator.isValidPhoneNumber("06-11-22-33-44"));
		assertTrue(Validator.isValidPhoneNumber("+336-11-22-33-44"));
		assertTrue(Validator.isValidPhoneNumber("00336-11-22-33-44"));
		assertTrue(Validator.isValidPhoneNumber("0033 6-11-22-33-44"));
		assertTrue(Validator.isValidPhoneNumber("+33 6-11-22-33-44"));
		
		assertFalse(Validator.isValidPhoneNumber("55 55 55 55 55"));
		assertFalse(Validator.isValidPhoneNumber("0 55 55 55 55"));
		assertFalse(Validator.isValidPhoneNumber("0q 55 55 55 55"));
		assertFalse(Validator.isValidPhoneNumber("0004 55 55 55 55"));
		assertFalse(Validator.isValidPhoneNumber("0033 05 55 55 55 55"));
		assertFalse(Validator.isValidPhoneNumber("0111 55 55 55 55"));
		assertFalse(Validator.isValidPhoneNumber("+004 55 55 55 55"));
		assertFalse(Validator.isValidPhoneNumber("+33 04 55 55 55 55"));
		
		
	}

	/**
	 * Test method for {@link util.Validator#isValidName(java.lang.String)}.
	 */
	@Test
	public final void testIsValidName() {
		assertTrue(Validator.isValidName("qwérasd"));
		assertTrue(Validator.isValidName("Jean-paul"));
		assertTrue(Validator.isValidName("del patchi"));
		assertTrue(Validator.isValidName("père noël"));
		
		
		assertFalse(Validator.isValidName("qwe.dw"));
		assertFalse(Validator.isValidName("pas_ed"));
		assertFalse(Validator.isValidName("qwe%wd"));
		assertFalse(Validator.isValidName("jackson5"));
		assertFalse(Validator.isValidName("$"));
	}

	/**
	 * Test method for {@link util.Validator#isValidIban(java.lang.String)}.
	 */
	@Test
	public final void testIsValidIban() {
		assertTrue(Validator.isValidIban("FR1420041010050500013M02606"));
		assertTrue(Validator.isValidIban(" FR14 2004 1010 0505 0001 3M02 606 "));
		
		
		assertFalse(Validator.isValidIban("FR13200410100440013M00006"));
	}

}
