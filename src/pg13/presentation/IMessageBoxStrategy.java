package pg13.presentation;

import org.eclipse.swt.widgets.Shell;

public interface IMessageBoxStrategy 
{
	public void errorMessage(Shell shell, String header, String message);
	
	public void infoMessage(Shell shell, String header, String message);
	
	public void hurrayMessage(Shell shell);

	void unsolvedMessage(Shell shell);
}
