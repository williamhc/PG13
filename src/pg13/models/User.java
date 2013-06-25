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
		this.puzzles = new ArrayList<Puzzle>();
	}

	public User(String name)
	{
		this.primaryKey = (long) -1;
		this.userName = name;
		this.puzzles = new ArrayList<Puzzle>();
	}

	public User(Long primaryKey, String name)
	{
		this.primaryKey = primaryKey;
		this.userName = name;
		this.puzzles = new ArrayList<Puzzle>();
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

	public String getName()
	{
		return userName;
	}

	public void setName(String userName)
	{
		this.userName = userName;
	}

	public void addPuzzle(Puzzle puzzle)
	{
		this.puzzles.add(puzzle);
	}

	public void removePuzzle(int pos)
	{
		this.puzzles.remove(pos);
	}

	public boolean removePuzzle(Puzzle puzzle)
	{
		return this.puzzles.remove(puzzle);
	}
}
