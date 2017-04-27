package testModel;

import java.util.Calendar;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;

import model.Account;
import model.AccountType;
import model.Agency;
import model.CountryCode;

public class TestAccount {
	private Account account;

	// TODO Mock
	CountryCode countryCode;
	Agency agency;
	AccountType accountType;
	Date date;
	/*
	 * (String number, String description, double initialBalance, double
	 * overdraft, Double threshold, String countryCode, String type)
	 */

	public void setUp() {
		this.date = Calendar.getInstance().getTime();
		this.countryCode = new CountryCode("1234");
		this.agency = new Agency("agenceName", "12345");

		this.account = new Account("1234 1234 1234", "description", 0.0, 0.0, 0.0, 0.0, this.countryCode, this.date,
				this.agency, this.accountType);
	}

	/**
	 * testing if the account number is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullAccountNumber() {
		account = new Account(null, "description", 0.0, 0.0, 0.0, 0.0, this.countryCode, this.date, this.agency,
				this.accountType);
	}

	/**
	 * testing if the account number is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_AccountNumberEmpty() {
		account = new Account(" ", "description", 0.0, 0.0, 0.0, 0.0, this.countryCode, this.date, this.agency,
				this.accountType);
	}

	/**
	 * testing if the account description is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullDescription() {
		account = new Account("1234 1234 1234", null, 0.0, 0.0, 0.0, 0.0, this.countryCode, this.date, this.agency,
				this.accountType);
	}

	/**
	 * testing if the account description is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_AccountDescriptionEmpty() {
		account = new Account("1234 1234 1234", "", 0.0, 0.0, 0.0, 0.0, this.countryCode, this.date, this.agency,
				this.accountType);
	}

	/**
	 * testing if the account overdraft is positive
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_ValueOfAccountOverdraft() {
		account = new Account("1234 1234 1234", "description", 0.0, 10.9, 0.0, 0.0, this.countryCode, this.date,
				this.agency, this.accountType);
	}

	@Test(expected = NullPointerException.class)
	public void test_NullCountryCode() {
		account = new Account("1234 1234 1234", "description", 0.0, 0.0, 0.0, 0.0, null, this.date, this.agency,
				this.accountType);
	}

	@Test(expected = NullPointerException.class)
	public void test_NullDate() {
		account = new Account("1234 1234 1234", "description", 0.0, 0.0, 0.0, 0.0, this.countryCode, null, this.agency,
				this.accountType);
	}

	@Test(expected = NullPointerException.class)
	public void test_NullAgency() {
		account = new Account("1234 1234 1234", "description", 0.0, 0.0, 0.0, 0.0, this.countryCode, this.date, null,
				this.accountType);
	}

	@Test(expected = NullPointerException.class)
	public void test_NullAccountType() {
		account =  new Account("1234 1234 1234", "description", 0.0, 0.0, 0.0, 0.0, this.countryCode, this.date, this.agency, null);
	}

	@Ignore
	public void test_getBalance() {
		//TODO mock up transaction, account.setTransaction() is private 
	}
	@Ignore
	public void test_getBalanceHistory() {
		//TODO mock up transaction, account.setTransaction() is private 
	}

}
