
package tests.models;

import java.util.Date;

import junit.framework.TestCase;

import pg13.models.Cryptogram;
import pg13.models.CryptogramPair;

	public class CryptogramTest extends TestCase
	{
		private final String DEFAULT_TITLE = "Practice Cryptogram";
		private final String DEFAULT_AUTHOR = "Lauren Slusky";
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
			cryptogram = new Cryptogram(DEFAULT_AUTHOR, DEFAULT_TITLE, DEFAULT_DATE, DEFAULT_PLAINTEXT);
			assertNotNull(cryptogram);
			assertEquals(DEFAULT_AUTHOR, cryptogram.getAuthor());
			assertEquals(DEFAULT_TITLE, cryptogram.getTitle());
			assertEquals(DEFAULT_DATE, cryptogram.getDateCreated());
			assertEquals(DEFAULT_PLAINTEXT, cryptogram.getPlaintext());
		}

		public void testCryptogramEmptyAuthor()
		{
			cryptogram = new Cryptogram("", DEFAULT_TITLE, DEFAULT_DATE, "");
			assertEquals("", cryptogram.getAuthor());
			assertEquals(DEFAULT_TITLE, cryptogram.getTitle());
			assertEquals(DEFAULT_DATE, cryptogram.getDateCreated());
			assertEquals("", cryptogram.getPlaintext());
		}
		
		public void testCryptogramEmptyTitle()
		{
			cryptogram = new Cryptogram(DEFAULT_AUTHOR, "", DEFAULT_DATE, "");
			assertEquals("", cryptogram.getTitle());		
			assertEquals(DEFAULT_AUTHOR, cryptogram.getAuthor());
			assertEquals(DEFAULT_DATE, cryptogram.getDateCreated());
			assertEquals("", cryptogram.getPlaintext());
		}
		
		public void testCryptogramEmptyDate()
		{
			cryptogram = new Cryptogram(DEFAULT_AUTHOR, DEFAULT_TITLE, null, "");
			assertEquals(null, cryptogram.getDateCreated());
			assertEquals(DEFAULT_AUTHOR, cryptogram.getAuthor());
			assertEquals(DEFAULT_TITLE, cryptogram.getTitle());
			assertEquals("", cryptogram.getPlaintext());
		}
		
		public void testCipherTextWorksNoPunctuation()
		{
			cryptogram = new Cryptogram(DEFAULT_AUTHOR, DEFAULT_TITLE, DEFAULT_DATE, DEFAULT_PLAINTEXT);
			assertEquals(DEFAULT_PLAINTEXT, cryptogram.getPlaintext());
			assertNotSame(DEFAULT_PLAINTEXT, cryptogram.getCiphertext());
			assertFalse(DEFAULT_PLAINTEXT.equals(cryptogram.getCiphertext())); //for some reason there isn't assertNotEquals in JUnit
			assertTrue((cryptogram.decrypt()).equalsIgnoreCase(DEFAULT_PLAINTEXT));
		}
		
		public void testCipherTextWorksPunctuation()
		{
			String plaintext =  "This. is, a! test%";
			cryptogram = new Cryptogram(DEFAULT_AUTHOR, DEFAULT_TITLE, DEFAULT_DATE, plaintext);
			assertEquals(plaintext, cryptogram.getPlaintext());
			assertNotSame(plaintext, cryptogram.getCiphertext());
			assertTrue((cryptogram.decrypt()).equalsIgnoreCase(plaintext));
			assertEquals(DEFAULT_AUTHOR, cryptogram.getAuthor());
			assertEquals(DEFAULT_TITLE, cryptogram.getTitle());
			assertEquals(DEFAULT_DATE, cryptogram.getDateCreated());
		}
		
		public void testCryptogramEmptyPlainText()
		{
			cryptogram = new Cryptogram(DEFAULT_AUTHOR, DEFAULT_TITLE, DEFAULT_DATE, "");
			assertEquals("", cryptogram.getPlaintext());
			assertEquals("", cryptogram.getCiphertext());
			assertEquals("", cryptogram.decrypt());
			assertEquals(DEFAULT_AUTHOR, cryptogram.getAuthor());
			assertEquals(DEFAULT_TITLE, cryptogram.getTitle());
			assertEquals(DEFAULT_DATE, cryptogram.getDateCreated());
		}

		public void testCryptogramAllPuncatuationPlainText()
		{	
			String plaintext = "!!!!!!!!!!!!!!!!!!!!!&!!!!!!!!!!";
			cryptogram = new Cryptogram(DEFAULT_AUTHOR, DEFAULT_TITLE, DEFAULT_DATE, plaintext);
			assertEquals(plaintext, cryptogram.getPlaintext());
			assertEquals(plaintext, cryptogram.getCiphertext());
			assertEquals(plaintext, cryptogram.decrypt());
		}
		
		public void testCryptogramCryptogramCompletion()
		{
			cryptogram = new Cryptogram(DEFAULT_AUTHOR, DEFAULT_TITLE, DEFAULT_DATE, "This is a test.");
			assertTrue(cryptogram.isCompleted("This is a test."));
			assertTrue(cryptogram.isCompleted("ThIs iS A tESt."));
			assertTrue(cryptogram.isCompleted(cryptogram.decrypt()));
			assertFalse(cryptogram.isCompleted("ThIs iS A tESt!"));
			assertFalse(cryptogram.isCompleted("This sentence is false!")); // dont think about it, dont think about it, dont think about...
			assertFalse(cryptogram.isCompleted(""));
			try
			{
				cryptogram.isCompleted(null); 
				fail();
			}
			catch (IllegalArgumentException iae)
			{
				
			}
		}
		
		public void testCryptogramUserUses()
		{
			cryptogram = new Cryptogram(DEFAULT_AUTHOR, DEFAULT_TITLE, DEFAULT_DATE, "This is a test.");
			cryptogram.setUserPlaintextForCiphertext('h', 'X');
			assertEquals(cryptogram.getUserPlaintextFromCiphertext('X'), 'H');
			assertEquals(cryptogram.getUserCiphertextFromPlaintext('h'), 'X');
			assertNotNull(cryptogram.getUserMapping());
		}
	}
