package testModel;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import model.Address;
import model.Owner;

public class TestOwner {
	
	/*String name, String firstName, 
	String phoneNumber, Date birthday, 
	String login, String pswd,  Address address
	*/
	Address add = new Address("foo","bar");
	
	Calendar dat = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR)-1, Calendar.MARCH, Calendar.THURSDAY, 12, 31, 15);
	
	String email = "azee@az.aze";
	
	/**
	 * testing if the Owner name is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullOwnerName() {
		Owner owner = new Owner(null, "foo","06 12 12 12 12", dat.getTime(), "lol", "lol",email, add);
	}
	
	/**
	 * testing if the Owner first name is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullOwnerFirstName() {
		Owner owner = new Owner("foo",null,"06 12 12 12 12", dat.getTime(), "lol", "lol",email, add);
	}
	
	/**
	 * testing if the Owner phone number is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullOwnerPhoneNumber() {
		Owner owner = new Owner("foo","bar",null, dat.getTime(), "lol", "lol",email, add);
	}
	
	/**
	 * testing if the Owner login is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullOwnerLogin() {
		Owner owner = new Owner("foo","bar", "06 12 12 12 12",dat.getTime(), null, "lol",email, add);
	}
	
	/**
	 * testing if the Owner pswd is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullOwnerPswd() {
		Owner owner = new Owner("foo","bar", "06 12 12 12 12",dat.getTime(),"lol", null,email, add);
	}
	
	/**
	 * testing if the Owner email is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullOwnerEmail() {
		Owner owner = new Owner("foo","bar", "06 12 12 12 12",dat.getTime(),"lol", "lol",null, add);
	}
	
	/**
	 * testing if the Owner birthday is in the future
	 */
	@Test(expected = IllegalArgumentException.class)	
	public void test_OwnerBirthday() {
		Calendar cal = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR)+1, Calendar.MARCH, Calendar.THURSDAY, 12, 31, 15);
		Owner owner = new Owner("foo","bar", "0612121212", cal.getTime(),"lol", "ll",email, add);
	}
	//
	//
	//Second Owner contructor : Owner(login,pwsd,email)
	//
	//
	
	/**
	 * testing if the Owner login is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullOwnerLogin2() {
		Owner owner = new Owner(null,"bar", email, "salt");
	}
	
	/**
	 * testing if the Owner pswd is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullOwnerPswd2() {
		Owner owner = new Owner("bar",null, email, "salt");
	}
	
	/**
	 * testing if the Owner email is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullOwnerEmail2() {
		Owner owner = new Owner("bar","foo", null, "salt");
	}
	
	
	/**
	 * testing if the Owner salt is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullOwnerSalt() {
		Owner owner = new Owner("bar","foo", email, null);
	}
	

}
