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
	 * testing if the Category name of the parent category is the same
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_CategoryDescriptionSameAsParentCategory() {
		Category category = new Category("hello", new Category("hello"));
	}

}
