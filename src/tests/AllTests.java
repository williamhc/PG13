package tests;

import tests.business.CryptogramManagerTest;
import tests.business.create.TestUserManager;
import tests.business.search.PuzzleAuthorColumnProviderTest;
import tests.business.search.PuzzleCategoryColumnProviderTest;
import tests.business.search.PuzzleDifficultyColumnProviderTest;
import tests.business.search.PuzzleTitleColumnProviderTest;
import tests.models.CryptogramPairTest;
import tests.models.CryptogramTest;
import tests.models.UserTest;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests
{
	public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("All tests");
        testModels();
        testBusiness();
        return suite;
    }

    private static void testModels()
    {
        suite.addTestSuite(CryptogramPairTest.class);
        suite.addTestSuite(CryptogramTest.class);
        suite.addTestSuite(UserTest.class);
    }

    private static void testBusiness()
    {
        suite.addTestSuite(CryptogramManagerTest.class);
        suite.addTestSuite(PuzzleAuthorColumnProviderTest.class);
        suite.addTestSuite(PuzzleCategoryColumnProviderTest.class);
        suite.addTestSuite(PuzzleDifficultyColumnProviderTest.class);
        suite.addTestSuite(PuzzleTitleColumnProviderTest.class);
        suite.addTestSuite(TestUserManager.class);
    }
}
