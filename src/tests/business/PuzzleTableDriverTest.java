package tests.business;

import java.util.ArrayList;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import pg13.business.PuzzleTableDriver;
import pg13.models.Cryptogram;
import pg13.models.Puzzle;
import junit.framework.TestCase;

public class PuzzleTableDriverTest extends TestCase {

	private PuzzleTableDriver pdt;
	private ArrayList<Puzzle> puzzleList;

	protected void setUp() throws Exception {
		this.puzzleList = new ArrayList<Puzzle>();
		this.pdt = new PuzzleTableDriver(this.puzzleList);
	}
	
	public void testGettingColumns()
	{
		ColumnLabelProvider titleCLP = this.pdt.getColumnLabelProvider("Title");
		assertNotNull(titleCLP);
		ColumnLabelProvider authorCLP = this.pdt.getColumnLabelProvider("Author");
		assertNotNull(authorCLP);
		ColumnLabelProvider categoryCLP = this.pdt.getColumnLabelProvider("Category");
		assertNotNull(categoryCLP);
		ColumnLabelProvider difficultyCLP = this.pdt.getColumnLabelProvider("Difficulty");
		assertNotNull(difficultyCLP);
		assertNull(this.pdt.getColumnLabelProvider("Not a Column"));
	}

	public void testRefreshingPuzzles()
	{
		Cryptogram newPuzz = new Cryptogram();
		int origSize = this.puzzleList.size();
		this.puzzleList.add(newPuzz);
		assertTrue(this.puzzleList.contains(newPuzz));
		this.pdt.refresh();
		assertFalse(this.puzzleList.contains(newPuzz));
		assertEquals(this.puzzleList.size(), origSize);
	}
}
