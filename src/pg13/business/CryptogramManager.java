package pg13.business;


import pg13.models.Cryptogram;

/**
 * @date May31st 2013
 *
 */
public class CryptogramManager
{

	Cryptogram cryptogram;

	public CryptogramManager()
	{
		this.cryptogram = new Cryptogram();
	}

	public CryptogramManager(Cryptogram cryptogram)
	{
		this.cryptogram = cryptogram;
	}

	public Cryptogram getCryptogram()
	{
		return cryptogram;
	}

	public void setPlaintext(String string)
	{
		this.validatePlaintext(string);
		this.cryptogram.setPlaintext(string);
	}

	public void validatePlaintext(String string)
	{
		if(string == null)
		{
			throw new IllegalArgumentException(String.format("PlainText for cryptogram cannot be null!"));
		}
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

	public String getPlaintext()
	{
		return this.cryptogram.getPlaintext();
	}

	/**
	 * Gets the user mapping for a cipher character
	 * @param cipherCharacter The cipher character for which we want the users' mapping
	 * @return A single uppercase letter string of the users mapping to the ciphercharacter. If there is not mapping, returns an empty string
	 */
	public String getUserMapping(char cipherCharacter)
	{
		String result = "";
		try
		{
			this.validateUserMapping(cipherCharacter);
		}
		catch(IllegalArgumentException iae)
		{
			return result;
		}

		char userChar = this.cryptogram.getUserPlaintextFromCiphertext(cipherCharacter);

		if(userChar != '\0')
		{
			result += userChar;
		}

		return result;

	}

	/**
	 * Updates a user mapping for a cryptogra. IE: map X to A where X is the character the user entered and A is the
	 * ciphertext
	 * @date June 1 2013
	 * @param plaintextChar The character that is being mapped
	 * @param ciphertextChar The character being mapped to
	 */
	public void setUserMapping(char plaintextChar, char ciphertextChar)
	{
		this.validateUserMapping(plaintextChar);
		this.validateUserMapping(ciphertextChar);
		this.cryptogram.setUserPlaintextForCiphertext(plaintextChar, ciphertextChar);
	}

	/**
	 * Validates a user mapping. Valid characters are those in the range of a-z or '\0'
	 * @param charToValidate The character to validate
	 * @date June 1 2013
	 */
	public void validateUserMapping(char charToValidate)
	{
		if(charToValidate != '\0' && "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(charToValidate) < 0)
		{
			throw new IllegalArgumentException(String.format("Illegal character %c", charToValidate));
		}
	}

	/**
	 * Validates a user mapping. Valid characters are those in the range of a-z or '\0'
	 * @param charToValidate Single character string or empty string to be validated.
	 * @date June 1 2013
	 */
	public void validateUserMapping(String charToValidate)
	{
		if(charToValidate.length() > 1)
		{
			throw new IllegalArgumentException(String.format("Illegal length. The string, %s, must of length 0 or 1", charToValidate));
		}
		if(charToValidate.length() == 0)
		{
			return;
		}
		this.validateUserMapping(charToValidate.toLowerCase().charAt(0));
	}

}
