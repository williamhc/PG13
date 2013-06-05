package tests.business;

import java.util.Date;

import pg13.business.CryptogramManager;
import pg13.models.Cryptogram;
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
				"Hard", new Date(), "This is the plaintext");
		this.cm = new CryptogramManager(cryptogram);
		assertEquals(cryptogram, cm.getCryptogram());
	}

	/**
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
	 * @author PaymahnMoghadasian
	 * @date June 1 2013
	 * 
	 *       I'm not quite sure what constitutes invalid plaintext for a
	 *       cryptogram. Somemone should extend these tests.
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
		} catch (IllegalArgumentException e)
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
	public void testGetUserCharForCipherChar()
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
				} else
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
				} else if (plaintext.charAt(i) == 'e')
				{
					assertEquals("E", userChar);
				} else
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
				cm.getCryptogram().setUserPlaintextForCiphertext(
						plaintext.charAt(i), cipherText.charAt(i));
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
	 * @author PaymahnMoghadasian
	 * @date June 4 2013
	 */
	public void testValidatePlaintext()
	{
		//we expect that none of these will raise an exception
		cm.validatePlaintext("a");
		cm.validatePlaintext("g");
		cm.validatePlaintext("abcdefghijklmnopqrstuvwxyz");
		cm.validatePlaintext("L");
		cm.validatePlaintext("Z");
		cm.validatePlaintext("A!");
		cm.validatePlaintext("AAAAAAABBBBBCCCCCCCC");
		cm.validatePlaintext("abcdefgHIJKL");
		cm.validatePlaintext("A b c d some phrase");
		cm.validatePlaintext("A large bear appeared!");
		cm.validatePlaintext("a!!!!!!??!?!");
		cm.validatePlaintext("A large & vicious animal!   Attack? ATTACK! ...");
		cm.validatePlaintext("");
		
		//all of these will raise an exception
		this.invalidPlaintext("~");
		this.invalidPlaintext("mid`dle");
		this.invalidPlaintext("~begin");
		this.invalidPlaintext("end~");
		this.invalidPlaintext("test invalid with spaces~");
		this.invalidPlaintext("~~~~~~~~~~~~~~~~`````````````");
		this.invalidPlaintext("[");
		this.invalidPlaintext("}");
		this.invalidPlaintext("\\");
	}
	
	private void invalidPlaintext(String plaintext)
	{
		try
		
		{
			cm.validatePlaintext(plaintext);
			fail();
		}
		catch(IllegalArgumentException e)
		{
			//expected
		}
	}

}
