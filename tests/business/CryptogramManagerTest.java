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
public class CryptogramManagerTest extends TestCase {
	private CryptogramManager cm;

	/**
	 * @date May 31 2013
	 * @author PaymahnMoghadasian
	 * 
	 *         Test the constructors in Cryptogram manager
	 */
	public void testConstructors() {
		Cryptogram cryptogram = new Cryptogram();

		this.cm = new CryptogramManager();
		assertNotNull(cm.getCryptogram());

		this.cm = new CryptogramManager(cryptogram);
		assertEquals(cryptogram, cm.getCryptogram());

		cryptogram = new Cryptogram("Someone's Name", "Random title",
				new Date(), "This is the plaintext");
		this.cm = new CryptogramManager(cryptogram);
		assertEquals(cryptogram, cm.getCryptogram());
	}

	/**
	 * @author PaymahnMoghadasian
	 * @date June 1 2013
	 */
	public void testValidPlaintext() {
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
	}

	private void setAndValidateValidPlainText(String plainText) {
		this.cm.setPlaintext(plainText);
		assertEquals(plainText, this.cm.getPlaintext());
	}
	
	/**
	 * @author PaymahnMoghadasian
	 * @date June 1 2013
	 * 
	 * I'm not quite sure what constitutes invalid plaintext for a cryptogram. Somemone should extend these tests.
	 */
	public void testInvalidPlaintext()
	{
		this.cm = new CryptogramManager();
		
		this.setAndValidateInvalidPlaintext("~");
	}
	
	private void setAndValidateInvalidPlaintext(String plaintext)
	{
		try
		{
			this.cm.setPlaintext(plaintext);
			fail();
		}catch(IllegalArgumentException e)
		{
			//expected
		}
	}

}
