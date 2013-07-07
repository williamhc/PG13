package tests.integration;

import pg13.app.PG13;
import pg13.app.Services;
import pg13.business.UserManager;
import pg13.models.User;
import tests.DBResetter;
import junit.framework.TestCase;


public class UserManagerDBSeamTest extends TestCase {

	private UserManager um;

	public void setUp()
	{
		DBResetter.ResetHSQLDB();
		Services.createDataAccess(PG13.dbName);
		um = new UserManager();
	}
	
	public void tearDown()
	{
		Services.closeDataAccess();
		DBResetter.ResetHSQLDB();
	}
	
	public void testCreateAndReadUsers(){
		int numUsers = um.getAllUsers().size();
		User bob = um.addUser("Bob");
		assertFalse(bob.getPrimaryKey() == um.getGuestPrimaryKey());
		assertEquals(um.findUser(bob.getPrimaryKey()).getPrimaryKey(), bob.getPrimaryKey());
		assertEquals(numUsers + 1, um.getAllUsers().size());
		assertEquals("Bob", um.getNameOfUser(bob.getPrimaryKey()));
		assertEquals(bob.getPrimaryKey(), um.findUser(bob).getPrimaryKey());
		assertTrue(um.getNamesOfAllUsers().contains("Bob"));
		
		User bob2 = um.addUser("Bob");
		assertNull(bob2);
		
		User john = um.addUser("John");
		assertNotNull(john);
		assertFalse(bob.getPrimaryKey() == john.getPrimaryKey());
		assertEquals(numUsers + 2, um.getAllUsers().size());
		assertEquals("John", um.getNameOfUser(john.getPrimaryKey()));
		assertEquals(john.getPrimaryKey(), um.findUser(john).getPrimaryKey());
		assertTrue(um.getNamesOfAllUsers().contains("John"));
		assertEquals(bob.getPrimaryKey(), um.findUser(bob).getPrimaryKey());
	}
	
}
