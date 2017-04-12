package model;

import util.Formater;

public class Category {

	private String categoryDescription;
	private String parentCategory=null;

	public Category(String categoryDescription) {

		checkCategoryDescription(categoryDescription);
		
		this.categoryDescription = Formater.formatNameCase(categoryDescription);
		
	}
	
	public Category(String categoryDescription, String parentCategory) {

		checkCategoryDescription(categoryDescription);
		checkParentCategory(parentCategory);

		this.categoryDescription = categoryDescription;
		this.parentCategory = parentCategory;
	}

	public static void checkCategoryDescription(String categoryDescription) throws IllegalArgumentException {
		if (categoryDescription.isEmpty()) {
			throw new IllegalArgumentException("Description of the category cannot be empty");
		}
	}
	
	public static void checkParentCategory(String parentCategory) throws IllegalArgumentException {
		if (parentCategory.isEmpty()) {
			throw new IllegalArgumentException("The parent category cannot be empty");
		}
	}
	
	public static void checkCategoryDescriptionDiffParentCategory(String categoryDescription, String parentCategory) throws IllegalArgumentException {
		if (parentCategory.equals(categoryDescription)) {
			throw new IllegalArgumentException("The parent category and the new category cannot be the same");
		}
	}

}
