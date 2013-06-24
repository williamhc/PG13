package pg13.business;
import java.util.ArrayList;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import pg13.models.Difficulty;
import pg13.models.Puzzle;

public class DifficultyFilter extends ViewerFilter {

	private ArrayList<Difficulty> difficulties;
	
	public DifficultyFilter()
	{
		this.difficulties = new ArrayList<Difficulty>();
	}

	public void addValue(Difficulty diff) {
		if(diff != null)
		{
			this.difficulties.add(diff);
		}
	}

	public void removeValue(Difficulty diff) {
		this.difficulties.remove(diff);
	}
	
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {			
		Puzzle p = (Puzzle) element;
		if(this.difficulties.size() == 0)
		{
			return true;
		}
		return p.getDifficulty() != null && this.difficulties.contains(p.getDifficulty());
	}
}
