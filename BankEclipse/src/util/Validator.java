/**
 *  @author Mathieu Traparic
 * 	Project : Bank
 * 	Creation date : 2017-04-11
 */
package util;

/**
 * Contains static methods to validate user inputs
 */
public class Validator {

	public static boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}

	/**
	 * @param name
	 * @return true is the phoneNumber is valid
	 * 
	 *    match the phone number against a regex, only accept french number
	 *    for now
	 */
	public static boolean isValidPhoneNumber(String name) {
		String ePattern = "^((\\+|00)[1-9]{2}|0)(( |-)?[0-9])(( |-)?[0-9]){8}$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(name);
		return m.matches();
	}
	
	/**
	 * @param phoneNumber
	 * @return true is name contains only letters, spaces and dashes
	 * 
	 *    
	 */
	public static boolean isValidName(String phoneNumber) {
		String ePattern = "^([A-Za-zÀ-ÿ]| |-)*$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(phoneNumber);
		return m.matches();
	}

	public static boolean isValidIban(String iban) {
		return IBANValidator.ibanTest(iban);
	}

}
