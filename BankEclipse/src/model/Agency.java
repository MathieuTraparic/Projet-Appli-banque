package model;

import util.Formater;

public class Agency {
	private String name;
	private String counterCode;

	public Agency(String agencyName, String counterCode) {

		chekCounterCode(counterCode);
		checkName(agencyName);

		this.counterCode = Formater.removeUsualSeparators(counterCode);
		this.name = Formater.formatNameCase(agencyName);
	}

	public static void checkName(String name) throws IllegalArgumentException {
		if (name.isEmpty()) {
			throw new IllegalArgumentException("The name of the agency cannot be empty");
		}
	}

	public static void chekCounterCode(String counterCode) throws IllegalArgumentException {
		if (counterCode.isEmpty()) {
			throw new IllegalArgumentException("The countercode cannot be empty");
		}
	}
}
