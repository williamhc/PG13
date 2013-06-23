package pg13.presentation;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;

public class LoginScreen extends Composite {

	/**
	 * Create and populates the login screen.
	 * @param parent
	 * @param style
	 */
	public LoginScreen(Composite parent, int style) 
	{
		super(parent, style);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		setLayout(new FormLayout());

	}

	@Override
	protected void checkSubclass() 
	{
		// Disable the check that prevents subclassing of SWT components
	}

}
