package pg13.business.search;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import pg13.models.Difficulty;
import pg13.models.Puzzle;

/**
 * Reports the difficulty as a string of a puzzle for displaying in a table column.
 */
public class PuzzleDifficultyColumnProvider extends ColumnLabelProvider 
{

	@Override
	public String getText(Object obj)
	{
		Puzzle p = (Puzzle) obj;
		Difficulty difficulty = p.getDifficulty();
		return difficulty != null  ? difficulty.toString() : "Unrated";
	}

}
