
package pg13.business.search;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.ColumnLabelProvider;


/**
 * A logic layer for interactions between the puzzle table GUI
 * and persistence layers. Defines how the puzzle table acts and its data.
 * @author williamhumphreys-cloutier
 */
public class PuzzleTableDriver {
	
	private Map<String, ColumnLabelProvider> columnLabels;
	
	/**
	 * Constructor -- creates a puzzle table driver instance.
	 * @author williamhumphreys-cloutier
	 * @date June 1 2013
	 */
	public PuzzleTableDriver(){
		this.columnLabels = new HashMap<String, ColumnLabelProvider>();
		this.columnLabels.put("Title", new PuzzleTitleColumnProvider());
		this.columnLabels.put("Author", new PuzzleAuthorColumnProvider());
		this.columnLabels.put("Category", new PuzzleCategoryColumnProvider());
		this.columnLabels.put("Difficulty", new PuzzleDifficultyColumnProvider());
	}

	public ColumnLabelProvider getColumnLabelProvider(String columnTitle) {
		return this.columnLabels.get(columnTitle);
	}
}
