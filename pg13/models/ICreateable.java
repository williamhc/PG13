package pg13.models;

/**
 * This interface should be implemented all objects that can be created. For instance, a word search can be created while a game of scrabble
 * can't. As of right now, this interface does not serve much of a purpose.
 * @author PaymahnMoghadasian
 *
 */
public interface ICreateable
{
	/**
	 * Create the object
	 */
	public void create();
	
	/**
	 * Validate the object. This should either be called before ICreateable.create() or called or at the beginning of ICreatable.create()
	 */
	public void validate();
}
