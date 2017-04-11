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
	Address add = new Address("foo","bar","foo","bar");
	Date dat = new Date();
	
	@Test(expected = NullPointerException.class)
	public void test_NullOwnerName() {
		Owner owner = new Owner(null, "foo","bar", dat, "lol", "lol", add);
	}
	
	@Test(expected = NullPointerException.class)
	public void test_NullOwnerFirstName() {
		Owner owner = new Owner("foo",null,"bar", dat, "lol", "lol", add);
	}
	
	@Test(expected = NullPointerException.class)
	public void test_NullOwnerPhoneNumber() {
		Owner owner = new Owner("foo","bar",null, dat, "lol", "lol", add);
	}
	
	@Test(expected = NullPointerException.class)
	public void test_NullOwnerLogin() {
		Owner owner = new Owner("foo","bar", "06 12 12 12 12",dat, null, "lol", add);
	}
	
	@Test(expected = NullPointerException.class)
	public void test_NullOwnerPswd() {
		Owner owner = new Owner("foo","bar", "0612121212",dat,"lol", null, add);
	}
	
	@Test(expected = IllegalArgumentException.class)	
	public void test_OwnerBirthday() {
		Calendar cal = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR)+1, Calendar.MARCH, Calendar.THURSDAY, 12, 31, 15);
		Owner owner = new Owner("foo","bar", "0612121212", cal.getTime(),"lol", "ll", add);
	}
	

}
