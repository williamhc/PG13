package pg13.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/*
 *  @author Lauren Slusky
 *  @date May 26 2013
 *  @title Cryptogram
 *  @description Class defining cryptograms
 */
public class Cryptogram extends Puzzle
{
	private String ciphertext;	// Coded string
	private String plaintext;	// the original user string
	private HashMap<Character, Character> decryptMap;
	
	
	/*
	 *  @author Lauren Slusky
	 *  @date May 26 2013
	 *  @title Cryptogram Constructor
	 */
	public Cryptogram()
	{
		super();
		this.ciphertext = "";
		this.plaintext = "";
		this.decryptMap = new HashMap<Character, Character> ();
	}
	
	/*
	 *  @author Lauren Slusky
	 *  @date May 26 2013
	 *  @title Cryptogram Constructor
	 *  @param String author - whoever designed the cryptogram
	 *  @param String title - name of cryptogram
	 *  @param Date dateCreated - date of creation of puzzle
	 *  @param String pt - plaintext given by the user for the puzzle
	 */ 
	public Cryptogram(String author, String title, Date dateCreated, String plaintext)
	{
		super(author, title, dateCreated);
		this.decryptMap = new HashMap<Character, Character> ();
		this.ciphertext = encrypt(plaintext);
		this.plaintext = plaintext;	
	}

	/*
	 *  @author Lauren Slusky
	 *  @date May 26 2013
	 *  @title getCipherText
	 *  @return String cipherText
	 */
	public String getCiphertext() {
		return ciphertext;
	}

	/*
	 *  @author Lauren Slusky
	 *  @date May 26 2013
	 *  @title setCipherText
	 *  @param String cipherText to set for a given puzzle
	 */
	public void setCiphertext(String ciphertext) {
		this.ciphertext = ciphertext;
	}

	/*
	 *  @author Lauren Slusky
	 *  @date May 26 2013
	 *  @title getPlainText
	 *  @return plaintext for a puzzle
	 */
	public String getPlaintext() {
		return plaintext;
	}

	/*
	 *  @author Lauren Slusky
	 *  @date May 26 2013
	 *  @title setPlainText
	 *  @return sets plaintext for a puzzle
	 */
	public void setPlaintext(String plaintext) {
		this.plaintext = plaintext;
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
		return this.ciphertext.equalsIgnoreCase(this.plaintext);
	}

	/*
	 *  @author Lauren Slusky
	 *  @date May 26 2013
	 *  @title encrypt
	 *  @return generates random ciphertext based on plain text
	 */	
	private String encrypt(String plain)
	{
		char temp;
		String ciphertext = "";;
		HashMap<Character, Character> mapping = generateMappingKeys();
		
		if(plain != null && plain.length() > 0)
		{
			plain = plain.toUpperCase();
			for(int i = 0; i < plain.length(); i++)
			{
				temp = plain.charAt(i);	
				
				// if the character is between A - Z, concat it's mapping on to the ciphertext
				if(temp >= 'A' && temp <= 'Z')
				{
					ciphertext += mapping.get(temp);
				}
				//else it is punctuation or a number so we want to map it to itself
				else
				{
					ciphertext += temp;
				}
				
			}
		}
		return ciphertext;
	}

	/*
	 *  @author Lauren Slusky
	 *  @date May 26 2013
	 *  @title generateAlphabet
	 *  @return generates an arraylist with the alphabet in it 
	 */	
	private ArrayList<Character> generateAlphabet() {
		ArrayList <Character> key =  new ArrayList<Character> ();
		
		key.add('A');
		key.add('B');
		key.add('C');
		key.add('D');
		key.add('E');
		key.add('F');
		key.add('G');
		key.add('H');
		key.add('I');
		key.add('J');
		key.add('K');
		key.add('L');
		key.add('M');
		key.add('N');
		key.add('O');
		key.add('P');
		key.add('Q');
		key.add('R');
		key.add('S');
		key.add('T');
		key.add('U');
		key.add('V');
		key.add('W');
		key.add('X');
		key.add('Y');
		key.add('Z');
		
		return key;
	}

	/*
	 *  @author Lauren Slusky
	 *  @date May 26 2013
	 *  @title generated
	 *  @return randomized one-to-one mapping each time it is called of letters
	 */	
	private HashMap<Character, Character> generateMappingKeys() {
		
		int alphabetIndex = 0;
		ArrayList<Character> toMap = generateAlphabet();
		ArrayList<Character> keys = generateAlphabet();
		HashMap<Character, Character> map = new HashMap<Character, Character> ();
		
		for(int i = 0; i < keys.size(); i++)
		{
			//Generate a random number that is between 0 - toMap size
			alphabetIndex = (int) (Math.random() * toMap.size());
			
			if(keys.get(i) != toMap.get(alphabetIndex))
			{
				//Put a mapping between the next alphabet letter and the random one from toMap
				map.put(keys.get(i), toMap.get(alphabetIndex));
				
				// Make sure we store the mapping we used to encrypt so 
				// this can be decrypted properly later
				// this is why we store the Code char as the key not the value in the
				// class version but reverse the for the above instance version
				this.decryptMap.put(toMap.get(alphabetIndex), keys.get(i));
				
				//Delete that letter as an option from toMap
				toMap.remove(alphabetIndex);
			}
		}
				
	
		return map;
	}

	/*
	 *  @author Lauren Slusky
	 *  @date May 26 2013
	 *  @title decrypt
	 *  @return decodes messages based on mapping and cipher text
	 */	
	public String decrypt() {
		String result = "";
		char temp;

		for(int i = 0; i < this.ciphertext.length(); i++)
		{
			temp = this.ciphertext.charAt(i);	
			
			// if the character is between A - Z, concat it's mapping on to the ciphertext
			if(temp >= 'A' && temp <= 'Z')
			{
				result += this.decryptMap.get(temp);
			}
			//else it is punctuation or a number so we want to map it to itself
			else
			{
				result += temp;
			}
			
		}
		return result;
	}
}
