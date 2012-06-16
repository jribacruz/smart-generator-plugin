package smart.generator.plugin.core.ui.wizards;

import org.eclipse.jface.dialogs.IDialogPage;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * The "New" wizard page allows setting the container for the new file as well
 * as the file name. The page will only accept file name without the extension
 * OR with the extension that matches the expected one (mpe).
 */

public class CompilationUnitWizardPage extends WizardPage {

	private ISelection selection;

	/**
	 * Constructor for SampleNewWizardPage.
	 * 
	 * @param pageName
	 */
	public CompilationUnitWizardPage(ISelection selection) {
		super("wizardPage");
		setTitle("Multi-page Editor File");
		setDescription("This wizard creates a new file with *.mpe extension that can be opened by a multi-page editor.");
		this.selection = selection;
	}

	/**
	 * @see IDialogPage#createControl(Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		FillLayout layout = new FillLayout();
		container.setLayout(layout);
		initialize();
		setControl(container);
	}

	/**
	 * Tests if the current workbench selection is a suitable container to use.
	 */

	private void initialize() {
	}

}