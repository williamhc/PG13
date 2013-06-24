package tests.business;
import pg13.business.DifficultyFilter;
import pg13.models.Cryptogram;
import pg13.models.Difficulty;
import pg13.models.Puzzle;
import junit.framework.TestCase;


public class DifficultyFilterTest extends TestCase {

	private DifficultyFilter testDifficultyFilter;
	private Puzzle testPuzzle;

	protected void setUp() throws Exception {
		this.testDifficultyFilter = new DifficultyFilter();
		this.testPuzzle = new Cryptogram();
		this.testPuzzle.setDifficulty(Difficulty.Easy);
	}

	public void testPuzzleWithoutDifficulty()
	{
		this.testPuzzle.setDifficulty(null);
		assertTrue(this.testDifficultyFilter.select(null, null, testPuzzle));
		this.testDifficultyFilter.addValue(null);
		assertTrue(this.testDifficultyFilter.select(null, null, testPuzzle));
		this.testDifficultyFilter.addValue(Difficulty.Easy);
	}

	public void testNullSearchValue()
	{
		assertTrue(this.testDifficultyFilter.select(null, null, testPuzzle));
		this.testDifficultyFilter.addValue(null);
		assertTrue(this.testDifficultyFilter.select(null, null, testPuzzle));
	}
	
	public void testMatchingSearch()
	{
		this.testDifficultyFilter.addValue(Difficulty.Easy);
		assertTrue(this.testDifficultyFilter.select(null, null, testPuzzle));
	}

	public void testNonMatchingSearch()
	{
		this.testDifficultyFilter.addValue(Difficulty.Hard);
		assertFalse(this.testDifficultyFilter.select(null, null, testPuzzle));
		this.testDifficultyFilter.addValue(Difficulty.Easy);
		assertTrue(this.testDifficultyFilter.select(null, null, testPuzzle));
	}

	public void testMultipleDifferentDifficultiesSearch()
	{
		this.testDifficultyFilter.addValue(Difficulty.Hard);
		this.testDifficultyFilter.addValue(Difficulty.Medium);
		assertFalse(this.testDifficultyFilter.select(null, null, testPuzzle));
		this.testDifficultyFilter.addValue(Difficulty.Easy);
		assertTrue(this.testDifficultyFilter.select(null, null, testPuzzle));
		this.testDifficultyFilter.removeValue(Difficulty.Easy);
		assertFalse(this.testDifficultyFilter.select(null, null, testPuzzle));
	}

	public void testMultipleUniqueDifficultiesSearch()
	{
		this.testDifficultyFilter.addValue(Difficulty.Hard);
		this.testDifficultyFilter.addValue(Difficulty.Easy);
		this.testDifficultyFilter.addValue(Difficulty.Easy);
		assertTrue(this.testDifficultyFilter.select(null, null, testPuzzle));
		this.testDifficultyFilter.removeValue(Difficulty.Easy);
		assertTrue(this.testDifficultyFilter.select(null, null, testPuzzle));
		this.testDifficultyFilter.removeValue(Difficulty.Easy);
		assertFalse(this.testDifficultyFilter.select(null, null, testPuzzle));
	}
}
