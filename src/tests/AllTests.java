package tests;

import tests.business.AuthorFilterTest;
import tests.business.CategoryFilterTest;
import tests.business.CryptogramManagerTest;
import tests.business.DifficultyFilterTest;
import tests.business.PuzzleAuthorColumnProviderTest;
import tests.business.PuzzleCategoryColumnProviderTest;
import tests.business.PuzzleDifficultyColumnProviderTest;
import tests.business.PuzzleTitleColumnProviderTest;
import tests.business.TestUserManager;
import tests.business.TitleFilterTest;
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
		suite.addTestSuite(TitleFilterTest.class);
		suite.addTestSuite(AuthorFilterTest.class);
		suite.addTestSuite(CategoryFilterTest.class);
		suite.addTestSuite(DifficultyFilterTest.class);
	}
}
