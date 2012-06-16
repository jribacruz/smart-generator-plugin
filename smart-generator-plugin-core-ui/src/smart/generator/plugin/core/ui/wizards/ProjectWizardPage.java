package smart.generator.plugin.core.ui.wizards;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import smart.generator.plugin.core.ui.context.ApplicationContext;
import smart.generator.plugin.core.ui.providers.ProjectContentProvider;
import smart.generator.plugin.core.ui.providers.ProjectLabelProvider;

public class ProjectWizardPage extends WizardPage {

	private ApplicationContext context;
	private ListViewer listViewer;

	protected ProjectWizardPage(ApplicationContext context) {
		super("projectWizardPage");
		this.context = context;
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		FillLayout layout = new FillLayout();
		container.setLayout(layout);

		listViewer = new ListViewer(container, SWT.BORDER);
		listViewer.setLabelProvider(new ProjectLabelProvider());
		listViewer.setContentProvider(new ProjectContentProvider());
		listViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				if (selection != null && !selection.isEmpty()) {
					getWizard().getContainer().updateButtons();
				}

			}
		});

		init();
		setControl(container);
	}

	private void init() {
		listViewer.setInput(context.getOpenProjectList());
	}

	@Override
	public boolean isPageComplete() {
		return listViewer.getSelection() != null && !listViewer.getSelection().isEmpty();
	}

}
