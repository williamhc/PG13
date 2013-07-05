package tests.models;

import junit.framework.TestCase;

import pg13.models.Category;
import pg13.models.Cryptogram;
import pg13.models.Difficulty;
import pg13.models.IRandomNumberGenerator;
import pg13.models.Puzzle;
import pg13.models.PuzzleValidationException;
import pg13.models.User;

public class CryptogramTest extends TestCase
{
	private class RightShiftGenerator implements IRandomNumberGenerator
	{
		public RightShiftGenerator()
		{
			
		}
		
		@Override
		public int random(int max) 
		{
			return 0;
		}
	}
	
	private class LeftShiftGenerator implements IRandomNumberGenerator
	{
		public LeftShiftGenerator()
		{
			
		}
		
		@Override
		public int random(int max) 
		{
			return max;
		}
	}
	
	private final String DEFAULT_TITLE = "Practice Cryptogram";
	private final String DEFAULT_AUTHOR = "Lauren Slusky";
	private final User DEFAULT_USER = new User("Lauren Slusky");
	private final Category DEFAULT_CATEGORY = Category.Computers;
	private final Difficulty DEFAULT_DIFFICULTY = Difficulty.Easy;
	private final String DEFAULT_DESCRIPTION = "Some Description";
	private final String DEFAULT_PLAINTEXT = "This is a test.";
	private final String DEFAULT_PLAINTEXT_RIGHTSHIFT = "UIJT JT B UFTU.";
	private final String DEFAULT_PLAINTEXT_LEFTSHIFT = "SGHR HR Z SDRS.";
	private final long DEFAULT_ID = 1;

	private Cryptogram cryptogram;

	public CryptogramTest(String arg0)
	{
		super(arg0);
	}

	public void testValidatingNewCryptogram()
	{
		cryptogram = new Cryptogram();
		try
		{
			cryptogram.validate();
			fail();
		}
		catch (PuzzleValidationException e)
		{
			// expected
		}
	}

	public void testValidatingValidCryptogram()
	{
		cryptogram = new Cryptogram();
		cryptogram.setPlaintext("Abc123");
		try
		{
			cryptogram.validate();
		}
		catch (PuzzleValidationException e)
		{
			fail(); // unexpected
		}
	}

	public void testValidatingEmptyCryptogram()
	{
		cryptogram = new Cryptogram();
		cryptogram.setPlaintext("");
		try
		{
			cryptogram.validate();
			fail();
		}
		catch (PuzzleValidationException e)
		{
			// expected
		}
	}

	public void testCryptogramGeneralData()
	{
		cryptogram = new Cryptogram(DEFAULT_USER, DEFAULT_TITLE,
				DEFAULT_DESCRIPTION, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY,
				DEFAULT_PLAINTEXT, DEFAULT_ID);
		assertNotNull(cryptogram);
		assertEquals(DEFAULT_AUTHOR, cryptogram.getAuthor());
		assertEquals(DEFAULT_TITLE, cryptogram.getTitle());
		assertEquals(DEFAULT_PLAINTEXT, cryptogram.getPlaintext());
		assertEquals(DEFAULT_CATEGORY, cryptogram.getCategory());
		assertEquals(DEFAULT_DIFFICULTY, cryptogram.getDifficulty());
		assertEquals(DEFAULT_ID, cryptogram.getID());
	}

	public void testCryptogramEmptyAuthor()
	{
		cryptogram = new Cryptogram(new User(""), DEFAULT_TITLE,
				DEFAULT_DESCRIPTION, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY, "",
				DEFAULT_ID);
		assertEquals("", cryptogram.getAuthor());
		assertEquals(DEFAULT_TITLE, cryptogram.getTitle());
		assertEquals("", cryptogram.getPlaintext());
		assertEquals(DEFAULT_CATEGORY, cryptogram.getCategory());
		assertEquals(DEFAULT_DIFFICULTY, cryptogram.getDifficulty());
		assertEquals(DEFAULT_ID, cryptogram.getID());
	}

	public void testCryptogramEmptyTitle()
	{
		cryptogram = new Cryptogram(DEFAULT_USER, "", DEFAULT_DESCRIPTION,
				DEFAULT_CATEGORY, DEFAULT_DIFFICULTY, "", DEFAULT_ID);
		assertEquals("", cryptogram.getTitle());
		assertEquals(DEFAULT_AUTHOR, cryptogram.getAuthor());
		assertEquals("", cryptogram.getPlaintext());
		assertEquals(DEFAULT_CATEGORY, cryptogram.getCategory());
		assertEquals(DEFAULT_DIFFICULTY, cryptogram.getDifficulty());
		assertEquals(DEFAULT_ID, cryptogram.getID());
	}

