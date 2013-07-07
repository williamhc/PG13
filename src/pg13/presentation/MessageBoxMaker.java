package pg13.presentation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class MessageBoxMaker implements IMessageBoxStrategy
{
	public MessageBoxMaker()
	{
		
	}
	
	@Override
	public void errorMessage(Shell shell, String header, String message) 
	{
		MessageBox dialog;
		
		dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
		dialog.setText(header);
		dialog.setMessage(message);

		dialog.open();
	}

	@Override
	public void infoMessage(Shell shell, String header, String message) 
	{
		MessageBox dialog;
		
		dialog = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
		dialog.setText(header);
		dialog.setMessage(message);

		dialog.open();
	}
	
	public void hurrayMessage(Shell shell)
	{
		new SolvedMessageBox(shell).open();
	}

	@Override
	public void unsolvedMessage(Shell shell)
	{
		new UnsolvedMessageBox(shell).open();
		
	}
}
