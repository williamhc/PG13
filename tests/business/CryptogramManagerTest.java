package tests.business;

import java.util.Date;

import pg13.business.CryptogramManager;
import pg13.models.Cryptogram;
import pg13.models.Difficulty;
import junit.framework.TestCase;

/**
 * @date May 31 2013
 * @author PaymahnMoghadasian
 * 
 */
public class CryptogramManagerTest extends TestCase
{
	private CryptogramManager cm;

	public void setUp()
	{
		this.cm = new CryptogramManager();
	}

	/**
	 * @date May 31 2013
	 * @author PaymahnMoghadasian
	 * 
	 *         Test the constructors in Cryptogram manager
	 */
	public void testConstructors()
	{
		Cryptogram cryptogram = new Cryptogram();

		this.cm = new CryptogramManager();
		assertNotNull(cm.getCryptogram());

		this.cm = new CryptogramManager(cryptogram);
		assertEquals(cryptogram, cm.getCryptogram());

		cryptogram = new Cryptogram("Someone's Name", "Random title", "Animal",
				Difficulty.Easy, new Date(), "This is the plaintext");
		this.cm = new CryptogramManager(cryptogram);
		assertEquals(cryptogram, cm.getCryptogram());
	}

	/**
	 * While this specifically tests setting plaintext, it also tests
	 * validatePlaintext because cm.setplaintext calls validatePlaintext before
	 * setting it.
	 * 
	 * @author PaymahnMoghadasian
	 * @date June 1 2013
	 */
	public void testValidPlaintext()
	{
		this.cm = new CryptogramManager();

		this.setAndValidateValidPlainText("a");
		this.setAndValidateValidPlainText("g");
		this.setAndValidateValidPlainText("abcdefghijklmnopqrstuvwxyz");
		this.setAndValidateValidPlainText("G");
		this.setAndValidateValidPlainText("Z");
		this.setAndValidateValidPlainText("A!");
		this.setAndValidateValidPlainText("AAAAAAABBBBBCCCCCCCC");
		this.setAndValidateValidPlainText("abcdefgHIJKL");
		this.setAndValidateValidPlainText("A b c d some phrase");
		this.setAndValidateValidPlainText("A large bear appeared!");
		this.setAndValidateValidPlainText("a!!!!!!??!?!");
		this.setAndValidateValidPlainText("A large & vicious animal!   Attack? ATTACK! ...");
		this.setAndValidateValidPlainText("");
	}

	private void setAndValidateValidPlainText(String plainText)
	{
		this.cm.setPlaintext(plainText);
		assertEquals(plainText, this.cm.getPlaintext());
	}

	/**
	 * While this specifically tests setting plaintext, it also tests
	 * validatePlaintext because cm.setplaintext calls validatePlaintext before
	 * setting it.
	 * 
	 * @author PaymahnMoghadasian
	 * @date June 1 2013
	 * 
	 */
	public void testInvalidPlaintext()
	{
		this.setAndValidateInvalidPlaintext("~");
		this.setAndValidateInvalidPlaintext("mid`dle");
		this.setAndValidateInvalidPlaintext("~begin");
		this.setAndValidateInvalidPlaintext("end~");
		this.setAndValidateInvalidPlaintext("test invalid with spaces~");
		this.setAndValidateInvalidPlaintext("~~~~~~~~~~~~~~~~`````````````");
		this.setAndValidateInvalidPlaintext("[");
		this.setAndValidateInvalidPlaintext("}");
		this.setAndValidateInvalidPlaintext("\\");
		try
		{
			this.cm.setPlaintext(null);
			fail();
		} catch (NullPointerException e)
		{
			// expected
		}
	}

	/**
	 * Try to set the plaintext of a crayptogram. An exception should be raised
	 * by the CryptogramManager because the plaintext is (expected to be)
	 * invalid.
	 * 
	 * @param plaintext
	 *            The plaintext to set
	 * @author PaymahnMoghadasian
	 * @date June 1 2013
	 */
	private void setAndValidateInvalidPlaintext(String plaintext)
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

	/**
	 * 
	 * @author PaymahnMoghadasian
	 * @date June 2 2013
	 * 
	 *       This is a beast of a method and is not exhaustive (I don't think).
	 *       Someone should look over it and see if there's a better way to
	 *       approach testing the method this method tests.
	 */
	public void testGetUserMapping()
	{
		String plaintext = "testing stuff";
		cm.setPlaintext(plaintext);
		String cipherText = cm.getCryptogram().getCiphertext();
		assertFalse(cipherText.equals(""));

		// test none mapped
		for (int i = 0; i < cipherText.length(); i++)
		{
			char currentChar = cipherText.charAt(i);
			if (Character.isLetter(currentChar))
			{
				String userChar = cm.getUserMapping(currentChar);
				assertEquals("", userChar);
			}
		}

		// test one mapped
		cm.getCryptogram().setUserPlaintextForCiphertext(plaintext.charAt(0),
				cipherText.charAt(0));
		for (int i = 0; i < cipherText.length(); i++)
		{
			char currentChar = cipherText.charAt(i);
			if (Character.isLetter(currentChar))
			{
				String userChar = cm.getUserMapping(currentChar);
				if (plaintext.charAt(i) == 't')
				{
					assertEquals("T", userChar);
				} 
				else
				{
					assertEquals("", userChar);
				}
			}
		}

		// test two mapped
		cm.getCryptogram().setUserPlaintextForCiphertext(plaintext.charAt(1),
				cipherText.charAt(1));
		for (int i = 0; i < cipherText.length(); i++)
		{
			char currentChar = cipherText.charAt(i);
			if (Character.isLetter(currentChar))
			{
				String userChar = cm.getUserMapping(currentChar);
				if (plaintext.charAt(i) == 't')
				{
					assertEquals("T", userChar);
				} 
				else if (plaintext.charAt(i) == 'e')
				{
					assertEquals("E", userChar);
				} 
				else
				{
					assertEquals("", userChar);
				}
			}
		}

		// test all mapped
		for (int i = 0; i < plaintext.length(); i++)
		{
			if (Character.isLetter(plaintext.charAt(i)))
			{
				cm.setUserMapping(plaintext.charAt(i), cipherText.charAt(i));
			}
		}
		for (int i = 0; i < cipherText.length(); i++)
		{
			char currentChar = cipherText.charAt(i);
			if (Character.isLetter(currentChar))
			{
				String userChar = cm.getUserMapping(currentChar);
				assertEquals(Character.toString(Character.toUpperCase(plaintext
						.charAt(i))), userChar);
			}
		}

	}

