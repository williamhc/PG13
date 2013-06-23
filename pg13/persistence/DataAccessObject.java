package pg13.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import pg13.models.Category;
import pg13.models.Cryptogram;
import pg13.models.Difficulty;
import pg13.models.Puzzle;
import pg13.models.User;

public class DataAccessObject implements DataAccess
{
	private Statement st1, st2, st3;
	private Connection c1;
	private ResultSet rs2, rs3, rs4, rs5;

	private String dbName;
	private String dbType;

	private ArrayList<User> users;
	private ArrayList<Puzzle> puzzles;

	private String cmdString;
	private int updateCount;

	private boolean beingBuilt;

	public DataAccessObject(String dbName)
	{
		System.out.println("creating dataaccessobject");
		this.dbName = dbName;
		this.beingBuilt = false;
	}

	@Override
	public ArrayList<Puzzle> getAllPuzzles()
	{
		System.out.println("Getting all puzzles");
		buildUsersAndPuzzles();
		return puzzles;
	}

	@Override
	public void savePuzzle(Puzzle puzzle)
	{
		String values;

		try
		{
			long id = puzzle.getID();
			String title = puzzle.getTitle();
			String description = puzzle.getDescription();
			String cat = puzzle.getCategory().toString();
			String dif = puzzle.getDifficulty().toString();
			String plain = ((Cryptogram) puzzle).getPlaintext();
			values = String.format("%d, '%s', '%s', '%s', '%s', '%s'",
					id, title, description, cat, dif, plain);
			cmdString = "Insert into Cryptograms Values(" + values + ")";
			System.out.println(cmdString);
			updateCount = st1.executeUpdate(cmdString);
			checkWarning(st1, updateCount);
		} catch (Exception e)
		{
			processSQLError(e);
		}
		try
		{
			values = puzzle.getUser().getPrimaryKey() + ", " + puzzle.getID();
			cmdString = "Insert into UserPuzzles Values(" + values + ")";
			System.out.println(cmdString);
			updateCount = st1.executeUpdate(cmdString);
			checkWarning(st1, updateCount);
		} catch (Exception e)
		{
			processSQLError(e);
		}

	}

	@Override
	public long getGuestPrimaryKey()
	{
		return DataAccess.GUEST_PRIMARY_KEY;
	}

	@Override
	public ArrayList<User> getUsers()
	{
		buildUsersAndPuzzles();
		return users;
	}

	private void buildUsersAndPuzzles()
	{
		if (!this.beingBuilt)
		{
			System.out.println("Building users and puzzles");
			this.beingBuilt = true;
			buildUsers();
			buildCryptograms();
			connectUsersAndPuzzles();
			this.beingBuilt = false;
		}
	}

	private void buildUsers()
	{
		
		System.out.println("Building users");
		User user;
		String name;
		long id;

		users = new ArrayList<User>();
		try
		{
			cmdString = "Select * from Users";
			rs2 = st1.executeQuery(cmdString);
		} catch (Exception e)
		{
			processSQLError(e);
		}
		try
		{
			while (rs2.next())
			{
				id = rs2.getLong("UserID");
				name = rs2.getString("Name");
				user = new User(id, name);
				users.add(user);
			}
			rs2.close();
		} catch (Exception e)
		{
			processSQLError(e);
		}
	}

	private void buildCryptograms()
	{
		System.out.println("Building cryptograms");
		
		Cryptogram cryptogram;

		long id;
		String title, description, plaintext;
		Category category;
		Difficulty difficulty;

		puzzles = new ArrayList<Puzzle>();
		try
		{
			cmdString = "Select * from Cryptograms";
			rs3 = st2.executeQuery(cmdString);
		} catch (Exception e)
		{
			processSQLError(e);
		}
		try
		{
			while (rs3.next())
			{
				title = rs3.getString("Title");
				id = rs3.getLong("PuzzleID");
				description = rs3.getString("Description");
				String temp = rs3.getString("Category");
				category = Category.valueOf(temp);
				difficulty = Difficulty.valueOf(rs3.getString("Difficulty"));
				plaintext = rs3.getString("Plaintext");
				cryptogram = new Cryptogram(null, title, category, difficulty, plaintext, id);
				puzzles.add(cryptogram);
			}
			rs3.close();
		} catch (Exception e)
		{
			processSQLError(e);
		}
	}

