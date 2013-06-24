package pg13.models;

public class PuzzleValidationException extends Exception
{
	static final long serialVersionUID = 1337L;

	public PuzzleValidationException(String message)
	{
		super(message);
	}
}
