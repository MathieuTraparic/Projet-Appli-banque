package model;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import util.Formater;

@Entity
@Table(name="category")
@NamedQuery(name = "Category.findAll", query = "SELECT t FROM Category t")
public class Category {

	private String description;

	public String getDescription() {
		return this.description;
	}

	public Category getParentCategory() {
		return this.parentCategory;
	}

	private Category parentCategory;

	private void init(String description) {
		checkdescription(description);

		this.description = Formater.formatNameCase(description);
		this.parentCategory = null;
	}

	public Category(String description) {
		init(description);
	}

	public Category(String description, Category parentCategory) {
		init(description);
		checkDiffParentCategory(this.description, parentCategory);
		this.parentCategory = parentCategory;
	}

	private static void checkdescription(String description) throws IllegalArgumentException {
		if (description.isEmpty()) {
			throw new IllegalArgumentException("Description of the category cannot be empty");
		}
	}

	private static void checkDiffParentCategory(String description, Category parentCategory)
			throws IllegalArgumentException {
		if (parentCategory.getDescription().equals(description)) {
			throw new IllegalArgumentException("The parent category and the new category cannot be the same");
		}
	}

}
