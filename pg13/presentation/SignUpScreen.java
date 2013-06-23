package pg13.presentation;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;
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
import org.eclipse.ui.internal.SwitchToWindowMenu;

public class SignUpScreen extends Composite 
{
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
		fd_lblLoginInfo.top = new FormAttachment(0, 50);
		fd_lblLoginInfo.bottom = new FormAttachment(0, 150);
		fd_lblLoginInfo.left = new FormAttachment(0, 162);
		fd_lblLoginInfo.right = new FormAttachment(100, -162);
		lblLoginInfo.setLayoutData(fd_lblLoginInfo);
		lblLoginInfo.setText("Create a Username with 5 - 15 characters.  Valid characters include A-Z, a-z, 0-9 and the following punctuation: .,-_!\"$.  No spaces are allowed.");
		
		ControlDecoration controlDecoration = new ControlDecoration(lblLoginInfo, SWT.LEFT | SWT.TOP);
		controlDecoration.setMarginWidth(10);
		controlDecoration.setImage(SWTResourceManager.getImage(SignUpScreen.class, "/javax/swing/plaf/metal/icons/Inform.gif"));
		controlDecoration.setDescriptionText("Some description");
		
		Button btnSignMeUp = new Button(this, SWT.NONE);
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
			}
		});
		btnCancel.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		FormData fd_btnCancel = new FormData();
		fd_btnCancel.bottom = new FormAttachment(btnSignMeUp, 0, SWT.BOTTOM);
		fd_btnCancel.right = new FormAttachment(txtLogIn, 0, SWT.RIGHT);
		btnCancel.setLayoutData(fd_btnCancel);
		btnCancel.setText("Cancel :(");

	}

	@Override
	protected void checkSubclass() 
	{
		// Disable the check that prevents subclassing of SWT components
	}
}
