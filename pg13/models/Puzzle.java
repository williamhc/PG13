package pg13.models;

import java.util.Date;

public abstract class Puzzle implements IFindable, ICreateable, IPlayable{
	String title, author;
	Date dateCreated;
	boolean isCompleted;

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
		this.title = title;
		this.dateCreated = dateCreated;
	}
	
	@Override
	public boolean getIsCompleted() {
		return this.isCompleted;
	}

	@Override
	public void setIsCompleted(boolean value) {
		this.isCompleted = value;
	}

	@Override
	public String getAuthor() {
		return this.author;
	}

	@Override
	public void setAuthor(String value) {
		this.author = value;
	}

	@Override
	public String getTitle() {
		return this.title;
	}

	@Override
	public void setTitle(String value) {
		this.title = value;
	}

	@Override
	public Date getDateCreated() {
		return this.dateCreated;
		}

	@Override
	public void setDateCreated(Date value) {
		this.dateCreated = value;
	}
	
	@Override
	public void startPlaying() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finishPlaying() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validate() {
		// TODO Auto-generated method stub
		
	}
}
