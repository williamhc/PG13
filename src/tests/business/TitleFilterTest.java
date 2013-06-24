package tests.business;
import pg13.business.TitleFilter;
import pg13.models.Cryptogram;
import pg13.models.Puzzle;
import junit.framework.TestCase;

/**
 * Tests for TitleFilter
 */
public class TitleFilterTest extends TestCase {

	private TitleFilter testTitleFilter;
	private Puzzle testPuzzle;
	private static String TITLE = "The Title of The Puzzle";

	protected void setUp() throws Exception {
		this.testTitleFilter = new TitleFilter();
		this.testPuzzle = new Cryptogram();
		this.testPuzzle.setTitle(TitleFilterTest.TITLE);
	}

	public void testNullSearchString()
	{
		assertTrue(this.testTitleFilter.select(null, null, testPuzzle));
		this.testTitleFilter.setSearchString(null);
		assertTrue(this.testTitleFilter.select(null, null, testPuzzle));
	}
	
	public void testEmptySearchString()
	{
		this.testTitleFilter.setSearchString("");
		assertTrue(this.testTitleFilter.select(null, null, testPuzzle));
	}
	
	public void testPerfectMatchingSearch()
	{
		this.testTitleFilter.setSearchString(TitleFilterTest.TITLE);
		assertTrue(this.testTitleFilter.select(null, null, testPuzzle));
	}
	
	public void testLowerCaseSearch()
	{
		this.testTitleFilter.setSearchString(TitleFilterTest.TITLE.toLowerCase());
		assertTrue(this.testTitleFilter.select(null, null, testPuzzle));
	}
	
	public void testMismatchCaseSearch()
	{
		this.testTitleFilter.setSearchString("ThE tItle of THE puzZlE");
		assertTrue(this.testTitleFilter.select(null, null, testPuzzle));
	}
	
	public void testPartialSearch()
	{
		this.testTitleFilter.setSearchString("title of");
		assertTrue(this.testTitleFilter.select(null, null, testPuzzle));
	}
	
	public void testPartialSearchExtraWhitespaceAtEnds()
	{
		this.testTitleFilter.setSearchString(" title of   ");
		assertTrue(this.testTitleFilter.select(null, null, testPuzzle));
	}

	public void testNonMatchingSearch()
	{
		this.testTitleFilter.setSearchString("not the title");
		assertFalse(this.testTitleFilter.select(null, null, testPuzzle));
	}

	
}
