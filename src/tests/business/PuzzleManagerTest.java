package tests.business;

import pg13.app.Services;
import pg13.business.PuzzleManager;
import pg13.models.Cryptogram;
import tests.persistence.StubDB;
import junit.framework.TestCase;

public class PuzzleManagerTest extends TestCase {

	private PuzzleManager pm;

	protected void setUp() throws Exception {
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
		catch(NullPointerException e)
		{
			// expected
		}
	}
	
	public void testDeletingAllUsers()
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

}
