package pg13.models;

/*
 * @author Lauren Slusky
 * @date May 28, 2013
 * Class used to house mapping between plain and cipher text characters
 */
public class CryptogramPair {

	private char plainc; // plain text character NOTE* this is the character that is used to index the array
	private char cipherc; // cipher text character
	
	public CryptogramPair(char plainc, char cipherc)
	{
		if(!isLetter(plainc) || !isLetter(cipherc))
		{
			throw new IllegalArgumentException();
		}
		this.plainc = plainc;
		this.cipherc = cipherc;
	}
	
	public char getPlainc() {
		return plainc;
	}
	
	public void setPlainc(char plainc) {
		this.plainc = plainc;
	}
	
	public char getCipherc() {
		return cipherc;
	}
	
	public void setCipherc(char cipherc) {
		this.cipherc = cipherc;
	}
	
	private boolean isLetter(char c)
	{
		return ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '\0');
	}
	
	/**
	 * @author PaymahnMoghadasian
	 * @date May 31 2013
	 * 
	 * @param object The other (hopefully) CryptogramPair to compare against
	 */
	public boolean equals(Object object)
	{
		if(!(object instanceof CryptogramPair))
			return false;
		
		CryptogramPair other = (CryptogramPair)object;
		
		return this.getPlainc() == other.getPlainc() && this.getCipherc() == other.getCipherc();
	}
}
