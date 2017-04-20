/*
 * Based on : https://gist.github.com/DandyDev/5394643
 */

package util;

import java.math.BigInteger;

public final class IBANHandler {

	public static final int IBANNUMBER_MIN_SIZE = 15;
	public static final int IBANNUMBER_MAX_SIZE = 34;
	public static final BigInteger IBANNUMBER_MAGIC_NUMBER = new BigInteger("97");

	public static boolean ibanTest(String accountNumber) {

		String newAccountNumber = Formater.removeSeparators(accountNumber, "( |-)+");

		// Check that the total IBAN length is correct as per the country. If
		// not, the IBAN is invalid.
		if (newAccountNumber.length() < IBANNUMBER_MIN_SIZE || newAccountNumber.length() > IBANNUMBER_MAX_SIZE) {
			return false;
		}

		// Move the four initial characters to the end of the string.
		newAccountNumber = newAccountNumber.substring(4) + newAccountNumber.substring(0, 4);


		// Interpret the string as a decimal integer and compute the remainder
		// of that number on division by 97.
		
		return convertToNumeric(newAccountNumber).mod(IBANNUMBER_MAGIC_NUMBER).intValue() == 1;

	}

	/**
	 * @param accountNumber
	 *            the account number (with rib key)
	 * @param agencyCode
	 * @param bankCode
	 * @param CountryCode
	 *            The 2 letters identifying the country
	 * @return The complete IBAN number as a String
	 */
	public static String genrateIBAN(String accountNumber, String agencyCode, String bankCode, String CountryCode) {
		StringBuilder bl = new StringBuilder();

		bl.append(Formater.removeUsualSeparators(bankCode));
		bl.append(Formater.removeUsualSeparators(agencyCode));
		bl.append(Formater.removeUsualSeparators(accountNumber));
		bl.append(Formater.removeUsualSeparators(CountryCode));
		bl.append("00");
		
		BigInteger incomplete =convertToNumeric(bl.toString());
		int key = incomplete.mod(IBANNUMBER_MAGIC_NUMBER).intValue();
		key =IBANNUMBER_MAGIC_NUMBER.intValue()+1-key;
		
		bl=new StringBuilder();
		
		bl.append(CountryCode);
		bl.append(key);
		bl.append(bankCode);
		bl.append(agencyCode);
		bl.append(accountNumber);
		
		return bl.toString();
		
	}

	private static BigInteger convertToNumeric(String iban) {
		// Replace each letter in the string with two digits, thereby expanding
		// the string, where A = 10, B = 11, ..., Z = 35.
		StringBuilder numericAccountNumber = new StringBuilder();
		for (int i = 0; i < iban.length(); i++) {
			numericAccountNumber.append(Character.getNumericValue(iban.charAt(i)));
		}
		return new BigInteger(numericAccountNumber.toString());
	}
}
