package pg13.business.search;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import pg13.models.Puzzle;

/**
 * Reports the difficulty as a string of a puzzle for displaying in a table column.
 * @author williamhumphreys-cloutier
 */
public class PuzzleDifficultyColumnProvider extends ColumnLabelProvider 
{

	@Override
	public String getText(Object obj)
	{
		Puzzle p = (Puzzle) obj;
		String difficulty = p.getDifficulty();
		return difficulty != null && difficulty.length() > 0 ? difficulty : "Unrated";
	}

}
