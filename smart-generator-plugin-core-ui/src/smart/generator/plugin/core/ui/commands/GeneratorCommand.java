package smart.generator.plugin.core.ui.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import smart.generator.plugin.core.ui.context.ApplicationContext;

import com.google.inject.Inject;

public class GeneratorCommand {

	@Inject
	private ApplicationContext context;

	public void execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
	}
}
