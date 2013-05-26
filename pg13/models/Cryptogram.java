/**
 * 
 */
package pg13.models;

import java.util.Arrays;
import java.util.Date;

/**
 * Defines....I can't think of what to write here.
 * 
 * @author PaymahnMoghadasian
 * 
 */
public class Cryptogram extends Puzzle {

	char[] userMap; //map letters to other letters. For example, index zero will contain the letter that 'A' maps to, index 1 will contains 'B's mapping, etc...
	char[] solutionMap;
	
	/**
	 * Standard default constructor.
	 */
	public Cryptogram() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param author The author of this cryptogram
	 * @param title The title of this cryptogram
	 * @param dateCreated The data of this cryptogram
	 */
	public Cryptogram(String author, String title, Date dateCreated) {
		super(author, title, dateCreated);
		// TODO Auto-generated constructor stub
	}

	/**
	 * A constructor that accepts the solution and user mappings
	 * @param userMap The mapping of characters the user comes up with to solve the puzzle
	 * @param solutionMap The true mapping of characters needed to solve the puzzle
	 */
	public Cryptogram(char[] userMap, char[] solutionMap) {
		// TODO Auto-generated constructor stub
		this.userMap = userMap;
		this.solutionMap = solutionMap;
		
		char[] temp = new char[26];
		if(this.userMap != null && this.solutionMap != null && !Arrays.equals(this.userMap, temp) && !Arrays.equals(solutionMap, temp)
				&&Arrays.equals(this.userMap, this.solutionMap))
		{
			this.isCompleted = true;
		}
	}

	/**
	 * Get the mapping for the solution of the puzzle
	 * @return
	 */
	public char[] getSoultionMapping() {
		// TODO Auto-generated method stub
		return this.solutionMap;
	}

	/**
	 * Get the mapping the user has decided on (so far) to solve the puzzle
	 * @return The mapping of the user
	 */
	public char[] getUserMapping() {
		// TODO Auto-generated method stub
		return this.userMap;
	}
	
	
	
	

}
