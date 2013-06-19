package pg13.presentation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import pg13.models.Cryptogram;

public class PlayScreen extends Composite 
{
	private Cryptogram workingPuzzle;		// the puzzle we are playing
	
	/**
	 * Creates and populates the play screen.
	 * @author Eric
	 * @param parent
	 * @param style
	 * @date June 19 2013
	 */
	public PlayScreen(Composite parent, int style) 
	{
		super(parent, style);
		
		// create new puzzle to edit
		workingPuzzle = new Cryptogram();
		
		// set layout
		setLayout(new FormLayout());
		
		// puzzle's general properties
		PuzzlePropertiesWidget cmpProperties = new PuzzlePropertiesWidget(this, SWT.NONE, workingPuzzle, false);
		FormData fd_cmpProperties = new FormData();
		fd_cmpProperties.top = new FormAttachment(0);
		fd_cmpProperties.left = new FormAttachment(0);
		fd_cmpProperties.bottom = new FormAttachment(100);
		fd_cmpProperties.right = new FormAttachment(33);
		cmpProperties.setLayoutData(fd_cmpProperties);
		
		// puzzle solve widget, for now is only cryptogram
		CryptogramSolveWidget cmpSolveWidget = new CryptogramSolveWidget(this, SWT.NONE, workingPuzzle);
		FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(100);
		fd_composite.right = new FormAttachment(100);
		fd_composite.top = new FormAttachment(0);
		fd_composite.left = new FormAttachment(33);
		cmpSolveWidget.setLayoutData(fd_composite);
		setTabList(new Control[]{cmpProperties, cmpSolveWidget});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}