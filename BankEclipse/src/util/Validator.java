/**
 *  @author Mathieu Traparic
 * 	Project : Bank
 * 	Creation date : 2017-04-11
 */
package util;

/**
 *	Contains static methods to validate user inputs
 */
public class Validator {
	
	public static boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}
	
	/**
	 * @param phoneNumber
	 * @return true is the phoneNumber is valid
	 * 
	 * match the phone number against a regex, only accept french number for now
	 */
	public static boolean isValidPhoneNumber(String phoneNumber){
		String ePattern = "^0([0-9] ?){9}$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(phoneNumber);
		return m.matches();
	}

	public static boolean isValidIban(String iban) {
		throw new UnsupportedOperationException("unimplemented");
	}
}