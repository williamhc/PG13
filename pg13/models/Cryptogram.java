/**
 * 
 */
package pg13.models;

import java.util.Date;

/**
 * Defines....I can't think of what to write here.
 * 
 * @author PaymahnMoghadasian
 * 
 */
public class Cryptogram extends Puzzle {

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

	public Object getSoultionMapping() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getUserMapping() {
		// TODO Auto-generated method stub
		return null;
	}

}
