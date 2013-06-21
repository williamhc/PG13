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
		assertEquals(1, manager.getNameOfAllUsers().size());
		this.checkForUnwantedUsers();
		
		User user1 = manager.addUser(Constants.AUTHOR);
		
		assertNotNull(manager.findUser(UserController.getGuestPrimaryKey()));
		assertEquals(user1, manager.findUser(user1));
		assertEquals(2, manager.getNameOfAllUsers().size());
		this.checkForUnwantedUsers();
		
		User user2 = manager.addUser("Another author");
		assertNotNull(manager.findUser(UserController.getGuestPrimaryKey()));
		assertEquals(user1, manager.findUser(user1));
		assertEquals(user2, manager.findUser(user2));
		assertEquals(3, manager.getNameOfAllUsers().size());
		this.checkForUnwantedUsers();
		
		assertEquals(Constants.AUTHOR, manager.getNameOfUser(user1.getPrimaryKey()));
		assertEquals("Another author", manager.getNameOfUser(user2.getPrimaryKey()));
	
		//test adding person with same name
		User user3 = manager.addUser(Constants.AUTHOR);
		assertNotNull(manager.findUser(UserController.getGuestPrimaryKey()));
		assertEquals(user1, manager.findUser(user1));
		assertEquals(user2, manager.findUser(user2));
		assertEquals(user3, manager.findUser(user3));
		assertEquals(4, manager.getNameOfAllUsers().size());
		this.checkForUnwantedUsers();
	}
	
	private void checkForUnwantedUsers()
	{
		assertNull(manager.findUser(-1));
		assertNull(manager.findUser(0));
	}
}
