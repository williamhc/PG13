package pg13.presentation;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;

public class CryptogramLetterWidget extends Composite {
	private Text txtPlaintextChar;
	private char ciphertextChar;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CryptogramLetterWidget(Composite parent, int style, char ciphertextChar) {
		super(parent, SWT.NONE);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		setLayout(new FormLayout());
		this.ciphertextChar = ciphertextChar;
		
		// ciphertext character
		Label lblCiphertextChar = new Label(this, SWT.NONE);
		lblCiphertextChar.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_lblCiphertextChar = new FormData();
		fd_lblCiphertextChar.bottom = new FormAttachment(100);
		fd_lblCiphertextChar.right = new FormAttachment(100);
		fd_lblCiphertextChar.top = new FormAttachment(50);
		fd_lblCiphertextChar.left = new FormAttachment(0);
		lblCiphertextChar.setLayoutData(fd_lblCiphertextChar);
		lblCiphertextChar.setAlignment(SWT.CENTER);
		lblCiphertextChar.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblCiphertextChar.setText("" + ciphertextChar);
		
		// area for the plaintext character entered by user
		txtPlaintextChar = new Text(this, SWT.BORDER | SWT.CENTER);
		txtPlaintextChar.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		//txtPlaintextChar.set
		FormData fd_txtPlaintextChar = new FormData();
		fd_txtPlaintextChar.top = new FormAttachment(0);
		fd_txtPlaintextChar.left = new FormAttachment(0);
		fd_txtPlaintextChar.right = new FormAttachment(100);
		fd_txtPlaintextChar.bottom = new FormAttachment(50);
		txtPlaintextChar.setLayoutData(fd_txtPlaintextChar);
		
		// if the ciphertext character is not a letter, then the plaintext character is given
		if ("ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(ciphertextChar) < 0)
		{
			txtPlaintextChar.setEditable(false);
			txtPlaintextChar.setText("" + ciphertextChar);
		}
		else
		{
			// limit characters that can be entered
			txtPlaintextChar.setTextLimit(1);
			txtPlaintextChar.addVerifyListener(new VerifyListener() 
			{
				public void verifyText(VerifyEvent event) 
				{
					char enteredChar;				// text that was entered
					boolean textIsValid = false;	// text that was entered is OK
					
					// allow editing keys
					switch (event.keyCode) 
					{  
		            	case SWT.BS:           // Backspace  
		            	case SWT.DEL:          // Delete  
		            	case SWT.HOME:         // Home  
		            	case SWT.END:          // End  
		            	case SWT.ARROW_LEFT:   // Left arrow  
		            	case SWT.ARROW_RIGHT:  // Right arrow  
		                textIsValid = true;  
					}  
					
					if (!textIsValid)
					{
						// set to lowercase
						event.text = event.text.toLowerCase();
						
						enteredChar = event.text.charAt(0);
						if ("abcdefghijklmnopqrstuvwxyz".indexOf(enteredChar) >= 0)
							textIsValid = true;
					}
					
					// block the event if text is not valid
					if (!textIsValid)
					{
						event.doit = false;
					}
				}
			});
		}

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public boolean isSpace()
	{
		return ciphertextChar == ' ';
	}

}
