package pg13.presentation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.graphics.Point;

import pg13.business.create.UserManager;
import pg13.models.Cryptogram;
import pg13.models.Puzzle;
import pg13.models.User;

/**
 * The main window for the application.
 */
public class MainWindow 
{
	private static MainWindow instance;
	
	private Display display;
	private Shell shell;
	private CreateScreen cmpCreateScreen;
	private FindScreen cmpFindScreen;
	private PlayScreen cmpPlayScreen;
	private Label lblLoggedInAs;
	private UserManager userManager;
	private User loggedInUser;

	/**
	 * gets the instance of the main window
	 * @date June 19 2013
	 */
	public static MainWindow getInstance()
	{
		if (instance == null)
		{
			instance = new MainWindow();
		}
		
		return instance;
	}
	
	/**
	 * Constructor -- creates an instance of the window when it is created
	 * @date May 26 2013
	 */
	private MainWindow()
	{
        display = Display.getDefault();
        this.userManager = new UserManager();
        loggedInUser = this.userManager.findUser(userManager.getGuestPrimaryKey()); //log in as guest
		createWindow();
	}
	
	public User getLoggedInUser()
	{
		return this.loggedInUser;
	}

	/**
	 * Displays the main window.
	 * @date May 26 2013
	 */
    public void runWindow()
    {
		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch())
			{
				display.sleep();
			}
		}
		display.dispose();
    }

    /**
     * Creates and populates the main window
     * @date May 26 2013
     */
    public void createWindow()
	{
		shell = new Shell();
		shell.setMinimumSize(new Point(640, 480));
		shell.setSize(692, 616);
		shell.setLocation(150,100);
		shell.setText(Constants.PG13);
		shell.setLayout(new FormLayout());
		
		// play button
		Button btnPlay = new Button(shell, SWT.NONE);
		btnPlay.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				playPressed();
			}
		});
		FormData fd_btnPlay = new FormData();
		fd_btnPlay.right = new FormAttachment(0, 86);
		fd_btnPlay.top = new FormAttachment(0, 11);
		fd_btnPlay.left = new FormAttachment(0, 5);
		btnPlay.setLayoutData(fd_btnPlay);
		btnPlay.setText(Constants.PLAY);
		
		// connect button
		Button btnConnect = new Button(shell, SWT.NONE);
		btnConnect.setEnabled(false);
		FormData fd_btnConnect = new FormData();
		fd_btnConnect.right = new FormAttachment(0, 172);
		fd_btnConnect.top = new FormAttachment(0, 11);
		fd_btnConnect.left = new FormAttachment(0, 91);
		btnConnect.setLayoutData(fd_btnConnect);
		btnConnect.setText(Constants.CONNECT);
		
		// create button
		Button btnCreate = new Button(shell, SWT.NONE);
		btnCreate.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				createPressed();
			}
		});
		FormData fd_btnCreate = new FormData();
		fd_btnCreate.right = new FormAttachment(0, 258);
		fd_btnCreate.top = new FormAttachment(0, 11);
		fd_btnCreate.left = new FormAttachment(0, 177);
		btnCreate.setLayoutData(fd_btnCreate);
		btnCreate.setText(Constants.CREATE);
		
		// container for user login info
		Composite cmpLogin = new Composite(shell, SWT.BORDER);
		FormData fd_cmpLogin = new FormData();
		fd_cmpLogin.right = new FormAttachment(100, -5);
		fd_cmpLogin.top = new FormAttachment(0, 5);
		fd_cmpLogin.left = new FormAttachment(100, -350);
		cmpLogin.setLayoutData(fd_cmpLogin);
		cmpLogin.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		cmpLogin.setLayout(new GridLayout(2, false));
		
		// label that identifies 
		lblLoggedInAs = new Label(cmpLogin, SWT.NONE);
		lblLoggedInAs.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		//TODO change this to pull from user
		lblLoggedInAs.setText(MessageConstants.GUEST_LOGON);
		
		// toolbar that contains the user buttons
		ToolBar toolBar = new ToolBar(cmpLogin, SWT.FLAT);
		toolBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		
		// separator
		ToolItem tltmSeparator1 = new ToolItem(toolBar, SWT.SEPARATOR);
		tltmSeparator1.setText("sep");
		
		// my puzzles button
		ToolItem tltmMyPuzzles = new ToolItem(toolBar, SWT.NONE);
		tltmMyPuzzles.setEnabled(false);
		tltmMyPuzzles.setText(Constants.MY_PUZZLES);
		
		// separator
		ToolItem tltmSeparator2 = new ToolItem(toolBar, SWT.SEPARATOR);
		tltmSeparator2.setText("sep");
		
		// mystery button
		ToolItem tltmMystery = new ToolItem(toolBar, SWT.NONE);
		tltmMystery.setEnabled(false);
		tltmMystery.setText(Constants.SOMETHING_ELSE);
		
		// separator
		ToolItem tltmSeparator3 = new ToolItem(toolBar, SWT.SEPARATOR);
		tltmSeparator3.setSelection(true);
		tltmSeparator3.setEnabled(true);
		tltmSeparator3.setText("sep");
		
		// login button
		ToolItem tltmLogin = new ToolItem(toolBar, SWT.NONE);
		tltmLogin.setEnabled(false);
		tltmLogin.setText(Constants.LOGIN);
		
		// horizontal line
		Label label = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		FormData fd_label = new FormData();
		fd_label.right = new FormAttachment(100, -5);
		fd_label.top = new FormAttachment(0, 46);
		fd_label.left = new FormAttachment(0, 5);
		label.setLayoutData(fd_label);
		
		// main area, where the create and play screens will be contained
		Composite cmpMainArea = new Composite(shell, SWT.BORDER);
		cmpMainArea.setLayout(new FormLayout());
		FormData fd_cmpMainArea = new FormData();
		fd_cmpMainArea.bottom = new FormAttachment(100, -35);
		fd_cmpMainArea.right = new FormAttachment(100, -5);
		fd_cmpMainArea.top = new FormAttachment(0, 50);
		fd_cmpMainArea.left = new FormAttachment(0, 5);
		cmpMainArea.setLayoutData(fd_cmpMainArea);
		cmpMainArea.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		// load the create screen, but hide it
		cmpCreateScreen = new CreateScreen(cmpMainArea, SWT.NONE);
		FormData fd_cmpCreateScreen = new FormData();
		fd_cmpCreateScreen.bottom = new FormAttachment(100);
		fd_cmpCreateScreen.right = new FormAttachment(100);
		fd_cmpCreateScreen.top = new FormAttachment(0);
		fd_cmpCreateScreen.left = new FormAttachment(0);
		cmpCreateScreen.setLayoutData(fd_cmpCreateScreen);
		cmpCreateScreen.setVisible(false);
		
		// load the create screen, but hide it
		cmpFindScreen = new FindScreen(cmpMainArea, SWT.NONE);
		FormData fd_cmpFindScreen = new FormData();
		fd_cmpFindScreen.bottom = new FormAttachment(100);
		fd_cmpFindScreen.right = new FormAttachment(100);
		fd_cmpFindScreen.top = new FormAttachment(0);
		fd_cmpFindScreen.left = new FormAttachment(0);
		cmpFindScreen.setLayoutData(fd_cmpFindScreen);
		cmpFindScreen.setVisible(false);
		
		// load the play screen, but hide it
		cmpPlayScreen = new PlayScreen(cmpMainArea, SWT.NONE);
		FormData fd_cmpPlayScreen = new FormData();
		fd_cmpPlayScreen.bottom = new FormAttachment(100);
		fd_cmpPlayScreen.right = new FormAttachment(100);
		fd_cmpPlayScreen.top = new FormAttachment(0);
		fd_cmpPlayScreen.left = new FormAttachment(0);
		cmpPlayScreen.setLayoutData(fd_cmpPlayScreen);
		cmpPlayScreen.setVisible(false);
		
		// welcome message
		Label lblWelcome = new Label(cmpMainArea, SWT.CENTER);
		lblWelcome.setFont(SWTResourceManager.getFont("Segoe UI", 22, SWT.NORMAL));
		lblWelcome.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_lblWelcome = new FormData();
		fd_lblWelcome.top = new FormAttachment(50, -130);
		fd_lblWelcome.right = new FormAttachment(50, 220);
		fd_lblWelcome.left = new FormAttachment(50, -220);
		lblWelcome.setLayoutData(fd_lblWelcome);
		lblWelcome.setText(MessageConstants.WELCOME_HEADER);
		
		Label lblWelcomeDescription = new Label(cmpMainArea, SWT.WRAP);
		lblWelcomeDescription.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblWelcomeDescription.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_lblWelcomeDescription = new FormData();
		fd_lblWelcomeDescription.top = new FormAttachment(lblWelcome, 14);
		fd_lblWelcomeDescription.bottom = new FormAttachment(lblWelcome, 200);
		fd_lblWelcomeDescription.right = new FormAttachment(50, 200);
		fd_lblWelcomeDescription.left = new FormAttachment(50, -200);
		lblWelcomeDescription.setLayoutData(fd_lblWelcomeDescription);
		lblWelcomeDescription.setText(MessageConstants.APPLICATION_INSTRUCTIONS);
		
		// Quit button
		Button btnQuit = new Button(shell, SWT.NONE);
		btnQuit.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				shell.dispose();
			}
		});
		FormData fd_btnQuit = new FormData();
		fd_btnQuit.left = new FormAttachment(100, -100);
		fd_btnQuit.bottom = new FormAttachment(100, -6);
		fd_btnQuit.right = new FormAttachment(100, -5);
		btnQuit.setLayoutData(fd_btnQuit);
		btnQuit.setText(Constants.QUIT);
		
		Label lblVersion = new Label(shell, SWT.NONE);
		FormData fd_lblVersion = new FormData();
		fd_lblVersion.bottom = new FormAttachment(btnQuit, 0, SWT.BOTTOM);
		fd_lblVersion.left = new FormAttachment(0, 10);
		lblVersion.setLayoutData(fd_lblVersion);
		lblVersion.setText(Constants.VERSION);
		
		
		// show the window
		shell.open();
	}
    
    /**
	 * Opens up the play screen for the given puzzle, currently only cryptograms supported.
	 * @date June 19 2013
	 */
    public void playPuzzle(Puzzle puzzle)
    {
    	// currently only cryptograms supported
    	if(puzzle instanceof Cryptogram)
    	{
    		cmpPlayScreen.setPuzzle((Cryptogram) puzzle);
    	}
    	
    	switchToPlayScreen();
    }
    
    /**
	 * Performs the actions for when the create button is pressed
	 * @date June 19 2013
	 */
    private void createPressed()
    {
    	// create a new puzzle to edit
    	cmpCreateScreen.setPuzzle(new Cryptogram());
    	
    	// show the create screen and hide other screens
		switchToCreateScreen();
    }
    
    /**
	 * Performs the actions for when the play button is pressed
	 * @date June 19 2013
	 */
    private void playPressed()
    {
    	// show the find screen and hide other screens
		switchToFindScreen();
		
		// refresh find screen
		cmpFindScreen.onLoad();
    }
    
    /**
	 * hides all views in the main window
	 * @date June 19 2013
	 */
    private void hideAllViews()
    {
    	cmpCreateScreen.setVisible(false);
		cmpFindScreen.setVisible(false);
		cmpPlayScreen.setVisible(false);
    }
    
    /**
	 * switches the view to the create screen
	 * @date June 19 2013
	 */
    public void switchToCreateScreen()
    {
    	hideAllViews();
    	cmpCreateScreen.setVisible(true);
    }
    
    /**
	 * switches the view to the find screen
	 * @date June 19 2013
	 */
    public void switchToFindScreen()
    {
    	hideAllViews();
    	cmpFindScreen.setVisible(true);
    }
    
    /**
	 * switches the view to the play screen
	 * @date June 19 2013
	 */
    public void switchToPlayScreen()
    {
    	hideAllViews();
    	cmpPlayScreen.setVisible(true);
    }
    
    /**
	 * switches the view to the welcome screen
	 * @date June 19 2013
	 */
    public void switchToWelcomeScreen()
    {
    	hideAllViews();
    }
}

