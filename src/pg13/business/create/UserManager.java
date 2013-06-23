package pg13.business.create;

import java.util.ArrayList;

import pg13.app.PG13;
import pg13.app.Services;
import pg13.models.User;
import pg13.persistence.DataAccess;

public class UserManager
{
	private DataAccess dataAccess;
	
	public UserManager()
	{
		dataAccess = Services.getDataAccess(PG13.dbName);
	}
	
	public User addUser(String name)
	{
		ArrayList<Long> keys = dataAccess.getSortedUserPrimaryKeys();
		long primaryKey = keys.get(keys.size() - 1).longValue() + 1;
		User user = new User(primaryKey, name);
		this.dataAccess.saveUser(user);
		return user;
	}
	
	public User findUser(User user)
	{
		return this.findUser(user.getPrimaryKey());
	}
	
	public User findUser(long primaryKey)
	{
		return this.dataAccess.findUser(primaryKey);
	}
	
	public String getNameOfUser(long primaryKey)
	{
		User user = this.findUser(primaryKey);
		
		return user.getName();
	}
	
	public ArrayList<String> getNameOfAllUsers()
	{
		ArrayList<User> users = this.dataAccess.getUsers();
		ArrayList<String> names = new ArrayList<String>();
		
		for(int i =0; i < users.size(); i++)
		{
			names.add(users.get(i).getName());
		}
		
		return names;
	}
	
	public long getGuestPrimaryKey()
	{
		return this.dataAccess.getGuestPrimaryKey();
	}
}
