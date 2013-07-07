package pg13.business;

import java.util.ArrayList;

import pg13.app.PG13;
import pg13.app.Services;
import pg13.models.Category;
import pg13.models.Cryptogram;
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
			boolean saved = this.dataAccess.savePuzzle(puzzle);
			if(!saved)
			{
				// if it didn't save, we'll do a rollback
				puzzle.setID(Puzzle.DEFAULT_ID);
			}
			return saved;
		}
		else
		{
			long id = puzzle.getID();
			boolean updateCategory = this.updateCategory(id, puzzle.getCategory());
			boolean updateDescription = this.updateDescription(id, puzzle.getDescription());
			boolean updateDifficulty = this.updateDifficulty(id, puzzle.getDifficulty());
			boolean updatePlaintext = this.updatePlaintext(id, ((Cryptogram)puzzle).getPlaintext());
			boolean updateTitle = this.updateTitle(id, puzzle.getTitle());
			
			return updateCategory && updateDescription && updateDifficulty && updatePlaintext && updateTitle;
		}

		
	}

	public boolean deletePuzzle(Puzzle puzzle)
	{
		return this.deletePuzzle(puzzle.getID());
	}

	public boolean deletePuzzle(long puzzleID)
	{
		return this.dataAccess.deletePuzzle(puzzleID);
	}

	private boolean updateDescription(long id, String newDescription)
	{
		return this.dataAccess.updateDescription(id, newDescription);
	}

	private boolean updateTitle(long id, String newTitle)
	{
		return this.dataAccess.updateTitle(id, newTitle);
	}

	private boolean updateCategory(long id, Category newCategory)
	{
		return this.dataAccess.updateCategory(id, newCategory);
	}

	private boolean updateDifficulty(long id, Difficulty newDifficulty)
	{
		return this.dataAccess.updateDifficulty(id, newDifficulty);
	}

	private boolean updatePlaintext(long id, String newPlaintext)
	{
		return this.dataAccess.updatePlaintext(id, newPlaintext);
	}

	public ArrayList<Puzzle> getAllPuzzles()
	{
		return this.dataAccess.getAllPuzzles();
	}
}
