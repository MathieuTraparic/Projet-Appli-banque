package model;

import util.Formater;

public class Category {

	private String description;
	private Category parentCategory;

	public Category(String description) {

		checkdescription(description);
		
		this.description = Formater.formatNameCase(description);
		this.parentCategory = null;
		
	}
	
	public Category(String description, Category parentCategory) {

		checkdescription(description);

		this.description = description;
		this.parentCategory = parentCategory;
	}

	public static void checkdescription(String description) throws IllegalArgumentException {
		if (description.isEmpty()) {
			throw new IllegalArgumentException("Description of the category cannot be empty");
		}
	}
	
	
	/*public static void checkCategoryDescriptionDiffParentCategory(String categoryDescription, String parentCategory) throws IllegalArgumentException {
		if (parentCategory.equals(categoryDescription)) {
			throw new IllegalArgumentException("The parent category and the new category cannot be the same");
		}
	}*/

}
