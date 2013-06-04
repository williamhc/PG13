package tests.business.search;

import pg13.business.search.PuzzleDifficultyColumnProvider;
import pg13.models.Cryptogram;
import junit.framework.TestCase;

/**
 * 
 * Tests for PuzzleDifficultyColumnProvider
 * @author williamhumphreys-cloutier
 *
 */
public class PuzzleDifficultyColumnProviderTest extends TestCase {
	private Cryptogram testCryptogram;
	private PuzzleDifficultyColumnProvider difficultyProvider;
	
	protected void setUp(){
		this.testCryptogram = new Cryptogram();
		this.difficultyProvider = new PuzzleDifficultyColumnProvider();
	}
	
	public void testEmptyDifficulty(){
		this.testCryptogram.setDifficulty("");
		String resultDifficulty = this.difficultyProvider.getText(this.testCryptogram);
		assertEquals(resultDifficulty, "Unrated");
	}

	public void testNullDifficulty(){
		this.testCryptogram.setDifficulty(null);
		String resultDifficulty = this.difficultyProvider.getText(this.testCryptogram);
		assertEquals(resultDifficulty, "Unrated");
	}
	
	public void testNormalDifficulty(){
		String originalDifficulty = "Puzzle of Doom!";
		this.testCryptogram.setDifficulty(originalDifficulty);
		String resultDifficulty = this.difficultyProvider.getText(this.testCryptogram);
		assertEquals(resultDifficulty, originalDifficulty);
	}
}
