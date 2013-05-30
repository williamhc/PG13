package pg13.presentation;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import pg13.models.Cryptogram;
import org.eclipse.swt.widgets.Button;

public class CryptogramSolveWidget extends Composite 
{
	private ArrayList<CryptogramLetterWidget> letterWidgets;
	private Cryptogram solvingCryptogram;
	
	/**
	 * Creates and populates the cryptogram solving widget.
	 * @author Eric
	 * @param parent
	 * @param style
	 * @date May 29 2013
	 */
	public CryptogramSolveWidget(Composite parent, int style, Cryptogram solvingCryptogram) 
	{
		super(parent, style);
		
		this.solvingCryptogram = solvingCryptogram;
		
		addControlListener(new ControlAdapter() 
		{
			@Override
			public void controlResized(ControlEvent e) 
			{
				updateLetterWidgetLayout();
			}
		});
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		setLayout(new FormLayout());
		
		Button btnCheckSolution = new Button(this, SWT.NONE);
		FormData fd_btnCheckSolution = new FormData();
		fd_btnCheckSolution.bottom = new FormAttachment(100, -6);
		fd_btnCheckSolution.right = new FormAttachment(100, -6);
		btnCheckSolution.setLayoutData(fd_btnCheckSolution);
		btnCheckSolution.setText("Check Solution");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	/*
	 * Displays the cryptogram in the centre of the window
	 * @author Eric
	 * @date May 29 2013
	 * @param ciphertext
	 */
	public void displayCryptogram(String ciphertext)
	{
		// clear the previous cryptogram display, if there is one
		if (letterWidgets != null && letterWidgets.size() > 0)
		{
			for (int i = 0; i < letterWidgets.size(); i++)
			{
				letterWidgets.get(i).dispose();	
			}
		}
		
		// create all the letter widgets
		letterWidgets = new ArrayList<CryptogramLetterWidget>();
		for (int i = 0; i < ciphertext.length(); i++)
		{
			letterWidgets.add(new CryptogramLetterWidget(this,SWT.NONE,solvingCryptogram,ciphertext.charAt(i)));
		}
		
		// call the update function which organizes all the widgets peroperly
		updateLetterWidgetLayout();
	}
	
	/*
	 * Updates the organization of the letter widgets in the cryptogram to display properly
	 * @author Eric
	 * @date May 29 2013
	 */
	private void updateLetterWidgetLayout()
	{
		final int LETTER_WIDGET_WIDTH = 22;
		final int LETTER_WIDGET_HEIGHT = 60;
		final int LINE_SPACING = 6;
		
		FormData fd_letterWidget;			// form data used for display
		int line;							// which line the text is on
		int lastSeenSpace;					// last seen space
		int leftPos;						// position from the left
		int expectedNumLines;				// expected number of lines
		int verticalOffset;					// vertical offset for displaying
		int horizontalOffset;				// horizontal offset for displaying
		
		if(letterWidgets != null && letterWidgets.size() > 0)
		{
			// calculate the y-offset
			if (letterWidgets.size()*LETTER_WIDGET_WIDTH > this.getBounds().width - 20)
			{
				expectedNumLines = ((int) (letterWidgets.size()*LETTER_WIDGET_WIDTH*1.1))/(this.getBounds().width - 20);
				verticalOffset = (expectedNumLines*LETTER_WIDGET_HEIGHT + (expectedNumLines - 1)*LINE_SPACING)/2;
				horizontalOffset = 0;
			}
			else
			{
				verticalOffset = 0;
				horizontalOffset = (this.getBounds().width - 20 - letterWidgets.size()*LETTER_WIDGET_WIDTH)/2;
			}
			
			
			line = 0;
			lastSeenSpace = -1;
			leftPos = 0;
			
			// go through all the other letters and arrange them, and separate by line
			for(int i = 0; i < letterWidgets.size(); i++)
			{
				// check if we would go past the end of the form
				if (10 + (leftPos+1)*LETTER_WIDGET_WIDTH >= this.getBounds().width - 10 && lastSeenSpace >= 0)
				{	
					// go to the start of the next line
					line++;
					leftPos = 0;
					
					// make the space invisible 
					letterWidgets.get(lastSeenSpace).setVisible(false);
					
					// redo all of the letters between the last space and this one
					for (int j = lastSeenSpace + 1; j < i; j++)
					{
						fd_letterWidget = new FormData();
						fd_letterWidget.right = new FormAttachment(0, 10 + (leftPos+1)*LETTER_WIDGET_WIDTH + horizontalOffset);
						fd_letterWidget.bottom = new FormAttachment(50, LETTER_WIDGET_HEIGHT/2 + line*(LETTER_WIDGET_HEIGHT + LINE_SPACING) - verticalOffset);
						fd_letterWidget.top = new FormAttachment(50, -(LETTER_WIDGET_HEIGHT + 1)/2 + line*(LETTER_WIDGET_HEIGHT + LINE_SPACING) - verticalOffset);
						fd_letterWidget.left = new FormAttachment(0, 10 + leftPos*LETTER_WIDGET_WIDTH + horizontalOffset);
						letterWidgets.get(j).setLayoutData(fd_letterWidget);
						
						leftPos++;
					}
				}
				
				fd_letterWidget = new FormData();
				fd_letterWidget.right = new FormAttachment(0, 10 + (leftPos+1)*LETTER_WIDGET_WIDTH + horizontalOffset);
				fd_letterWidget.bottom = new FormAttachment(50, LETTER_WIDGET_HEIGHT/2 + line*(LETTER_WIDGET_HEIGHT + LINE_SPACING) - verticalOffset);
				fd_letterWidget.top = new FormAttachment(50, -(LETTER_WIDGET_HEIGHT + 1)/2 + line*(LETTER_WIDGET_HEIGHT + LINE_SPACING) - verticalOffset);
				fd_letterWidget.left = new FormAttachment(0, 10 + leftPos*LETTER_WIDGET_WIDTH + horizontalOffset);
				letterWidgets.get(i).setLayoutData(fd_letterWidget);
				letterWidgets.get(i).setVisible(true);
				
				
				if (letterWidgets.get(i).isSpace())
				{
					// check if the space is past the end of the line
					lastSeenSpace = i;
				}
				leftPos++;
			}
			
			
			
			// indicate that the layout has changed
			this.layout(true);
		}
	}
	
	/*
	 * Updates the contents of all the letters according to the cryptogram's stored contents
	 * @author Eric
	 * @date May 29 2013
	 */
	public void updateLetterWidgetContents()
	{
		for(int i = 0; i < letterWidgets.size(); i++)
		{
			letterWidgets.get(i).updateContents();
		}
		
		this.layout(true,true);
	}
}
