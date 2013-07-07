package tests;

import acceptanceTests.TestRunner;
import junit.framework.TestCase;

public class AcceptanceTests extends TestCase {
	public void setUp()
	{
		DBResetter.ResetHSQLDB();
	}
	
	public void tearDown()
	{
		DBResetter.ResetHSQLDB();
	}
	
	public void testAcceptance()
	{
	   	String[] params = new String[1];
    	params[0] = "0";  // sleep parameter in 1/2 seconds
		try {
			TestRunner.main(params);
		} catch (Exception e) {
			fail();
		}
	}
}
