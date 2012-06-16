package smart.generator.plugin.core.ui.wizards;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import smart.generator.plugin.core.ui.context.ApplicationContext;
import smart.generator.plugin.core.ui.providers.ProjectContentProvider;
import smart.generator.plugin.core.ui.providers.ProjectLabelProvider;

public class ProjectWizardPage extends WizardPage {

	private ApplicationContext context;
	private CheckboxTableViewer tableViewer;

	private String projectPath;

	protected ProjectWizardPage(ApplicationContext context) {
		super("projectWizardPage");
		setTitle("Projetos");
		setDescription("Selecione o projeto alvo da geração.");
		this.context = context;
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		FillLayout layout = new FillLayout();
		container.setLayout(layout);

		tableViewer = CheckboxTableViewer.newCheckList(container, SWT.BORDER);
		tableViewer.setLabelProvider(new ProjectLabelProvider());
		tableViewer.setContentProvider(new ProjectContentProvider());

		tableViewer.addCheckStateListener(new ICheckStateListener() {

			@Override
			public void checkStateChanged(CheckStateChangedEvent changedEvent) {
				getWizard().getContainer().updateButtons();
				if (changedEvent.getChecked()) {
					IProject project = (IProject) tableViewer.getCheckedElements()[0];
					projectPath = project.getLocation().toString();
				}
			}
		});

		init();
		setControl(container);
	}

	private void init() {
		tableViewer.setInput(context.getOpenProjectList());
	}

	public String getProjectPath() {
		return projectPath;
	}

	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}

	public CheckboxTableViewer getTableViewer() {
		return tableViewer;
	}

	public void setTableViewer(CheckboxTableViewer tableViewer) {
		this.tableViewer = tableViewer;
	}

}
