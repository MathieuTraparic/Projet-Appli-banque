package model;

import util.Formater;

public class Agency {
	private String agencyName;
	private String counterCode;

	public Agency(String agencyName, String counterCode) {

		chekCounterCode(counterCode);
		checkAgencyName(agencyName);

		this.counterCode = Formater.removeUsualSeparators(counterCode);
		this.agencyName = Formater.formatNameCase(agencyName);
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
