package tests.atrStubs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import acceptanceTests.Register;

import pg13.presentation.IMessageBoxStrategy;
import pg13.presentation.MessageConstants;

public class MessageBoxMakerStub implements IMessageBoxStrategy
{	
	Label lblMessage;
	
	public MessageBoxMakerStub()
	{	
		Register.newWindow(this);	
	}

	@Override
	public void errorMessage(Shell shell, String header, String message) 
	{
		if (lblMessage == null)
		{
			lblMessage = new Label(shell, SWT.NONE);
			lblMessage.setVisible(false);
		}
		lblMessage.setText(message);
	}

	@Override
	public void infoMessage(Shell shell, String header, String message) 
	{
		if (lblMessage == null)
		{
			lblMessage = new Label(shell, SWT.NONE);
			lblMessage.setVisible(false);
		}
		lblMessage.setText(message);
	}

	@Override
	public void hurrayMessage(Shell shell)
	{
		if (lblMessage == null)
		{
			lblMessage = new Label(shell, SWT.NONE);
			lblMessage.setVisible(false);
		}
		lblMessage.setText(MessageConstants.SOLVED_PUZZLE_MESSAGE);
		
	}
	
	@Override
	public void unsolvedMessage(Shell shell)
	{
		if (lblMessage == null)
		{
			lblMessage = new Label(shell, SWT.NONE);
			lblMessage.setVisible(false);
		}
		lblMessage.setText(MessageConstants.UNSOLVED_PUZZLE_MESSAGE);
		
	}

}
