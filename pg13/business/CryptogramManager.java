package pg13.business;


import pg13.models.Cryptogram;

/**
 * @date May31st 2013
 * @author PaymahnMoghadasian
 *
 */
public class CryptogramManager {

	Cryptogram cryptogram;
	
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
		this.cryptogram.setPlaintext(string);
	}

	public void validatePlaintext(String string) {
		char[] characters = string.toCharArray();
		for (int i = 0; i < characters.length; i++)
		{
			char curr = characters[i];
			if ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890.,:;- !?()&#$%'\"".indexOf(curr) < 0)
			{
				throw new IllegalArgumentException(String.format("Invalid character (%c) in plaintext", curr));
			}
		}		
	}

	public String getPlaintext() {
		return this.cryptogram.getPlaintext();
	}
	
	/**
	 * 
	 * @param cipherCharacter The cipher character for which we want the users' mapping
	 * @return A single uppercase letter string of the users mapping to the ciphercharacter. If there is not mapping, returns an empty string
	 */
	public String getUserCharForCipherChar(char cipherCharacter)
	{
		String result = "";
		char userChar = this.cryptogram.getUserPlaintextFromCiphertext(cipherCharacter);
		
		if(userChar != '\0')
		{
			result += userChar;
		}
		
		return result;
		
	}
	
	public void setUserCharForUserChar(char plaintextChar, char ciphertextChar)
	{
		this.cryptogram.setUserPlaintextForCiphertext(plaintextChar, ciphertextChar);
	}
	
	public void setUserCharForUserChar(String plaintextChar, char ciphertextChar)
	{
		if(plaintextChar.length() > 0)
		{
			this.setUserCharForUserChar(plaintextChar.charAt(0), ciphertextChar);
		}
		else
		{
			this.setUserCharForUserChar('\0', ciphertextChar);
		}
	}
	
	public void validateUserCharForCipherChar(char charToValidate)
	{
		if(charToValidate != '\0' && "abcdefghijklmnopqrstuvwxyz".indexOf(charToValidate) < 0)
		{
			throw new IllegalArgumentException(String.format("Illegal character %c", charToValidate));
		}
	}
	
	public void validateUserCharForCipherChar(String charToValidate)
	{
		if(charToValidate.length() > 1)
		{
			throw new IllegalArgumentException(String.format("Illegal length. The string, %s, must of length 0 or 1", charToValidate));
		}
		if(charToValidate.length() == 0)
		{
			return;
		}
		this.validateUserCharForCipherChar(charToValidate.toLowerCase().charAt(0));
	}

}
