package tests.business;

import pg13.business.CryptogramManager;
import pg13.models.Category;
import pg13.models.Cryptogram;
import pg13.models.Difficulty;
import pg13.models.User;
import junit.framework.TestCase;

public class CryptogramManagerTest extends TestCase
{
	private CryptogramManager cm;

	public void setUp()
	{
		this.cm = new CryptogramManager();
	}

	public void testEmptyCryptoManager()
	{
		Cryptogram crypto = this.cm.getCryptogram();
		assertNotNull(crypto);
	}

	public void testInitializedCryptogramManager()
	{
		Cryptogram crypto = new Cryptogram();
		this.cm = new CryptogramManager(crypto);
		assertEquals(this.cm.getCryptogram(), crypto);
	}

	public void testNonDefaultInitializedCryptogramManager()
	{
		Cryptogram crypto = new Cryptogram(new User("Someone's Name"),
				"Random title", "Put some curse words in there.",
				Category.Animals, Difficulty.Easy, "This is the plaintext");
		this.cm = new CryptogramManager(crypto);
		assertEquals(crypto, cm.getCryptogram());
	}

	public void testValidPlaintext()
	{
		this.setValidPlainText("a");
		this.setValidPlainText("abcdefghijklmnopqrstuvwxyz");
		this.setValidPlainText("G");
		this.setValidPlainText("AAAAAAABBBBBCCCCCCCC");
		this.setValidPlainText("abcdefgHIJKL");
		this.setValidPlainText("A large bear appeared!");
		this.setValidPlainText("a!!!!!!??!?!");
		this.setValidPlainText("A large & vicious animal!   Attack? ATTACK! ...");
		this.setValidPlainText("");
	}

	private void setValidPlainText(String plainText)
	{
		Cryptogram crypto = this.cm.getCryptogram();
		this.cm.setPlaintext(plainText);
		assertEquals(plainText, this.cm.getPlaintext());
		// check that the actual cryptogram was affected as well
		assertEquals(plainText, crypto.getPlaintext());
	}

	public void testInvalidPlaintext()
	{
		this.setInvalidPlaintext("~");
		this.setInvalidPlaintext("mid`dle");
		this.setInvalidPlaintext("~begin");
		this.setInvalidPlaintext("end~");
		this.setInvalidPlaintext("test invalid with spaces~");
		this.setInvalidPlaintext("~~~~~~~`````");
		this.setInvalidPlaintext("[");
		this.setInvalidPlaintext("}");
		this.setInvalidPlaintext("\\");
		this.setInvalidPlaintext(null);
	}

	private void setInvalidPlaintext(String plaintext)
	{
		try
		{
			this.cm.setPlaintext(plaintext);
			fail();
		}
		catch (IllegalArgumentException e)
		{
			// expected
		}
	}

	public void testUserMappingSimple()
	{
		assertEquals(this.cm.getUserMapping('N'), "");
		this.cm.setUserMapping('X', 'N');
		assertEquals(this.cm.getUserMapping('N'), "X");
		this.cm.setUserMapping('Y', 'N');
		assertEquals(this.cm.getUserMapping('N'), "Y");
		assertEquals(this.cm.getUserMapping('M'), "");
		this.cm.setUserMapping('Z', 'M');
		assertEquals(this.cm.getUserMapping('M'), "Z");
	}

	public void testUserMappingCapitalLettersNotDistinct()
	{
		this.cm.setUserMapping('x', 'n');
		assertEquals(this.cm.getUserMapping('n'), "X");
		assertEquals(this.cm.getUserMapping('N'), "X");
		this.cm.setUserMapping('X', 'N');
		assertEquals(this.cm.getUserMapping('n'), "X");
		assertEquals(this.cm.getUserMapping('N'), "X");
		this.cm.setUserMapping('X', 'n');
		assertEquals(this.cm.getUserMapping('n'), "X");
		assertEquals(this.cm.getUserMapping('N'), "X");
		this.cm.setUserMapping('x', 'N');
		assertEquals(this.cm.getUserMapping('n'), "X");
		assertEquals(this.cm.getUserMapping('N'), "X");
	}

	public void testUserMappingNullCharacter()
	{
		assertEquals(this.cm.getUserMapping('X'), "");
		this.cm.setUserMapping('\0', 'X');
		assertEquals(this.cm.getUserMapping('X'), "");
	}

	public void testUserMappingNumber()
	{
		assertEquals(this.cm.getUserMapping('6'), "");
		this.setInvalidMapping('X', '6');
		assertEquals(this.cm.getUserMapping('6'), "");
	}

	public void testUserMappingSpecialChar()
	{
		assertEquals(this.cm.getUserMapping('\\'), "");
		assertEquals(this.cm.getUserMapping(']'), "");
		assertEquals(this.cm.getUserMapping('#'), "");
		this.setInvalidMapping('X', '\\');
		this.setInvalidMapping('X', ']');
		this.setInvalidMapping('X', '#');
		assertEquals(this.cm.getUserMapping('\\'), "");
		assertEquals(this.cm.getUserMapping(']'), "");
		assertEquals(this.cm.getUserMapping('#'), "");
	}

	public void testUserMappingStringTooLong()
	{
		try
		{
			this.cm.validateUserMapping("more than one char");
			fail();
		}
		catch(IllegalArgumentException e)
		{
			//expected
		}
	}

	public void testUserMappingStringRightLength()
	{
		try
		{
			this.cm.validateUserMapping("c");
			this.cm.validateUserMapping("");
		}
		catch(IllegalArgumentException e)
		{
			fail(); //unexpected
		}
	}

	private void setInvalidMapping(char plaintext, char cipherChar)
	{
		// try the char version
		try
		{
			cm.setUserMapping(plaintext, cipherChar);
			fail();
		}
		catch (IllegalArgumentException e)
		{
			// expected
		}
	}
}
