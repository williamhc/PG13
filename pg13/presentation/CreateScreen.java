package pg13.presentation;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;

public class CreateScreen extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CreateScreen(Composite parent, int style) {
		super(parent, style);
		
		Label lblOmgHi = new Label(this, SWT.NONE);
		lblOmgHi.setBounds(93, 116, 55, 15);
		lblOmgHi.setText("OMG HI");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
