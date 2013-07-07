package pg13.presentation;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import pg13.org.eclipse.wb.swt.SWTResourceManager;

import acceptanceTests.EventLoop;
import acceptanceTests.Register;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.custom.StackLayout;

public class SolvedMessageBox extends Dialog
{

	protected Object result;
	protected Shell shell;
	private Label octoRiddler;
	private Button okButton;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public SolvedMessageBox(Shell parent) {
		super(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		Register.newWindow(this);
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		if (EventLoop.isEnabled())
		{
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setImage(org.eclipse.wb.swt.SWTResourceManager.getImage(SolvedMessageBox.class, "/junit/swingui/icons/ok.gif"));
		shell.setSize(320, 200);
		shell.setText(MessageConstants.PUZZLE_SOLVED);
		shell.setLayout(null);
		
		octoRiddler = new Label(shell, SWT.WRAP);
		octoRiddler.setAlignment(SWT.CENTER);
		octoRiddler.setBackground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_WHITE));
		octoRiddler.setBounds(10, 10, 132, 134);
		octoRiddler.setImage(org.eclipse.wb.swt.SWTResourceManager.getImage(SolvedMessageBox.class, "/images/riddlocat.png"));
		octoRiddler.setFont(SWTResourceManager.getFont("Lucida Grande", 13, SWT.NORMAL));
		
		okButton = new Button(shell, SWT.NONE);
		okButton.setBounds(179, 132, 100, 30);
		okButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				result = SWT.OK;
				shell.dispose();
			}
		});
		okButton.setText("OK");
		okButton.setSelection(true);
		
		Label lblCongratsYouSolved = new Label(shell, SWT.WRAP | SWT.SHADOW_IN);
		lblCongratsYouSolved.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblCongratsYouSolved.setAlignment(SWT.CENTER);
		lblCongratsYouSolved.setBounds(148, 20, 156, 101);
		lblCongratsYouSolved.setText(MessageConstants.SOLVED_PUZZLE_MESSAGE);
		
	}
} 
