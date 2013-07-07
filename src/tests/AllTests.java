package tests;

import acceptanceTests.TestRunner;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests
{
	public static TestSuite suite;

	public static Test suite()
	{
		suite = new TestSuite("All tests");
		DBResetter.ResetHSQLDB();
		suite.addTest(UnitTests.suite());
		suite.addTest(IntegrationTests.suite());
		suite.addTestSuite(AcceptanceTests.class);
		return suite;
	}
}
