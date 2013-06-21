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

public class CryptogramSolveWidget extends Composite 
{
	private ArrayList<CryptogramLetterWidget> letterWidgets;
	private Cryptogram solvingCryptogram;
	
	/**
	 * Creates and populates the cryptogram solving widget.
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

	}

	@Override
	protected void checkSubclass()
	{
		// Disable the check that prevents subclassing of SWT components
	}

	/**
	 * Sets the cryptogram we are currently solving to the specified cryptogram
	 * @date May 29 2013
	 * @param newCryptogram
	 */
	public void setCryptogram(Cryptogram newCryptogram)
	{
		this.solvingCryptogram = newCryptogram;
		
		// update the display
		displayCryptogram();
	}
	
	/**
	 * Displays the cryptogram in the centre of the window
	 * @date May 29 2013
	 * @param ciphertext
	 */
	public void displayCryptogram()
	{
		String ciphertext;
		
		if (this.solvingCryptogram != null)
		{
			ciphertext = this.solvingCryptogram.getCiphertext();
			
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
	}
	
	/**
	 * Updates the organization of the letter widgets in the cryptogram to display properly
	 * @date May 29 2013
	 */
	public void updateLetterWidgetLayout()
	{
		final int LETTER_WIDGET_WIDTH = 26;
		final int LETTER_WIDGET_HEIGHT = 62;
		final int LINE_SPACING = 6;
		
		FormData fd_letterWidget;			// form data used for display
		int line;							// which line the text is on
		int lastSeenSpaceLine;				// line last seen space was on
		int lastSeenSpace;					// last seen space
		int leftPos;						// position from the left
		int expectedNumLines;				// expected number of lines
		int verticalOffset;					// vertical offset for displaying
		int horizontalOffset;				// horizontal offset for displaying
		int finalHeight;					// final height after everything is layed out
		int finalYPos;						// final y position
		int widthInLetters;					// the amount of letters that can fit in the
											// allowed width
		
		widthInLetters = (this.getBounds().width - 20)/LETTER_WIDGET_WIDTH;
		
		if(letterWidgets != null && letterWidgets.size() > 0)
		{
			// calculate the y-offset
			if (letterWidgets.size() > widthInLetters)
			{
				expectedNumLines = ((int) (letterWidgets.size()*LETTER_WIDGET_WIDTH*1.3))/(this.getBounds().width - 20) + 1;
				verticalOffset = ((expectedNumLines - 1)*LETTER_WIDGET_HEIGHT + (expectedNumLines - 1)*LINE_SPACING)/2;
				horizontalOffset = 0;
			}
			else
			{
				expectedNumLines = 1;
				verticalOffset = 0;
				horizontalOffset = (this.getBounds().width - 20 - letterWidgets.size()*LETTER_WIDGET_WIDTH)/2;
			}
			
			
			line = 0;
			lastSeenSpace = -1;
			lastSeenSpaceLine = -1;
			leftPos = 0;
			
			// go through all the other letters and arrange them, and separate by line
			for(int i = 0; i < letterWidgets.size(); i++)
			{
				// check if we would go past the end of the form
				if (10 + (leftPos+1)*LETTER_WIDGET_WIDTH >= this.getBounds().width - 10)
				{	
					// if a space was seen on this line
					if (lastSeenSpace >= 0 && lastSeenSpaceLine == line)
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
					else
					{
						// go to the start of the next line
						line++;
						leftPos = 0;
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
					lastSeenSpaceLine = line;
				}
				leftPos++;
			}
			
			finalHeight = (expectedNumLines) * LETTER_WIDGET_HEIGHT + (expectedNumLines - 1) * LINE_SPACING;
			if (expectedNumLines <= line)
			{
				finalHeight += 2*(LETTER_WIDGET_HEIGHT + LINE_SPACING);
			}
			finalYPos = Math.max(0, this.getParent().getBounds().height/2 - finalHeight/2);
			this.setBounds(this.getBounds().x, finalYPos, this.getBounds().width, finalHeight );
			
			// indicate that the layout has changed
			this.layout(true);
		}
	}
	
	/**
	 * Updates the contents of all the letters according to the cryptogram's stored contents
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
