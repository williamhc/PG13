package pg13.business;

import java.util.ArrayList;

import pg13.app.PG13;
import pg13.app.Services;
import pg13.models.Category;
import pg13.models.Difficulty;
import pg13.models.Puzzle;
import pg13.persistence.DataAccess;

public class PuzzleManager
{

	private DataAccess dataAccess;

	public PuzzleManager()
	{
		this.dataAccess = Services.getDataAccess(PG13.dbName);
	}

	public boolean save(Puzzle puzzle)
	{
		if (puzzle.getID() == Puzzle.DEFAULT_ID)
		{
			ArrayList<Long> keys = dataAccess.getSortedPuzzleIDs();
			long nextID = keys.size() == 0 ? 1 : keys.get(keys.size() - 1) + 1;
			puzzle.setID(nextID);
		}

		return this.dataAccess.savePuzzle(puzzle);
	}

	public boolean deletePuzzle(Puzzle puzzle)
	{
		return this.deletePuzzle(puzzle.getID());
	}

	public boolean deletePuzzle(long puzzleID)
	{
		return this.dataAccess.deletePuzzle(puzzleID);
	}

	public boolean updateDescription(long id, String newDescription)
	{
		return this.dataAccess.updateDescription(id, newDescription);
	}

	public boolean updateTitle(long id, String newTitle)
	{
		return this.dataAccess.updateTitle(id, newTitle);
	}

	public boolean updateCategory(long id, String newCategory)
	{
		return this.updateCategory(id, Category.valueOf(newCategory));
	}

	public boolean updateCategory(long id, Category newCategory)
	{
		return this.dataAccess.updateCategory(id, newCategory);
	}

	public boolean updateDifficulty(long id, String newDifficulty)
	{
		return this.updateDifficulty(id, Difficulty.valueOf(newDifficulty));
	}

	public boolean updateDifficulty(long id, Difficulty newDifficulty)
	{
		return this.dataAccess.updateDifficulty(id, newDifficulty);
	}

	public boolean updatePlaintext(long id, String newPlaintext)
	{
		return this.dataAccess.updatePlaintext(id, newPlaintext);
	}

	public ArrayList<Puzzle> getAllPuzzles()
	{
		return this.dataAccess.getAllPuzzles();
	}
}
