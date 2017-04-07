package model;

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
		this.zip = zip;
		this.city = city;
	}
	
	public static void check_line1(String line1) throws IllegalArgumentException {
		if (line1.length()==0) {
			throw new IllegalArgumentException("line1 cannot be empty");
		}
	}

	public static void check_zip(String zip) throws IllegalArgumentException {
		if (zip.length()==0 || zip.length()>50) {
			throw new IllegalArgumentException("zip must be valid");
		}
	}
	public static void check_city(String city) throws IllegalArgumentException {
		if (city.length()==0) {
			throw new IllegalArgumentException("city cannot be empty");
		}
	}
	
	
	
	
}
