package pg13.business.search;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import pg13.models.Puzzle;

/**
 * Reports the category as a string of a puzzle for displaying in a table column.
 * @author williamhumphreys-cloutier
 */
public class PuzzleCategoryColumnProvider extends ColumnLabelProvider 
{
	@Override
	public String getText(Object obj)
	{
		Puzzle p = (Puzzle) obj;
		String category = p.getCategory();
		return category != null && category.length() > 0 ? category : "Uncategorized";
	}
}
