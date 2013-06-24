package tests.business.create;

import javax.xml.crypto.Data;

import pg13.app.PG13;
import pg13.app.Services;
import pg13.business.create.UserManager;
import pg13.models.User;
import pg13.persistence.DataAccess;
import pg13.persistence.StubDB;
import pg13.presentation.Constants;
import junit.framework.TestCase;

public class TestUserManager extends TestCase
{
	private UserManager manager;
	
	public void testFindingUsers()
	{
		Services.createDataAccess(new StubDB(PG13.dbName));
		manager = new UserManager();
		
		assertNotNull(manager.findUser(DataAccess.GUEST_PRIMARY_KEY));
		
		for(int i = 2; i <= DataAccess.NUM_INITIAL_USERS; i++)
			assertNotNull(manager.findUser(i));
		
		assertNull(manager.findUser(DataAccess.GUEST_PRIMARY_KEY - 1));
		assertEquals(DataAccess.NUM_INITIAL_USERS, manager.getNamesOfAllUsers().size());
		this.checkForUnwantedUsers();
		
		User user1 = manager.addUser(Constants.AUTHOR);
		
		assertNotNull(manager.findUser(DataAccess.GUEST_PRIMARY_KEY));
		assertEquals(user1, manager.findUser(user1));
		assertEquals(DataAccess.NUM_INITIAL_USERS + 1, manager.getNamesOfAllUsers().size());
		this.checkForUnwantedUsers();
		
		User user2 = manager.addUser("Another author");
		assertNotNull(manager.findUser(DataAccess.GUEST_PRIMARY_KEY));
		assertEquals(user1, manager.findUser(user1));
		assertEquals(user2, manager.findUser(user2));
		assertEquals(DataAccess.NUM_INITIAL_USERS + 2, manager.getNamesOfAllUsers().size());
		this.checkForUnwantedUsers();
		
		assertEquals(Constants.AUTHOR, manager.getNameOfUser(user1.getPrimaryKey()));
		assertEquals("Another author", manager.getNameOfUser(user2.getPrimaryKey()));
	
		//test adding person with same name
		User user3 = manager.addUser(Constants.AUTHOR);
		assertNotNull(manager.findUser(DataAccess.GUEST_PRIMARY_KEY));
		assertEquals(user1, manager.findUser(user1));
		assertEquals(user2, manager.findUser(user2));
		assertEquals(user3, manager.findUser(user3));
		assertEquals(DataAccess.NUM_INITIAL_USERS + 3, manager.getNamesOfAllUsers().size());
		this.checkForUnwantedUsers();
	}
	
	private void checkForUnwantedUsers()
	{
		assertNull(manager.findUser(-1));
		assertNull(manager.findUser(0));
	}
}