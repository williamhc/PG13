package pg13.presentation;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;

import pg13.business.search.PuzzleTableDriver;
import pg13.models.Puzzle;

public class FindScreen extends Composite 
{
	private static class ContentProvider implements IStructuredContentProvider 
	{
		public Object[] getElements(Object newElements) 
		{
		      @SuppressWarnings("unchecked")
			ArrayList<Puzzle> puzzles = (ArrayList<Puzzle>) newElements;
		      return puzzles.toArray();
		}
		public void dispose()
		{
		}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) 
		{
		}
	}
	private Text txtTitle;
	private Text txtAuthor;
	private Table table;
	private TableViewer tableViewer;
	private PuzzleTableDriver tableDriver;
	private ArrayList<Puzzle> puzzleResults;

	/**
	 * Creates and populates the Find screen.
	 * @author Eric
	 * @param parent
	 * @param style
	 * @date May 31 2013
	 */
	public FindScreen(Composite parent, int style) 
	{
		super(parent, style);
		setLayout(new FormLayout());
		
		// separates the left information from the results table
		Label separator = new Label(this, SWT.SEPARATOR | SWT.VERTICAL);
		FormData fd_separator = new FormData();
		fd_separator.bottom = new FormAttachment(100);
		fd_separator.top = new FormAttachment(0);
		fd_separator.left = new FormAttachment(0, 200);
		separator.setLayoutData(fd_separator);
		
		// "Find Puzzles to Play"
		Label lblFindAPuzzle = new Label(this, SWT.NONE);
		lblFindAPuzzle.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		FormData fd_lblFindAPuzzle = new FormData();
		fd_lblFindAPuzzle.top = new FormAttachment(0, 10);
		fd_lblFindAPuzzle.left = new FormAttachment(0, 10);
		lblFindAPuzzle.setLayoutData(fd_lblFindAPuzzle);
		lblFindAPuzzle.setText(Constants.FIND_PUZZLES);
		
		// composite that contains the radio buttons to filter puzzle search
		Composite cmpPuzzleFilter = new Composite(this, SWT.BORDER);
		cmpPuzzleFilter.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		cmpPuzzleFilter.setLayout(new FormLayout());
		FormData fd_cmpPuzzleFilter = new FormData();
		fd_cmpPuzzleFilter.bottom = new FormAttachment(lblFindAPuzzle, 84, SWT.BOTTOM);
		fd_cmpPuzzleFilter.top = new FormAttachment(lblFindAPuzzle, 6);
		fd_cmpPuzzleFilter.right = new FormAttachment(separator, -10);
		fd_cmpPuzzleFilter.left = new FormAttachment(0, 10);
		cmpPuzzleFilter.setLayoutData(fd_cmpPuzzleFilter);
		
		Button btnAllPuzzles = new Button(cmpPuzzleFilter, SWT.RADIO);
		btnAllPuzzles.setSelection(true);
		FormData fd_btnAllPuzzles = new FormData();
		fd_btnAllPuzzles.right = new FormAttachment(100, -6);
		fd_btnAllPuzzles.top = new FormAttachment(0, 6);
		fd_btnAllPuzzles.left = new FormAttachment(0, 6);
		btnAllPuzzles.setLayoutData(fd_btnAllPuzzles);
		btnAllPuzzles.setText(Constants.ALL_PUZZLES);
		btnAllPuzzles.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Button btnFriendsPuzzles = new Button(cmpPuzzleFilter, SWT.RADIO);
		btnFriendsPuzzles.setEnabled(false);
		FormData fd_btnFriendsPuzzles = new FormData();
		fd_btnFriendsPuzzles.top = new FormAttachment(0, 28);
		fd_btnFriendsPuzzles.right = new FormAttachment(100, -6);
		fd_btnFriendsPuzzles.left = new FormAttachment(0, 6);
		btnFriendsPuzzles.setLayoutData(fd_btnFriendsPuzzles);
		btnFriendsPuzzles.setText(Constants.FRIENDS_PUZZLES);
		btnFriendsPuzzles.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Button btnMyPuzzles = new Button(cmpPuzzleFilter, SWT.RADIO);
		btnMyPuzzles.setEnabled(false);
		FormData fd_btnMyPuzzles = new FormData();
		fd_btnMyPuzzles.right = new FormAttachment(100, -6);
		fd_btnMyPuzzles.top = new FormAttachment(0, 50);
		fd_btnMyPuzzles.left = new FormAttachment(0, 6);
		btnMyPuzzles.setLayoutData(fd_btnMyPuzzles);
		btnMyPuzzles.setText(Constants.MY_PUZZLES);
		btnMyPuzzles.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		// composite that contains additional search filters for the properties of the puzzle
		Composite cmpPuzzleSearch = new Composite(this, SWT.BORDER);
		cmpPuzzleSearch.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		cmpPuzzleSearch.setLayout(new FormLayout());
		FormData fd_cmpPuzzleSearch = new FormData();
		fd_cmpPuzzleSearch.bottom = new FormAttachment(100, -40);
		fd_cmpPuzzleSearch.right = new FormAttachment(separator, -10);
		fd_cmpPuzzleSearch.top = new FormAttachment(cmpPuzzleFilter, 8);
		fd_cmpPuzzleSearch.left = new FormAttachment(0, 10);
		cmpPuzzleSearch.setLayoutData(fd_cmpPuzzleSearch);
		
		// search by title
		Label lblTitle = new Label(cmpPuzzleSearch, SWT.NONE);
		lblTitle.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_lblTitle = new FormData();
		fd_lblTitle.top = new FormAttachment(0, 6);
		fd_lblTitle.left = new FormAttachment(0, 6);
		lblTitle.setLayoutData(fd_lblTitle);
		lblTitle.setText(Constants.TITLE);
		
		txtTitle = new Text(cmpPuzzleSearch, SWT.BORDER);
		txtTitle.setEnabled(false);
		FormData fd_txtTitle = new FormData();
		fd_txtTitle.right = new FormAttachment(100, -6);
		fd_txtTitle.top = new FormAttachment(lblTitle, 2);
		fd_txtTitle.left = new FormAttachment(0, 6);
		txtTitle.setLayoutData(fd_txtTitle);
		
		// search by author
		Label lblAuthor = new Label(cmpPuzzleSearch, SWT.NONE);
		lblAuthor.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_lblAuthor = new FormData();
		fd_lblAuthor.top = new FormAttachment(txtTitle, 6);
		fd_lblAuthor.left = new FormAttachment(0, 6);
		lblAuthor.setLayoutData(fd_lblAuthor);
		lblAuthor.setText(Constants.AUTHOR);
		
		txtAuthor = new Text(cmpPuzzleSearch, SWT.BORDER);
		txtAuthor.setEnabled(false);
		FormData fd_txtAuthor = new FormData();
		fd_txtAuthor.top = new FormAttachment(lblAuthor, 2);
		fd_txtAuthor.right = new FormAttachment(100, -6);
		fd_txtAuthor.left = new FormAttachment(0, 6);
		txtAuthor.setLayoutData(fd_txtAuthor);
		
		// filter by category
		Label lblCategory = new Label(cmpPuzzleSearch, SWT.NONE);
		lblCategory.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_lblCategory = new FormData();
		fd_lblCategory.top = new FormAttachment(txtAuthor, 6);
		fd_lblCategory.left = new FormAttachment(0, 6);
		lblCategory.setLayoutData(fd_lblCategory);
		lblCategory.setText(Constants.CATEGORY + ":");
		
		Combo cmbCategory = new Combo(cmpPuzzleSearch, SWT.NONE);
		cmbCategory.setEnabled(false);
		cmbCategory.setItems(new String[] {"All Categories", "Animals", "Biology", "Computers", "Games", "General Trivia", "Geography", "History", "Miscellaneous", "Politics", "Science", "Space", "Sports"});
		FormData fd_cmbCategory = new FormData();
		fd_cmbCategory.top = new FormAttachment(lblCategory, 2);
		fd_cmbCategory.right = new FormAttachment(100, -6);
		fd_cmbCategory.left = new FormAttachment(0, 6);
		cmbCategory.setLayoutData(fd_cmbCategory);
		cmbCategory.select(0);
		
		// filter by difficulty
		Label lblDifficulty = new Label(cmpPuzzleSearch, SWT.NONE);
		lblDifficulty.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_lblDifficulty = new FormData();
		fd_lblDifficulty.top = new FormAttachment(cmbCategory, 8);
		fd_lblDifficulty.left = new FormAttachment(0, 6);
		lblDifficulty.setLayoutData(fd_lblDifficulty);
		lblDifficulty.setText(Constants.DIFFICULTY + ":");
		
		Button btnAllDifficulties = new Button(cmpPuzzleSearch, SWT.CHECK);
		btnAllDifficulties.setEnabled(false);
		FormData fd_btnAllDifficulties = new FormData();
		fd_btnAllDifficulties.top = new FormAttachment(lblDifficulty, 4);
		fd_btnAllDifficulties.left = new FormAttachment(0, 6);
		btnAllDifficulties.setLayoutData(fd_btnAllDifficulties);
		btnAllDifficulties.setText(Constants.ALL_DIFFICULTIES);
		btnAllDifficulties.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Button btnEasy = new Button(cmpPuzzleSearch, SWT.CHECK);
		btnEasy.setEnabled(false);
		FormData fd_btnEasy = new FormData();
		fd_btnEasy.top = new FormAttachment(btnAllDifficulties, 4);
		fd_btnEasy.left = new FormAttachment(0, 6);
		btnEasy.setLayoutData(fd_btnEasy);
		btnEasy.setText(Constants.EASY);
		btnEasy.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Button btnMedium = new Button(cmpPuzzleSearch, SWT.CHECK);
		btnMedium.setEnabled(false);
		FormData fd_btnAverage = new FormData();
		fd_btnAverage.left = new FormAttachment(0, 6);
		fd_btnAverage.top = new FormAttachment(btnEasy, 4);
		btnMedium.setLayoutData(fd_btnAverage);
		btnMedium.setText(Constants.MEDIUM);
		btnMedium.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Button btnDifficult = new Button(cmpPuzzleSearch, SWT.CHECK);
		btnDifficult.setEnabled(false);
		FormData fd_btnDifficult = new FormData();
		fd_btnDifficult.top = new FormAttachment(btnMedium, 4);
		fd_btnDifficult.left = new FormAttachment(0, 6);
		btnDifficult.setLayoutData(fd_btnDifficult);
		btnDifficult.setText(Constants.HARD);
		btnDifficult.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
//		Button btnExpert = new Button(cmpPuzzleSearch, SWT.CHECK);
//		btnExpert.setEnabled(false);
//		FormData fd_btnExpert = new FormData();
//		fd_btnExpert.top = new FormAttachment(btnDifficult, 4);
//		fd_btnExpert.left = new FormAttachment(0, 6);
//		btnExpert.setLayoutData(fd_btnExpert);
//		btnExpert.setText("Expert");
//		btnExpert.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		// play the selected puzzle!
		Button btnPlaySelectedPuzzle = new Button(this, SWT.NONE);
		btnPlaySelectedPuzzle.setEnabled(false);
		FormData fd_btnPlaySelectedPuzzle = new FormData();
		fd_btnPlaySelectedPuzzle.right = new FormAttachment(separator, -10);
		fd_btnPlaySelectedPuzzle.bottom = new FormAttachment(100, -10);
		fd_btnPlaySelectedPuzzle.top = new FormAttachment(100, -36);
		fd_btnPlaySelectedPuzzle.left = new FormAttachment(lblFindAPuzzle, 0, SWT.LEFT);
		btnPlaySelectedPuzzle.setLayoutData(fd_btnPlaySelectedPuzzle);
		btnPlaySelectedPuzzle.setText(MessageConstants.SOLVE_SELECTED);
		
		// create a table viewer to show puzzles
		this.tableViewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		FormData fd_table = new FormData();
		fd_table.bottom = new FormAttachment(100, -10);
		fd_table.right = new FormAttachment(100, -10);
		fd_table.top = new FormAttachment(separator, 10, SWT.TOP);
		fd_table.left = new FormAttachment(separator, 6);
		table.setLayoutData(fd_table);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));

		// add business layer table driver
		this.puzzleResults = new ArrayList<Puzzle>();
		this.tableDriver = new PuzzleTableDriver(this.puzzleResults);

		// make the columns
		this.createColumns(this, this.tableViewer);

		// add a content provider
		this.tableViewer.setContentProvider(new ContentProvider());
		this.tableViewer.setInput(this.puzzleResults);
	}

	private void createColumns(final Composite parent, final TableViewer viewer)
	{
		String[] titles = {"Title", "Author", "Category", "Difficulty" };
		int[] bounds = { 100, 100, 100, 100 };

		// create each column
		for(int i = 0; i < titles.length; i++)
		{
			TableViewerColumn col = createTableViewerColumn(titles[i], bounds[i], i);
			col.setLabelProvider(this.tableDriver.getColumnLabelProvider(titles[i]));
		}
	}

	private TableViewerColumn createTableViewerColumn(String title, int width, final int colNumber)
	{
	    final TableViewerColumn viewerColumn = new TableViewerColumn(this.tableViewer, SWT.NONE);
	    final TableColumn column = viewerColumn.getColumn();
	    column.setText(title);
	    column.setWidth(width);
	    column.setResizable(true);
	    return viewerColumn;
	}

	@Override
	protected void checkSubclass()
	{
		// Disable the check that prevents subclassing of SWT components
	}

	public void onLoad()
	{
		this.tableDriver.refresh();
		this.tableViewer.setInput(this.puzzleResults);
	}
}
