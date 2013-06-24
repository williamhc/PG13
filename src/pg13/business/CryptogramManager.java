package pg13.business;


import pg13.models.Cryptogram;

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

	public void setUserMapping(char plaintextChar, char ciphertextChar)
	{
		//validates the mapping, then sets it
		this.validateUserMapping(plaintextChar);
		this.validateUserMapping(ciphertextChar);
		this.cryptogram.setUserPlaintextForCiphertext(plaintextChar, ciphertextChar);
	}

	public void validateUserMapping(char charToValidate)
	{
		if(charToValidate != '\0' && "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(charToValidate) < 0)
		{
			throw new IllegalArgumentException(String.format("Illegal character %c", charToValidate));
		}
	}

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
