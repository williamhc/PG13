package pg13.presentation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.graphics.Point;

/**
 * The main window for the application.
 * @author Eric
 */
public class MainWindow 
{
	private Display display;
	private Shell shell;
	private CreateScreen cmpCreateScreen;
	private Label lblLoggedInAs;

	/**
	 * Constructor -- Launches an instance of the window when it is created
	 * @author Eric
	 * @date May 26 2013
	 */
	public MainWindow()
	{
        display = Display.getDefault();
		createWindow();
		runWindow();
	}

	/**
	 * Displays the main window.
	 * @author Eric
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
     * @author Eric
     * @date May 26 2013
     */
    public void createWindow()
	{
		shell = new Shell();
		shell.setMinimumSize(new Point(640, 480));
		shell.setSize(692, 616);
		shell.setLocation(150,100);
		shell.setText("PG13");
		shell.setLayout(new FormLayout());
		
		// play button
		// TODO make this button do something
		Button btnPlay = new Button(shell, SWT.NONE);
		FormData fd_btnPlay = new FormData();
		fd_btnPlay.right = new FormAttachment(0, 86);
		fd_btnPlay.top = new FormAttachment(0, 11);
		fd_btnPlay.left = new FormAttachment(0, 5);
		btnPlay.setLayoutData(fd_btnPlay);
		btnPlay.setText("Play");
		
		// connect button
		// TODO make this button do something
		Button btnConnect = new Button(shell, SWT.NONE);
		FormData fd_btnConnect = new FormData();
		fd_btnConnect.right = new FormAttachment(0, 172);
		fd_btnConnect.top = new FormAttachment(0, 11);
		fd_btnConnect.left = new FormAttachment(0, 91);
		btnConnect.setLayoutData(fd_btnConnect);
		btnConnect.setText("Connect");
		
		// create button
		Button btnCreate = new Button(shell, SWT.NONE);
		btnCreate.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				// show the create screen
				cmpCreateScreen.setVisible(true);
			}
		});
		FormData fd_btnCreate = new FormData();
		fd_btnCreate.right = new FormAttachment(0, 258);
		fd_btnCreate.top = new FormAttachment(0, 11);
		fd_btnCreate.left = new FormAttachment(0, 177);
		btnCreate.setLayoutData(fd_btnCreate);
		btnCreate.setText("Create");
		
		// container for user login info
		Composite cmpLogin = new Composite(shell, SWT.BORDER);
		FormData fd_cmpLogin = new FormData();
		fd_cmpLogin.right = new FormAttachment(100, -5);
		fd_cmpLogin.top = new FormAttachment(0, 5);
		fd_cmpLogin.left = new FormAttachment(100, -350);
		cmpLogin.setLayoutData(fd_cmpLogin);
		cmpLogin.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		cmpLogin.setLayout(new GridLayout(2, false));
		
		// label that identifies 
		lblLoggedInAs = new Label(cmpLogin, SWT.NONE);
		lblLoggedInAs.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		lblLoggedInAs.setText("Logged in as Guest");
		
		// toolbar that contains the user buttons
		ToolBar toolBar = new ToolBar(cmpLogin, SWT.FLAT);
		toolBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		
		// separator
		ToolItem tltmSeparator1 = new ToolItem(toolBar, SWT.SEPARATOR);
		tltmSeparator1.setText("sep");
		
		// my puzzles button
		ToolItem tltmMyPuzzles = new ToolItem(toolBar, SWT.NONE);
		tltmMyPuzzles.setText("My Puzzles");
		
		// separator
		ToolItem tltmSeparator2 = new ToolItem(toolBar, SWT.SEPARATOR);
		tltmSeparator2.setText("sep");
		
		// mystery button
		// TODO decide what this button does
		ToolItem tltmMystery = new ToolItem(toolBar, SWT.NONE);
		tltmMystery.setText("Something Else");
		
		// separator
		ToolItem tltmSeparator3 = new ToolItem(toolBar, SWT.SEPARATOR);
		tltmSeparator3.setSelection(true);
		tltmSeparator3.setEnabled(true);
		tltmSeparator3.setText("sep");
		
		// login button
		ToolItem tltmLogin = new ToolItem(toolBar, SWT.NONE);
		tltmLogin.setText("Login");
		
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
		fd_cmpMainArea.bottom = new FormAttachment(100, -10);
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
		
		// TODO load a play screen here as well, as with the create screen
		
		// show the window
		shell.open();
	}
}

