package pg13.models;

public class Cryptogram extends Puzzle
{
	private static final int ALPHABET_SIZE = 26; // Letters of the alphabet
	private static final String INVALID_PLAINTEXT_MESSAGE = "A cryptogram must have at least one character in its plaintext.";

	private String ciphertext; // Coded string
	private String plaintext; // the original user string
	private CryptogramPair[] solutionMapping; // Mapping for plaintext to
												// ciphertext characters used
												// for encryption and decryption
	private CryptogramPair[] userMapping; // User Entered mapping of characters
											// when solving a cryptogram ordered
											// by ciphertext

	private IRandomNumberGenerator randomNumberGenerator;
	
	public Cryptogram()
	{
		super();
		this.randomNumberGenerator = new RealRandomNumberGenerator();
		
		this.solutionMapping = setMappingKeys(false);
		generateMappingKeys();
		this.userMapping = setMappingKeys(true);
		this.ciphertext = "";
		this.plaintext = "";
	}

	public Cryptogram(User user, String title, String description,
			Category category, Difficulty difficulty, String plaintext, IRandomNumberGenerator generator)
	{
		super(user, title, description, category, difficulty);
		this.randomNumberGenerator = generator;
		
		this.solutionMapping = setMappingKeys(false);
		generateMappingKeys();
		this.userMapping = setMappingKeys(true);
		this.plaintext = plaintext;
		this.ciphertext = encrypt();
	}
	
	public Cryptogram(User user, String title, String description,
			Category category, Difficulty difficulty, String plaintext)
	{
		this(user, title, description, category, difficulty, plaintext, new RealRandomNumberGenerator());
	}

	public Cryptogram(User user, String title, String description,
			Category category, Difficulty difficulty, String plaintext, long id, IRandomNumberGenerator generator)
	{
		this(user, title, description, category, difficulty, plaintext, generator);
		this.setID(id);
	}
	
	public Cryptogram(User user, String title, String description,
			Category category, Difficulty difficulty, String plaintext, long id)
	{
		this(user, title, description, category, difficulty, plaintext, id, new RealRandomNumberGenerator());
	}
	

	public String getCiphertext()
	{
		return ciphertext;
	}

	public String getPlaintext()
	{
		return plaintext;
	}

	public void setPlaintext(String plaintext)
	{
		this.plaintext = plaintext;
		this.ciphertext = encrypt();
	}

	public void resetUserMapping()
	{
		this.userMapping = setMappingKeys(true);
	}

	public CryptogramPair[] getSolutionMapping()
	{
		return solutionMapping;
	}

	public CryptogramPair[] getUserMapping()
	{
		return userMapping;
	}

	public char getUserPlaintextFromCiphertext(char ciphertextc)
	{
		int index = Character.toUpperCase(ciphertextc) - 'A';
		return Character.toUpperCase(this.userMapping[index].getPlainc());
	}

	public void setUserPlaintextForCiphertext(char plaintextc, char ciphertextc)
	{
		int index = Character.toUpperCase(ciphertextc) - 'A'; // spot the in the
																// array of the
																// plaintext
																// ciphertext
																// pairing
		this.userMapping[index].setPlainc(Character.toUpperCase(plaintextc)); // map
																				// the
																				// ciphertext
																				// to
																				// the
																				// given
																				// plaintext
	}

	public boolean isCompleted()
	{
		String userString = decrypt(this.userMapping);
		if (userString == null)
		{
			throw new IllegalArgumentException();
		}
		return userString.equalsIgnoreCase(this.plaintext);
	}

	private CryptogramPair[] setMappingKeys(boolean orderByCipherText)
	{
		char[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
				'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
				'W', 'X', 'Y', 'Z' };
		CryptogramPair[] map = new CryptogramPair[ALPHABET_SIZE];
		for (int i = 0; i < alphabet.length; i++)
		{
			char letter = alphabet[i];
			if (orderByCipherText)
			{
				map[i] = new CryptogramPair('\0', letter); // no ciphertext char
															// to map yet
			}
			else
			{
				map[i] = new CryptogramPair(letter, '\0'); // no ciphertext char
															// to map yet
			}
		}
		return map;
	}

	private void generateMappingKeys()
	{
		char[] alphabet = shuffleAlphabet();

		for (int i = 0; i < alphabet.length; i++)
		{
			char letter = alphabet[i];
			this.solutionMapping[i].setCipherc(letter);
		}
	}

	private char[] shuffleAlphabet()
	{
		char[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
				'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
				'W', 'X', 'Y', 'Z' };
		int posn = ALPHABET_SIZE;
		int newPosn = 0;
		char temp;

		while (posn > 1)
		{
			posn = posn - 1;
			// we use posn - 1 so that way there is no chance of the random
			// element being posn and the element swapping places with itself
			newPosn = randomNumberGenerator.random(posn - 1);

			// regular old swapskie
			temp = alphabet[posn];
			alphabet[posn] = alphabet[newPosn];
			alphabet[newPosn] = temp;
		}
		return alphabet;
	}

	private String encrypt()
	{
		String ciphertext = "";
		String plain = this.plaintext;
		int index = 0;

		if (this.plaintext != null && this.plaintext.length() > 0)
		{
			plain = plain.toUpperCase();

			for (int i = 0; i < plain.length(); i++)
			{
				char temp = plain.charAt(i);

				// alphabet character
				if (temp >= 'A' && temp <= 'Z')
				{
					index = temp - 'A'; // gives me index in charMapping for the
										// cipher text letter that matches this
										// plaintext char
					ciphertext += this.solutionMapping[index].getCipherc();
				}
				// punctuation, spaces, etc
				else
				{
					ciphertext += temp;
				}
			}
		}

		return ciphertext;
	}

	public String decrypt(CryptogramPair[] mapping)
	{
		String plaintext = "";
		char[] decryptKey = reOrderMappingByDecrypt(mapping); // an array of
																// plaintext
																// characters
																// ordered by
																// ciphertext
																// character
																// index
		String ciphertext = this.ciphertext;
		int index = 0;

		if (this.ciphertext != null && this.ciphertext.length() > 0)
		{
			for (int i = 0; i < ciphertext.length(); i++)
			{
				char temp = ciphertext.charAt(i);

				// alphabet character
				if (temp >= 'A' && temp <= 'Z')
				{
					index = temp - 'A'; // gives me index associated with the
										// ciphertext character (A = 0, B = 1)
										// in charMapping
					plaintext += decryptKey[index];
				}
				// punctuation, spaces, etc
				else
				{
					plaintext += temp;
				}
			}
		}

		return plaintext;
	}

	private char[] reOrderMappingByDecrypt(CryptogramPair[] tofix)
	{
		char[] reorder = new char[ALPHABET_SIZE];
		int index = 0;

		for (int i = 0; i < tofix.length; i++)
		{
			index = tofix[i].getCipherc() - 'A'; // get ciphertext character
													// index
			reorder[index] = tofix[i].getPlainc(); // put plaintext character in
													// that spot
		}
		return reorder;
	}

	public void validate() throws PuzzleValidationException
	{
		super.validate();
		if (this.plaintext == null || this.plaintext.length() == 0)
		{
			throw new PuzzleValidationException(INVALID_PLAINTEXT_MESSAGE);
		}
	}

	public void prepareForSave()
	{
		this.resetUserMapping();
	}
}
