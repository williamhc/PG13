package pg13.presentation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import pg13.models.Cryptogram;
import org.eclipse.swt.widgets.Label;

public class PlayScreen extends Composite 
{
	private Cryptogram workingPuzzle;		// the puzzle we are playing
	private PuzzlePropertiesWidget cmpProperties;	// the properties area
	private CryptogramSolveWidget cmpSolveWidget;	// the area to solve the puzzle
	
	/**
	 * Creates and populates the play screen.
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
		
		// separator between properties and play area
		Label separator = new Label(this, SWT.SEPARATOR | SWT.VERTICAL);
		FormData fd_separator = new FormData();
		fd_separator.left = new FormAttachment(33);
		fd_separator.bottom = new FormAttachment(100);
		fd_separator.top = new FormAttachment(0);
		separator.setLayoutData(fd_separator);
		
		// puzzle's general properties
		cmpProperties = new PuzzlePropertiesWidget(this, SWT.NONE, workingPuzzle, false);
		FormData fd_cmpProperties = new FormData();
		fd_cmpProperties.top = new FormAttachment(0);
		fd_cmpProperties.left = new FormAttachment(0);
		fd_cmpProperties.bottom = new FormAttachment(100);
		fd_cmpProperties.right = new FormAttachment(33);
		cmpProperties.setLayoutData(fd_cmpProperties);
		
		// puzzle solve widget, for now is only cryptogram
		cmpSolveWidget = new CryptogramSolveWidget(this, SWT.NONE, workingPuzzle);
		FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(100);
		fd_composite.right = new FormAttachment(100);
		fd_composite.top = new FormAttachment(0);
		fd_composite.left = new FormAttachment(33);
		cmpSolveWidget.setLayoutData(fd_composite);
		setTabList(new Control[]{cmpProperties, cmpSolveWidget});
	}

	/**
	 * Sets the puzzle being played
	 * @param newPuzzle
	 * @date June 19 2013
	 */
	public void setPuzzle(Cryptogram newPuzzle)
	{
		workingPuzzle = newPuzzle;
		cmpProperties.setPuzzle(newPuzzle);
		cmpSolveWidget.setCryptogram(newPuzzle);
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
