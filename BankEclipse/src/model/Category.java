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

@Entity
@Table(name = "category")
@NamedQuery(name = "Category.findAll", query = "SELECT t FROM Category t")
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String description;
	private Category parentCategory;

	private void init(String description) {
		checkdescription(description);

		this.description = Formater.formatNameCase(description);
		this.parentCategory = null;
	}

	private Category() {

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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
	
	@ManyToOne
	@JoinColumn(name="idParentCategory")
	public Category getParentCategory() {
		return this.parentCategory;
	}

	private void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

}
