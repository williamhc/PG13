package tests.models;

import java.util.Date;

import pg13.models.Cryptogram;
import junit.framework.TestCase;

public class TestCryptogram extends TestCase {
	Cryptogram cryptogram;

	protected void setUp() throws Exception {
		super.setUp();

	}

	/**
	 * This solution tests the constructors defined the the superclass of
	 * Cryptogram, Puzzle
	 */
	public void testPuzzleConstructors() {
		this.cryptogram = new Cryptogram();
		this.assertDefaultPuzzleConstructor();
		this.assertNullMappings();

		String author = "John Anderson";
		String title = "Greatest Cryptogram EVER!!!";
		Date today = new Date();
		this.cryptogram = new Cryptogram(author, title, today);
		assertEquals(author, this.cryptogram.getAuthor());
		assertEquals(title, this.cryptogram.getTitle());
		assertEquals(today, this.cryptogram.getDateCreated());
		assertFalse(this.cryptogram.getIsCompleted());
		this.assertNullMappings();
	}

	/**
	 * This method will test the cryptogram constructor that takes in a solution
	 * mapping and a user mapping as parameters
	 */
	public void testMappingConstructor() {

		//test both null
		this.cryptogram = new Cryptogram(null, null);
		this.assertDefaultPuzzleConstructor();
		this.assertNullMappings();
		
		
		char[] solutionMap = new char[26];
		char[] userMap = new char[26];
		
		//test only one null
		this.cryptogram = new Cryptogram(userMap, null);
		this.assertDefaultPuzzleConstructor();
		assertNull(this.cryptogram.getSoultionMapping());
		assertNotNull(this.cryptogram.getUserMapping());
		
		this.cryptogram = new Cryptogram(null, solutionMap);
		this.assertDefaultPuzzleConstructor();
		assertNotNull(this.cryptogram.getSoultionMapping());
		assertNull(this.cryptogram.getUserMapping());
		
		//test neither null and both defaul
		this.cryptogram = new Cryptogram(userMap, solutionMap);
		this.assertDefaultPuzzleConstructor();
		assertEquals(solutionMap, this.cryptogram.getSoultionMapping());
		assertEquals(userMap, this.cryptogram.getUserMapping());
		
		//test default again (I found out after I wrote this test that this for loop doesn't change the value of the maps)
		for(int i = 0; i < solutionMap.length; i++)
		{
			solutionMap[i] = (char)0;
			userMap[i] = (char)0;
		}
		this.cryptogram = new Cryptogram(userMap, solutionMap);
		this.assertDefaultPuzzleConstructor();
		assertEquals(solutionMap, this.cryptogram.getSoultionMapping());
		assertEquals(userMap, this.cryptogram.getUserMapping());
		
		//test one mapped and other default
		for(int i = 0; i < solutionMap.length; i++)
		{
			solutionMap[i] = (char)(i + (int)'A');
		}
		this.cryptogram = new Cryptogram(userMap, solutionMap);
		this.assertDefaultPuzzleConstructor();
		assertEquals(solutionMap, this.cryptogram.getSoultionMapping());
		assertEquals(userMap, this.cryptogram.getUserMapping());
		
		//test both mapped to same thing
		for(int i = 0; i < userMap.length; i++)
		{
			userMap[i] = (char)(i + (int)'A');
		}
		this.cryptogram = new Cryptogram(userMap, solutionMap);
		assertNull(this.cryptogram.getAuthor());
		assertNull(this.cryptogram.getTitle());
		assertNull(this.cryptogram.getDateCreated());
		assertTrue(this.cryptogram.getIsCompleted()); //can't call assertDefaultPUzzleConstructor because of this line
		assertEquals(solutionMap, this.cryptogram.getSoultionMapping());
		assertEquals(userMap, this.cryptogram.getUserMapping());
		
		//test different maps
		userMap[0] = 'Z';
		userMap[25] = 'A';
		this.cryptogram = new Cryptogram(userMap, solutionMap);
		assertDefaultPuzzleConstructor();
		assertEquals(userMap, this.cryptogram.getUserMapping());
		assertEquals(solutionMap, this.cryptogram.getSoultionMapping());
		
	}
	
	private void assertDefaultPuzzleConstructor()
	{
		assertNull(this.cryptogram.getAuthor());
		assertNull(this.cryptogram.getTitle());
		assertNull(this.cryptogram.getDateCreated());
		assertFalse(this.cryptogram.getIsCompleted());
	}
	
	private void assertNullMappings()
	{
		assertNull(this.cryptogram.getSoultionMapping());
		assertNull(this.cryptogram.getUserMapping());
	}

}
