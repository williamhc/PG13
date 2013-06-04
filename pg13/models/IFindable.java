package pg13.models;

import java.util.Date;

/**
 * This interface defines severael getters and setters necessary for objects to implement if we want them to be found
 * when scanning the database (we think). As of right now this does not serve much of a purpose.
 * @author PaymahnMoghadasian
 *
 */
public interface IFindable {
	
	/**
	 * Get the author of the object
	 * @return The name of the author
	 */
	public String getAuthor();
	
	/**
	 * Set the author of this object
	 * @param value The new author of the object
	 */
	public void setAuthor(String value);
	
	/**
	 * Get the title of this object
	 * @return The title of the object
	 */
	public String getTitle();
	
	/**
	 * Set the title of the object
	 * @param value The new title of the object
	 */
	public void setTitle(String value);
	
	/**
	 * Get the category of this object
	 * @return The category of the object
	 */
	public String getCategory();

	/**
	 * Set the category of the object
	 * @param value The new category of the object
	 */
	public void setCategory(String value);

	/**
	 * Get the difficulty of this object
	 * @return The difficulty of the object
	 */
	public String getDifficulty();

	/**
	 * Set the difficulty of the object
	 * @param value The new difficulty of the object
	 */
	public void setDifficulty(String value);

	/**
	 * Get the creation date of the object
	 * @return The creation date of the object
	 */
	public Date getDateCreated();
	
	/**
	 * Set the creation date of the object
	 * @param value The new creation date of the object
	 */
	public void setDateCreated(Date value);
	

}
