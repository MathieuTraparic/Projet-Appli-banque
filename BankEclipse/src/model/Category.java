package model;

public class Category {
	
	private String wording;

	public Category(String wording) {

		checkWording(wording);
		
		this.wording = wording;
	}
	
	public void checkWording(String wording){
		if (wording == null){
			throw new NullPointerException("Description cannot be null");
		}
	}
	
	

}
