/**
 *  @author Mathieu Traparic
 * 	Project : TestBank
 * 	Creation date : 2017-04-12
 */
package testUtil;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import util.Formater;

public class TestFormater {


	/**
	 * Test method for {@link util.Formater#removeUsualSeparators(java.lang.String)}.
	 */
	@Test
	public final void testRemoveUsualSeparators() {
		assertEquals("QQQQQ", Formater.removeUsualSeparators(" Q Q Q Q Q ----"));
	}

	/**
	 * Test method for {@link util.Formater#removeSeparators(java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testRemoveSeparators() {
		assertEquals("QQQQQ", Formater.removeSeparators(" Q Q Q  Q   --Q", "( |-)+"));
	}

	/**
	 * Test method for {@link util.Formater#formatNameCase(java.lang.String)}.
	 */
	@Test
	public final void testFormatNameCase() {
		assertEquals("Lucien", Formater.formatNameCase("lUcIeN"));
		assertEquals("Lucien", Formater.formatNameCase("Lucien"));
		assertEquals("", Formater.formatNameCase(""));
		
		assertEquals("Asd123", Formater.formatNameCase("asd123"));
		assertEquals("…ole", Formater.formatNameCase("ÈOle"));
	}

}
