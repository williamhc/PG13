package pg13.persistence;

import java.util.ArrayList;

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
