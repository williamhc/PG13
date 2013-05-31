package pg13.presentation;

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
import org.eclipse.swt.custom.TableTree;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.custom.ScrolledComposite;

public class FindScreen extends Composite 
{
	private Text txtTitle;
	private Text txtAuthor;
	private Table table;

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
		lblFindAPuzzle.setText("Find Puzzles:");
		
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
		btnAllPuzzles.setText("All Puzzles");
		btnAllPuzzles.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Button btnFriendsPuzzles = new Button(cmpPuzzleFilter, SWT.RADIO);
		FormData fd_btnFriendsPuzzles = new FormData();
		fd_btnFriendsPuzzles.top = new FormAttachment(0, 28);
		fd_btnFriendsPuzzles.right = new FormAttachment(100, -6);
		fd_btnFriendsPuzzles.left = new FormAttachment(0, 6);
		btnFriendsPuzzles.setLayoutData(fd_btnFriendsPuzzles);
		btnFriendsPuzzles.setText("My Friends' Puzzles");
		btnFriendsPuzzles.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Button btnMyPuzzles = new Button(cmpPuzzleFilter, SWT.RADIO);
		FormData fd_btnMyPuzzles = new FormData();
		fd_btnMyPuzzles.right = new FormAttachment(100, -6);
		fd_btnMyPuzzles.top = new FormAttachment(0, 50);
		fd_btnMyPuzzles.left = new FormAttachment(0, 6);
		btnMyPuzzles.setLayoutData(fd_btnMyPuzzles);
		btnMyPuzzles.setText("My Puzzles");
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
		lblTitle.setText("Title:");
		
		txtTitle = new Text(cmpPuzzleSearch, SWT.BORDER);
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
		lblAuthor.setText("Author:");
		
		txtAuthor = new Text(cmpPuzzleSearch, SWT.BORDER);
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
		lblCategory.setText("Category:");
		
		Combo cmbCategory = new Combo(cmpPuzzleSearch, SWT.NONE);
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
		lblDifficulty.setText("Difficulty:");
		
