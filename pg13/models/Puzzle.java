package pg13.models;

import java.util.Date;

public abstract class Puzzle implements IFindable, ICreateable, IPlayable{
	private String title, author;
	private Date dateCreated;
	private boolean isCompleted;

	protected Puzzle()
	{
		this.author = null;
		this.isCompleted = false;
		this.title = null;
		this.dateCreated = null;
	}
	
	protected Puzzle(String author, String title, Date dateCreated)
	{
		this();
		this.author = author;
		this.isCompleted = false;
		this.title = title;
		this.dateCreated = dateCreated;
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
