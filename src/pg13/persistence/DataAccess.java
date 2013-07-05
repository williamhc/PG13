package pg13.persistence;

import java.util.ArrayList;

import pg13.models.Category;
import pg13.models.Difficulty;
import pg13.models.Puzzle;
import pg13.models.User;

public interface DataAccess
{
	public static final long GUEST_PRIMARY_KEY = 1;
	public static final String GUEST_NAME = "Guest";
	public static final int NUM_INITIAL_USERS = 2;

	public ArrayList<Puzzle> getAllPuzzles();

	public boolean savePuzzle(Puzzle puzzle);
	
	public boolean deletePuzzle(long puzzleID);
	
	public boolean updateDescription(long id, String newDescription);
	
	public boolean updateTitle(long id, String newTitle);
	
	public boolean updateCategory(long id, Category newCategory);
	
	public boolean updateDifficulty(long id, Difficulty newDifficulty);
	
	public boolean updatePlaintext(long id, String newPlaintext);

	public ArrayList<Long> getSortedPuzzleIDs();
	
	public long getGuestPrimaryKey();

	public String getGuestName();

	public ArrayList<User> getUsers();

	public boolean saveUser(User user);

	public User findUser(long primaryKey);

	public ArrayList<Long> getSortedUserPrimaryKeys();


	public void open(String string);

	public void close();
}
