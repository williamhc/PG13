package pg13.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
	private String result;
	private static String EOF = "  ";

	public DataAccessObject(String dbName)
	{
		this.dbName = dbName;
	}

	@Override
	public ArrayList<Puzzle> getAllPuzzles()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePuzzle(Puzzle puzzle)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getGuestPrimaryKey()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<User> getUsers()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUser(long primaryKey)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Long> getPrimaryKeys()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void open(String string)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveUser(User user)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getGuestName()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
