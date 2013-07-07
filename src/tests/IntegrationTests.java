package tests;

import junit.framework.Test;
import junit.framework.TestSuite;
import tests.integration.*;

public class IntegrationTests
{
	public static TestSuite suite;

	public static Test suite()
	{
		suite = new TestSuite("All tests");
		DBResetter.ResetHSQLDB();
		suite.addTestSuite(PuzzleManagerDBSeamTest.class);
		return suite;
	}
}