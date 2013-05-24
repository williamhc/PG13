package pg13.models;

/**
 * This interface should be implemented by anything that can be played. Games are a good example of things that can be played.
 * @author PaymahnMoghadasian
 *
 */
public interface IPlayable {
	/**
	 * Get the completion state of the object (either it's completed or not)
	 * @return The state of puzzle (whether it's completed or not)
	 */
	public boolean getIsCompleted();
	
	/**
	 * Set the completion state of the object (either it's completed or not)
	 * @param value The new completion state
	 */
	public void setIsCompleted(boolean value);
	
	/**
	 * Start playing. Not sure what else to write here; I feel that the method is self documented
	 */
	public void startPlaying();
	
	/**
	 * Finish playing. Not sure what else to write here; I feel that the method is self documented
	 */
	public void finishPlaying();
}
