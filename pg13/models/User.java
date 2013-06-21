package pg13.models;

import java.util.ArrayList;

public class User 
{
	private static long nextID = 1;
	
	private Long primaryKey;
	private ArrayList<Puzzle> puzzles;
	private String name;
	
	public User()
	{
		this.primaryKey = nextID++;
		this.name = null;
		this.puzzles = new ArrayList<Puzzle> ();		
	}
	
	public User(String name)
	{
		this();
		this.name = name;
	}
	
	public User(String name, ArrayList<Puzzle> puzzles)
	{
		this(name);
		this.puzzles = puzzles;
	}
	
	public long getPrimaryKey()
	{
		return primaryKey;
	}
	
	public ArrayList<Puzzle> getPuzzles() 
	{
		return puzzles;
	}
	
	public void setPuzzles(ArrayList<Puzzle> puzzles) 
	{
		this.puzzles = puzzles;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String userName) 
	{
		this.name = userName;
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
