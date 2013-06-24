package pg13.business;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import pg13.models.Puzzle;

// Reports the title as a string of a puzzle for displaying in a table colum
public class PuzzleTitleColumnProvider extends ColumnLabelProvider
{
	@Override
	public String getText(Object obj)
	{
		Puzzle p = (Puzzle) obj;
		String title = p.getTitle();
		return title != null && title.length() > 0 ? title : "Untitled";
	}
}
