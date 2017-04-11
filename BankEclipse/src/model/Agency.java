package model;

public class Agency {
	private String agencyName;
	private String counterCode;

	public Agency(String agencyName, String counterCode) {

		chekCounterCode(counterCode);
		checkAgencyName(agencyName);

		this.counterCode = counterCode;
		this.agencyName = agencyName;
	}

	public static void checkAgencyName(String agencyName) throws IllegalArgumentException {
		if (agencyName.isEmpty()) {
			throw new IllegalArgumentException("The name of the agency cannot be empty");
		}
	}

	public static void chekCounterCode(String counterCode) throws IllegalArgumentException {
		if (counterCode.isEmpty()) {
			throw new IllegalArgumentException("The countercode cannot be empty");
		}
	}
}
