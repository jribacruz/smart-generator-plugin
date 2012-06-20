package smart.generator.plugin.loader.manager;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

import smart.generator.plugin.console.core.Log;
import smart.generator.plugin.loader.digester.TemplateLoaderDigester;
import smart.generator.plugin.loader.model.Configuration;
import smart.generator.plugin.loader.model.Template;
import smart.generator.plugin.loader.reader.TemplateReader;
import smart.generator.plugin.model.descriptors.TemplateDescriptor;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.inject.Inject;

public class TemplateLoaderManager {

	@Inject
	private Log log;

	private File repositoryFile;

	private Collection<TemplateDescriptor> descriptors;

	public TemplateLoaderManager() {
		super();
		this.descriptors = new ArrayList<TemplateDescriptor>();

	}

	public boolean init(String repositoryPath) {
		this.repositoryFile = new File(repositoryPath);
		log.info("Loader inicializado.");
		load();
		return this.repositoryFile.exists();
	}

	private void load() {
		if (repositoryFile.exists() && repositoryFile.isDirectory()) {
			log.info("Lendo repositorio: " + repositoryFile.getAbsolutePath());
			/* carrega os arquivos de configuracao */
			Collection<Configuration> configurations = Collections2.transform(Arrays.asList(repositoryFile.listFiles()),
					new FunctionConfiguration());
			/* gera os template descriptors */
			for (Configuration configuration : configurations) {
				this.descriptors.addAll(Collections2.transform(configuration.getTemplates(),
						new FunctionTemplateDescriptor()));
			}
		}
	}

	public List<TemplateDescriptor> getDescriptors() {
		return new ArrayList<TemplateDescriptor>(descriptors);
	}

	public void setDescriptors(List<TemplateDescriptor> descriptors) {
		this.descriptors = descriptors;
	}

	private class FunctionConfiguration implements Function<File, Configuration> {

		@Override
		public Configuration apply(File file) {
			String configPath = FilenameUtils.concat(file.getAbsolutePath(), "configuration.xml");
			File configFile = new File(configPath);
			log.info("Carregando arquivo de configuração: " + configPath);
			TemplateLoaderDigester digester = new TemplateLoaderDigester();
			return digester.digester(configFile);
		}

	}

	private class FunctionTemplateDescriptor implements Function<Template, TemplateDescriptor> {

		private TemplateReader reader = new TemplateReader();

		@Override
		public TemplateDescriptor apply(Template template) {
			TemplateDescriptor descriptor = new TemplateDescriptor();
			descriptor.setAppendModelName(reader.appendModelName(template));
			descriptor.setFileAppend(reader.getFileAppend(template));
			descriptor.setFileName(reader.getFileName(template));
			descriptor.setFileOutput(reader.getFileOutput(template));
			descriptor.setFileUncapitalize(reader.getFileUncapitalize(template));
			descriptor.setTemplateName(reader.getTemplateName(template));
			log.info("Template Descriptor carregado: " + descriptor);
			return descriptor;
		}

	}

}
