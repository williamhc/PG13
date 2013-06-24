package tests.business;

import pg13.business.PuzzleTitleColumnProvider;
import pg13.models.Cryptogram;
import junit.framework.TestCase;

/**
 * Tests for PuzzleTitleColumnProvider
 */
public class PuzzleTitleColumnProviderTest extends TestCase
{

	private Cryptogram testCryptogram;
	private PuzzleTitleColumnProvider titleProvider;
	
	protected void setUp()
	{
		this.testCryptogram = new Cryptogram();
		this.titleProvider = new PuzzleTitleColumnProvider();
	}
	
	public void testEmptyTitle()
	{
		this.testCryptogram.setTitle("");
		String resultTitle = this.titleProvider.getText(this.testCryptogram);
		assertEquals(resultTitle, "Untitled");
	}

	public void testNullTitle()
	{
		this.testCryptogram.setTitle(null);
		String resultTitle = this.titleProvider.getText(this.testCryptogram);
		assertEquals(resultTitle, "Untitled");		
	}
	
	public void testNormalTitle()
	{
		String originalTitle = "Puzzle of Doom!";
		this.testCryptogram.setTitle(originalTitle);
		String resultTitle = this.titleProvider.getText(this.testCryptogram);
		assertEquals(resultTitle, originalTitle);
	}

}
