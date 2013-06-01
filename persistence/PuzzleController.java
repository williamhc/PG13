/**
 * 
 */
package persistence;

import java.util.ArrayList;
import java.util.Date;

import pg13.models.Cryptogram;
import pg13.models.Puzzle;

/**
 * A simple fake DB to serve up puzzles and act like a persistence layer.
 * @author williamhumphreys-cloutier
 *
 */
public class PuzzleController {

	public ArrayList<Puzzle> getAllPuzzles() {
		ArrayList<Puzzle> puzzleList = new ArrayList<Puzzle>();
		String[][] dummyPuzzles = {
				{"Toughy", "Will", "Testing", "Easy"},
				{"Wootbots", "Will", "Fake", "Average"}
		};
		for(String[] def: dummyPuzzles)
		{
			Cryptogram dummyPuzzle = new Cryptogram(def[0], def[1], def[2], def[3], new Date(), "Fake!");
			puzzleList.add(dummyPuzzle);
		}
		return puzzleList;
	}

}
