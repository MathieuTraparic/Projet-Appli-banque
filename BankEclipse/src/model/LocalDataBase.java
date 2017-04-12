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
