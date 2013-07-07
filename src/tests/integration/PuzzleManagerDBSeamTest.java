package tests.integration;

import java.util.ArrayList;

import pg13.app.PG13;
import pg13.app.Services;
import pg13.business.PuzzleManager;
import pg13.models.Category;
import pg13.models.Cryptogram;
import pg13.models.Difficulty;
import pg13.models.Puzzle;
import pg13.models.User;
import pg13.persistence.DataAccess;
import tests.DBResetter;
import junit.framework.TestCase;

public class PuzzleManagerDBSeamTest extends TestCase
{
	private final User joe = new User(2l, "Joe");
	
	PuzzleManager pm;
	
	public void setUp()
	{
		DBResetter.ResetHSQLDB();
		Services.createDataAccess(PG13.dbName);
		pm = new PuzzleManager();
	}
	
	public void tearDown()
	{
		Services.closeDataAccess();
		DBResetter.ResetHSQLDB();
	}
	
	public void testCreateReadDelete()
	{
		int numberOfPuzzles;
		Cryptogram cr = new Cryptogram(joe, "A title", "A description", Category.Animals, Difficulty.Easy, "Plaintext!");
		assertEquals(Puzzle.DEFAULT_ID, cr.getID());
		numberOfPuzzles = pm.getAllPuzzles().size();
		assertTrue(pm.save(cr));  
		assertFalse(cr.getID() == Puzzle.DEFAULT_ID);
		assertEquals(numberOfPuzzles + 1, pm.getAllPuzzles().size());
		
		
		Cryptogram cr2 = new Cryptogram(joe, "A title", "A descr2iption", Category.Animals, Difficulty.Easy, "Plaintext!");
		assertEquals(Puzzle.DEFAULT_ID, cr2.getID());
		assertTrue(pm.save(cr2));
		assertFalse(cr2.getID() == Puzzle.DEFAULT_ID);
		assertEquals(numberOfPuzzles + 2, pm.getAllPuzzles().size());
		
		assertTrue(pm.deletePuzzle(cr.getID()));
		assertEquals(numberOfPuzzles + 1, pm.getAllPuzzles().size());
		
		assertTrue(pm.deletePuzzle(cr2.getID()));
		assertEquals(numberOfPuzzles, pm.getAllPuzzles().size());
	}
	
	public void testReadUpdate()
	{
		int numberOfPuzzles = pm.getAllPuzzles().size();
		
		Cryptogram cr = new Cryptogram(joe, "Title", "Description", Category.Animals, Difficulty.Medium, "Plaintext!");
		assertTrue(pm.save(cr));
		assertEquals(numberOfPuzzles + 1, pm.getAllPuzzles().size());
		
		
		cr.setTitle("new title");
		cr.setDescription("'nother description");
		cr.setCategory(Category.Science);
		cr.setDifficulty(Difficulty.Hard);
		assertTrue(pm.save(cr));
		assertEquals(numberOfPuzzles + 1, pm.getAllPuzzles().size());
		
		
		Cryptogram cr2 = new Cryptogram(joe, "a", "b", Category.Animals, Difficulty.Easy, "c");
		cr2.setID(9001);
		ArrayList<Puzzle> p= pm.getAllPuzzles();
		assertFalse(pm.save(cr2));
		assertFalse(pm.deletePuzzle(cr2.getID()));
		
	}
}
