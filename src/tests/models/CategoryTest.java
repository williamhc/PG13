package tests.models;

import java.util.ArrayList;

import junit.framework.TestCase;
import pg13.models.Category;

public class CategoryTest extends TestCase {
	public void testValuesAsStrings()
	{
		ArrayList<String> strs = Category.valuesAsStrings();
		int num_categories = Category.values().length;
		assertEquals(Category.values()[0].toString(), strs.get(0));
		assertEquals(Category.values().length, strs.size());
		assertEquals(Category.values()[num_categories-1].toString(), strs.get(num_categories-1));
	}
}
