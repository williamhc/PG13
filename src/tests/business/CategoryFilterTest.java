package tests.business;
import pg13.business.CategoryFilter;
import pg13.models.Category;
import pg13.models.Cryptogram;
import pg13.models.Puzzle;
import junit.framework.TestCase;

/**
 * Tests for CategoryFilter
 */
public class CategoryFilterTest extends TestCase {

	private CategoryFilter testCategoryFilter;
	private Puzzle testPuzzle;

	protected void setUp() throws Exception {
		this.testCategoryFilter = new CategoryFilter();
		this.testPuzzle = new Cryptogram();
		this.testPuzzle.setCategory(Category.Animals);
	}

	public void testUncategorizedPuzzle()
	{
		this.testPuzzle.setCategory(null);
		this.testCategoryFilter.setSearchValue(null);
		assertTrue(this.testCategoryFilter.select(null, null, testPuzzle));
	}

	public void testNullSearchValue()
	{
		assertTrue(this.testCategoryFilter.select(null, null, testPuzzle));
		this.testCategoryFilter.setSearchValue(null);
		assertTrue(this.testCategoryFilter.select(null, null, testPuzzle));
	}
	
	public void testMatchingSearch()
	{
		this.testCategoryFilter.setSearchValue(Category.Animals);
		assertTrue(this.testCategoryFilter.select(null, null, testPuzzle));
	}

	public void testNonMatchingSearch()
	{
		this.testCategoryFilter.setSearchValue(Category.Computers);
		assertFalse(this.testCategoryFilter.select(null, null, testPuzzle));
	}
}