	public void testNoID()
	{
		cryptogram = new Cryptogram(DEFAULT_USER, DEFAULT_TITLE,
				DEFAULT_DESCRIPTION, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY,
				DEFAULT_PLAINTEXT);
		assertEquals(DEFAULT_AUTHOR, cryptogram.getAuthor());
		assertEquals(DEFAULT_TITLE, cryptogram.getTitle());
		assertEquals(DEFAULT_PLAINTEXT, cryptogram.getPlaintext());
		assertEquals(DEFAULT_CATEGORY, cryptogram.getCategory());
		assertEquals(DEFAULT_DIFFICULTY, cryptogram.getDifficulty());
		assertEquals(Puzzle.DEFAULT_ID, cryptogram.getID());
	}

	public void testCipherTextWorksNoPunctuation()
	{
		cryptogram = new Cryptogram(DEFAULT_USER, DEFAULT_TITLE,
				DEFAULT_DESCRIPTION, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY,
				DEFAULT_PLAINTEXT, DEFAULT_ID);
		
		assertEquals(DEFAULT_CATEGORY, cryptogram.getCategory());
		assertEquals(DEFAULT_DIFFICULTY, cryptogram.getDifficulty());
		assertEquals(DEFAULT_ID, cryptogram.getID());
		
		assertEquals(DEFAULT_PLAINTEXT, cryptogram.getPlaintext());
		assertNotSame(DEFAULT_PLAINTEXT, cryptogram.getCiphertext());
		assertFalse(DEFAULT_PLAINTEXT.equals(cryptogram.getCiphertext())); 
		assertTrue((cryptogram.decrypt(cryptogram.getSolutionMapping())).equalsIgnoreCase(DEFAULT_PLAINTEXT));
		
		// in order to test the encryption more thoroughly, test the encryption
		// with stubbed out random number generators
		
		// with this generator, it should generate a left shift cipher
		cryptogram = new Cryptogram(DEFAULT_USER, DEFAULT_TITLE,
				DEFAULT_DESCRIPTION, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY,
				DEFAULT_PLAINTEXT, DEFAULT_ID, new LeftShiftGenerator());
		
		assertEquals(DEFAULT_PLAINTEXT, cryptogram.getPlaintext());
		assertNotSame(DEFAULT_PLAINTEXT, cryptogram.getCiphertext());
		assertFalse(DEFAULT_PLAINTEXT.equals(cryptogram.getCiphertext())); 
		
		assertEquals(DEFAULT_PLAINTEXT_LEFTSHIFT, cryptogram.getCiphertext());
		assertTrue((cryptogram.decrypt(cryptogram.getSolutionMapping())).equalsIgnoreCase(DEFAULT_PLAINTEXT));
		
		// with this generator, it should generate a right shift cipher
		cryptogram = new Cryptogram(DEFAULT_USER, DEFAULT_TITLE,
				DEFAULT_DESCRIPTION, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY,
				DEFAULT_PLAINTEXT, DEFAULT_ID, new RightShiftGenerator());
		
		assertEquals(DEFAULT_PLAINTEXT, cryptogram.getPlaintext());
		assertNotSame(DEFAULT_PLAINTEXT, cryptogram.getCiphertext());
		assertFalse(DEFAULT_PLAINTEXT.equals(cryptogram.getCiphertext())); 
		
		assertEquals(DEFAULT_PLAINTEXT_RIGHTSHIFT, cryptogram.getCiphertext());
		assertTrue((cryptogram.decrypt(cryptogram.getSolutionMapping())).equalsIgnoreCase(DEFAULT_PLAINTEXT));
		
	}

