package pg13.business;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import pg13.models.Puzzle;

public class AuthorFilter extends ViewerFilter
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
		return p.getAuthor().toLowerCase().matches(this.searchString);
	}

	public void setAbsoluteSearchString(String abs_str)
	{
		if (abs_str == null)
		{
			return;
		}
		this.searchString = abs_str.toLowerCase();
	}
}
