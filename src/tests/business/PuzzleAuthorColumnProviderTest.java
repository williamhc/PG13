package tests.business;

import pg13.business.PuzzleAuthorColumnProvider;
import pg13.models.Cryptogram;
import junit.framework.TestCase;

public class PuzzleAuthorColumnProviderTest extends TestCase 
{

	private Cryptogram testCryptogram;
	private PuzzleAuthorColumnProvider authorProvider;
	
	protected void setUp()
	{
		this.testCryptogram = new Cryptogram();
		this.authorProvider = new PuzzleAuthorColumnProvider();
	}
	
	public void testEmptyAuthor()
	{
		this.testCryptogram.setAuthor("");
		String resultAuthor = this.authorProvider.getText(this.testCryptogram);
		assertEquals(resultAuthor, "Guest");
	}

	public void testNullAuthor()
	{
		this.testCryptogram.setAuthor(null);
		String resultAuthor = this.authorProvider.getText(this.testCryptogram);
		assertEquals(resultAuthor, "Guest");		
	}
	
	public void testNormalAuthor()
	{
		String originalAuthor = "Will Coolcat";
		this.testCryptogram.setAuthor(originalAuthor);
		String resultAuthor = this.authorProvider.getText(this.testCryptogram);
		assertEquals(resultAuthor, originalAuthor);
	}

}
