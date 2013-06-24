package pg13.business;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import pg13.models.Puzzle;

public class TitleFilter extends ViewerFilter
{

	private String searchString;

	public void setSearchString(String str)
	{
		if (str == null)
		{
			return;
		}
		this.searchString = ".*" + str.trim().toLowerCase() + ".*";
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element)
	{
		Puzzle p = (Puzzle) element;
		if (this.searchString == null || this.searchString.length() == 0)
		{
			return true;
		}
		return p.getTitle().toLowerCase().matches(this.searchString);
	}

}
