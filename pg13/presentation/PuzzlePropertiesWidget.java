package pg13.presentation;

import java.util.Enumeration;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;

import pg13.business.create.PuzzleCreator;
import pg13.models.Category;
import pg13.models.Cryptogram;
import pg13.models.Difficulty;
import pg13.models.Puzzle;

public class PuzzlePropertiesWidget extends Composite
{
	boolean editMode;						// can we edit the properties of the puzzle?
	private Text txtPuzzleName;
	private Text txtDescription;
	private Label lblCategory;
	private Label lblDifficulty;
	private Combo cmbDificulty;
	private Combo cmbCategory;
	private Button btnSavePuzzle;
	private Puzzle displayingPuzzle;

	/**
	 * Creates and populates the properties widget.
	 * @author Eric
	 * @param parent
	 * @param style
	 * @date May 29 2013
	 */
	public PuzzlePropertiesWidget(Composite parent, int style, Puzzle displayingPuzzle, boolean editMode)
	{
		super(parent, style);
		this.displayingPuzzle = displayingPuzzle;
		setLayout(new FormLayout());

		// puzzle name
		txtPuzzleName = new Text(this, SWT.BORDER);
		txtPuzzleName.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		FormData fd_txtPuzzleName = new FormData();
		fd_txtPuzzleName.top = new FormAttachment(0, 10);
		fd_txtPuzzleName.left = new FormAttachment(0, 10);
		fd_txtPuzzleName.bottom = new FormAttachment(0, 49);
		fd_txtPuzzleName.right = new FormAttachment(100, -10);
		txtPuzzleName.setLayoutData(fd_txtPuzzleName);
		txtPuzzleName.addModifyListener(new ModifyListener()
		{
			@Override
			public void modifyText(ModifyEvent e)
			{
				updatePuzzleTitle();
			}
		});

		// puzzle description label
		Label lblDescription = new Label(this, SWT.NONE);
		FormData fd_lblDescription = new FormData();
		fd_lblDescription.top = new FormAttachment(txtPuzzleName, 10);
		fd_lblDescription.left = new FormAttachment(0, 10);
		lblDescription.setLayoutData(fd_lblDescription);
		lblDescription.setText(Constants.DESCRIPTION);


		// puzzle description text field
		txtDescription = new Text(this, SWT.BORDER | SWT.WRAP);
		FormData fd_txtDescription = new FormData();
		fd_txtDescription.bottom = new FormAttachment(lblDescription, 134, SWT.BOTTOM);
		fd_txtDescription.right = new FormAttachment(100, -10);
		fd_txtDescription.top = new FormAttachment(lblDescription, 4);
		fd_txtDescription.left = new FormAttachment(0, 10);
		txtDescription.setLayoutData(fd_txtDescription);
		txtDescription.addModifyListener(new ModifyListener()
		{
			@Override
			public void modifyText(ModifyEvent e)
			{
				updatePuzzleDescription();
			}
		});

		// category label
		lblCategory = new Label(this, SWT.NONE);
		FormData fd_lblCategory = new FormData();
		fd_lblCategory.left = new FormAttachment(0, 10);
		fd_lblCategory.top = new FormAttachment(txtDescription, 10);
		lblCategory.setLayoutData(fd_lblCategory);
		lblCategory.setText(Constants.CATEGORY);

		// category selection box
		cmbCategory = new Combo(this, SWT.READ_ONLY);
		cmbCategory.setItems(getCategories(Category.values()));
		FormData fd_cmbCategory = new FormData();
		fd_cmbCategory.right = new FormAttachment(60);
		fd_cmbCategory.top = new FormAttachment(lblCategory, 4);
		fd_cmbCategory.left = new FormAttachment(0, 10);
		cmbCategory.setLayoutData(fd_cmbCategory);
		cmbCategory.select(7);
		cmbCategory.addModifyListener(new ModifyListener()

		{
			@Override
			public void modifyText(ModifyEvent e)
			{
				updatePuzzleCategory();
			}
		});


		// difficulty label
		lblDifficulty = new Label(this, SWT.NONE);
		FormData fd_lblDifficulty = new FormData();
		fd_lblDifficulty.top = new FormAttachment(cmbCategory, 10);
		fd_lblDifficulty.left = new FormAttachment(0, 10);
		lblDifficulty.setLayoutData(fd_lblDifficulty);
		lblDifficulty.setText(Constants.DIFFICULTY);

		// difficulty selection box
		cmbDificulty = new Combo(this, SWT.READ_ONLY);
		cmbDificulty.setItems(getCategories(Difficulty.values()));
		FormData fd_cmbDificulty = new FormData();
		fd_cmbDificulty.right = new FormAttachment(60);
		fd_cmbDificulty.top = new FormAttachment(lblDifficulty, 4);
		fd_cmbDificulty.left = new FormAttachment(0, 10);
		cmbDificulty.setLayoutData(fd_cmbDificulty);
		cmbDificulty.select(0);
		cmbDificulty.addModifyListener(new ModifyListener()
		{
			@Override
			public void modifyText(ModifyEvent e)
			{
				updatePuzzleDifficulty();
			}
		});


		// save puzzle button
		this.btnSavePuzzle = new Button(this, SWT.NONE);
		FormData fd_btnSavePuzzle = new FormData();
		fd_btnSavePuzzle.top = new FormAttachment(100, -40);
		fd_btnSavePuzzle.bottom = new FormAttachment(100, -10);
		fd_btnSavePuzzle.left = new FormAttachment(50, -70);
		fd_btnSavePuzzle.right = new FormAttachment(50, 70);
		btnSavePuzzle.setLayoutData(fd_btnSavePuzzle);
		btnSavePuzzle.setText("Save this Puzzle");
		btnSavePuzzle.addSelectionListener(new SelectionListener()
		{

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				savePuzzle();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e)
			{
				savePuzzle();
			}

		});
		this.setEditMode(editMode);
	}

	/**
	 * Sets the puzzle we are displaying.
	 * @author Eric
	 * @date June 19 2013
	 */
	public void setPuzzle(Cryptogram newPuzzle)
	{
		this.displayingPuzzle = newPuzzle;
		updateFields();
	}

	/**
	 * Updates all the fields to display.
	 * @author Eric
	 * @date June 19 2013
	 */
	private void updateFields()
	{
		this.txtPuzzleName.setText((displayingPuzzle.getTitle() == null? "": displayingPuzzle.getTitle()));
		this.txtDescription.setText((displayingPuzzle.getDescription() == null? "": displayingPuzzle.getDescription()));
		// TODO make the combo boxes display correctly, how does this work with our enum sol?
	}

	/**
	 * Creates and populates the array of categories from the given enum
	 * @author Lauren
	 * @date June 19, 2013
	 */

	@SuppressWarnings("rawtypes")
	private String[] getCategories(Enumeration[] catEnum)
	{
		String[] categories = new String[catEnum.length];

		for(int i = 0; i < catEnum.length; i++)
		{
			categories[i] = catEnum[i].toString();
		}
		return categories;
	}

	/**
	 * Enables or disables widgets in the screen to be editable.
	 * @author Eric
	 * @param editMode
	 * @date June 19 2013
	 */
	private void setEditMode(boolean editMode)
	{
		this.editMode = editMode;
		this.cmbCategory.setEnabled(this.editMode);
		this.cmbDificulty.setEnabled(this.editMode);
		this.txtDescription.setEnabled(this.editMode);
		this.txtPuzzleName.setEnabled(this.editMode);
		this.btnSavePuzzle.setVisible(this.editMode);
	}

	/**
	 * Saves the puzzle being displayed.
	 * @author Eric
	 * @date June 19 2013
	 */
	private void savePuzzle()
	{
		new PuzzleCreator().save(displayingPuzzle);
	}

	/**
	 * Updates the displaying puzzle's title field.
	 * @author Eric
	 * @date June 19 2013
	 */
	private void updatePuzzleTitle()
	{
		displayingPuzzle.setTitle(txtPuzzleName.getText());
	}

	/**
	 * Updates the displaying puzzle's description field.
	 * @author Eric
	 * @date June 19 2013
	 */
	private void updatePuzzleDescription()
	{
		displayingPuzzle.setDescription(txtDescription.getText());
	}

	/**
	 * Updates the displaying puzzle's category field.
	 * @author Eric
	 * @date June 19 2013
	 */
	private void updatePuzzleCategory()
	{
		displayingPuzzle.setCategory(Category.valueOf(cmbCategory.getText()));
	}

	/**
	 * Updates the displaying puzzle's difficulty field.
	 * @author Eric
	 * @date June 19 2013
	 */
	private void updatePuzzleDifficulty()
	{
		displayingPuzzle.setDifficulty(Difficulty.valueOf(cmbDificulty.getText()));
	}

	@Override
	protected void checkSubclass()
	{
		// Disable the check that prevents subclassing of SWT components
	}
}
