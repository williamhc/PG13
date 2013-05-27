
package tests.models;

import java.util.Date;

import junit.framework.TestCase;

import pg13.models.Cryptogram;

	public class CryptogramTest extends TestCase
	{
		public CryptogramTest(String arg0)
		{
			super(arg0);
		}

		public void testEmptyCryptogram()
		{
			Cryptogram cryptogram;

			System.out.println("\nStarting testCryptogram");

			cryptogram = new Cryptogram();
			assertNotNull(cryptogram);
			System.out.println("Finished testCryptogram");
		}
		
		public void testCryptogramGeneralData()
		{
			Cryptogram cryptogram;

			System.out.println("\nStarting testCryptogramGeneralData");
			cryptogram = new Cryptogram("Lauren Slusky", "Practice Cryptogram", new Date(), "This is a test");
			assertNotNull(cryptogram);
			assertEquals("Lauren Slusky", cryptogram.getAuthor());
			assertEquals("Practice Cryptogram", cryptogram.getTitle());
			System.out.println("Finished testCryptogramGeneralData");
		}

		public void testCipherTextWorks1()
		{
			Cryptogram cryptogram;

			System.out.println("\nStarting testCipherTextWorks1");		
			cryptogram = new Cryptogram("Lauren Slusky", "Practice Cryptogram", new Date(), "This is a test");
			assertEquals("This is a test", cryptogram.getPlaintext());
			assertNotSame("This is a test", cryptogram.getCiphertext());
			assertTrue((cryptogram.decrypt()).equalsIgnoreCase("This is a test"));
			
			System.out.println("Finished testCipherTextWorks1");
			
		}
		
		public void testCipherTextWorks2()
		{
			Cryptogram cryptogram;

			System.out.println("\nStarting testCipherTextWorks2");		
			cryptogram = new Cryptogram("Lauren Slusky", "Practice Cryptogram", new Date(), "This. is, a! test%");
			assertEquals("This. is, a! test%", cryptogram.getPlaintext());
			assertNotSame("This. is, a! test%", cryptogram.getCiphertext());
			assertTrue((cryptogram.decrypt()).equalsIgnoreCase("This. is, a! test%"));
			
			System.out.println("Finished testCipherTextWorks2");
			
		}
		
		public void testCryptogramEmptyPlainText()
		{
			Cryptogram cryptogram;

			System.out.println("\nStarting testCryptogramEmptyPlainText");		
			cryptogram = new Cryptogram("Lauren Slusky", "Practice Cryptogram", new Date(), "");
			assertEquals("", cryptogram.getPlaintext());
			assertEquals("", cryptogram.getCiphertext());
			assertEquals("", cryptogram.decrypt());
			
			System.out.println("Finished testCryptogramEmptyPlainText");
			
		}
		
		public void testCryptogramEmptyAuthor()
		{
			Cryptogram cryptogram;

			System.out.println("\nStarting testCryptogramEmptyAuthor");		
			cryptogram = new Cryptogram("", "Practice Cryptogram", new Date(), "");
			assertEquals("", cryptogram.getAuthor());			
			System.out.println("Finished testCryptogramEmptyAuthor");
			
		}
		
		public void testCryptogramEmptyTitle()
		{
			Cryptogram cryptogram;

			System.out.println("\nStarting testCryptogramEmptyTitle");		
			cryptogram = new Cryptogram("Lauren Slusky", "", new Date(), "");
			assertEquals("", cryptogram.getTitle());			
			System.out.println("Finished testCryptogramEmptyTitle");
			
		}
		public void testCryptogramEmptyDate()
		{
			Cryptogram cryptogram;

			System.out.println("\nStarting testCryptogramEmptyDate");		
			cryptogram = new Cryptogram("Lauren Slusky", "Practice Cryptogram", null, "");
			assertEquals(null, cryptogram.getDateCreated());
			System.out.println("Finished testCryptogramEmptyDate");
			
		}
	}
