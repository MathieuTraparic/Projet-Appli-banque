package testModel;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import model.Advisor;;

public class TestAdvisor {
	
	/*
	 * 	private String name;
	 *	private String firstName;
	 *	private String phoneNumber;
	 *	private String email;
	 *	private Date assignmentDate;
	 */
	
	Date date = new Date();
	
	/**
	 * testing if Advisor name is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullAdvisorName() {
		Advisor advisor = new Advisor(null,"bar","0612121212","bar", date);
	}
	
	/**
	 * testing if Advisor name is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_EmptyAdvisorName() {
		Advisor advisor = new Advisor("","bar","0612121212","bar", date);
	}
	
	/**
	 * testing if Advisor first name is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullAdvisorFirstName() {
		Advisor advisor = new Advisor("bar",null,"0612121212","bar", date);
	}
	
	/**
	 * testing if Advisor first name is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_EmptyAdvisorFirstName() {
		Advisor advisor = new Advisor("bar","","0612121212","bar", date);
	}
	
	/**
	 * testing if phone number is null
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_NullAdvisorPhoneNumber() {
		Advisor advisor = new Advisor("bar","bar",null,"bar", date);
	}
	
	/**
	 * testing if phone number is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_EmptyAdvisorPhoneNumber() {
		Advisor advisor = new Advisor("bar","bar","","bar", date);
	}
	
	/**
	 * testing if Advisor email is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullAdvisorEmail() {
		Advisor advisor = new Advisor("bar","bar","0612121212",null, date);
	}
	
	/**
	 * testing if Advisor email is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_EmptyAdvisorEmail() {
		Advisor advisor = new Advisor("bar","bar","0612121212","", date);
	}
	
	/**
	 * testing if the assignment date of the new advisor is in the future
	 */
	@Test(expected = IllegalArgumentException.class)	
	public void test_AdvisorAssignmentDate() {
		Calendar cal = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR)+2, Calendar.APRIL,24, 12, 31, 15);
		Advisor advisor = new Advisor("bar","bar","0612121212","machin@truc.com", cal.getTime());
	}
	
	/**
	 * testing if the assignment date of the new advisor is in the future
	 */
	@Test
	public void test_isValidAssignmentDate() {
		
	
		Calendar cal = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.MARCH, 12, 12, 31, 15);
		assertTrue(Advisor.isValidAssignmentDate(cal.getTime()));
		 cal = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.APRIL, 12, 12, 31, 15);
		assertTrue(Advisor.isValidAssignmentDate(cal.getTime()));
		
		 cal = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR)+2, Calendar.APRIL, 14, 12, 31, 15);
		assertFalse(Advisor.isValidAssignmentDate(cal.getTime()));
	}
	

}
