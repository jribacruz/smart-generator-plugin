package smart.generator.plugin.core.ui.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import smart.generator.plugin.core.ui.commands.GeneratorCommand;
import smart.generator.plugin.core.ui.module.SmartGeneratorModule;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class GeneratorHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public GeneratorHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Injector injector = Guice.createInjector(new SmartGeneratorModule());
		GeneratorCommand command = injector.getInstance(GeneratorCommand.class);
		command.execute(event);
		return null;
	}
}
