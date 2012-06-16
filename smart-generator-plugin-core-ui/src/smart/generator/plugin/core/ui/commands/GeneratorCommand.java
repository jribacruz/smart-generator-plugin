package smart.generator.plugin.core.ui.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import smart.generator.plugin.core.ui.context.ApplicationContext;
import smart.generator.plugin.core.ui.wizards.GeneratorWizard;

import com.google.inject.Inject;

public class GeneratorCommand {

	@Inject
	private GeneratorWizard wizard;

	@Inject
	private ApplicationContext context;

	public void execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		context.setSelection(window.getSelectionService().getSelection());
		WizardDialog dialog = new WizardDialog(window.getShell(), wizard);
		dialog.create();
		dialog.open();
	}
}
