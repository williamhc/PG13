package pg13.business;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import pg13.models.Category;
import pg13.models.Puzzle;

public class CategoryFilter extends ViewerFilter {

	private Category searchValue;
	
	public void setSearchValue(Category category) {
		this.searchValue = category;
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {			
		Puzzle p = (Puzzle) element;
		return this.searchValue == null || p.getCategory() == this.searchValue;
	}

	public Category getSearchValue() {
		return this.searchValue;
	}
}
