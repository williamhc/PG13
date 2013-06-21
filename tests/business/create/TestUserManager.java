package tests.business.create;

import pg13.business.create.UserManager;
import pg13.models.User;
import pg13.persistence.UserController;
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
		this.checkForImpossibleUsers();
		
		User user1 = new User("Paymahn Moghadasian");
		
		manager.addUser(user1);
		
		assertNotNull(manager.findUser(UserController.getGuestPrimaryKey()));
		assertEquals(user1, manager.findUser(user1));
		this.checkForImpossibleUsers();
	
	}
	
	private void checkForImpossibleUsers()
	{
		assertNull(manager.findUser(-1));
		assertNull(manager.findUser(0));
	}
}
