/**
 *  @author Mathieu Traparic
 * 	Project : Bank
 * 	Creation date : 2017-04-12
 */
package model;

import java.util.HashMap;
import java.util.Map;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

/**
 *	Singleton to use as cache database
 */
public class LocalDataBase implements Observable{
	private LocalDataBase instance=null;
	
	HashMap<String, String> frequencies;
	HashMap<String, String> countryCodes;
	HashMap<String, String> transactionType;
	
	
	public HashMap<String, String> getFrequencies() {
		return this.frequencies;
	}

	public void setFrequencies(HashMap<String, String> frequencies) {
		this.frequencies = frequencies;
	}

	public HashMap<String, String> getCountryCodes() {
		return this.countryCodes;
	}

	public void setCountryCodes(HashMap<String, String> countryCodes) {
		this.countryCodes = countryCodes;
	}

	public HashMap<String, String> getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(HashMap<String, String> transactionType) {
		this.transactionType = transactionType;
	}

	private LocalDataBase(){
		frequencies = new HashMap<String, String>();
		countryCodes =  new HashMap<String, String>();
		transactionType =  new HashMap<String, String>();
	}
	
	public LocalDataBase getInstance(){
		if(instance==null){
			instance=new LocalDataBase();
		}
		
		return instance;
	}

	@Override
	public void addListener(InvalidationListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListener(InvalidationListener listener) {
		// TODO Auto-generated method stub
		
	}
		
}
