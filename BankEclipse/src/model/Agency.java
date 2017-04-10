package model;

public class Agency {
	private String agencyName;
	private String counterCode;
	
	public Agency (String agencyName, String counterCode) {
		
		chekCounterCode(counterCode);
		checkAgencyName(agencyName);
		
		this.counterCode = counterCode;
		this.agencyName = agencyName;
	}
	
	public void checkAgencyName(String agencyName){
		if (agencyName==null){
			throw new NullPointerException("agencyName cannot be null");
		}
	}
	
	public void chekCounterCode(String counterCode){
		if (counterCode==null){
			throw new NullPointerException("counterCode cannot be null");
		}
	}
}
