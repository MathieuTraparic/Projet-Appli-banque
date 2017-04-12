package model;

import util.Formater;

public class Address {
	private String line1;
	private String line2=null;
	private String zip;
	private String city;
	
	
	public Address(String line1, String line2, String zip, String city) {
		check_city(city);
		check_line1(line1);
		check_zip(zip);
		
		this.line1 = line1;
		this.line2 = line2;
		this.zip = Formater.removeUsualSeparators(zip);
		this.city = Formater.formatNameCase(city);
	}
	
	private static void check_line1(String line1) throws IllegalArgumentException {
		if (line1.isEmpty()) {
			throw new IllegalArgumentException("line1 cannot be empty");
		}
	}

	private static void check_zip(String zip) throws IllegalArgumentException {
		if (zip.isEmpty() || !isValidZip(zip)) {
			throw new IllegalArgumentException("zip must be valid");
		}
	}
	private static void check_city(String city) throws IllegalArgumentException {
		if (city.isEmpty()) {
			throw new IllegalArgumentException("city cannot be empty");
		}
	}
	
	public static boolean isValidZip(String zip){
		return zip.length()<=50;
	}
	
	
	
	
}
