package smart.generator.plugin.core.ui.wizards;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import smart.generator.plugin.console.core.Log;
import smart.generator.plugin.core.ui.context.ApplicationContext;
import smart.generator.plugin.loader.manager.TemplateLoaderManager;
import smart.generator.plugin.loader.model.Application;
import smart.generator.plugin.model.manager.ModelManager;
import smart.generator.plugin.model.metamodel.XModel;
import smart.generator.plugin.writer.manager.TemplateWriterManager;

import com.google.inject.Inject;

/**
 * This is a sample new wizard. Its role is to create a new file resource in the
 * provided container. If the container resource (a folder or a project) is
 * selected in the workspace when the wizard is opened, it will accept it as the
 * target container. The wizard creates one file with the extension "mpe". If a
 * sample multi-page editor (also available as a template) is registered for the
 * same extension, it will be able to open it.
 */

public class GeneratorWizard extends Wizard implements INewWizard {

	@Inject
	private ApplicationContext context;

	@Inject
	private ModelManager modelManager;

	@Inject
	private TemplateLoaderManager loaderManager;

	@Inject
	private TemplateWriterManager writerManager;

	@Inject
	private Log log;

	private CompilationUnitWizardPage compilationUnitPage;

	private ProjectWizardPage projectWizardPage;

	private ISelection selection;

	/**
	 * Constructor for GeneratorWizard.
	 */
	public GeneratorWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	/**
	 * Adding the page to the wizard.
	 */

	@Override
	public void addPages() {

		compilationUnitPage = new CompilationUnitWizardPage(selection);
		compilationUnitPage.setUnits(context.getCompilationUnitList());
		addPage(compilationUnitPage);

		projectWizardPage = new ProjectWizardPage(context);
		addPage(projectWizardPage);
	}

	/**
	 * This method is called when 'Finish' button is pressed in the wizard. We
	 * will create an operation and run it using wizard as execution context.
	 */
	@Override
	public boolean performFinish() {
		IRunnableWithProgress op = new IRunnableWithProgress() {
			@Override
			public void run(IProgressMonitor monitor) throws InvocationTargetException {
				try {
					doFinish();
				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};
		try {
			getContainer().run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error", realException.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * The worker method. It will find the container, create the file if missing
	 * or just replace its contents, and open the editor on the newly created
	 * file.
	 */

	private void doFinish() throws CoreException {
		Set<ICompilationUnit> selectedUnits = compilationUnitPage.getSelectedUnits();
		String projectPath = projectWizardPage.getProjectPath();
		loaderManager.init(projectPath, context.getProjectMetamodelPath(), context.getRepositoryPath());
		log.info("Descriptor: " + loaderManager.getDescriptors());

		Application application = loaderManager.getApplication();

		/*
		 * inicializando o writer manager com o caminho do projeto e a lista de
		 * descriptors
		 */
		writerManager.init(projectPath, loaderManager.getDescriptors());
		for (ICompilationUnit selectedUnit : selectedUnits) {

			XModel model = modelManager.put(selectedUnit);
			log.info("Modelo: " + model);
			writerManager.write(model, application);
		}
		context.refresh();
	}

	@Override
	public boolean canFinish() {
		if (projectWizardPage.getTableViewer().getCheckedElements().length > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}
}