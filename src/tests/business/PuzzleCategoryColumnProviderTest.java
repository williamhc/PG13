package tests.business;

import pg13.business.PuzzleCategoryColumnProvider;
import pg13.models.Category;
import pg13.models.Cryptogram;
import junit.framework.TestCase;

/**
 * Tests for PuzzleCategoryColumnProvider
 *
 */
public class PuzzleCategoryColumnProviderTest extends TestCase
{

	private Cryptogram testCryptogram;
	private PuzzleCategoryColumnProvider categoryProvider;
	
	protected void setUp()
	{
		this.testCryptogram = new Cryptogram();
		this.categoryProvider = new PuzzleCategoryColumnProvider();
	}
	
	public void testNullCategory()
	{
		this.testCryptogram.setCategory(null);
		String resultCategory = this.categoryProvider.getText(this.testCryptogram);
		assertEquals(resultCategory, "Uncategorized");
	}
	
	public void testNormalCategory()
	{
		Category originalCategory = Category.Geography;
		this.testCryptogram.setCategory(originalCategory);
		String resultCategory = this.categoryProvider.getText(this.testCryptogram);
		assertEquals(resultCategory, originalCategory.toString());
	}

}
