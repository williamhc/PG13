package pg13.business;


import pg13.models.Cryptogram;

/**
 * @date May31st 2013
 * @author PaymahnMoghadasian
 *
 */
public class CryptogramManager {

	Cryptogram cryptogram;
	String plaintext;
	
	public CryptogramManager()
	{
		this.cryptogram = new Cryptogram();
	}

	public CryptogramManager(Cryptogram cryptogram) {
		this.cryptogram = cryptogram;
	}

	public Cryptogram getCryptogram() {
		return cryptogram;
	}

	public void setPlaintext(String string) {
		this.validatePlaintext(string);
		this.plaintext = string;
	}

	private void validatePlaintext(String string) {
		char[] characters = string.toCharArray();
		for (int i = 0; i < characters.length; i++)
		{
			char curr = characters[i];
			if ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890.,:;- !?()&#$%'\"".indexOf(curr) < 0)
			{
				throw new IllegalArgumentException("Invalid character in plaintext");
			}
		}		
	}

	public String getPlaintext() {
		return this.plaintext;
	}
}
