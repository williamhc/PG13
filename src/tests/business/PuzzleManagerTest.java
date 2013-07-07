package tests.business;

import pg13.app.Services;
import pg13.business.PuzzleManager;
import pg13.models.Category;
import pg13.models.Cryptogram;
import pg13.models.Difficulty;
import pg13.models.User;
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
		assertFalse(this.pm.save(newPuzz));
		newPuzz.setUser(new User("Joe"));
		newPuzz.setPlaintext("ABC123");
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
		Cryptogram cm = new Cryptogram();
		cm.setUser(new User("Joe"));
		cm.setPlaintext("ABC123");
		assertTrue(this.pm.save(cm));
		
		Cryptogram updatedCryptogram = new Cryptogram(null, "updated title", "new description", Category.Geography, Difficulty.Medium, "new plaintext", cm.getID());

		assertTrue(this.pm.save(updatedCryptogram));
		assertEquals(Category.Geography, cm.getCategory());
		assertEquals("new description", cm.getDescription());
		assertEquals(Difficulty.Medium, cm.getDifficulty());
		assertEquals("new plaintext", cm.getPlaintext());
		assertEquals("updated title", cm.getTitle());
	}

	public void testUpdatingInvalidPuzzle()
	{
		Cryptogram cm = new Cryptogram(null, "A title", "some description", Category.Computers, Difficulty.Medium, "new plaintext", 14);
		assertFalse(this.pm.save(cm));
	}

}
