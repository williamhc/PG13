package tests.persistence;

import pg13.models.Category;
import pg13.models.Cryptogram;
import pg13.models.Difficulty;
import pg13.models.User;
import junit.framework.TestCase;

public class TestStubDB extends TestCase
{	
	private StubDB db;
	
	public void setUp()
	{
		db = new StubDB("testing");
		db.open("testing");
	}
	
	public void tearDown()
	{
		db.close();
	}
	
	public void testAddingDistinctPuzzles()
	{
		int numPuzzles = db.getAllPuzzles().size();
		
		assertEquals(db.getSortedPuzzleIDs().size(),numPuzzles);
		
		Cryptogram cr = new Cryptogram(null, "Title1", "Description1", Category.Biology, Difficulty.Easy, "No plaintext", 10);
		db.savePuzzle(cr);
		assertEquals(numPuzzles + 1, db.getAllPuzzles().size());
		assertEquals(numPuzzles + 1, db.getSortedPuzzleIDs().size());
		numPuzzles = db.getAllPuzzles().size();
		
		cr = new Cryptogram(null, "Title2", "Description2", Category.Games, Difficulty.Hard, "hard", 11);
		db.savePuzzle(cr);
		assertEquals(numPuzzles + 1, db.getAllPuzzles().size());
		assertEquals(numPuzzles + 1, db.getSortedPuzzleIDs().size());
		numPuzzles = db.getAllPuzzles().size();
	}
	
	public void testAddingSamePuzzle()
	{
		int numPuzzles = db.getAllPuzzles().size();
		
		assertEquals(db.getSortedPuzzleIDs().size(),numPuzzles);
		
		Cryptogram cr = new Cryptogram(null, "Title1", "Description1", Category.Biology, Difficulty.Easy, "No plaintext", 10);
		db.savePuzzle(cr);
		assertEquals(numPuzzles + 1, db.getAllPuzzles().size());
		assertEquals(numPuzzles + 1, db.getSortedPuzzleIDs().size());
		numPuzzles = db.getAllPuzzles().size();
		
		//add the same puzzle again
		db.savePuzzle(cr);
		assertEquals(numPuzzles + 1, db.getAllPuzzles().size());
		assertEquals(numPuzzles + 1, db.getSortedPuzzleIDs().size());
		numPuzzles = db.getAllPuzzles().size();
	}
	
	public void testAddingUniqueUsers()
	{
		int numUsers = db.getUsers().size();
		
		assertEquals(db.getSortedUserPrimaryKeys().size(), numUsers);
		
		User u = new User(10l, "TestUser");
		db.saveUser(u);
		assertEquals(numUsers + 1, db.getUsers().size());
		assertEquals(numUsers + 1, db.getSortedUserPrimaryKeys().size());
		assertEquals(u, db.findUser(u.getPrimaryKey()));
		assertNotNull(db.findUser(db.getGuestPrimaryKey()));
		numUsers = db.getUsers().size();
		
		u = new User(11l, "TestUser2");
		db.saveUser(u);
		assertEquals(numUsers + 1, db.getUsers().size());
		assertEquals(numUsers + 1, db.getSortedUserPrimaryKeys().size());
		assertEquals(u, db.findUser(u.getPrimaryKey()));
		
	}
	
	public void testAddingSameUser()
	{
		int numUsers = db.getUsers().size();
		
		User u = new User(10l, "TestUser");
		db.saveUser(u);
		assertEquals(numUsers + 1, db.getUsers().size());
		assertEquals(numUsers + 1, db.getSortedUserPrimaryKeys().size());
		assertEquals(u, db.findUser(u.getPrimaryKey()));
		numUsers = db.getUsers().size();
		
		db.saveUser(u);
		assertEquals(numUsers + 1, db.getUsers().size());
		assertEquals(numUsers + 1, db.getSortedUserPrimaryKeys().size());
		assertEquals(u, db.findUser(u.getPrimaryKey()));
	}
	
	public void testFindingUnaddedUsers()
	{
		assertNull(db.findUser(-1));
		assertNull(db.findUser(11l));
		
		db.saveUser(new User(10l, "James"));
		assertNull(db.findUser(-1));
		assertNull(db.findUser(11l));
		
		
	}
}
