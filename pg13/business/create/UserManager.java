package pg13.business.create;

import java.util.ArrayList;

import pg13.models.User;
import pg13.persistence.UserController;

public class UserManager
{
	private UserController db;
	
	public UserManager()
	{
		this.db = UserController.getInstance();
	}
	
	public void addUser(User user)
	{
		this.db.persist(user);
	}
	
	public User findUser(User user)
	{
		return this.findUser(user.getPrimaryKey());
	}
	
	public User findUser(long primaryKey)
	{
		return this.db.findUser(primaryKey);
	}
	
	public String getNameOfUser(long primaryKey)
	{
		User user = this.findUser(primaryKey);
		
		return user.getName();
	}
	
	public ArrayList<String> getNameOfAllUsers()
	{
		ArrayList<User> users = this.db.getUsers();
		ArrayList<String> names = new ArrayList<String>();
		
		for(int i =0; i < users.size(); i++)
		{
			names.add(users.get(i).getName());
		}
		
		return names;
	}
}
