package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import util.Formater;
import util.Validator;

@Entity
@Table(name = "category")
@NamedQuery(name = "Category.findAll", query = "SELECT t FROM Category t")
public class Category implements Serializable {

	private static final long serialVersionUID = -1609819592902961280L;

	private int id;
	private String description;
	private Category parentCategory;

	/*
	 * Used only by the ORM
	 */
	@SuppressWarnings("unused")
	private Category() {

	}

	/**Initialize the attributes for the two different constructor
	 * @param description
	 */
	private void init(String description) {
		this.setDescription(Formater.formatNameCase(description));
		this.parentCategory = null;
	}

	/**Constructor used when category parent is not assigned
	 * @param description contains only letters, spaces, apostrophe and dashes
	 */
	public Category(String description) {
		init(description);
	}

	/**Constructor used when a parent category is assigned
	 * @param description contains only letters, spaces, apostrophe and dashes
	 * @param parentCategory
	 */
	public Category(String description, Category parentCategory) {

		init(description);

		this.setParentCategory(parentCategory);
	}
	
	/**
	 * @param  name
	 * @return true if name contains only letters, spaces, apostrophe and dashes
	 */
	public static boolean isValidName(String name){
		return Validator.isValidName(name);
	}

	private static void checkDescription(String description) throws IllegalArgumentException {
		if (description.isEmpty()) {
			throw new IllegalArgumentException("Description of the category cannot be empty");
		}
		else if(!isValidName(description)){
			throw new IllegalArgumentException("The category name is incorrect");
		}
	}

	private static void checkDiffParentCategory(String description, Category parentCategory)
			throws IllegalArgumentException {
		if (parentCategory.getDescription().equals(description)) {
			throw new IllegalArgumentException("The parent category and the new category cannot be the same");
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	/*
	 * Used only by the ORM
	 */
	@SuppressWarnings("unused")
	private void setId(int id) {
		this.id = id;
	}

	public void setDescription(String description) {
		checkDescription(description);

		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	@ManyToOne
	@JoinColumn(name = "idParentCategory")
	public Category getParentCategory() {
		return this.parentCategory;
	}

	public void setParentCategory(Category parentCategory) {

		checkDiffParentCategory(this.description, parentCategory);

		this.parentCategory = parentCategory;
	}

	@Override
	public String toString() {
		return description;
	}

}
