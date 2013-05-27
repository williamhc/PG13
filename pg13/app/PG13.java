package pg13.app;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Text;
import pg13.org.eclipse.wb.swt.SWTResourceManager;
import pg13.presentation.MainWindow;

public class PG13 
{
	/**
	 * Launches the main window.
	 * @author Eric
	 * @param args
	 * @date May 26 2013
	 */
	public static void main(String[] args) 
	{
		// launch a main window
		new MainWindow();
	}
}