	public void testCipherTextWorksPunctuation()
	{
		String plaintext = "This. is, a! test%";
		String rightShift = "UIJT. JT, B! UFTU%";
		String leftShift = "SGHR. HR, Z! SDRS%";
		
		cryptogram = new Cryptogram(DEFAULT_USER, DEFAULT_TITLE,
				DEFAULT_DESCRIPTION, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY,
				plaintext, DEFAULT_ID);
		assertEquals(plaintext, cryptogram.getPlaintext());
		assertNotSame(plaintext, cryptogram.getCiphertext());
		assertTrue((cryptogram.decrypt(cryptogram.getSolutionMapping())).equalsIgnoreCase(plaintext));
		assertEquals(DEFAULT_AUTHOR, cryptogram.getAuthor());
		assertEquals(DEFAULT_TITLE, cryptogram.getTitle());
		assertEquals(DEFAULT_CATEGORY, cryptogram.getCategory());
		assertEquals(DEFAULT_DIFFICULTY, cryptogram.getDifficulty());
		assertEquals(DEFAULT_ID, cryptogram.getID());
		
		// test encryption directly with stubbed out generators
		cryptogram = new Cryptogram(DEFAULT_USER, DEFAULT_TITLE,
				DEFAULT_DESCRIPTION, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY,
				plaintext, DEFAULT_ID, new RightShiftGenerator());
		assertEquals(plaintext, cryptogram.getPlaintext());
		assertNotSame(plaintext, cryptogram.getCiphertext());
		assertEquals(rightShift, cryptogram.getCiphertext());
		assertTrue((cryptogram.decrypt(cryptogram.getSolutionMapping())).equalsIgnoreCase(plaintext));
		
		cryptogram = new Cryptogram(DEFAULT_USER, DEFAULT_TITLE,
				DEFAULT_DESCRIPTION, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY,
				plaintext, DEFAULT_ID, new LeftShiftGenerator());
		assertEquals(plaintext, cryptogram.getPlaintext());
		assertNotSame(plaintext, cryptogram.getCiphertext());
		assertEquals(leftShift, cryptogram.getCiphertext());
		assertTrue((cryptogram.decrypt(cryptogram.getSolutionMapping())).equalsIgnoreCase(plaintext));
	}

	public void testCryptogramEmptyPlainText()
	{
		cryptogram = new Cryptogram(DEFAULT_USER, DEFAULT_TITLE,
				DEFAULT_DESCRIPTION, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY, "",
				DEFAULT_ID);
		assertEquals("", cryptogram.getPlaintext());
		assertEquals("", cryptogram.getCiphertext());
		assertEquals("", cryptogram.decrypt(cryptogram.getSolutionMapping()));
		assertEquals(DEFAULT_AUTHOR, cryptogram.getAuthor());
		assertEquals(DEFAULT_TITLE, cryptogram.getTitle());
		assertEquals(DEFAULT_CATEGORY, cryptogram.getCategory());
		assertEquals(DEFAULT_DIFFICULTY, cryptogram.getDifficulty());
		assertEquals(DEFAULT_ID, cryptogram.getID());

		try
		{
			cryptogram.validate();
			fail();
		}
		catch (PuzzleValidationException e)
		{
			// expected
		}
	}

	public void testCryptogramAllPuncatuationPlainText()
	{
		String plaintext = "!!!!!!!!!!!!!!!!!!!!!&!!!!!!!!!!";
		cryptogram = new Cryptogram(DEFAULT_USER, DEFAULT_TITLE,
				DEFAULT_DESCRIPTION, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY,
				plaintext, DEFAULT_ID);
		assertEquals(plaintext, cryptogram.getPlaintext());
		assertEquals(plaintext, cryptogram.getCiphertext());
		assertEquals(plaintext,
				cryptogram.decrypt(cryptogram.getSolutionMapping()));
		assertEquals(DEFAULT_AUTHOR, cryptogram.getAuthor());
		assertEquals(DEFAULT_TITLE, cryptogram.getTitle());
		assertEquals(DEFAULT_CATEGORY, cryptogram.getCategory());
		assertEquals(DEFAULT_DIFFICULTY, cryptogram.getDifficulty());
		assertEquals(DEFAULT_ID, cryptogram.getID());
	}

	public void testCryptogramCryptogramCompletion()
	{
		cryptogram = new Cryptogram(DEFAULT_USER, DEFAULT_TITLE,
				DEFAULT_DESCRIPTION, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY,
				"This is a test.");
		this.setUserMappingForTest(cryptogram);
		assertTrue(cryptogram.isCompleted());
	}

	private void setUserMappingForTest(Cryptogram cryptogram)
	{
		for (int i = 0; i < cryptogram.getSolutionMapping().length; i++)
		{
			cryptogram.setUserPlaintextForCiphertext(
					cryptogram.getSolutionMapping()[i].getPlainc(),
					cryptogram.getSolutionMapping()[i].getCipherc());
		}

	}

