package tests.models;

import java.util.ArrayList;

import pg13.models.Category;
import pg13.models.Cryptogram;
import pg13.models.Difficulty;
import pg13.models.Puzzle;
import pg13.models.User;
import junit.framework.TestCase;

public class UserTest extends TestCase
{
	private ArrayList<Puzzle> testPuzzles;
	private final String DEFAULT_TITLE = "Practice Cryptogram";
	private final String DEFAULT_AUTHOR = "Lauren Slusky";
	private final User DEFAULT_USER = new User("Lauren Slusky");
	private final Category DEFAULT_CATEGORY = Category.Computers;
	private final Difficulty DEFAULT_DIFFICULTY = Difficulty.Easy;
	private final String DEFAULT_TEXT = "This is a test";
	private final String DEFAULT_DESCRIPTION = "Default description";
	private Puzzle DEFAULT_PUZZLE;

	public UserTest(String arg0)
	{
		super(arg0);
	}

	public void setUp()
	{
		DEFAULT_PUZZLE = new Cryptogram(DEFAULT_USER, DEFAULT_TITLE,
				DEFAULT_DESCRIPTION, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY,
				DEFAULT_TEXT);
		testPuzzles = new ArrayList<Puzzle>();
		testPuzzles.add(DEFAULT_PUZZLE);
		testPuzzles.add(DEFAULT_PUZZLE);

	}

	public void testDefaultUser()
	{
		User user = new User();
		assertNotNull(user);
		assertEquals(null, user.getName());
		assert (-1 == user.getPrimaryKey().longValue());
		assert (user.getPuzzles().size() == 0);
	}

	public void testUserGivenUserName()
	{
		User user = new User(DEFAULT_AUTHOR);
		assertEquals(DEFAULT_AUTHOR, user.getName());
		assert (-1 == user.getPrimaryKey().longValue());
		assert (user.getPuzzles().size() == 0);
		user.setName(DEFAULT_AUTHOR);
		assertEquals(DEFAULT_AUTHOR, user.getName());
	}

	public void testUserGivenNameAndKey()
	{
		User user = new User(new Long(10), DEFAULT_AUTHOR);
		assertEquals(DEFAULT_AUTHOR, user.getName());
		assert (10 == user.getPrimaryKey().longValue());
		assert (user.getPuzzles().size() == 0);
	}

	public void testUserGivenAllUserInformation()
	{
		User user = new User(new Long(10), DEFAULT_AUTHOR, testPuzzles);
		assertEquals(DEFAULT_AUTHOR, user.getName());
		assert (10 == user.getPrimaryKey().longValue());
		assert (user.getPuzzles().size() > 0);
	}

	public void testUserListOfPuzzles()
	{
		User user = new User(new Long(10), DEFAULT_AUTHOR);
		assert (user.getPuzzles().size() == 0);
		Cryptogram cp = new Cryptogram(new User("Author"), "Title",
				DEFAULT_DESCRIPTION, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY,
				DEFAULT_TEXT);
		user.addPuzzle(cp);
		assert (user.getPuzzles().size() == 1);
		user.addPuzzle(cp);
		user.addPuzzle(cp);
		assert (user.getPuzzles().size() == 3);
		user.setPuzzles(testPuzzles);
		assert (user.getPuzzles().size() == 2);
		user.setPuzzles(null);
		assertNull(user.getPuzzles());

		// test the removal (and obviously a bit more adding)
		user = new User(new Long(10), DEFAULT_AUTHOR);
		assert (user.getPuzzles().size() == 0);
		cp = new Cryptogram(new User("Author"), "Title", DEFAULT_DESCRIPTION,
				DEFAULT_CATEGORY, DEFAULT_DIFFICULTY, DEFAULT_TEXT);
		user.addPuzzle(cp);
		user.addPuzzle(cp);
		user.addPuzzle(cp);
		assertNotNull(user.getPuzzles());
		assert (user.getPuzzles().size() == 3);
		user.removePuzzle(0);
		assert (user.getPuzzles().size() == 2);
		user.setPuzzles(testPuzzles);
		assertFalse(user.removePuzzle(cp));
		assertTrue(user.removePuzzle(testPuzzles.get(0)));
	}
}
