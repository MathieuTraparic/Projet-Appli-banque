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
			throw new NullPointerException("bankName cannot be null");
		}
	}
	
	public void checkBankCode(String bankCode){
		if (bankCode==null){
			throw new NullPointerException("bankCode cannot be null");
		}
	}

}
