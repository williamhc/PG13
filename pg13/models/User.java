package pg13.models;

import java.util.ArrayList;

public class User 
{
	private Long primaryKey;
	private ArrayList<Puzzle> puzzles;
	private String userName;
	
	public User()
	{
		this.primaryKey = (long) -1;
		this.userName = null;
		this.puzzles = new ArrayList<Puzzle> ();		
	}
	
	public User(String name)
	{
		this.primaryKey = (long) -1;
		this.userName = name;
		this.puzzles = new ArrayList<Puzzle> ();				
	}
	
	public User(Long primaryKey, String name)
	{
		this.primaryKey = primaryKey;
		this.userName = name;
		this.puzzles = new ArrayList<Puzzle> ();
	}
	
	public User(Long primaryKey, String name, ArrayList<Puzzle> puzzles)
	{
		this.primaryKey = primaryKey;
		this.userName = name;
		this.puzzles = puzzles;		
	}
	
	public Long getPrimaryKey()
	{
		return primaryKey;
	}
	
	public void setPrimaryKey(Long primaryKey)
	{
		this.primaryKey = primaryKey;
	}
	
	public ArrayList<Puzzle> getPuzzles() 
	{
		return puzzles;
	}
	
	public void setPuzzles(ArrayList<Puzzle> puzzles) 
	{
		this.puzzles = puzzles;
	}
	
	public String getUserName() 
	{
		return userName;
	}
	
	public void setUserName(String userName) 
	{
		this.userName = userName;
	}
	
	/**
	 * @author Lauren Slusky
	 * @date June 19 2013
	 * 
	 * @param add a puzzle to the users list
	 */
	public void addPuzzle(Puzzle puzzle)
	{
		this.puzzles.add(puzzle);
	}
	
	/**
	 * @author Lauren Slusky
	 * @date June 19 2013
	 * 
	 * @param remove a puzzle by position in the list
	 */
	public void removePuzzle(int pos)
	{
		this.puzzles.remove(pos);
	}
	
	/**
	 * @author Lauren Slusky
	 * @date June 19 2013
	 * 
	 * @param remove a puzzle by object
	 * @return true is puzzle is found and removed, false otherwise
	 */
	public boolean removePuzzle(Puzzle puzzle)
	{
		return this.puzzles.remove(puzzle);
	}
}
