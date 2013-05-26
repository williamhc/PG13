
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
		
		public void testCryptogramData1()
		{
			Cryptogram cryptogram;

			System.out.println("\nStarting testCryptogramData1");		
			cryptogram = new Cryptogram("Lauren Slusky", "Practice Cryptogram", new Date(), "This is a test");
			assertNotNull(cryptogram);
			assertEquals("Lauren Slusky", cryptogram.getAuthor());
			assertEquals("Practice Cryptogram", cryptogram.getTitle());
			assertEquals("This is a test", cryptogram.getPlainText());
			assertNotSame("This is a test", cryptogram.getCipherText());
			System.out.println(cryptogram.getPlainText());
			System.out.println(cryptogram.getCipherText());
			
			System.out.println("Finished testCryptogramData1");
			
		}
		
		public void testCryptogramData2()
		{
			Cryptogram cryptogram;

			System.out.println("\nStarting testCryptogramData2");		
			cryptogram = new Cryptogram("Lauren Slusky", "Practice Cryptogram", new Date(), "This. is, a! test%");
			assertNotNull(cryptogram);
			assertEquals("Lauren Slusky", cryptogram.getAuthor());
			assertEquals("Practice Cryptogram", cryptogram.getTitle());
			assertEquals("This. is, a! test%", cryptogram.getPlainText());
			assertNotSame("This is a test", cryptogram.getCipherText());
			System.out.println(cryptogram.getPlainText());
			System.out.println(cryptogram.getCipherText());
			
			System.out.println("Finished testCryptogramData2");
			
		}
		
		public void testCryptogramData3()
		{
			Cryptogram cryptogram;

			System.out.println("\nStarting testCryptogramData3");		
			cryptogram = new Cryptogram("Lauren Slusky", "Practice Cryptogram", new Date(), "");
			assertNotNull(cryptogram);
			assertEquals("Lauren Slusky", cryptogram.getAuthor());
			assertEquals("Practice Cryptogram", cryptogram.getTitle());
			assertEquals("", cryptogram.getPlainText());
			assertEquals("", cryptogram.getCipherText());
			System.out.println(cryptogram.getPlainText());
			System.out.println(cryptogram.getCipherText());
			
			System.out.println("Finished testCryptogramData3");
			
		}
	}
