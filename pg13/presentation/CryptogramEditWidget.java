package pg13.presentation;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import pg13.business.CryptogramManager;
import pg13.models.Cryptogram;

public class CryptogramEditWidget extends Composite {
	private Text txtPlaintext; // plaintext used to generate cryptogram
	private CryptogramSolveWidget cmpPreview; // preview area for the cryptogram
	private CryptogramManager cm;
	private boolean editMode;
	private Button btnPreview;

	/**
	 * Creates and populates the cryptogram edit widget.
	 *
	 * @author Eric
	 * @param parent
	 * @param style
	 * @date May 29 2013
	 */
	public CryptogramEditWidget(Composite parent, int style,
			Cryptogram workingCryptogram, boolean editMode)
		{
		super(parent, style);
		setLayout(new FormLayout());
		this.cm = new CryptogramManager(workingCryptogram);

		// cryptogram preview widget
		cmpPreview = new CryptogramSolveWidget(this, SWT.BORDER,
				workingCryptogram);
		FormData fd_cmpPreview = new FormData();
		fd_cmpPreview.bottom = new FormAttachment(100, -10);
		fd_cmpPreview.right = new FormAttachment(100, -10);
		fd_cmpPreview.top = new FormAttachment(0, 136);
		fd_cmpPreview.left = new FormAttachment(0, 10);
		cmpPreview.setLayoutData(fd_cmpPreview);

		Label lblPlaintext = new Label(this, SWT.NONE);
		FormData fd_lblPlaintext = new FormData();
		fd_lblPlaintext.top = new FormAttachment(0, 10);
		fd_lblPlaintext.left = new FormAttachment(0, 10);
		lblPlaintext.setLayoutData(fd_lblPlaintext);
		lblPlaintext.setText("Cryptogram Plaintext");

		Label lblPreview = new Label(this, SWT.NONE);
		FormData fd_lblPreview = new FormData();
		fd_lblPreview.bottom = new FormAttachment(cmpPreview, -6);
		fd_lblPreview.left = new FormAttachment(0, 10);
		lblPreview.setLayoutData(fd_lblPreview);
		lblPreview.setText("Preview");

		txtPlaintext = new Text(this, SWT.BORDER);
		txtPlaintext.addVerifyListener(new VerifyListener()
		{
			public void verifyText(VerifyEvent event)
			{
				try
				{
					cm.validatePlaintext(event.text);
				}
				catch (IllegalArgumentException e)
				{
					event.doit = false;
				}
			}
		});
		FormData fd_txtPlaintext = new FormData();
		fd_txtPlaintext.right = new FormAttachment(100, -10);
		fd_txtPlaintext.bottom = new FormAttachment(lblPreview, -6);
		fd_txtPlaintext.top = new FormAttachment(lblPlaintext, 6);
		fd_txtPlaintext.left = new FormAttachment(cmpPreview, 0, SWT.LEFT);
		txtPlaintext.setLayoutData(fd_txtPlaintext);

		this.btnPreview = new Button(this, SWT.NONE);
		btnPreview.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				preview();
			}
		});
		FormData fd_btnPreview = new FormData();
		fd_btnPreview.top = new FormAttachment(lblPreview, -5, SWT.TOP);
		fd_btnPreview.right = new FormAttachment(100, -10);
		btnPreview.setLayoutData(fd_btnPreview);
		btnPreview.setText("Generate Preview");

		this.setEditMode(editMode);
	}

	/*
	 * Set the edit mode of the widget - if false, the cryptogram will not be changeable
	 *
	 * @author Will
	 *
	 * @date June 4th 2013
	 */
	private void setEditMode(boolean editMode) {
		this.editMode = editMode;
		this.txtPlaintext.setEnabled(editMode);
		this.btnPreview.setVisible(editMode);
	}

	/*
	 * Previews the cryptogram -- displays the cryptogram in the preview screen
	 *
	 * @author Eric
	 *
	 * @date May 29 2013
	 */
	private void preview()
	{
		try
		{
			this.cm.setPlaintext(txtPlaintext.getText());
			cmpPreview.setCryptogram(this.cm.getCryptogram());
			cmpPreview.displayCryptogram();
		}
		catch (IllegalArgumentException e)
		{
			MessageBox dialog = new MessageBox(this.getShell(), SWT.OK);
			dialog.setText("Invalid text");
			dialog.setMessage("The text you have entered is invalid. Please modify the text.");
		}
	}

	@Override
	protected void checkSubclass()
	{
		// Disable the check that prevents subclassing of SWT components
	}
}
