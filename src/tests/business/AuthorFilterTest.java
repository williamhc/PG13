package tests.business;
import pg13.business.AuthorFilter;
import pg13.models.Cryptogram;
import pg13.models.Puzzle;
import junit.framework.TestCase;

public class AuthorFilterTest extends TestCase {

	private AuthorFilter testAuthorFilter;
	private Puzzle testPuzzle;
	private static String AUTHOR_NAME = "Will The Q.A.";

	protected void setUp() throws Exception {
		this.testAuthorFilter = new AuthorFilter();
		this.testPuzzle = new Cryptogram();
		this.testPuzzle.setAuthor(AuthorFilterTest.AUTHOR_NAME);
	}

	public void testNullSearchString()
	{
		assertTrue(this.testAuthorFilter.select(null, null, testPuzzle));
		this.testAuthorFilter.setSearchString(null);
		assertTrue(this.testAuthorFilter.select(null, null, testPuzzle));
	}
	
	public void testEmptySearchString()
	{
		this.testAuthorFilter.setSearchString("");
		assertTrue(this.testAuthorFilter.select(null, null, testPuzzle));
	}
	
	public void testPerfectMatchingSearch()
	{
		this.testAuthorFilter.setSearchString(AuthorFilterTest.AUTHOR_NAME);
		assertTrue(this.testAuthorFilter.select(null, null, testPuzzle));
	}
	
	public void testLowerCaseSearch()
	{
		this.testAuthorFilter.setSearchString(AuthorFilterTest.AUTHOR_NAME.toLowerCase());
		assertTrue(this.testAuthorFilter.select(null, null, testPuzzle));
	}
	
	public void testMismatchCaseSearch()
	{
		this.testAuthorFilter.setSearchString("wiLL tHe q.A.");
		assertTrue(this.testAuthorFilter.select(null, null, testPuzzle));
	}
	
	public void testPartialSearch()
	{
		this.testAuthorFilter.setSearchString("will");
		assertTrue(this.testAuthorFilter.select(null, null, testPuzzle));
	}
	
	public void testPartialSearchExtraWhitespaceAtEnds()
	{
		this.testAuthorFilter.setSearchString("   will the    ");
		assertTrue(this.testAuthorFilter.select(null, null, testPuzzle));
	}

	public void testNonMatchingSearch()
	{
		this.testAuthorFilter.setSearchString("not will at all");
		assertFalse(this.testAuthorFilter.select(null, null, testPuzzle));
	}

	public void testAbsoluteMatchingPerfectMatch()
	{
		this.testAuthorFilter.setAbsoluteSearchString(AUTHOR_NAME);
		assertTrue(this.testAuthorFilter.select(null, null, testPuzzle));
	}

	public void testAbsoluteMatchingSubstring()
	{
		this.testAuthorFilter.setAbsoluteSearchString(AUTHOR_NAME.substring(0, AUTHOR_NAME.length()/2));
		assertFalse(this.testAuthorFilter.select(null, null, testPuzzle));
	}

	public void testAbsoluteMatchingWhitespace()
	{
		this.testAuthorFilter.setAbsoluteSearchString(" " + AUTHOR_NAME);
		assertFalse(this.testAuthorFilter.select(null, null, testPuzzle));

		this.testAuthorFilter.setAbsoluteSearchString(" " + AUTHOR_NAME + " \t");
		assertFalse(this.testAuthorFilter.select(null, null, testPuzzle));

		this.testAuthorFilter.setAbsoluteSearchString(AUTHOR_NAME + "   ");
		assertFalse(this.testAuthorFilter.select(null, null, testPuzzle));
	}

	public void testAbsoluteMatchingNull()
	{
		this.testAuthorFilter.setAbsoluteSearchString(null);
		assertTrue(this.testAuthorFilter.select(null, null, testPuzzle));
	}

	public void testAbsoluteMatchingCaseInsensitive()
	{
		this.testAuthorFilter.setAbsoluteSearchString(AUTHOR_NAME.toLowerCase());
		assertTrue(this.testAuthorFilter.select(null, null, testPuzzle));

		this.testAuthorFilter.setAbsoluteSearchString(AUTHOR_NAME.toUpperCase());
		assertTrue(this.testAuthorFilter.select(null, null, testPuzzle));
	}

}
