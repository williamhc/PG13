package pg13.models;

import java.util.Date;

public abstract class Puzzle implements IFindable, ICreateable, IPlayable
{
	private String title, description;
	private User user;
	private Category category;
	private Difficulty difficulty;
	private Date dateCreated;
	private boolean isCompleted;

	protected Puzzle()
	{
		this.user = new User();
		this.isCompleted = false;
		this.title = null;
		this.category = null;
		this.difficulty = null;
		this.dateCreated = null;
	}
	
	protected Puzzle(User user, String title, Category category, Difficulty difficulty, Date dateCreated)
	{
		this();
		this.user = user;
		this.isCompleted = false;
		this.title = title;
		this.dateCreated = dateCreated;
		this.difficulty = difficulty;
		this.category = category;
	}
	
	public boolean isCompleted() 
	{
		return this.isCompleted;
	}

	public void setIsCompleted(boolean value)
	{
		this.isCompleted = value;
	}

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

	public Date getDateCreated() 
	{
		return this.dateCreated;
		}

	public void setDateCreated(Date value) 
	{
		this.dateCreated = value;
	}
	
	public void startPlaying() 
	{
		//empty but required to be here because of interfaces
	}

	public void finishPlaying()
	{
		//empty but required to be here because of interfaces
	}

	public void create()
	{
		//empty but required to be here because of interfaces
	}

	public void validate()
	{
		//empty but required to be here because of interfaces
	}
}
