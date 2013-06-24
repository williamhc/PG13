package pg13.presentation;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import pg13.org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import pg13.business.create.UserManager;
import pg13.models.User;

public class SignUpScreen extends Composite 
{
	private UserManager userManager;
	private Label lblInvalidUser;
	private ControlDecoration invalidUserDecor;
	private Text txtLogIn;

	/**
	 * Creates and populates the sign up screen.
	 * @param parent
	 * @param style
	 * @date June 23 2013
	 */
	public SignUpScreen(Composite parent, int style) 
	{
		super(parent, style);
		
		userManager = new UserManager();
		
		setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		setLayout(new FormLayout());
		
		txtLogIn = new Text(this, SWT.BORDER);
		txtLogIn.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		txtLogIn.setTextLimit(15);
		FormData fd_txtLogIn = new FormData();
		fd_txtLogIn.left = new FormAttachment(0, 162);
		fd_txtLogIn.bottom = new FormAttachment(0, 192);
		fd_txtLogIn.right = new FormAttachment(100, -162);
		txtLogIn.setLayoutData(fd_txtLogIn);
		
		Label lblLoginInfo = new Label(this, SWT.WRAP | SWT.SHADOW_IN);
		lblLoginInfo.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblLoginInfo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_lblLoginInfo = new FormData();
		fd_lblLoginInfo.top = new FormAttachment(0, 75);
		fd_lblLoginInfo.bottom = new FormAttachment(0, 150);
		fd_lblLoginInfo.left = new FormAttachment(0, 162);
		fd_lblLoginInfo.right = new FormAttachment(100, -162);
		lblLoginInfo.setLayoutData(fd_lblLoginInfo);
		lblLoginInfo.setText("Create a Username with 1 - 15 characters.  Valid characters include A-Z, a-z, 0-9.  No spaces are allowed.");
		
		ControlDecoration signUpInfoImage = new ControlDecoration(lblLoginInfo, SWT.LEFT | SWT.TOP);
		signUpInfoImage.setMarginWidth(10);
		signUpInfoImage.setImage(SWTResourceManager.getImage(SignUpScreen.class, "/javax/swing/plaf/metal/icons/Inform.gif"));
		signUpInfoImage.setDescriptionText("Some description");
		
		lblInvalidUser = new Label(this, SWT.WRAP);
		lblInvalidUser.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblInvalidUser.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblInvalidUser.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		FormData fd_lblInvalidUser = new FormData();
		fd_lblInvalidUser.bottom = new FormAttachment(txtLogIn, 93, SWT.BOTTOM);
		fd_lblInvalidUser.right = new FormAttachment(txtLogIn, 0, SWT.RIGHT);
		fd_lblInvalidUser.top = new FormAttachment(txtLogIn, 19);
		fd_lblInvalidUser.left = new FormAttachment(txtLogIn, 0, SWT.LEFT);
		lblInvalidUser.setLayoutData(fd_lblInvalidUser);
		lblInvalidUser.setText("That username is already taken or is invalid.");
		lblInvalidUser.setVisible(false);
		
		
		
		Button btnSignMeUp = new Button(this, SWT.NONE);
		btnSignMeUp.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				createUser();
			}
		});
		btnSignMeUp.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		FormData fd_btnSignMeUp = new FormData();
		fd_btnSignMeUp.top = new FormAttachment(txtLogIn, 110);
		fd_btnSignMeUp.left = new FormAttachment(txtLogIn, 0, SWT.LEFT);
		btnSignMeUp.setLayoutData(fd_btnSignMeUp);
		btnSignMeUp.setText("Sign Me Up!");
		
		Button btnCancel = new Button(this, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				MainWindow.getInstance().switchToWelcomeScreen();
				clearLoginScreen();
			}
		});
		btnCancel.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		FormData fd_btnCancel = new FormData();
		fd_btnCancel.bottom = new FormAttachment(btnSignMeUp, 0, SWT.BOTTOM);
		fd_btnCancel.right = new FormAttachment(txtLogIn, 0, SWT.RIGHT);
		btnCancel.setLayoutData(fd_btnCancel);
		btnCancel.setText("Cancel");
	
		invalidUserDecor = new ControlDecoration(lblInvalidUser, SWT.LEFT | SWT.TOP);
		invalidUserDecor.setMarginWidth(10);
		invalidUserDecor.setImage(SWTResourceManager.getImage(SignUpScreen.class, "/javax/swing/plaf/metal/icons/Error.gif"));
		invalidUserDecor.setDescriptionText("Some description");

	}
	
	private void clearLoginScreen() 
	{
		txtLogIn.setText("");
		lblInvalidUser.setVisible(false);
	}
	
	private void createUser()
	{
		String name = txtLogIn.getText();
		User user = userManager.addUser(name);
		if(user != null)
		{
			clearLoginScreen();
			MessageBox dialog = new MessageBox(this.getShell() , SWT.ICON_INFORMATION | SWT.OK);
			dialog.setText("Success!");
			dialog.setMessage("Successfully Created a user");
			dialog.open(); 
			MainWindow.getInstance().login(user);
			MainWindow.getInstance().switchToWelcomeScreen();
		}
		else
		{
			lblInvalidUser.setVisible(true);
			this.redraw();
		}		
	}
	@Override
	protected void checkSubclass() 
	{
		// Disable the check that prevents subclassing of SWT components
	}
}
