package model;

public class Category {
	
	private String categoryDescription;

	public Category(String categoryDescription) {

		checkCategoryDescription(categoryDescription);
		
		this.categoryDescription = categoryDescription;
	}
	
	public void checkCategoryDescription(String categoryDescription){
		if (categoryDescription == null){
			throw new NullPointerException("Description of the category cannot be null");
		}
		if (categoryDescription.isEmpty()){
			throw new IllegalArgumentException("Description of the category cannot be empty");
		}
	}
	
	

}
