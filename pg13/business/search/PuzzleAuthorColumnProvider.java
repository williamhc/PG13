package pg13.business.search;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import pg13.models.Puzzle;

/**
 * Reports the author of a puzzle as a string for displaying in a table column.
 * @author williamhumphreys-cloutier
 */
public class PuzzleAuthorColumnProvider extends ColumnLabelProvider
{
	@Override
	public String getText(Object obj)
	{
		Puzzle p = (Puzzle) obj;
		String author = p.getAuthor();
		//TODO login as guest option so we should not hardcode guest
		return author != null && author.length() > 0 ? author : "Guest";
	}
}
