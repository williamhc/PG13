package tests;

import acceptanceTests.TestRunner;

public class AcceptanceTestsRunner
{
    public static void main(String[] args) throws Exception
    {
    	String[] parms = new String[1];
    	parms[0] = "0";  // sleep parameter in 1/2 seconds
    	TestRunner.main(parms);
    }
}
