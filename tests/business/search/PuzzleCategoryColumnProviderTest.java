package tests.business.search;

import pg13.business.search.PuzzleCategoryColumnProvider;
import pg13.models.Cryptogram;
import junit.framework.TestCase;

/**
 * Tests for PuzzleCategoryColumnProvider
 * @category williamhumphreys-cloutier
 *
 */
public class PuzzleCategoryColumnProviderTest extends TestCase {

	private Cryptogram testCryptogram;
	private PuzzleCategoryColumnProvider categoryProvider;
	
	protected void setUp(){
		this.testCryptogram = new Cryptogram();
		this.categoryProvider = new PuzzleCategoryColumnProvider();
	}
	
	public void testEmptyCategory(){
		this.testCryptogram.setCategory("");
		String resultCategory = this.categoryProvider.getText(this.testCryptogram);
		assertEquals(resultCategory, "Uncategorized");
	}

	public void testNullCategory(){
		this.testCryptogram.setCategory(null);
		String resultCategory = this.categoryProvider.getText(this.testCryptogram);
		assertEquals(resultCategory, "Uncategorized");
	}
	
	public void testNormalCategory(){
		String originalCategory = "Will Coolcat";
		this.testCryptogram.setCategory(originalCategory);
		String resultCategory = this.categoryProvider.getText(this.testCryptogram);
		assertEquals(resultCategory, originalCategory);
	}

}
