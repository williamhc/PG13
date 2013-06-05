package tests.models;

import junit.framework.TestCase;

import pg13.models.CryptogramPair;

public class CryptogramPairTest extends TestCase
 {		
	public CryptogramPairTest(String arg0)
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
			new CryptogramPair('!', '!');
			fail();
		}
		catch(IllegalArgumentException iae)
		{
			
		}
	}
}