	private void connectUsersAndPuzzles()
	{
		long userID, puzzleID;
		User user;
		Puzzle puzzle;

		try
		{
			cmdString = "Select * from UserPuzzles";
			rs4 = st3.executeQuery(cmdString);
		} catch (Exception e)
		{
			processSQLError(e);
		}
		try
		{
			while (rs4.next())
			{
				userID = rs4.getLong("UserID");
				puzzleID = rs4.getLong("PuzzleID");
				user = this.findUser(userID);
				puzzle = this.findPuzzle(puzzleID);

				user.addPuzzle(puzzle);
				puzzle.setUser(user);
			}
			rs4.close();
		} catch (Exception e)
		{
			processSQLError(e);
		}
	}

	@Override
	public User findUser(long primaryKey)
	{
		buildUsersAndPuzzles();

		for (User user : this.users)
		{
			if (user.getPrimaryKey() == primaryKey)
			{
				return user;
			}
		}

		return null;
	}

	private Puzzle findPuzzle(long puzzleID)
	{
		buildUsersAndPuzzles();

		for (Puzzle puzzle : this.puzzles)
		{
			if (puzzle.getID() == puzzleID)
			{
				return puzzle;
			}
		}

		return null;
	}

	@Override
	public ArrayList<Long> getSortedUserPrimaryKeys()
	{
		buildUsersAndPuzzles();
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
		buildUsersAndPuzzles();
		ArrayList<Long> keys = new ArrayList<Long>();
		
		for(Puzzle puzzle: this.puzzles)
		{
			keys.add(puzzle.getID());
		}
		Collections.sort(keys);
		return keys;
	}

	@Override
	public void open(String string)
	{
		String url;
		try
		{
			// Setup for HSQL
			dbType = "HSQL";
			Class.forName("org.hsqldb.jdbcDriver").newInstance();
			url = "jdbc:hsqldb:database/" + dbName; // stored on disk mode
			c1 = DriverManager.getConnection(url, "SA", "");
			st1 = c1.createStatement();
			st2 = c1.createStatement();
			st3 = c1.createStatement();
		} catch (Exception e)
		{
			processSQLError(e);
		}
		System.out.println("Opened " + dbType + " database " + dbName);
	}

	@Override
	public void close()
	{
		try
		{ // commit all changes to the database
			cmdString = "shutdown compact";
			rs2 = st1.executeQuery(cmdString);
			c1.close();
		} catch (Exception e)
		{
			processSQLError(e);
		}
		System.out.println("Closed " + dbType + " database " + dbName);
	}

	@Override
	public void saveUser(User user)
	{
		String values;

		try
		{
			values = String.format("%d, %s", user.getPrimaryKey(),
					user.getName());
			cmdString = "Insert into Users Values(" + values + ")";
			System.out.println(cmdString);
			updateCount = st1.executeUpdate(cmdString);
			checkWarning(st1, updateCount);
		} catch (Exception e)
		{
			processSQLError(e);
		}
		try
		{
			for (Puzzle puzzle : user.getPuzzles())
			{
				values = user.getPrimaryKey() + ", "
						+ puzzle.getID();
				cmdString = "Insert into UserPuzzles Values(" + values + ")";
				System.out.println(cmdString);
				updateCount = st1.executeUpdate(cmdString);
				checkWarning(st1, updateCount);
			}
		} catch (Exception e)
		{
			processSQLError(e);
		}
	}

	@Override
	public String getGuestName()
	{
		return DataAccess.GUEST_NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see srsys.persistence.DataAccess2#checkWarning(java.sql.Statement, int)
	 */
	public String checkWarning(Statement st, int updateCount)
	{
		String result;

		result = null;
		try
		{
			SQLWarning warning = st.getWarnings();
			if (warning != null)
			{
				result = warning.getMessage();
			}
		} catch (Exception e)
		{
			result = processSQLError(e);
		}
		if (updateCount != 1)
		{
			result = "Tuple not inserted correctly.";
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see srsys.persistence.DataAccess2#processSQLError(java.lang.Exception)
	 */
	public String processSQLError(Exception e)
	{
		String result;
		result = "*** SQL Error: " + e.getMessage();
		// e.printStackTrace();
		System.out.println(result);
		return result;
	}
}
