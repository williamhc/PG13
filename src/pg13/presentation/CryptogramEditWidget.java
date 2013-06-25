package pg13.presentation;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;

import pg13.business.CryptogramManager;
import pg13.models.Cryptogram;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.custom.ScrolledComposite;
import pg13.org.eclipse.wb.swt.SWTResourceManager;

public class CryptogramEditWidget extends Composite
{
	private Text txtPlaintext; // plaintext used to generate cryptogram
	private CryptogramSolveWidget cmpPreview; // preview area for the cryptogram
	private CryptogramManager cm;

	public CryptogramEditWidget(Composite parent, int style,
			Cryptogram workingCryptogram, boolean editMode)
	{
		super(parent, style);
		setLayout(new FormLayout());
		this.cm = new CryptogramManager(workingCryptogram);

		ScrolledComposite cmpPreviewScrollable = new ScrolledComposite(this,
				SWT.BORDER | SWT.V_SCROLL);
		cmpPreviewScrollable.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		FormData fd_cmpPreviewScrollable = new FormData();
		fd_cmpPreviewScrollable.bottom = new FormAttachment(100, -10);
		fd_cmpPreviewScrollable.right = new FormAttachment(100, -10);
		fd_cmpPreviewScrollable.top = new FormAttachment(0, 136);
		fd_cmpPreviewScrollable.left = new FormAttachment(0, 10);
		cmpPreviewScrollable.setLayoutData(fd_cmpPreviewScrollable);
		cmpPreviewScrollable.setExpandHorizontal(true);

		// cryptogram preview widget
		cmpPreview = new CryptogramSolveWidget(cmpPreviewScrollable, SWT.NONE,
				workingCryptogram);
		FormData fd_cmpPreview = new FormData();
		fd_cmpPreview.bottom = new FormAttachment(100, -10);
		fd_cmpPreview.right = new FormAttachment(100, -10);
		fd_cmpPreview.top = new FormAttachment(0, 136);
		fd_cmpPreview.left = new FormAttachment(0, 10);
		cmpPreview.setLayoutData(fd_cmpPreview);

		cmpPreviewScrollable.setContent(cmpPreview);

		Label lblPlaintext = new Label(this, SWT.NONE);
		FormData fd_lblPlaintext = new FormData();
		fd_lblPlaintext.top = new FormAttachment(0, 10);
		fd_lblPlaintext.left = new FormAttachment(0, 10);
		lblPlaintext.setLayoutData(fd_lblPlaintext);
		lblPlaintext.setText(Constants.CRYPTOGRAM_PLAINTEXT);

		Label lblPreview = new Label(this, SWT.NONE);
		FormData fd_lblPreview = new FormData();
		fd_lblPreview.bottom = new FormAttachment(cmpPreviewScrollable, -6);
		fd_lblPreview.left = new FormAttachment(0, 10);
		lblPreview.setLayoutData(fd_lblPreview);
		lblPreview.setText(Constants.PREVIEW);

		txtPlaintext = new Text(this, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		txtPlaintext.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent event)
			{
				updatePuzzlePlaintext();
			}
		});
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
		fd_txtPlaintext.left = new FormAttachment(cmpPreviewScrollable, 0,
				SWT.LEFT);
		txtPlaintext.setTextLimit(Constants.MAX_PLAINTEXT_CHARS);
		txtPlaintext.setLayoutData(fd_txtPlaintext);

		this.setEditMode(editMode);
	}

	private void setEditMode(boolean editMode)
	{
		this.txtPlaintext.setEnabled(editMode);
	}

	private void updatePuzzlePlaintext()
	{
		try
		{
			this.cm.setPlaintext(txtPlaintext.getText());
			cmpPreview.displayCryptogram();
		}
		catch (IllegalArgumentException e)
		{
			MessageBox dialog = new MessageBox(this.getShell(), SWT.OK);
			dialog.setText(MessageConstants.INVALID_TEXT);
			dialog.setMessage(MessageConstants.INVALID_TEXT_MESSAGE);
		}
	}

	public void setCryptogram(Cryptogram newCryptogram)
	{
		this.cm = new CryptogramManager(newCryptogram);
		txtPlaintext.setText(cm.getPlaintext());
		cmpPreview.setCryptogram(newCryptogram);
	}

	@Override
	protected void checkSubclass()
	{
		// Disable the check that prevents subclassing of SWT components
	}
}
