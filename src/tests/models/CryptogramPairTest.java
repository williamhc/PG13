package tests.models;

import pg13.models.CryptogramPair;
import junit.framework.TestCase;

public class CryptogramPairTest extends TestCase
 {	
	
	CryptogramPair cp;
	
	public CryptogramPairTest(String arg0)
	{
		super(arg0);
	}

	public void testCryptogramPairClass()
	{
		cp = new CryptogramPair('L', 'L');
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
	
	public void testValidGettersAndSetters()
	{
		cp = new CryptogramPair('a', 'b');
		for(char ch1 = 'a'; ch1 < 'z'; ch1++)
		{
			cp.setCipherc(ch1);
			assertEquals(ch1, cp.getCipherc());
			
			cp.setCipherc(Character.toUpperCase(ch1));
			assertEquals(Character.toUpperCase(ch1), cp.getCipherc());
			
			cp.setPlainc(ch1);
			assertEquals(ch1, cp.getPlainc());
			
			cp.setPlainc(Character.toUpperCase(ch1));
			assertEquals(Character.toUpperCase(ch1), cp.getPlainc());
		}
		
		cp.setCipherc('\0');
		assertEquals('\0', cp.getCipherc());
		
		cp.setPlainc('\0');
		assertEquals('\0', cp.getPlainc());
	}
	
	public void testInvalidSetters()
	{
		cp = new CryptogramPair('a', 'b');
		this.setInvalidCiperCharacter('4');
		this.setInvalidCiperCharacter('[');
		this.setInvalidCiperCharacter('`');
		this.setInvalidCiperCharacter('/');
		this.setInvalidCiperCharacter('\\');
		this.setInvalidCiperCharacter('\n');
		this.setInvalidCiperCharacter((char)('a' - 1));
		this.setInvalidCiperCharacter((char)('A' - 1));
		this.setInvalidCiperCharacter((char)('z' + 1));
		this.setInvalidCiperCharacter((char)('Z' + 1));

		this.setInvalidPlaintextCharacter('4');
		this.setInvalidPlaintextCharacter('[');
		this.setInvalidPlaintextCharacter('`');
		this.setInvalidPlaintextCharacter('/');
		this.setInvalidPlaintextCharacter('\\');
		this.setInvalidPlaintextCharacter('\n');
		this.setInvalidPlaintextCharacter((char)('a' - 1));
		this.setInvalidPlaintextCharacter((char)('A' - 1));
		this.setInvalidPlaintextCharacter((char)('z' + 1));
		this.setInvalidPlaintextCharacter((char)('Z' + 1));
	}
	
	private void setInvalidCiperCharacter(char toSet)
	{
		try
		{
			cp.setCipherc(toSet);
			fail();
		}
		catch(IllegalArgumentException e)
		{
			//expected
		}
	}
	
	private void setInvalidPlaintextCharacter(char toSet)
	{
		try
		{
			cp.setPlainc(toSet);
			fail();
		}
		catch(IllegalArgumentException e)
		{
			//expected
		}
	}
}
