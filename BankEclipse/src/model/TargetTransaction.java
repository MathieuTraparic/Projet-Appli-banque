package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import util.Formater;
import util.Validator;

@Entity
@Table(name="targettransaction")
@NamedQuery(name = "TargetTransaction.findAll", query = "SELECT t FROM TargetTransaction t")
public class TargetTransaction implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String summary;
	private String iban;
	
	private TargetTransaction(){
		
	}
	
	public TargetTransaction(String summary, String iban) {
		
		checkIban(iban);
		checkSummary(summary);
		
		this.summary = Formater.formatNameCase(summary);
		this.iban = Formater.removeUsualSeparators(iban);
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	private static void checkIban(String iban) throws IllegalArgumentException{
		if (iban.isEmpty()){
			throw new IllegalArgumentException("IBAN must be filled with valid number");
		}
		if (!isValid(iban)){
			throw new IllegalArgumentException("IBAN must be filled with valid number");
		}
	}
	
	public static boolean isValid(String iban){
		return (Validator.isValidIban(iban));
	}
	
	private static void checkSummary(String summary) throws IllegalArgumentException{

		if (summary.isEmpty()){
			throw new IllegalArgumentException("Summary must be filled with the name of the target transaction");
		}
	}

	private String getSummary() {
		return this.summary;
	}

	private void setSummary(String summary) {
		this.summary = summary;
	}

	private String getIban() {
		return this.iban;
	}

	private void setIban(String iban) {
		this.iban = iban;
	}
}
