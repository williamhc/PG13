package tests.business;

import pg13.business.PuzzleDifficultyColumnProvider;
import pg13.models.Cryptogram;
import pg13.models.Difficulty;
import junit.framework.TestCase;

public class PuzzleDifficultyColumnProviderTest extends TestCase 
{
	private Cryptogram testCryptogram;
	private PuzzleDifficultyColumnProvider difficultyProvider;
	
	protected void setUp()
	{
		this.testCryptogram = new Cryptogram();
		this.difficultyProvider = new PuzzleDifficultyColumnProvider();
	}
	

	public void testNullDifficulty()
	{
		this.testCryptogram.setDifficulty(null);
		String resultDifficulty = this.difficultyProvider.getText(this.testCryptogram);
		assertEquals(resultDifficulty, "Unrated");
	}
	
	public void testNormalDifficulty()
	{
		Difficulty originalDifficulty = Difficulty.Medium;
		this.testCryptogram.setDifficulty(originalDifficulty);
		String resultDifficulty = this.difficultyProvider.getText(this.testCryptogram);
		assertEquals(resultDifficulty, originalDifficulty.toString());
	}
}
