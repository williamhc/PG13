
package pg13.business.search;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import persistence.PuzzleController;
import pg13.models.Cryptogram;
import pg13.models.Puzzle;


/**
 * A logic layer for interactions between the puzzle table GUI
 * and persistence layers. Defines how the puzzle table acts and its data.
 * @author williamhumphreys-cloutier
 */
public class PuzzleTableDriver {
	
	private Map<String, ColumnLabelProvider> columnLabels;
	private ArrayList<Puzzle> puzzleList;
	private PuzzleController db;

	/**
	 * Constructor -- creates a puzzle table driver instance.
	 * @author williamhumphreys-cloutier
	 * @date June 1 2013
	 */
	public PuzzleTableDriver(ArrayList<Puzzle> puzzleList){
		//initialize the puzzle controller
		this.db = new PuzzleController();

		// initialize the label providers
		this.columnLabels = new HashMap<String, ColumnLabelProvider>();
		this.columnLabels.put("Title", new PuzzleTitleColumnProvider());
		this.columnLabels.put("Author", new PuzzleAuthorColumnProvider());
		this.columnLabels.put("Category", new PuzzleCategoryColumnProvider());
		this.columnLabels.put("Difficulty", new PuzzleDifficultyColumnProvider());

		// set the initial puzzle list
		this.puzzleList = puzzleList;
		ArrayList<Puzzle> dbResponse = this.db.getAllPuzzles();
		this.puzzleList.addAll(dbResponse);
	}

	public ColumnLabelProvider getColumnLabelProvider(String columnTitle) {
		return this.columnLabels.get(columnTitle);
	}
}
