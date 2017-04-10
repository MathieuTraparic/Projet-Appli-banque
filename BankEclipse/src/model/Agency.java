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
			throw new NullPointerException("The name of the agency cannot be null");
		}
		if (agencyName.isEmpty()){
			throw new IllegalArgumentException("The name of the agency cannot be empty");
		}
	}
	
	public void chekCounterCode(String counterCode){
		if (counterCode==null){
			throw new NullPointerException("the counter code cannot be null");
		}
		if (counterCode.isEmpty()){
			throw new IllegalArgumentException("The countercode cannot be empty");
		}
	}
}
