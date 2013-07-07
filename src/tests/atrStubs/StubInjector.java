package tests.atrStubs;

import pg13.presentation.MainWindow;

public class StubInjector 
{
	public StubInjector()
	{
		
	}
	
	public void injectMessageBoxStub()
	{
		MainWindow.getInstance().setMessageBoxStrategy(new MessageBoxMakerStub());
	}
}
