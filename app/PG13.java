package app;
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
import org.eclipse.wb.swt.SWTResourceManager;


//this is a test - Lauren Slusky
public class PG13 {

	protected Shell shlPg;
	private Text txtPuzzleName;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			PG13 window = new PG13();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlPg.open();
		shlPg.layout();
		while (!shlPg.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlPg = new Shell();
		shlPg.setSize(450, 300);
		shlPg.setText("PG13");
		shlPg.setLayout(null);

		Button btnCreateAPuzzle = new Button(shlPg, SWT.NONE);
		btnCreateAPuzzle.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.BOLD));
		btnCreateAPuzzle.setBounds(128, 141, 184, 42);
		btnCreateAPuzzle.setText("Create a Puzzle");

		txtPuzzleName = new Text(shlPg, SWT.BORDER);
		txtPuzzleName.setToolTipText("Put your puzzle name here to start.");
		txtPuzzleName.setText("Puzzle name");
		txtPuzzleName.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		txtPuzzleName.setBounds(128, 77, 184, 23);

	}
}