	public void testCryptogramUserUses()
	{
		cryptogram = new Cryptogram(DEFAULT_USER, DEFAULT_TITLE,
				DEFAULT_DESCRIPTION, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY,
				"This is a test.");
		cryptogram.setUserPlaintextForCiphertext('h', 'X');
		assertEquals(cryptogram.getUserPlaintextFromCiphertext('X'), 'H');
		assertNotNull(cryptogram.getUserMapping());
		
		// test to full solution using the stubbed out generators
		cryptogram = new Cryptogram(DEFAULT_USER, DEFAULT_TITLE,
				DEFAULT_DESCRIPTION, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY,
				"This is a test.", new RightShiftGenerator());
		cryptogram.setUserPlaintextForCiphertext('t', 'U');
		cryptogram.setUserPlaintextForCiphertext('h', 'I');
		cryptogram.setUserPlaintextForCiphertext('i', 'J');
		cryptogram.setUserPlaintextForCiphertext('s', 'T');
		cryptogram.setUserPlaintextForCiphertext('a', 'B');
		cryptogram.setUserPlaintextForCiphertext('e', 'F');
		assertNotNull(cryptogram.getUserMapping());
		assertTrue(cryptogram.isCompleted());
		
		cryptogram = new Cryptogram(DEFAULT_USER, DEFAULT_TITLE,
				DEFAULT_DESCRIPTION, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY,
				"This is a test.", new LeftShiftGenerator());
		cryptogram.setUserPlaintextForCiphertext('t', 'S');
		cryptogram.setUserPlaintextForCiphertext('h', 'G');
		cryptogram.setUserPlaintextForCiphertext('i', 'H');
		cryptogram.setUserPlaintextForCiphertext('s', 'R');
		cryptogram.setUserPlaintextForCiphertext('a', 'Z');
		cryptogram.setUserPlaintextForCiphertext('e', 'D');
		assertNotNull(cryptogram.getUserMapping());
		assertTrue(cryptogram.isCompleted());
	}

	public void testSetAndGetValidUserMappings()
	{
		cryptogram = new Cryptogram(DEFAULT_USER, DEFAULT_TITLE,
				DEFAULT_DESCRIPTION, DEFAULT_CATEGORY, DEFAULT_DIFFICULTY,
				DEFAULT_PLAINTEXT);
		for (char ch1 = 'a'; ch1 <= 'z'; ch1++)
		{
			for (char ch2 = 'a'; ch2 <= 'z'; ch2++)
			{
				cryptogram.setUserPlaintextForCiphertext(ch1, ch2);
				assertEquals(Character.toUpperCase(ch1),
						cryptogram.getUserPlaintextFromCiphertext(ch2));

				cryptogram.setUserPlaintextForCiphertext(
						Character.toUpperCase(ch1), ch2);
				assertEquals(Character.toUpperCase(ch1),
						cryptogram.getUserPlaintextFromCiphertext(ch2));

				cryptogram.setUserPlaintextForCiphertext(ch1,
						Character.toUpperCase(ch2));
				assertEquals(Character.toUpperCase(ch1),
						cryptogram.getUserPlaintextFromCiphertext(ch2));

				cryptogram.setUserPlaintextForCiphertext(
						Character.toUpperCase(ch1), Character.toUpperCase(ch2));
				assertEquals(Character.toUpperCase(ch1),
						cryptogram.getUserPlaintextFromCiphertext(ch2));
			}
		}
	}

	public void testResetUserMapping()
	{
		cryptogram = new Cryptogram();
		char guess = 'A';
		char encrypted = 'B';
		this.cryptogram.setUserPlaintextForCiphertext(guess, encrypted);
		assertEquals(guess,	this.cryptogram.getUserPlaintextFromCiphertext(encrypted));
		this.cryptogram.resetUserMapping();
		assertEquals('\0', this.cryptogram.getUserPlaintextFromCiphertext(encrypted));
	}

	public void testSavePreparation()
	{
		cryptogram = new Cryptogram();
		char guess = 'A';
		char encrypted = 'B';
		this.cryptogram.setUserPlaintextForCiphertext(guess, encrypted);
		this.cryptogram.prepareForSave();
		assertEquals('\0',
				this.cryptogram.getUserPlaintextFromCiphertext(encrypted));
	}
}
