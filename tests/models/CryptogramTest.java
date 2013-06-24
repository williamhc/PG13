
package tests.models;

import junit.framework.TestCase;

import pg13.models.Category;
import pg13.models.Cryptogram;
import pg13.models.Difficulty;
import pg13.models.Puzzle;
import pg13.models.PuzzleValidationException;
import pg13.models.User;

	public class CryptogramTest extends TestCase
	{
		private final String DEFAULT_TITLE = "Practice Cryptogram";
		private final String DEFAULT_AUTHOR = "Lauren Slusky";
		private final User	DEFAULT_USER = new User("Lauren Slusky");
		private final Category DEFAULT_CATEGORY = Category.Computers;
		private final Difficulty DEFAULT_DIFFICULTY = Difficulty.Easy;
		private final String DEFAULT_DESCRIPTION = "Some Description";
		private final String DEFAULT_PLAINTEXT = "This is a test.";
		private final long DEFAULT_ID = 1;
		
		private Cryptogram cryptogram;
		
		public CryptogramTest(String arg0)
		{
			super(arg0);
		}
		
		public void testEmptyCryptogram()
		{
			cryptogram = new Cryptogram();
			assertNotNull(cryptogram);
			
			try
			{
				cryptogram.validate();
				fail();
			}
			catch(PuzzleValidationException e)
			{
				
			}
		}
		
		public void testCryptogramGeneralData()
		{
			cryptogram = new Cryptogram(DEFAULT_USER, DEFAULT_TITLE, DEFAULT_DESCRIPTION, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY, DEFAULT_PLAINTEXT, DEFAULT_ID);
			assertNotNull(cryptogram);
			assertEquals(DEFAULT_AUTHOR, cryptogram.getAuthor());
			assertEquals(DEFAULT_TITLE, cryptogram.getTitle());
			assertEquals(DEFAULT_PLAINTEXT, cryptogram.getPlaintext());
			assertEquals(DEFAULT_CATEGORY, cryptogram.getCategory());
			assertEquals(DEFAULT_DIFFICULTY, cryptogram.getDifficulty());
			assertEquals(DEFAULT_ID, cryptogram.getID());
		}

		public void testCryptogramEmptyAuthor()
		{
			cryptogram = new Cryptogram(new User(""), DEFAULT_TITLE, DEFAULT_DESCRIPTION, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY, "", DEFAULT_ID);
			assertEquals("", cryptogram.getAuthor());
			assertEquals(DEFAULT_TITLE, cryptogram.getTitle());
			assertEquals("", cryptogram.getPlaintext());
			assertEquals(DEFAULT_CATEGORY, cryptogram.getCategory());
			assertEquals(DEFAULT_DIFFICULTY, cryptogram.getDifficulty());
			assertEquals(DEFAULT_ID, cryptogram.getID());
		}
		
		
		
		public void testCryptogramEmptyTitle()
		{
			cryptogram = new Cryptogram(DEFAULT_USER, "", DEFAULT_DESCRIPTION, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY, "", DEFAULT_ID);
			assertEquals("", cryptogram.getTitle());		
			assertEquals(DEFAULT_AUTHOR, cryptogram.getAuthor());
			assertEquals("", cryptogram.getPlaintext());
			assertEquals(DEFAULT_CATEGORY, cryptogram.getCategory());
			assertEquals(DEFAULT_DIFFICULTY, cryptogram.getDifficulty());
			assertEquals(DEFAULT_ID, cryptogram.getID());
		}

		
		public void testNoID()
		{
			cryptogram = new Cryptogram(DEFAULT_USER, DEFAULT_TITLE, DEFAULT_DESCRIPTION, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY, DEFAULT_PLAINTEXT);
			assertEquals(DEFAULT_AUTHOR, cryptogram.getAuthor());
			assertEquals(DEFAULT_TITLE, cryptogram.getTitle());
			assertEquals(DEFAULT_PLAINTEXT, cryptogram.getPlaintext());
			assertEquals(DEFAULT_CATEGORY, cryptogram.getCategory());
			assertEquals(DEFAULT_DIFFICULTY, cryptogram.getDifficulty());
			assertEquals(Puzzle.DEFAULT_ID, cryptogram.getID());
		}
		
		public void testCipherTextWorksNoPunctuation()
		{
			cryptogram = new Cryptogram(DEFAULT_USER, DEFAULT_TITLE, DEFAULT_DESCRIPTION, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY, DEFAULT_PLAINTEXT, DEFAULT_ID);
			assertEquals(DEFAULT_PLAINTEXT, cryptogram.getPlaintext());
			assertNotSame(DEFAULT_PLAINTEXT, cryptogram.getCiphertext());
			assertFalse(DEFAULT_PLAINTEXT.equals(cryptogram.getCiphertext())); //for some reason there isn't assertNotEquals in JUnit
			assertTrue((cryptogram.decrypt(cryptogram.getSolutionMapping())).equalsIgnoreCase(DEFAULT_PLAINTEXT));
			assertEquals(DEFAULT_CATEGORY, cryptogram.getCategory());
			assertEquals(DEFAULT_DIFFICULTY, cryptogram.getDifficulty());
			assertEquals(DEFAULT_ID, cryptogram.getID());
		}
		
		public void testCipherTextWorksPunctuation()
		{
			String plaintext =  "This. is, a! test%";
			cryptogram = new Cryptogram(DEFAULT_USER, DEFAULT_TITLE, DEFAULT_DESCRIPTION, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY, plaintext, DEFAULT_ID);
			assertEquals(plaintext, cryptogram.getPlaintext());
			assertNotSame(plaintext, cryptogram.getCiphertext());
			assertTrue((cryptogram.decrypt(cryptogram.getSolutionMapping())).equalsIgnoreCase(plaintext));
			assertEquals(DEFAULT_AUTHOR, cryptogram.getAuthor());
			assertEquals(DEFAULT_TITLE, cryptogram.getTitle());
			assertEquals(DEFAULT_CATEGORY, cryptogram.getCategory());
			assertEquals(DEFAULT_DIFFICULTY, cryptogram.getDifficulty());
			assertEquals(DEFAULT_ID, cryptogram.getID());
		}
		
		public void testCryptogramEmptyPlainText()
		{
			cryptogram = new Cryptogram(DEFAULT_USER, DEFAULT_TITLE, DEFAULT_DESCRIPTION, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY, "", DEFAULT_ID);
			assertEquals("", cryptogram.getPlaintext());
			assertEquals("", cryptogram.getCiphertext());
			assertEquals("", cryptogram.decrypt(cryptogram.getSolutionMapping()));
			assertEquals(DEFAULT_AUTHOR, cryptogram.getAuthor());
			assertEquals(DEFAULT_TITLE, cryptogram.getTitle());
			assertEquals(DEFAULT_CATEGORY, cryptogram.getCategory());
			assertEquals(DEFAULT_DIFFICULTY, cryptogram.getDifficulty());
			assertEquals(DEFAULT_ID, cryptogram.getID());
			
			try
			{
				cryptogram.validate();
				fail();
			}
			catch(PuzzleValidationException e)
			{
				
			}
		}

		public void testCryptogramAllPuncatuationPlainText()
		{	
			String plaintext = "!!!!!!!!!!!!!!!!!!!!!&!!!!!!!!!!";
			cryptogram = new Cryptogram(DEFAULT_USER, DEFAULT_TITLE, DEFAULT_DESCRIPTION, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY, plaintext, DEFAULT_ID);
			assertEquals(plaintext, cryptogram.getPlaintext());
			assertEquals(plaintext, cryptogram.getCiphertext());
			assertEquals(plaintext, cryptogram.decrypt(cryptogram.getSolutionMapping()));
			assertEquals(DEFAULT_AUTHOR, cryptogram.getAuthor());
			assertEquals(DEFAULT_TITLE, cryptogram.getTitle());
			assertEquals(DEFAULT_CATEGORY, cryptogram.getCategory());
			assertEquals(DEFAULT_DIFFICULTY, cryptogram.getDifficulty());
			assertEquals(DEFAULT_ID, cryptogram.getID());
		}
		
		public void testCryptogramCryptogramCompletion()
		{
			cryptogram = new Cryptogram(DEFAULT_USER, DEFAULT_TITLE, DEFAULT_DESCRIPTION, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY, "This is a test.");
			this.setUserMappingForTest(cryptogram);
			assertTrue(cryptogram.isCompleted());
		}
		
		private void setUserMappingForTest(Cryptogram cryptogram) 
		{
			for(int i = 0; i < cryptogram.getSolutionMapping().length; i++)
			{			
				cryptogram.setUserPlaintextForCiphertext(cryptogram.getSolutionMapping()[i].getPlainc(), cryptogram.getSolutionMapping()[i].getCipherc());
			}
			
		}

		public void testCryptogramUserUses()
		{
			cryptogram = new Cryptogram(DEFAULT_USER, DEFAULT_TITLE, DEFAULT_DESCRIPTION, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY, "This is a test.");
			cryptogram.setUserPlaintextForCiphertext('h', 'X');
			assertEquals(cryptogram.getUserPlaintextFromCiphertext('X'), 'H');
			assertNotNull(cryptogram.getUserMapping());
		}		
		
		public void testSetAndGetValidUserMappings()
		{
			cryptogram = new Cryptogram(DEFAULT_USER, DEFAULT_TITLE, DEFAULT_DESCRIPTION, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY, DEFAULT_PLAINTEXT);
			for(char ch1 = 'a'; ch1 <= 'z'; ch1++)
			{
				for(char ch2 = 'a'; ch2 <= 'z'; ch2++ )
				{
					cryptogram.setUserPlaintextForCiphertext(ch1, ch2);
					assertEquals(Character.toUpperCase(ch1), cryptogram.getUserPlaintextFromCiphertext(ch2));
					
					cryptogram.setUserPlaintextForCiphertext(Character.toUpperCase(ch1), ch2);
					assertEquals(Character.toUpperCase(ch1), cryptogram.getUserPlaintextFromCiphertext(ch2));
					
					cryptogram.setUserPlaintextForCiphertext(ch1, Character.toUpperCase(ch2));
					assertEquals(Character.toUpperCase(ch1), cryptogram.getUserPlaintextFromCiphertext(ch2));
					
					cryptogram.setUserPlaintextForCiphertext(Character.toUpperCase(ch1), Character.toUpperCase(ch2));
					assertEquals(Character.toUpperCase(ch1), cryptogram.getUserPlaintextFromCiphertext(ch2));
				}
			}
		}
	}
