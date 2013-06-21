	package tests.models;


	import java.util.ArrayList;
import java.util.Date;

import pg13.models.Category;
import pg13.models.Cryptogram;
import pg13.models.Difficulty;
import pg13.models.Puzzle;
import pg13.models.User;
import junit.framework.TestCase;


public class UserTest extends TestCase
{		
	private ArrayList <Puzzle> testPuzzles;
	private final String DEFAULT_TITLE = "Practice Cryptogram";
	private final String DEFAULT_AUTHOR = "Lauren Slusky";
	private final User	DEFAULT_USER = new User("Lauren Slusky");
	private final Category DEFAULT_CATEGORY = Category.Computers;
	private final Difficulty DEFAULT_DIFFICULTY = Difficulty.Easy;
	private final String DEFAULT_TEXT = "This is a test";
	private final Date DEFAULT_DATE = new Date();
	private Puzzle DEFAULT_PUZZLE;
	
	public UserTest(String arg0)
	{
		super(arg0);
	}
	
	public void setUp()
	{
		DEFAULT_PUZZLE = new Cryptogram(DEFAULT_USER, DEFAULT_TITLE, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY, DEFAULT_DATE, DEFAULT_TEXT);
		testPuzzles = new ArrayList<Puzzle> ();
		testPuzzles.add(DEFAULT_PUZZLE);
		testPuzzles.add(DEFAULT_PUZZLE);
	
	}
	public void testUserConstructorNoArguments()
	{
		User user = new User();
		assertNotNull(user);
		assertEquals(null, user.getName());
		assertTrue(0 < user.getPrimaryKey());
		assertTrue(user.getPuzzles().size() == 0);
	}
	
	public void testUserConstructorOneArguments()
	{
		User user = new User(DEFAULT_AUTHOR);
		assertEquals(DEFAULT_AUTHOR, user.getName());
		assertTrue(0 < user.getPrimaryKey());
		assertTrue(user.getPuzzles().size() == 0);	
		user.setName(DEFAULT_AUTHOR);
		assertEquals(DEFAULT_AUTHOR, user.getName());
	}
	
	public void testUserConstructorTwoArguments()
	{
		User user = new User(DEFAULT_AUTHOR);
		assertEquals(DEFAULT_AUTHOR, user.getName());
		assertTrue(0 < user.getPrimaryKey());
		assertTrue(user.getPuzzles().size() == 0);
	}
	
	public void testUserConstructorThreeArguments()
	{
		User user = new User(DEFAULT_AUTHOR, testPuzzles);
		assertEquals(DEFAULT_AUTHOR, user.getName());
		assertTrue(0 < user.getPrimaryKey());
		assertTrue(user.getPuzzles().size() > 0);
	}
	
	public void testUserArrayListAddElements()
	{
		User user = new User(DEFAULT_AUTHOR);
		assertTrue(user.getPuzzles().size() == 0);
		Cryptogram cp = new Cryptogram(new User("Author"), "Title", DEFAULT_CATEGORY, DEFAULT_DIFFICULTY, DEFAULT_DATE, DEFAULT_TEXT);
		user.addPuzzle(cp);
		assertTrue(user.getPuzzles().size() == 1);
		user.addPuzzle(cp);
		user.addPuzzle(cp);
		assertTrue(user.getPuzzles().size() == 3);
		user.setPuzzles(testPuzzles);
		assertTrue(user.getPuzzles().size() == 2);
		user.setPuzzles(null);
		assertNull(user.getPuzzles());		
	}
	
	public void testUserArrayListRemoveElements()
	{
		User user = new User(DEFAULT_AUTHOR);
		assertTrue(user.getPuzzles().size() == 0);
		Cryptogram cp = new Cryptogram(new User("Author"), "Title", DEFAULT_CATEGORY, DEFAULT_DIFFICULTY, DEFAULT_DATE, DEFAULT_TEXT);
		user.addPuzzle(cp);
		user.addPuzzle(cp);
		user.addPuzzle(cp);
		assertNotNull(user.getPuzzles());
		assertTrue(user.getPuzzles().size() == 3);
		user.removePuzzle(0);
		assertTrue(user.getPuzzles().size() == 2);
		user.setPuzzles(testPuzzles);
		assertFalse(user.removePuzzle(cp));
		assertTrue(user.removePuzzle(testPuzzles.get(0)));
	}
}
