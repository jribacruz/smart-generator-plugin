package smart.generator.plugin.core.ui.module;

import smart.generator.plugin.console.core.Log;
import smart.generator.plugin.core.ui.commands.GeneratorCommand;
import smart.generator.plugin.core.ui.context.ApplicationContext;
import smart.generator.plugin.core.ui.wizards.GeneratorWizard;
import smart.generator.plugin.loader.manager.TemplateLoaderManager;
import smart.generator.plugin.model.manager.ModelManager;
import smart.generator.plugin.writer.manager.TemplateWriterManager;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class SmartGeneratorModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(GeneratorCommand.class);
		bind(ApplicationContext.class).in(Scopes.SINGLETON);
		bind(ModelManager.class).in(Scopes.SINGLETON);
		bind(GeneratorWizard.class);
		bind(Log.class);
		bind(TemplateLoaderManager.class);
		bind(TemplateWriterManager.class);
	}

}