		Button btnAllDifficulties = new Button(cmpPuzzleSearch, SWT.CHECK);
		FormData fd_btnAllDifficulties = new FormData();
		fd_btnAllDifficulties.top = new FormAttachment(lblDifficulty, 4);
		fd_btnAllDifficulties.left = new FormAttachment(0, 6);
		btnAllDifficulties.setLayoutData(fd_btnAllDifficulties);
		btnAllDifficulties.setText("All Difficulties");
		btnAllDifficulties.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Button btnEasy = new Button(cmpPuzzleSearch, SWT.CHECK);
		FormData fd_btnEasy = new FormData();
		fd_btnEasy.top = new FormAttachment(btnAllDifficulties, 4);
		fd_btnEasy.left = new FormAttachment(0, 6);
		btnEasy.setLayoutData(fd_btnEasy);
		btnEasy.setText("Easy");
		btnEasy.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Button btnAverage = new Button(cmpPuzzleSearch, SWT.CHECK);
		FormData fd_btnAverage = new FormData();
		fd_btnAverage.left = new FormAttachment(0, 6);
		fd_btnAverage.top = new FormAttachment(btnEasy, 4);
		btnAverage.setLayoutData(fd_btnAverage);
		btnAverage.setText("Average");
		btnAverage.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Button btnDifficult = new Button(cmpPuzzleSearch, SWT.CHECK);
		FormData fd_btnDifficult = new FormData();
		fd_btnDifficult.top = new FormAttachment(btnAverage, 4);
		fd_btnDifficult.left = new FormAttachment(0, 6);
		btnDifficult.setLayoutData(fd_btnDifficult);
		btnDifficult.setText("Difficult");
		btnDifficult.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Button btnExpert = new Button(cmpPuzzleSearch, SWT.CHECK);
		FormData fd_btnExpert = new FormData();
		fd_btnExpert.top = new FormAttachment(btnDifficult, 4);
		fd_btnExpert.left = new FormAttachment(0, 6);
		btnExpert.setLayoutData(fd_btnExpert);
		btnExpert.setText("Expert");
		btnExpert.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		// table which displays all the puzzles to play
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		FormData fd_table = new FormData();
		fd_table.bottom = new FormAttachment(100, -10);
		fd_table.right = new FormAttachment(100, -10);
		fd_table.top = new FormAttachment(0, 10);
		fd_table.left = new FormAttachment(separator, 10);
		table.setLayoutData(fd_table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnTitle = new TableColumn(table, SWT.NONE);
		tblclmnTitle.setMoveable(true);
		tblclmnTitle.setWidth(136);
		tblclmnTitle.setText("Title");
		
		TableColumn tblclmnAuthor = new TableColumn(table, SWT.NONE);
		tblclmnAuthor.setMoveable(true);
		tblclmnAuthor.setWidth(100);
		tblclmnAuthor.setText("Author");
		
		TableColumn tblclmnCategory = new TableColumn(table, SWT.NONE);
		tblclmnCategory.setMoveable(true);
		tblclmnCategory.setWidth(100);
		tblclmnCategory.setText("Category");
		
		TableColumn tblclmnDifficulty = new TableColumn(table, SWT.NONE);
		tblclmnDifficulty.setMoveable(true);
		tblclmnDifficulty.setWidth(100);
		tblclmnDifficulty.setText("Difficulty");
		
		// some test entries to play with
		TableItem testItem1 = new TableItem(table, SWT.NONE);
		testItem1.setText(new String[] {"This is nothing", "Eric", "Animals", "Easy"});
		
		TableItem testItem2 = new TableItem(table, SWT.NONE);
		testItem2.setText(new String[] {"This also does nothing", "Eric", "Science", "Average"});
		
		TableItem testItem3 = new TableItem(table, SWT.NONE);
		testItem3.setText(new String[] {"Is three enough?", "Eric", "Computers", "Easy"});
		
		TableItem testItem4 = new TableItem(table, SWT.NONE);
		testItem4.setText(new String[] {"Maybe four", "Eric", "Biology", "Difficult"});
		
		TableItem testItem5 = new TableItem(table, SWT.NONE);
		testItem5.setText(new String[] {"Yeah, definitely four", "Eric", "Space", "Expert"});
		
		TableItem testItem6 = new TableItem(table, SWT.NONE);
		testItem6.setText(new String[] {"Wait a minute..", "Eric", "Computers", "Difficult"});
		
		TableItem testItem7 = new TableItem(table, SWT.NONE);
		testItem7.setText(new String[] {"Oops", "Eric", "Animals", "Average"});
		
		TableItem testItem8 = new TableItem(table, SWT.NONE);
		testItem8.setText(new String[] {"Stop adding so many", "William", "Space", "Easy"});
		
		TableItem testItem9 = new TableItem(table, SWT.NONE);
		testItem9.setText(new String[] {"Wait...", "William", "Biology", "Average"});
		
		TableItem testItem10 = new TableItem(table, SWT.NONE);
		testItem10.setText(new String[] {"I can use these to test filters!", "William", "Sports", "Difficult"});
		
		TableItem testItem11 = new TableItem(table, SWT.NONE);
		testItem11.setText(new String[] {"And how long names look on this display!", "Eric", "Space", "Easy"});
		
		TableItem testItem12 = new TableItem(table, SWT.NONE);
		testItem12.setText(new String[] {"Yeah whatever", "William", "Science", "Average"});
		
		TableItem testItem13 = new TableItem(table, SWT.NONE);
		testItem13.setText(new String[] {"Don't make Will talk like that", "Lauren", "Computers", "Expert"});
		
		TableItem testItem14 = new TableItem(table, SWT.NONE);
		testItem14.setText(new String[] {"Hey...", "Lauren", "Biology", "Expert"});
		
		TableItem testItem15 = new TableItem(table, SWT.NONE);
		testItem15.setText(new String[] {"Stop it!", "Lauren", "Science", "Expert"});
		
		TableItem testItem16 = new TableItem(table, SWT.NONE);
		testItem16.setText(new String[] {"hehehe...", "Eric", "Politics", "Difficult"});
		
		TableItem testItem17 = new TableItem(table, SWT.NONE);
		testItem17.setText(new String[] {"I'm here, too", "Paymahn", "Politics", "Average"});
		
		TableItem testItem18 = new TableItem(table, SWT.NONE);
		testItem18.setText(new String[] {"Build failing...", "Travis", "Computers", "Easy"});
		
		TableItem testItem19 = new TableItem(table, SWT.NONE);
		testItem19.setText(new String[] {"Shut up, Travis", "Eric", "Science", "Difficult"});
		
		TableItem testItem20 = new TableItem(table, SWT.NONE);
		testItem20.setText(new String[] {"He's my friend", "William", "Computers", "Easy"});
		
		TableItem testItem21 = new TableItem(table, SWT.NONE);
		testItem21.setText(new String[] {"This should be enough to test vertical scrolling", "Eric", "Space", "Expert"});
		
		// play the selected puzzle!
		Button btnPlaySelectedPuzzle = new Button(this, SWT.NONE);
		FormData fd_btnPlaySelectedPuzzle = new FormData();
		fd_btnPlaySelectedPuzzle.right = new FormAttachment(separator, -10);
		fd_btnPlaySelectedPuzzle.bottom = new FormAttachment(100, -10);
		fd_btnPlaySelectedPuzzle.top = new FormAttachment(100, -36);
		fd_btnPlaySelectedPuzzle.left = new FormAttachment(lblFindAPuzzle, 0, SWT.LEFT);
		btnPlaySelectedPuzzle.setLayoutData(fd_btnPlaySelectedPuzzle);
		btnPlaySelectedPuzzle.setText("Play Selected Puzzle");
		
		

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
