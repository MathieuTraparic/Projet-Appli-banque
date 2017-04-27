package testModel;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import model.Address;
import model.Advisor;
import model.Agency;
import model.Bank;
import model.CpVille;;

public class TestAdvisor {

	/*
	 * private String name; private String firstName; private String
	 * phoneNumber; private String email; private Date assignmentDate;
	 */

	private Date assignmentDate;
	private Advisor advisor;
	private Agency agency;

	@Before
	public void setUp() {
		this.assignmentDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR) - 1, Calendar.APRIL, 24,
				12, 31, 15).getTime();
		// TODO mock
		this.agency = new Agency("df", "123", new Address("bl", null, new CpVille("132", "awefg")),
				new Bank("sdf", "12345"));
		this.advisor = new Advisor("name", "firstname", "0512345678", "mach@aer.com", assignmentDate, agency);

	}

	@Test
	public void test_dateIsNow() {
		this.advisor = new Advisor("name", "firstname", "0512345678", "mach@aer.com", Calendar.getInstance().getTime(),
				agency);
	}

	/**
	 * testing if Advisor name is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullAdvisorName() {
		this.advisor = new Advisor(null, "firstname", "0512345678", "mach@aer.com", assignmentDate, agency);
	}

	/**
	 * testing if Advisor name is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_EmptyAdvisorName() {
		this.advisor = new Advisor("", "firstname", "0512345678", "mach@aer.com", assignmentDate, agency);
	}

	/**
	 * testing if Advisor first name is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullAdvisorFirstName() {
		this.advisor = new Advisor("name", null, "0512345678", "machin@free.com", assignmentDate, agency);
	}

	/**
	 * testing if Advisor first name is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_EmptyAdvisorFirstName() {
		this.advisor = new Advisor("name", "", "0512345678", "mach@aer.com", assignmentDate, agency);
	}

	/**
	 * testing if phone number is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullAdvisorPhoneNumber() {
		this.advisor = new Advisor("name", "firstname", null, "mach@aer.com", assignmentDate, agency);
	}

	/**
	 * testing if phone number is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_EmptyAdvisorPhoneNumber() {
		this.advisor = new Advisor("name", "firstname", "", "mach@aer.com", assignmentDate, agency);
	}

	/**
	 * testing if Advisor email is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullAdvisorEmail() {
		this.advisor = new Advisor("name", "firstname", "0512345678", null, assignmentDate, agency);
	}

	/**
	 * testing if Advisor email is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_EmptyAdvisorEmail() {
		this.advisor = new Advisor("name", "firstname", "0512345678", "", assignmentDate, agency);
	}

	/**
	 * testing if the assignment date of the new advisor is in the future
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_AdvisorAssignmentDate() {
		Calendar cal = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR) + 2, Calendar.APRIL, 24, 12, 31,
				15);
		this.advisor = new Advisor("name", "firstname", "0512345678", "mach@aer.com", cal.getTime(), agency);
	}

}
