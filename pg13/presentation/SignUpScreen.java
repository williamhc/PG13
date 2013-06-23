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
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		setLayout(new FormLayout());
		
		txtLogIn = new Text(this, SWT.BORDER);
		txtLogIn.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		FormData fd_txtLogIn = new FormData();
		fd_txtLogIn.top = new FormAttachment(0, 157);
		fd_txtLogIn.right = new FormAttachment(100, -162);
		fd_txtLogIn.left = new FormAttachment(0, 162);
		fd_txtLogIn.bottom = new FormAttachment(0, 192);
		txtLogIn.setLayoutData(fd_txtLogIn);
		
		Label lblLoginInfo = new Label(this, SWT.NONE);
		FormData fd_lblLoginInfo = new FormData();
		fd_lblLoginInfo.right = new FormAttachment(txtLogIn, 0, SWT.RIGHT);
		fd_lblLoginInfo.top = new FormAttachment(0, 50);
		fd_lblLoginInfo.left = new FormAttachment(txtLogIn, 0, SWT.LEFT);
		fd_lblLoginInfo.bottom = new FormAttachment(0, 118);
		lblLoginInfo.setLayoutData(fd_lblLoginInfo);
		lblLoginInfo.setText("Blurby Blurby Blah Blah Blah");
		
		ControlDecoration controlDecoration = new ControlDecoration(lblLoginInfo, SWT.LEFT | SWT.TOP);
		controlDecoration.setImage(SWTResourceManager.getImage(SignUpScreen.class, "/javax/swing/plaf/metal/icons/Inform.gif"));
		controlDecoration.setDescriptionText("Some description");

	}

	@Override
	protected void checkSubclass() 
	{
		// Disable the check that prevents subclassing of SWT components
	}

}
