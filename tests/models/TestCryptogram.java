package tests.models;

import java.util.Date;

import pg13.models.Cryptogram;
import junit.framework.TestCase;

public class TestCryptogram extends TestCase {
	Cryptogram cryptogram;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		
	}
	
	public void testConstructors()
	{
		cryptogram = new Cryptogram();
		assertNull(cryptogram.getAuthor());
		assertNull(cryptogram.getTitle());
		assertNull(cryptogram.getDateCreated());
		assertFalse(cryptogram.getIsCompleted());
		assertNull(cryptogram.getSoultionMapping());
		assertNull(cryptogram.getUserMapping());
		
		String author = "John Anderson";
		String title = "Greatest Cryptogram EVER!!!";
		Date today = new Date();
		cryptogram = new Cryptogram(author, title, today);
		assertEquals(author, cryptogram.getAuthor());
		assertEquals(title, cryptogram.getTitle());
		assertEquals(today, cryptogram.getDateCreated());
		assertFalse(cryptogram.getIsCompleted());
		assertNull(cryptogram.getSoultionMapping());
		assertNull(cryptogram.getUserMapping());
	}

}
