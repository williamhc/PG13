package tests.persistence;

import java.util.ArrayList;
import java.util.Collections;

import pg13.app.PG13;
import pg13.models.Category;
import pg13.models.Cryptogram;
import pg13.models.Difficulty;
import pg13.models.Puzzle;
import pg13.models.PuzzleValidationException;
import pg13.models.User;
import pg13.persistence.DataAccess;

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
	public boolean savePuzzle(Puzzle puzzle)
	{
		try
		{
			puzzle.validate();
		} catch(PuzzleValidationException pve)
		{
			return false;
		}

		this.puzzles.add(puzzle);
		return true;
	}

	@Override
	public boolean deletePuzzle(long puzzleID)
	{
		Puzzle puz = this.findPuzzle(puzzleID);
		if (puz != null)
		{
			this.puzzles.remove(puz);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateDescription(long id, String newDescription)
	{
		Puzzle puz = this.findPuzzle(id);
		if (puz == null)
		{
			return false;
		}

		puz.setDescription(newDescription);
		return true;
	}

	@Override
	public boolean updateTitle(long id, String newTitle)
	{
		Puzzle puz = this.findPuzzle(id);
		if (puz == null)
		{
			return false;
		}

		puz.setTitle(newTitle);
		return true;
	}

	@Override
	public boolean updateCategory(long id, Category newCategory)
	{
		Puzzle puz = this.findPuzzle(id);
		if (puz == null)
		{
			return false;
		}

		puz.setCategory(newCategory);
		return true;
	}

	@Override
	public boolean updateDifficulty(long id, Difficulty newDifficulty)
	{
		Puzzle puz = this.findPuzzle(id);
		if (puz == null)
		{
			return false;
		}

		puz.setDifficulty(newDifficulty);
		return true;
	}

	@Override
	public boolean updatePlaintext(long id, String newPlaintext)
	{
		Puzzle puz = this.findPuzzle(id);
		if (puz == null)
		{
			return false;
		}

		((Cryptogram) puz).setPlaintext(newPlaintext);
		return true;
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
		for (int i = 0; i < this.users.size(); i++)
		{
			if (users.get(i).getPrimaryKey() == primaryKey)
				return this.users.get(i);
		}

		return null;
	}

	@Override
	public ArrayList<Long> getSortedUserPrimaryKeys()
	{
		ArrayList<Long> keys = new ArrayList<Long>();

		for (User user : this.users)
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

		for (Puzzle puzzle : this.puzzles)
		{
			keys.add(puzzle.getID());
		}

		Collections.sort(keys);
		return keys;
	}

	@Override
	public boolean saveUser(User user)
	{
		this.users.add(user);
		return true;
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
		puzzle = new Cryptogram(joe, "My first puzzle", "A puzzle",
				Category.Miscellaneous, Difficulty.Hard, "Fake!", 1);
		joe.addPuzzle(puzzle);
		puzzles.add(puzzle);
		puzzle = new Cryptogram(joe, "another puzzle", "Another puzzle",
				Category.Animals, Difficulty.Medium, "Butterflies", 2);
		joe.addPuzzle(puzzle);
		puzzles.add(puzzle);

	}

	@Override
	public void close()
	{
	}

	private Puzzle findPuzzle(long id)
	{

		for (Puzzle puz : this.puzzles)
		{
			if (puz.getID() == id)
			{
				return puz;
			}
		}
		return null;
	}

}
