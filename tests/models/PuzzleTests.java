package tests.models;

import junit.framework.Test;
import junit.framework.TestSuite;

public class PuzzleTests
{
	public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Puzzle tests");
      //  suite.addTestSuite(CalculateGPATest.class);
      //  suite.addTestSuite(CalculateGPA2Test.class);
       return suite;
    }
}
