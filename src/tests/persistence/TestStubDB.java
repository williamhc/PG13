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

		assertEquals(db.getSortedPuzzleIDs().size(), numPuzzles);

		Cryptogram cr = new Cryptogram(null, "Title1", "Description1",
				Category.Biology, Difficulty.Easy, "No plaintext", 10);
		assertTrue(db.savePuzzle(cr));
		assertEquals(numPuzzles + 1, db.getAllPuzzles().size());
		assertEquals(numPuzzles + 1, db.getSortedPuzzleIDs().size());
		numPuzzles = db.getAllPuzzles().size();

		cr = new Cryptogram(null, "Title2", "Description2", Category.Games,
				Difficulty.Hard, "hard", 11);
		assertTrue(db.savePuzzle(cr));
		assertEquals(numPuzzles + 1, db.getAllPuzzles().size());
		assertEquals(numPuzzles + 1, db.getSortedPuzzleIDs().size());
		numPuzzles = db.getAllPuzzles().size();

		assertTrue(db.deletePuzzle(10));
		assertEquals(numPuzzles - 1, db.getAllPuzzles().size());
		assertTrue(db.deletePuzzle(11));
		assertEquals(numPuzzles - 2, db.getAllPuzzles().size());
	}

	public void testAddingSamePuzzle()
	{
		int numPuzzles = db.getAllPuzzles().size();

		assertEquals(db.getSortedPuzzleIDs().size(), numPuzzles);

		Cryptogram cr = new Cryptogram(null, "Title1", "Description1",
				Category.Biology, Difficulty.Easy, "No plaintext", 10);
		assertTrue(db.savePuzzle(cr));
		assertEquals(numPuzzles + 1, db.getAllPuzzles().size());
		assertEquals(numPuzzles + 1, db.getSortedPuzzleIDs().size());
		numPuzzles = db.getAllPuzzles().size();

		// add the same puzzle again
		assertTrue(db.savePuzzle(cr));
		assertEquals(numPuzzles + 1, db.getAllPuzzles().size());
		assertEquals(numPuzzles + 1, db.getSortedPuzzleIDs().size());
		numPuzzles = db.getAllPuzzles().size();
	}

	public void testDeletingAllPuzzles()
	{
		assertTrue(db.deletePuzzle(1));
		assertTrue(db.deletePuzzle(2));
		assertFalse(db.deletePuzzle(1));

		assertEquals(0, db.getAllPuzzles().size());
	}

	public void testModifyingPuzzlesThatExist()
	{
		Cryptogram cr1 = new Cryptogram(null, "Title1", "Description1",
				Category.Biology, Difficulty.Easy, "No plaintext", 10);
		db.savePuzzle(cr1);
		Cryptogram cr2 = new Cryptogram(null, "Title2", "Description2",
				Category.Games, Difficulty.Hard, "hard", 11);
		db.savePuzzle(cr2);

		assertTrue(db.updateCategory(10, Category.Politics));
		assertEquals(Category.Politics, cr1.getCategory());

		assertTrue(db.updateDescription(11, "New and improved description"));
		assertEquals("New and improved description", cr2.getDescription());

		assertTrue(db.updateDifficulty(10, Difficulty.Hard));
		assertEquals(Difficulty.Hard, cr1.getDifficulty());

		assertTrue(db.updatePlaintext(11, "new and improved plaintext"));
		assertEquals("new and improved plaintext", cr2.getPlaintext());

		assertTrue(db.updateTitle(10, "updated title"));
		assertEquals("updated title", cr1.getTitle());
	}

	public void testModifyingPuzzlesThatDontExists()
	{
		assertFalse(db.updateCategory(13, Category.Politics));
		assertFalse(db.updateDescription(15, "New and improved description"));
		assertFalse(db.updateDifficulty(3, Difficulty.Hard));
		assertFalse(db.updatePlaintext(1100, "new and improved plaintext"));
		assertFalse(db.updateTitle(-1, "updated title"));
	}

	public void testAddingUniqueUsers()
	{
		int numUsers = db.getUsers().size();

		assertEquals(db.getSortedUserPrimaryKeys().size(), numUsers);

		User u = new User(10l, "TestUser");
		assertTrue(db.saveUser(u));
		assertEquals(numUsers + 1, db.getUsers().size());
		assertEquals(numUsers + 1, db.getSortedUserPrimaryKeys().size());
		assertEquals(u, db.findUser(u.getPrimaryKey()));
		assertNotNull(db.findUser(db.getGuestPrimaryKey()));
		numUsers = db.getUsers().size();

		u = new User(11l, "TestUser2");
		assertTrue(db.saveUser(u));
		assertEquals(numUsers + 1, db.getUsers().size());
		assertEquals(numUsers + 1, db.getSortedUserPrimaryKeys().size());
		assertEquals(u, db.findUser(u.getPrimaryKey()));

	}

	public void testAddingSameUser()
	{
		int numUsers = db.getUsers().size();

		User u = new User(10l, "TestUser");
		assertTrue(db.saveUser(u));
		assertEquals(numUsers + 1, db.getUsers().size());
		assertEquals(numUsers + 1, db.getSortedUserPrimaryKeys().size());
		assertEquals(u, db.findUser(u.getPrimaryKey()));
		numUsers = db.getUsers().size();

		assertTrue(db.saveUser(u));
		assertEquals(numUsers + 1, db.getUsers().size());
		assertEquals(numUsers + 1, db.getSortedUserPrimaryKeys().size());
		assertEquals(u, db.findUser(u.getPrimaryKey()));
	}

	public void testFindingUnaddedUsers()
	{
		assertNull(db.findUser(-1));
		assertNull(db.findUser(11l));

		assertTrue(db.saveUser(new User(10l, "James")));
		assertNull(db.findUser(-1));
		assertNull(db.findUser(11l));

	}
}
