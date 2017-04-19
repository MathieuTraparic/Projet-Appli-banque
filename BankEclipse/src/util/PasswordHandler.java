/**
 *  @author Mathieu Traparic
 * 	Project : Bank
 * 	Creation date : 2017-04-19
 */
package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 *
 */
public class PasswordHandler {
	//should match the hash function output size as a rule of thumb
	private static final int SALT_SIZE=8;
	private static final String ALGO="SHA-256";


	/**
	 * @param input	the password and salt
	 * @return the password hashed and converted in Base64 string
	 */
	public static String hash(String input){
		MessageDigest md=null;
		try {
			md = MessageDigest.getInstance(ALGO);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			md.update(input.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] hash =md.digest();
		
		
		return  Base64.getEncoder().encodeToString(hash);
	}
	
	/**
	 * @return a random Base64 String 
	 */
	public static String getNewSalt(){
		SecureRandom s=null;
		try {
			s=SecureRandom.getInstanceStrong();
		} catch (NoSuchAlgorithmException e) {
			e.getMessage();
		}
		byte[] salt= new byte[SALT_SIZE];
		s.nextBytes(salt);
		return Base64.getEncoder().encodeToString(salt);
	}
	
}
