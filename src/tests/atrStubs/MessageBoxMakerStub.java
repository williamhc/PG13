package tests.atrStubs;

import org.eclipse.swt.widgets.Shell;

import pg13.presentation.IMessageBoxStrategy;

public class MessageBoxMakerStub implements IMessageBoxStrategy
{
	public MessageBoxMakerStub()
	{
		
	}

	@Override
	public void errorMessage(Shell shell, String header, String message) 
	{
		// stub, do nothing!
		
	}

	@Override
	public void infoMessage(Shell shell, String header, String message) 
	{
		// stub, do nothing!
		
	}

}
