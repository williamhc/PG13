package pg13.presentation;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import pg13.models.Cryptogram;
import org.eclipse.swt.widgets.Control;

public class CreateScreen extends Composite
{
	private Cryptogram workingPuzzle; // the puzzle we are working on
	private PuzzlePropertiesWidget cmpProperties; // puzzle properties area
	private CryptogramEditWidget cmpEditWidget; // editing area

	public CreateScreen(Composite parent, int style)
	{
		super(parent, style);

		// create new puzzle to edit
		workingPuzzle = new Cryptogram();

		// set layout
		setLayout(new FormLayout());

		// puzzle's general properties
		cmpProperties = new PuzzlePropertiesWidget(this, SWT.NONE, workingPuzzle, true);
		FormData fd_cmpProperties = new FormData();
		fd_cmpProperties.top = new FormAttachment(0);
		fd_cmpProperties.left = new FormAttachment(0);
		fd_cmpProperties.bottom = new FormAttachment(100);
		fd_cmpProperties.right = new FormAttachment(33);
		cmpProperties.setLayoutData(fd_cmpProperties);

		// puzzle edit widget, for now is only cryptogram
		cmpEditWidget = new CryptogramEditWidget(this, SWT.NONE, workingPuzzle, true);
		FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(100);
		fd_composite.right = new FormAttachment(100);
		fd_composite.top = new FormAttachment(0);
		fd_composite.left = new FormAttachment(33);
		cmpEditWidget.setLayoutData(fd_composite);
		setTabList(new Control[] { cmpProperties, cmpEditWidget });

	}

	public void setPuzzle(Cryptogram newPuzzle)
	{
		workingPuzzle = newPuzzle;
		cmpProperties.setPuzzle(newPuzzle);
		cmpEditWidget.setCryptogram(newPuzzle);
	}

	@Override
	protected void checkSubclass()
	{
		// Disable the check that prevents subclassing of SWT components
	}
}
