package tests.atrStubs;

import pg13.presentation.MainWindow;

public class StubInjector 
{
	public void injectMessageBoxMakerStub()
	{
		MainWindow.getInstance().setMessageBoxStrategy(new MessageBoxMakerStub());
	}
}
