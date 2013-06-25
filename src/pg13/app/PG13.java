package pg13.app;

import pg13.presentation.MainWindow;

public class PG13
{

	public static String dbName = "PG13";

	public static void main(String[] args)
	{
		startUp();
		// launch a main window
		MainWindow.getInstance().runWindow();

		shutDown();
	}

	public static void startUp()
	{
		Services.createDataAccess(dbName);
	}

	public static void shutDown()
	{
		Services.closeDataAccess();
	}
}
