package pg13.models;

public abstract class Puzzle
{
	private static final String DEFAULT_TITLE = "New Puzzle";
	private static final Category DEFAULT_CATEGORY = Category.Miscellaneous;
	private static final Difficulty DEFAULT_DIFFICULTY = Difficulty.Easy;
	private static final String DEFAULT_DESCRIPTION = "Describe your puzzle!";
	public static final long DEFAULT_ID = -1;

	private String title, description;
	private User user;
	private Category category;
	private Difficulty difficulty;
	private long id;

	protected Puzzle()
	{
		this.user = new User();
		this.title = DEFAULT_TITLE;
		this.category = DEFAULT_CATEGORY;
		this.difficulty = DEFAULT_DIFFICULTY;
		this.description = DEFAULT_DESCRIPTION;
		this.id = DEFAULT_ID;
	}

	protected Puzzle(User user, String title, String description,
			Category category, Difficulty difficulty)
	{
		this();
		this.user = user;
		this.title = title;
		this.description = description;
		this.difficulty = difficulty;
		this.category = category;
	}

	public abstract boolean isCompleted();

	public String getTitle()
	{
		return this.title;
	}

	public void setTitle(String value)
	{
		this.title = value;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public String getAuthor()
	{
		return this.user.getName();
	}

	public void setAuthor(String name)
	{
		this.user.setName(name);
	}

	public Category getCategory()
	{
		return category;
	}

	public void setCategory(Category category)
	{
		this.category = category;
	}

	public Difficulty getDifficulty()
	{
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty)
	{
		this.difficulty = difficulty;
	}

	public void validate() throws PuzzleValidationException
	{

	}

	public long getID()
	{
		return this.id;
	}

	public void setID(long id)
	{
		this.id = id;
	}

	public void prepareForSave()
	{
		// do any cleanup necessary before the puzzle can be saved
	}
}
