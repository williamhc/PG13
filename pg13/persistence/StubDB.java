package pg13.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import pg13.app.PG13;
import pg13.models.Category;
import pg13.models.Cryptogram;
import pg13.models.Difficulty;
import pg13.models.Puzzle;
import pg13.models.User;

public class StubDB implements DataAccess
{
	private String dbName;
	private String dbType = "stub";

	private ArrayList<Puzzle> puzzles;
	private ArrayList<User> users;
	
	public StubDB(String dbName)
	{
		this.dbName = dbName;
	}

	public StubDB()
	{
		this(PG13.dbName);
	}
	
	
	@Override
	public ArrayList<Puzzle> getAllPuzzles()
	{
		return this.puzzles;
	}

	@Override
	public void savePuzzle(Puzzle puzzle)
	{
		this.puzzles.add(puzzle);
	}

	@Override
	public long getGuestPrimaryKey()
	{
		return DataAccess.GUEST_PRIMARY_KEY;
	}
	
	@Override
	public String getGuestName()
	{
		return DataAccess.GUEST_NAME;
	}

	@Override
	public ArrayList<User> getUsers()
	{
		return this.users;
	}

	@Override
	public User findUser(long primaryKey)
	{
		for(int i =0; i < this.users.size(); i++)
		{
			if(users.get(i).getPrimaryKey() == primaryKey)
				return this.users.get(i);
		}
		
		return null;
	}

	@Override
	public ArrayList<Long> getSortedUserPrimaryKeys()
	{
ArrayList<Long> keys = new ArrayList<Long>();
		
		for(User user: this.users)
		{
			keys.add(user.getPrimaryKey());
		}
		
		Collections.sort(keys);
		return keys;
	}
	
	@Override
	public ArrayList<Long> getSortedPuzzleIDs()
	{
ArrayList<Long> keys = new ArrayList<Long>();
		
		for(Puzzle puzzle: this.puzzles)
		{
			keys.add(puzzle.getID());
		}
		
		Collections.sort(keys);
		return keys;
	}

	@Override
	public void saveUser(User user)
	{
		this.users.add(user);
	}
	
	@Override
	public void open(String string)
	{
		Puzzle puzzle;
		
		long guestKey = this.getGuestPrimaryKey();
		
		users = new ArrayList<User>();
		User guest = new User(guestKey, this.getGuestName());
		users.add(guest);
		User joe = new User(guestKey + 1, "Joe");
		users.add(joe);
		
		puzzles = new ArrayList<Puzzle>();
		puzzle = new Cryptogram(joe, "My first puzzle", Category.Miscellaneous, Difficulty.Hard, "Fake!", 1);
		joe.addPuzzle(puzzle);
		puzzles.add(puzzle);
		puzzle = new Cryptogram(joe, "another puzzle", Category.Animals, Difficulty.Medium, "Butterflies", 2);
		joe.addPuzzle(puzzle);
		puzzles.add(puzzle);
	
		System.out.println("Opened " +dbType +" database " +dbName);

	}

	@Override
	public void close()
	{
		System.out.println("Closed " +dbType +" database " +dbName);
	}

}
