
package tests.models;

import java.util.Date;

import junit.framework.TestCase;

import pg13.models.Cryptogram;
import pg13.models.CryptogramPair;

	public class CryptogramTest extends TestCase
	{
		public CryptogramTest(String arg0)
		{
			super(arg0);
		}

		public void testCryptogramPairClass()
		{
			CryptogramPair cp = new CryptogramPair('L', 'L');
			assertNotNull(cp);
		}
		
		public void testCryptogramPairClassCharacters()
		{
			assertNotNull(new CryptogramPair('L', 'L'));	
			assertNotNull(new CryptogramPair('L', 'R'));	
			assertNotNull(new CryptogramPair('\0', '\0'));
			assertNotNull(new CryptogramPair('L', '\0'));
			try
			{
				CryptogramPair cp1 = new CryptogramPair('!', '!');
				fail();
			}
			catch(IllegalArgumentException iae)
			{
				
			}
		}
		
		public void testEmptyCryptogram()
		{
			Cryptogram cryptogram;

			cryptogram = new Cryptogram();
			assertNotNull(cryptogram);
		}
		
		public void testCryptogramGeneralData()
		{
			Cryptogram cryptogram;
			cryptogram = new Cryptogram("Lauren Slusky", "Practice Cryptogram", new Date(), "This is a test");
			assertNotNull(cryptogram);
			assertEquals("Lauren Slusky", cryptogram.getAuthor());
			assertEquals("Practice Cryptogram", cryptogram.getTitle());
		}

		public void testCryptogramEmptyAuthor()
		{
			Cryptogram cryptogram;	
			cryptogram = new Cryptogram("", "Practice Cryptogram", new Date(), "");
			assertEquals("", cryptogram.getAuthor());						
		}
		
		public void testCryptogramEmptyTitle()
		{
			Cryptogram cryptogram;	
			cryptogram = new Cryptogram("Lauren Slusky", "", new Date(), "");
			assertEquals("", cryptogram.getTitle());			
		}
		
		public void testCryptogramEmptyDate()
		{
			Cryptogram cryptogram;	
			cryptogram = new Cryptogram("Lauren Slusky", "Practice Cryptogram", null, "");
			assertEquals(null, cryptogram.getDateCreated());			
		}
		
		public void testCipherTextWorksNoPunctuation()
		{
			Cryptogram cryptogram;
	
			cryptogram = new Cryptogram("Lauren Slusky", "Practice Cryptogram", new Date(), "This is a test");
			assertEquals("This is a test", cryptogram.getPlaintext());
			assertNotSame("This is a test", cryptogram.getCiphertext());
			assertTrue((cryptogram.decrypt()).equalsIgnoreCase("This is a test"));
		}
		
		public void testCipherTextWorksPunctuation()
		{
			Cryptogram cryptogram;
			cryptogram = new Cryptogram("Lauren Slusky", "Practice Cryptogram", new Date(), "This. is, a! test%");
			assertEquals("This. is, a! test%", cryptogram.getPlaintext());
			assertNotSame("This. is, a! test%", cryptogram.getCiphertext());
			assertTrue((cryptogram.decrypt()).equalsIgnoreCase("This. is, a! test%"));		
		}
		
		public void testCryptogramEmptyPlainText()
		{
			Cryptogram cryptogram;
	
			cryptogram = new Cryptogram("Lauren Slusky", "Practice Cryptogram", new Date(), "");
			assertEquals("", cryptogram.getPlaintext());
			assertEquals("", cryptogram.getCiphertext());
			assertEquals("", cryptogram.decrypt());
		}

		public void testCryptogramAllPuncatuationPlainText()
		{
			Cryptogram cryptogram;
	
			cryptogram = new Cryptogram("Lauren Slusky", "Practice Cryptogram", new Date(), "!!!!!!!!!!!!!!!!!!!!!&!!!!!!!!!!");
			assertEquals("!!!!!!!!!!!!!!!!!!!!!&!!!!!!!!!!", cryptogram.getPlaintext());
			assertEquals("!!!!!!!!!!!!!!!!!!!!!&!!!!!!!!!!", cryptogram.getCiphertext());
			assertEquals("!!!!!!!!!!!!!!!!!!!!!&!!!!!!!!!!", cryptogram.decrypt());
		}
		
		public void testCryptogramCryptogramCompletion()
		{
			Cryptogram cryptogram;
			cryptogram = new Cryptogram("Lauren Slusky", "Practice Cryptogram", new Date(), "This is a test.");
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
			Cryptogram cryptogram;
			cryptogram = new Cryptogram("Lauren Slusky", "Practice Cryptogram", new Date(), "This is a test.");
			cryptogram.setUserPlaintextForCiphertext('h', 'X');
			assertEquals(cryptogram.getUserPlaintextFromCiphertext('X'), 'H');
			assertEquals(cryptogram.getUserCiphertextFromPlaintext('h'), 'X');
			assertNotNull(cryptogram.getUserMapping());
		}
	}
