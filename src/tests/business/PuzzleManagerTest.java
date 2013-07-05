package tests.business;

import pg13.app.Services;
import pg13.business.PuzzleManager;
import pg13.models.Category;
import pg13.models.Cryptogram;
import pg13.models.Difficulty;
import tests.persistence.StubDB;
import junit.framework.TestCase;

public class PuzzleManagerTest extends TestCase
{

	private PuzzleManager pm;

	protected void setUp() throws Exception
	{
		Services.createDataAccess(new StubDB());
		this.pm = new PuzzleManager();
	}

	public void testSavingNewPuzzle()
	{
		Cryptogram newPuzz = new Cryptogram();
		int origSize = this.pm.getAllPuzzles().size();
		assertFalse(this.pm.getAllPuzzles().contains(newPuzz));
		assertTrue(this.pm.save(newPuzz));
		assertTrue(this.pm.getAllPuzzles().contains(newPuzz));
		assertEquals(this.pm.getAllPuzzles().size(), origSize + 1);

		assertTrue(this.pm.deletePuzzle(newPuzz));
		assertEquals(origSize, this.pm.getAllPuzzles().size());
	}

	public void testSavingNullPuzzle()
	{
		try
		{
			this.pm.save(null);
			fail();
		}
		catch (NullPointerException e)
		{
			// expected
		}
	}

	public void testDeletingAllPuzzles()
	{
		int origSize = this.pm.getAllPuzzles().size();

		assertTrue(this.pm.deletePuzzle(1));
		assertEquals(origSize - 1, this.pm.getAllPuzzles().size());

		assertTrue(this.pm.deletePuzzle(2));
		assertEquals(origSize - 2, this.pm.getAllPuzzles().size());

		assertFalse(this.pm.deletePuzzle(1));
		assertEquals(origSize - 2, this.pm.getAllPuzzles().size());

		assertFalse(this.pm.deletePuzzle(new Cryptogram()));
		assertEquals(origSize - 2, this.pm.getAllPuzzles().size());
	}

	public void testUpdatingValidPuzzle()
	{
		long id = 10;
		Cryptogram cm = new Cryptogram();
		cm.setID(id);

		this.pm.save(cm);

		assertTrue(this.pm.updateCategory(id, "Animals"));
		assertEquals(Category.Animals, cm.getCategory());

		assertTrue(this.pm.updateCategory(id, Category.Geography));
		assertEquals(Category.Geography, cm.getCategory());

		assertTrue(this.pm.updateDescription(id, "new description"));
		assertEquals("new description", cm.getDescription());

		assertTrue(this.pm.updateDifficulty(id, "Hard"));
		assertEquals(Difficulty.Hard, cm.getDifficulty());

		assertTrue(this.pm.updateDifficulty(id, Difficulty.Easy));
		assertEquals(Difficulty.Easy, cm.getDifficulty());

		assertTrue(this.pm.updatePlaintext(id, "new plaintext"));
		assertEquals("new plaintext", cm.getPlaintext());

		assertTrue(this.pm.updateTitle(id, "updated title"));
		assertEquals("updated title", cm.getTitle());
	}

	public void testUpdatingInvalidPuzzle()
	{
		assertFalse(this.pm.updateCategory(-1, "Animals"));
		assertFalse(this.pm.updateCategory(12, Category.Geography));
		assertFalse(this.pm.updateDescription(1394, "new description"));
		assertFalse(this.pm.updateDifficulty(15, "Hard"));
		assertFalse(this.pm.updateDifficulty(23, Difficulty.Easy));
		assertFalse(this.pm.updatePlaintext(314, "new plaintext"));
		assertFalse(this.pm.updateTitle(271, "updated title"));
	}

}
