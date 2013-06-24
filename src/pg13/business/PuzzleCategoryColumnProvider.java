package pg13.business;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import pg13.models.Category;
import pg13.models.Puzzle;

//Reports the category as a string of a puzzle for displaying in a table column.

public class PuzzleCategoryColumnProvider extends ColumnLabelProvider
{
	@Override
	public String getText(Object obj)
	{
		Puzzle p = (Puzzle) obj;
		Category category = p.getCategory();
		return category != null ? category.toString() : "Uncategorized";
	}
}
