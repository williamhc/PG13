/**
 *
 */
package pg13.persistence;

import java.util.ArrayList;
import java.util.Date;

import pg13.models.Category;
import pg13.models.Cryptogram;
import pg13.models.Difficulty;
import pg13.models.Puzzle;
import pg13.models.User;

/**
 * A simple fake DB to serve up puzzles and act like a persistence layer.
 * @author williamhumphreys-cloutier
 *
 */
public class PuzzleController
{
	static private PuzzleController pc_instance;
	private ArrayList<Puzzle> puzzleList;

	//I assume this is a singleton class?
	private PuzzleController()
	{
		this.puzzleList = new ArrayList<Puzzle>();
		String[][] dummyPuzzles =
		{
			{"Toughy", "Will", "Testing", "Easy"},
			{"Wootbots", "Will", "Fake", "Average"}
		};
		for(String[] def: dummyPuzzles)
		{
			Cryptogram dummyPuzzle = new Cryptogram(new User(def[0]), def[1], Category.Miscellaneous, Difficulty.Medium, new Date(), "Fake!");
			puzzleList.add(dummyPuzzle);
		}

	}

	public static PuzzleController getInstance()
	{
		if(pc_instance == null)
		{
			pc_instance = new PuzzleController();
		}
		return pc_instance;
	}

	public ArrayList<Puzzle> getAllPuzzles()
	{
		return this.puzzleList;
	}

	public void persist(Puzzle puzzle)
	{
		this.puzzleList.add(puzzle);
	}

}
