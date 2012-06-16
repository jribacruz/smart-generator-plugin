package smart.generator.plugin.core.ui.wizards;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jface.dialogs.IDialogPage;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import smart.generator.plugin.core.ui.providers.CompilationUnitContentProvider;
import smart.generator.plugin.core.ui.providers.CompilationUnitLabelProvider;

/**
 * The "New" wizard page allows setting the container for the new file as well
 * as the file name. The page will only accept file name without the extension
 * OR with the extension that matches the expected one (mpe).
 */

public class CompilationUnitWizardPage extends WizardPage {

	@SuppressWarnings("unused")
	private ISelection selection;

	private CheckboxTableViewer tableViewer;

	private Set<ICompilationUnit> units;

	private Set<ICompilationUnit> selectedUnits;

	/**
	 * Constructor for SampleNewWizardPage.
	 * 
	 * @param pageName
	 */
	public CompilationUnitWizardPage(ISelection selection) {
		super("compilationUnitWizardPage");
		setTitle("Metamodelos");
		setDescription("Selecione os metamodelos que serão usados para a geração");
		this.selection = selection;
		this.selectedUnits = new HashSet<ICompilationUnit>();
	}

	/**
	 * @see IDialogPage#createControl(Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		FillLayout layout = new FillLayout();
		container.setLayout(layout);

		tableViewer = CheckboxTableViewer.newCheckList(container, SWT.BORDER);
		tableViewer.setLabelProvider(new CompilationUnitLabelProvider());
		tableViewer.setContentProvider(new CompilationUnitContentProvider());
		tableViewer.addCheckStateListener(new ICheckStateListener() {

			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {
				getWizard().getContainer().updateButtons();
			}
		});

		initialize();
		setControl(container);
	}

	@Override
	public boolean isPageComplete() {
		return false;
	}

	@Override
	public boolean canFlipToNextPage() {
		if (tableViewer.getCheckedElements().length > 0) {
			selectedUnits.clear();
			Object[] selectedElementList = tableViewer.getCheckedElements();
			for (Object selectedElement : selectedElementList) {
				selectedUnits.add((ICompilationUnit) selectedElement);
			}
			return true;
		}
		return false;
	}

	private void initialize() {
		tableViewer.setInput(units);
	}

	public void setUnits(Set<ICompilationUnit> units) {
		this.units = units;
	}

	public Set<ICompilationUnit> getSelectedUnits() {
		return selectedUnits;
	}

}