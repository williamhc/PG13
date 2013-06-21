package tests.business.create;

import pg13.business.create.UserManager;
import pg13.models.User;
import pg13.persistence.UserController;
import pg13.presentation.Constants;
import junit.framework.TestCase;

public class TestUserManager extends TestCase
{
	private UserManager manager;
	
	public void testFindingUsers()
	{
		manager = new UserManager();
		
		assertNotNull(manager.findUser(UserController.getGuestPrimaryKey()));
		assertNull(manager.findUser(UserController.getGuestPrimaryKey() + 1));
		assertNull(manager.findUser(UserController.getGuestPrimaryKey() - 1));
		this.checkForUnwantedUsers();
		
		User user1 = manager.addUser(Constants.AUTHOR);
		
		assertNotNull(manager.findUser(UserController.getGuestPrimaryKey()));
		assertEquals(user1, manager.findUser(user1));
		this.checkForUnwantedUsers();
		
		User user2 = manager.addUser("Another author");
		assertNotNull(manager.findUser(UserController.getGuestPrimaryKey()));
		assertEquals(user1, manager.findUser(user1));
		assertEquals(user2, manager.findUser(user2));
		this.checkForUnwantedUsers();
	
	}
	
	private void checkForUnwantedUsers()
	{
		assertNull(manager.findUser(-1));
		assertNull(manager.findUser(0));
	}
}
