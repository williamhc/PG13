package pg13.presentation;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;

public class CreateScreen extends Composite {

	/**
	 * Creates and populates the create screen.
	 * @author Eric
	 * @param parent
	 * @param style
	 * @date May 26 2013
	 */
	public CreateScreen(Composite parent, int style) {
		super(parent, style);
		setLayout(new FormLayout());
		
		// stub just to show that the screen is in fact being displayed
		// TODO fill in the rest of this window, I'll get right on this... -- Eric
		Label lblStubby = new Label(this, SWT.NONE);
		FormData fd_lblStubby = new FormData();
		fd_lblStubby.bottom = new FormAttachment(100);
		fd_lblStubby.right = new FormAttachment(100);
		fd_lblStubby.top = new FormAttachment(0, 100);
		fd_lblStubby.left = new FormAttachment(0);
		lblStubby.setLayoutData(fd_lblStubby);
		lblStubby.setAlignment(SWT.CENTER);
		lblStubby.setFont(SWTResourceManager.getFont("Segoe UI", 28, SWT.NORMAL));
		lblStubby.setText("Create Screen Stub");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}

