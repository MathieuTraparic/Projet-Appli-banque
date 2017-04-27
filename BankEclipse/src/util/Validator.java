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
	

	/**
	 * private constructor because it shouldn't have to be instantiated
	 */
	private Validator() {
		super();
	}

	/**
	 * @param email ex : azer@aze.azeaze or aze@ze.zeee
	 * @return true if the email is valid
	 */
	public static boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}

	/**
	 * @param phoneNumber ex: +33610203010 or 0033610203040 or 0610203040
	 * @return true if the phoneNumber is valid
	 * 
	 *    match the phone number against a regex, only accept French number
	 *    for now
	 */
	public static boolean isValidPhoneNumber(String phoneNumber) {
		if (phoneNumber!=null){
			String ePattern = "^((\\+|00)[1-9]{2}|0)(( |-)?[0-9])(( |-)?[0-9]){8}$";
			java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
			java.util.regex.Matcher m = p.matcher(phoneNumber);
			return m.matches();
		}
		else{
			return false;
		}

	}
	
	/**
	 * @param name
	 * @return true is name contains only letters, spaces, apostrophe and dashes
	 * 
	 *    
	 */
	public static boolean isValidName(String name) {
		String ePattern = "^([A-Za-z�-�]| |-|')*$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(name);
		return m.matches();
	}

	public static boolean isValidIban(String iban) {
		return IBANHandler.ibanTest(iban);
	}

}
