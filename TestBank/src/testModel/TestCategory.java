package testModel;

import org.junit.Test;

import model.Category;;

public class TestCategory {
	@Test(expected = NullPointerException.class)
	public void test_NullOwnerName() {
		Category category = new Category(null);
	}
	
	@Test
	public void test_CategoryDescriptionSameAsParentCategory() {
		Category category = new Category("hello","hello");
	}
	
}