	/**
	 * This function and the following function test the setting methods and also the validateUserMapping method.
	 * This is exhaustive.
	 * @author PaymahnMoghadasian
	 * @date June 4 2013
	 */
	public void testSetValidUserMapping()
	{
		// valid mappings
		for(char ch1 = 'a'; ch1 <= 'z'; ch1++)
		{
			for(char ch2='a'; ch2 <= 'z'; ch2++)
			{
				//char versions
				this.setValidMapping(ch1, ch2);
				this.setValidMapping(ch1, Character.toUpperCase(ch2));
				this.setValidMapping(Character.toUpperCase(ch1), ch2);
				this.setValidMapping(Character.toUpperCase(ch1), Character.toUpperCase(ch2));
				this.setValidMapping('\0', ch2);
				
				//string versions
				this.setValidMapping("" + ch1,ch2);
				this.setValidMapping("" + ch1, Character.toUpperCase(ch2));
				this.setValidMapping("" + Character.toUpperCase(ch1), ch2);
				this.setValidMapping("" + Character.toUpperCase(ch1), Character.toUpperCase(ch2));
				this.setValidMapping("", ch2);
			}
		}
	}
	
	/**
	 * @author PaymahnMoghadasian
	 * @date June 4 2013
	 */
	public void testSetInvalidUserMappings()
	{
		for(char ch = 'a'; ch <= 'z'; ch++)
		{
			this.setInvalidMapping(ch, '0');
			this.setInvalidMapping(ch, '5');
			this.setInvalidMapping(ch, '9');
			this.setInvalidMapping('0', ch);
			this.setInvalidMapping('5', ch);
			this.setInvalidMapping('9', ch);
			
			this.setInvalidMapping(Character.toUpperCase(ch), '0');
			this.setInvalidMapping(Character.toUpperCase(ch), '5');
			this.setInvalidMapping(Character.toUpperCase(ch), '9');
			this.setInvalidMapping('0', Character.toUpperCase(ch));
			this.setInvalidMapping('5', Character.toUpperCase(ch));
			this.setInvalidMapping('9', Character.toUpperCase(ch));
			
			this.setInvalidMapping(ch, '.');
			this.setInvalidMapping(ch, '[');
			this.setInvalidMapping(ch, '!');
			this.setInvalidMapping('\f', ch);
			this.setInvalidMapping('\n', ch);
			this.setInvalidMapping(':', ch);
			
			this.setInvalidMapping(Character.toUpperCase(ch), '.');
			this.setInvalidMapping(Character.toUpperCase(ch), '[');
			this.setInvalidMapping(Character.toUpperCase(ch), '!');
			this.setInvalidMapping('*', Character.toUpperCase(ch));
			this.setInvalidMapping('\\', Character.toUpperCase(ch));
			this.setInvalidMapping(':', Character.toUpperCase(ch));		
		}
		
		this.setInvalidMapping('\0', ']');
	}

	/**
	 * Sets the plaintext of the cryptogram manager and asserts that it was correctly set
	 * @param plaintext The plaintext to set - assumed to be valid
	 * @param cipherChar The ciphertext to set for - assumed to be valid
	 * @author PaymahnMoghadasian
	 * @date June 4 2013
	 */
	private void setValidMapping(char plaintext, char cipherChar)
	{
		cm.setUserMapping(plaintext, cipherChar);
		if (plaintext == '\0')
		{
			assertEquals("", cm.getUserMapping(cipherChar));
		} 
		else
		{
			assertEquals(Character.toString(Character.toUpperCase(plaintext)),
					cm.getUserMapping(cipherChar));
		}
	}

	/**
	 * Sets the plaintext of the cryptogram manager and asserts that it was set correctly
	 * @param plaintext The text to set - assumed to be valid
	 * @param cipherChar the ciphertext to set for
	 */
	private void setValidMapping(String plaintext, char cipherChar)
	{
		cm.setUserMapping(plaintext, cipherChar);
		assertEquals(plaintext.toUpperCase(), cm.getUserMapping(cipherChar));
	}
	
	private void setInvalidMapping(char plaintext, char cipherChar)
	{
		//try the char version
		try
		{
			cm.setUserMapping(plaintext, cipherChar);
			fail();
		}
		catch(IllegalArgumentException e)
		{
			//expected
		}
		
		//try the string version
		try
		{
			cm.setUserMapping("" + plaintext, cipherChar);
			fail();
		}
		catch(IllegalArgumentException e)
		{
			//expected
		}
	}
	

}
