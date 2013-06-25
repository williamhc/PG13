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
		this.pm.save(newPuzz);
		assertTrue(this.pm.getAllPuzzles().contains(newPuzz));
		assertEquals(this.pm.getAllPuzzles().size(), origSize + 1);
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

}
