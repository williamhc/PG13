
package tests.models;

import java.util.Date;

import junit.framework.TestCase;

import pg13.models.Cryptogram;

	public class CryptogramTest extends TestCase
	{
		private final String DEFAULT_TITLE = "Practice Cryptogram";
		private final String DEFAULT_AUTHOR = "Lauren Slusky";
		private final String DEFAULT_CATEGORY = "Hamsters";
		private final String DEFAULT_DIFFICULTY = "Easy";
		private final Date DEFAULT_DATE = new Date();
		private final String DEFAULT_PLAINTEXT = "This is a test.";
		
		private Cryptogram cryptogram;
		
		public CryptogramTest(String arg0)
		{
			super(arg0);
		}
		
		public void testEmptyCryptogram()
		{
			cryptogram = new Cryptogram();
			assertNotNull(cryptogram);
		}
		
		public void testCryptogramGeneralData()
		{
			cryptogram = new Cryptogram(DEFAULT_AUTHOR, DEFAULT_TITLE, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY, DEFAULT_DATE, DEFAULT_PLAINTEXT);
			assertNotNull(cryptogram);
			assertEquals(DEFAULT_AUTHOR, cryptogram.getAuthor());
			assertEquals(DEFAULT_TITLE, cryptogram.getTitle());
			assertEquals(DEFAULT_DATE, cryptogram.getDateCreated());
			assertEquals(DEFAULT_PLAINTEXT, cryptogram.getPlaintext());
			assertEquals(DEFAULT_CATEGORY, cryptogram.getCategory());
			assertEquals(DEFAULT_DIFFICULTY, cryptogram.getDifficulty());
		}

		public void testCryptogramEmptyAuthor()
		{
			cryptogram = new Cryptogram("", DEFAULT_TITLE, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY, DEFAULT_DATE, "");
			assertEquals("", cryptogram.getAuthor());
			assertEquals(DEFAULT_TITLE, cryptogram.getTitle());
			assertEquals(DEFAULT_DATE, cryptogram.getDateCreated());
			assertEquals("", cryptogram.getPlaintext());
			assertEquals(DEFAULT_CATEGORY, cryptogram.getCategory());
			assertEquals(DEFAULT_DIFFICULTY, cryptogram.getDifficulty());
		}
		
		public void testCryptogramEmptyTitle()
		{
			cryptogram = new Cryptogram(DEFAULT_AUTHOR, "", DEFAULT_CATEGORY, DEFAULT_DIFFICULTY, DEFAULT_DATE, "");
			assertEquals("", cryptogram.getTitle());		
			assertEquals(DEFAULT_AUTHOR, cryptogram.getAuthor());
			assertEquals(DEFAULT_DATE, cryptogram.getDateCreated());
			assertEquals("", cryptogram.getPlaintext());
			assertEquals(DEFAULT_CATEGORY, cryptogram.getCategory());
			assertEquals(DEFAULT_DIFFICULTY, cryptogram.getDifficulty());
		}
		
		public void testCryptogramEmptyDate()
		{
			cryptogram = new Cryptogram(DEFAULT_AUTHOR, DEFAULT_TITLE, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY, null, "");
			assertEquals(null, cryptogram.getDateCreated());
			assertEquals(DEFAULT_AUTHOR, cryptogram.getAuthor());
			assertEquals(DEFAULT_TITLE, cryptogram.getTitle());
			assertEquals("", cryptogram.getPlaintext());
			assertEquals(DEFAULT_CATEGORY, cryptogram.getCategory());
			assertEquals(DEFAULT_DIFFICULTY, cryptogram.getDifficulty());
		}
		
		public void testCipherTextWorksNoPunctuation()
		{
			cryptogram = new Cryptogram(DEFAULT_AUTHOR, DEFAULT_TITLE, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY, DEFAULT_DATE, DEFAULT_PLAINTEXT);
			assertEquals(DEFAULT_PLAINTEXT, cryptogram.getPlaintext());
			assertNotSame(DEFAULT_PLAINTEXT, cryptogram.getCiphertext());
			assertFalse(DEFAULT_PLAINTEXT.equals(cryptogram.getCiphertext())); //for some reason there isn't assertNotEquals in JUnit
			assertTrue((cryptogram.decrypt(cryptogram.getSolutionMapping())).equalsIgnoreCase(DEFAULT_PLAINTEXT));
			assertEquals(DEFAULT_CATEGORY, cryptogram.getCategory());
			assertEquals(DEFAULT_DIFFICULTY, cryptogram.getDifficulty());
		}
		
		public void testCipherTextWorksPunctuation()
		{
			String plaintext =  "This. is, a! test%";
			cryptogram = new Cryptogram(DEFAULT_AUTHOR, DEFAULT_TITLE, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY, DEFAULT_DATE, plaintext);
			assertEquals(plaintext, cryptogram.getPlaintext());
			assertNotSame(plaintext, cryptogram.getCiphertext());
			assertTrue((cryptogram.decrypt(cryptogram.getSolutionMapping())).equalsIgnoreCase(plaintext));
			assertEquals(DEFAULT_AUTHOR, cryptogram.getAuthor());
			assertEquals(DEFAULT_TITLE, cryptogram.getTitle());
			assertEquals(DEFAULT_DATE, cryptogram.getDateCreated());
			assertEquals(DEFAULT_CATEGORY, cryptogram.getCategory());
			assertEquals(DEFAULT_DIFFICULTY, cryptogram.getDifficulty());
		}
		
		public void testCryptogramEmptyPlainText()
		{
			cryptogram = new Cryptogram(DEFAULT_AUTHOR, DEFAULT_TITLE, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY, DEFAULT_DATE, "");
			assertEquals("", cryptogram.getPlaintext());
			assertEquals("", cryptogram.getCiphertext());
			assertEquals("", cryptogram.decrypt(cryptogram.getSolutionMapping()));
			assertEquals(DEFAULT_AUTHOR, cryptogram.getAuthor());
			assertEquals(DEFAULT_TITLE, cryptogram.getTitle());
			assertEquals(DEFAULT_DATE, cryptogram.getDateCreated());
			assertEquals(DEFAULT_CATEGORY, cryptogram.getCategory());
			assertEquals(DEFAULT_DIFFICULTY, cryptogram.getDifficulty());
		}

		public void testCryptogramAllPuncatuationPlainText()
		{	
			String plaintext = "!!!!!!!!!!!!!!!!!!!!!&!!!!!!!!!!";
			cryptogram = new Cryptogram(DEFAULT_AUTHOR, DEFAULT_TITLE, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY, DEFAULT_DATE, plaintext);
			assertEquals(plaintext, cryptogram.getPlaintext());
			assertEquals(plaintext, cryptogram.getCiphertext());
			assertEquals(plaintext, cryptogram.decrypt(cryptogram.getSolutionMapping()));
			assertEquals(DEFAULT_AUTHOR, cryptogram.getAuthor());
			assertEquals(DEFAULT_TITLE, cryptogram.getTitle());
			assertEquals(DEFAULT_DATE, cryptogram.getDateCreated());
			assertEquals(DEFAULT_CATEGORY, cryptogram.getCategory());
			assertEquals(DEFAULT_DIFFICULTY, cryptogram.getDifficulty());
		}
		
		public void testCryptogramCryptogramCompletion()
		{
			cryptogram = new Cryptogram(DEFAULT_AUTHOR, DEFAULT_TITLE, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY, DEFAULT_DATE, "This is a test.");
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
			cryptogram = new Cryptogram(DEFAULT_AUTHOR, DEFAULT_TITLE, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY, DEFAULT_DATE, "This is a test.");
			cryptogram.setUserPlaintextForCiphertext('h', 'X');
			assertEquals(cryptogram.getUserPlaintextFromCiphertext('X'), 'H');
			assertNotNull(cryptogram.getUserMapping());
		}
		
		public void testSetPlaintext()
		{
			cryptogram = new Cryptogram(DEFAULT_AUTHOR, DEFAULT_TITLE, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY, DEFAULT_DATE, DEFAULT_PLAINTEXT);
			cryptogram.setUserPlaintextForCiphertext(DEFAULT_PLAINTEXT.charAt(0), cryptogram.getCiphertext().charAt(0));
			this.SetPlaintextandValidateMappings("new plaintext");
			this.SetPlaintextandValidateMappings("new plaintext!");
			this.SetPlaintextandValidateMappings("newplaintext");
			this.SetPlaintextandValidateMappings("");
			this.SetPlaintextandValidateMappings("12345678");
			this.SetPlaintextandValidateMappings("![");			
		}
		
		private void SetPlaintextandValidateMappings(String plaintext)
		{
			cryptogram.setPlaintext(plaintext);
			for(char ch = 'a'; ch < 'z'; ch++)
			{
				assertEquals('\0', cryptogram.getUserPlaintextFromCiphertext(ch));
				assertEquals('\0', cryptogram.getUserPlaintextFromCiphertext(Character.toUpperCase(ch)));
			}
		}
		
		public void testSetAndGetValidUserMappings()
		{
			cryptogram = new Cryptogram(DEFAULT_AUTHOR, DEFAULT_TITLE, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY, DEFAULT_DATE, DEFAULT_PLAINTEXT);
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
