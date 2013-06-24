package pg13.models;

public class CryptogramPair
{

	private char plainc; // plain text character NOTE* this is the character
							// that is used to index the array
	private char cipherc; // cipher text character

	public CryptogramPair(char plainc, char cipherc)
	{
		if (!isLetter(plainc) || !isLetter(cipherc))
		{
			throw new IllegalArgumentException();
		}
		this.plainc = plainc;
		this.cipherc = cipherc;
	}

	public char getPlainc()
	{
		return plainc;
	}

	public void setPlainc(char plainc)
	{
		if (!isLetter(plainc))
		{
			throw new IllegalArgumentException("Invalid plaintext character");
		}
		this.plainc = plainc;
	}

	public char getCipherc()
	{
		return cipherc;
	}

	public void setCipherc(char cipherc)
	{
		if (!isLetter(cipherc))
		{
			throw new IllegalArgumentException("Invalid ciphertext character");
		}
		this.cipherc = cipherc;
	}

	private boolean isLetter(char c)
	{
		return ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '\0');
	}

	public boolean equals(Object object)
	{
		if (!(object instanceof CryptogramPair))
			return false;

		CryptogramPair other = (CryptogramPair) object;

		return this.getPlainc() == other.getPlainc()
				&& this.getCipherc() == other.getCipherc();
	}
}
