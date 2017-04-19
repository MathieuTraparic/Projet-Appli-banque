/**
 *  @author Mathieu Traparic
 * 	Project : TestBank
 * 	Creation date : 2017-04-19
 */
package testUtil;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import util.PasswordHandler;

/**
 *
 */
public class TestPasswordHandler {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link util.PasswordHandler#hash(java.lang.String)}.
	 */
	@Test
	public final void testHash() {
		String salt =PasswordHandler.getNewSalt();
		String s =PasswordHandler.hash("qwerw"+salt);
		System.out.println(s);
	}

}
