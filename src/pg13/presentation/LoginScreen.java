package pg13.presentation;

import java.util.ArrayList;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import pg13.org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import pg13.business.create.UserManager;
import pg13.models.User;

public class LoginScreen extends Composite 
{
	private UserManager userManager;
	private Combo cmbUsernames;
	/**
	 * Create and populates the login screen.
	 * @param parent
	 * @param style
	 */
	public LoginScreen(Composite parent, int style) 
	{
		super(parent, style);
		userManager = new UserManager();
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		setLayout(new FormLayout());
		
		cmbUsernames = new Combo(this, SWT.READ_ONLY);
		cmbUsernames.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		
		populateUserList();
		
		FormData fd_cmbUsernames = new FormData();
		fd_cmbUsernames.top = new FormAttachment(0, 200);
		fd_cmbUsernames.left = new FormAttachment(50, -100);
		fd_cmbUsernames.bottom = new FormAttachment(100, -69);
		fd_cmbUsernames.right = new FormAttachment(50, 100);
		cmbUsernames.setLayoutData(fd_cmbUsernames);
		
		Label lblLoginInfo = new Label(this, SWT.WRAP | SWT.SHADOW_IN);
		lblLoginInfo.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblLoginInfo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_lblLoginInfo = new FormData();
		fd_lblLoginInfo.top = new FormAttachment(0, 75);
		fd_lblLoginInfo.bottom = new FormAttachment(0, 175);
		fd_lblLoginInfo.left = new FormAttachment(0, 162);
		fd_lblLoginInfo.right = new FormAttachment(100, -162);
		lblLoginInfo.setLayoutData(fd_lblLoginInfo);
		lblLoginInfo.setText("Select your username from the list below. If you do not have a username you may click cancel and go sign up or select Guest from the drop down.");
		
		ControlDecoration controlDecoration = new ControlDecoration(lblLoginInfo, SWT.LEFT | SWT.TOP);
		controlDecoration.setMarginWidth(10);
		controlDecoration.setImage(SWTResourceManager.getImage(SignUpScreen.class, "/javax/swing/plaf/metal/icons/Inform.gif"));
		controlDecoration.setDescriptionText("Some description");
		
		Button btnLogMeIn = new Button(this, SWT.NONE);
		btnLogMeIn.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				loginPressed();
			}
		});
		btnLogMeIn.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		FormData fd_btnLogMeIn = new FormData();
		fd_btnLogMeIn.left = new FormAttachment(cmbUsernames, 0, SWT.LEFT);
		btnLogMeIn.setLayoutData(fd_btnLogMeIn);
		btnLogMeIn.setText("Log Me In!");
		
		Button btnCancel = new Button(this, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				MainWindow.getInstance().switchToWelcomeScreen();
			}
		});
		fd_btnLogMeIn.top = new FormAttachment(btnCancel, 0, SWT.TOP);
		btnCancel.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		FormData fd_btnCancel = new FormData();
		fd_btnCancel.bottom = new FormAttachment(0, 330);
		fd_btnCancel.right = new FormAttachment(cmbUsernames, 0, SWT.RIGHT);
		btnCancel.setLayoutData(fd_btnCancel);
		btnCancel.setText("Cancel");

	}

	private void populateUserList() 
	{
		//convert arraylist of users from db to an array of strings
		String [] users = new String [userManager.getNamesOfAllUsers().size()];
		userManager.getNamesOfAllUsers().toArray(users);
		cmbUsernames.setItems(users);
	}
	
	public void refresh()
	{
		populateUserList();
	}
	
	private void loginPressed()
	{
		ArrayList<User> allUsers = userManager.getAllUsers();
		int selected = this.cmbUsernames.getSelectionIndex();
		
		if(selected >= 0)
		{
			MainWindow.getInstance().login(allUsers.get(selected));
			MainWindow.getInstance().switchToWelcomeScreen();
		}
	}

	@Override
	protected void checkSubclass() 
	{
		// Disable the check that prevents subclassing of SWT components
	}
}
