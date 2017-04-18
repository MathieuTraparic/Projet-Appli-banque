package model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import util.Formater;

@Entity
@Table(name="agency")
@NamedQuery(name = "Agency.findAll", query = "SELECT t FROM Agency t")
public class Agency {
	private String name;
	private String counterCode;
	
	private static ArrayList<String> AGENCY = new ArrayList<String>(){
		{
			add("LI");
			add("LO");
		}
	};

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
	
	public static Iterable<String> getAgency() {
		return AGENCY;
	}
}
