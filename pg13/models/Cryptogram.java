package pg13.models;

import java.util.Date;

/*
 *  @author Lauren Slusky
 *  @date May 26 2013
 *  @title Cryptogram Class
 *  @description Class defining 
 */
public class Cryptogram extends Puzzle
{
	private String cipherText;	// Coded string
	private String plainText;	// the original user string
	
	
	/*
	 *  @author Lauren Slusky
	 *  @date May 26 2013
	 *  @title Cryptogram Constructor
	 */
	public Cryptogram()
	{
		super();
		this.setCipherText("");
		this.setPlainText("");
	}
	
	/*
	 *  @author Lauren Slusky
	 *  @date May 26 2013
	 *  @title Cryptogram Constructor
	 *  @param String author - whoever designed the cryptogram
	 *  @param String title - name of cryptogram
	 *  @param Date dateCreated - date of creation of puzzle
	 *  @param String ct - ciphertext for given plaintext
	 *  @param String pt - plaintext given by the user for the puzzle
	 */ 
	public Cryptogram(String author, String title, Date dateCreated, String ct, String pt)
	{
		super(author, title, dateCreated);
		this.setCipherText(ct);
		this.setPlainText(pt);
	}

	/*
	 *  @author Lauren Slusky
	 *  @date May 26 2013
	 *  @title getCipherText
	 *  @return String cipherText
	 */
	public String getCipherText() {
		return cipherText;
	}

	/*
	 *  @author Lauren Slusky
	 *  @date May 26 2013
	 *  @title setCipherText
	 *  @param String cipherText to set for a given puzzle
	 */
	public void setCipherText(String cipherText) {
		this.cipherText = cipherText;
	}

	/*
	 *  @author Lauren Slusky
	 *  @date May 26 2013
	 *  @title getPlainText
	 *  @return plaintext for a puzzle
	 */
	public String getPlainText() {
		return plainText;
	}

	/*
	 *  @author Lauren Slusky
	 *  @date May 26 2013
	 *  @title setPlainText
	 *  @return sets plaintext for a puzzle
	 */
	public void setPlainText(String plainText) {
		this.plainText = plainText;
	}

	
	/*
	 *  @author Lauren Slusky
	 *  @date May 26 2013
	 *  @title setPlainText
	 *  @return true if ciphertext and plaintext are the same (solution for cryptogram found)
	 *   and false if there are not yet the same
	 */	
	public boolean isCompleted()
	{
		return this.cipherText.equals(this.plainText);
	}
}
