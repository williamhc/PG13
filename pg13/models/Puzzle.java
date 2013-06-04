package pg13.models;

import java.util.Date;

public abstract class Puzzle implements IFindable, ICreateable, IPlayable{
	private String title, description, author, category, difficulty;
	private Date dateCreated;
	private boolean isCompleted;

	protected Puzzle()
	{
		this.author = null;
		this.isCompleted = false;
		this.title = null;
		this.category = null;
		this.difficulty = null;
		this.dateCreated = null;
	}
	
	protected Puzzle(String author, String title, String category, String difficulty, Date dateCreated)
	{
		this();
		this.author = author;
		this.isCompleted = false;
		this.title = title;
		this.dateCreated = dateCreated;
		this.difficulty = difficulty;
		this.category = category;
	}
	
	public boolean isCompleted() {
		return this.isCompleted;
	}

	public void setIsCompleted(boolean value) {
		this.isCompleted = value;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String value) {
		this.author = value;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String value) {
		this.title = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public Date getDateCreated() {
		return this.dateCreated;
		}

	public void setDateCreated(Date value) {
		this.dateCreated = value;
	}
	
	public void startPlaying() {
		// TODO Auto-generated method stub
		
	}

	public void finishPlaying() {
		// TODO Auto-generated method stub
		
	}

	public void create() {
		// TODO Auto-generated method stub
		
	}

	public void validate() {
		// TODO Auto-generated method stub
		
	}
}
