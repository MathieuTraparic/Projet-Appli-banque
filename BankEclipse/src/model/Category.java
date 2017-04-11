package model;

public class Category {

	private String categoryDescription;

	public Category(String categoryDescription) {

		checkCategoryDescription(categoryDescription);

		this.categoryDescription = categoryDescription;
	}

	public static void checkCategoryDescription(String categoryDescription) throws IllegalArgumentException {
		if (categoryDescription.isEmpty()) {
			throw new IllegalArgumentException("Description of the category cannot be empty");
		}
	}

}
