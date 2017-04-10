package model;

public class Bank {
	private String bankName;
	private String bankCode;
	
	public Bank(String bankName, String bankCode) {
		
		checkBankCode(bankCode);
		checkBankName(bankName);
		
		this.bankName = bankName;
		this.bankCode = bankCode;
	}
	
	public void checkBankName(String bankName){
		if (bankName==null){
			throw new NullPointerException("The name of the bank cannot be null");
		}
		if (bankName.isEmpty()){
			throw new IllegalArgumentException("The name of the bank cannot be empty");
		}
	}
	
	public void checkBankCode(String bankCode){
		if (bankCode==null){
			throw new NullPointerException("The code of the bank cannot be null");
		}
		if (bankCode.isEmpty()){
			throw new IllegalArgumentException("The code of the bank cannot be empty");
		}
	}

}
