package testModel;

import org.junit.Test;

import model.Category;;

public class TestCategory {

	/**
	 * testing if the Category name is null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullCategoryName() {
		Category category = new Category(null);
	}
	
	/**
	 * testing if the Category name is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_EmptyCategoryName() {
		Category category = new Category("");
	}

	/**
	 * testing if the Category name of the parent category is the same
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_CategoryDescriptionSameAsParentCategory() {
		Category category = new Category("hello", new Category("hello"));
	}
	
	/**
	 * testing if the Category name of the parent category is the empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_EmptyCategoryDescriptionParentCategory() {
		Category category = new Category("hello", new Category(""));
	}
	
	/**
	 * testing if the Category name of the parent category is the null
	 */
	@Test(expected = NullPointerException.class)
	public void test_NullCategoryDescriptionParentCategory() {
		Category category = new Category("hello", new Category(null));
	}

}
