package smart.generator.plugin.loader.manager;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.Velocity;

import smart.generator.plugin.console.core.Log;
import smart.generator.plugin.loader.digester.TemplateLoaderDigester;
import smart.generator.plugin.loader.model.Configuration;
import smart.generator.plugin.loader.model.Template;
import smart.generator.plugin.loader.reader.TemplateReader;
import smart.generator.plugin.model.descriptors.TemplateDescriptor;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.inject.Inject;

public class TemplateLoaderManager {

	@Inject
	private Log log;

	private File repositoryFile;

	private Collection<TemplateDescriptor> descriptors;

	private Collection<String> templatePathList;

	public TemplateLoaderManager() {
		super();
		this.descriptors = new ArrayList<TemplateDescriptor>();
		this.templatePathList = new ArrayList<String>();
	}

	public boolean init(String repositoryPath) {
		log.info("Loader inicializado.");
		this.repositoryFile = new File(repositoryPath);
		load();
		initVelocity();
		return this.repositoryFile.exists();
	}

	private void load() {
		if (repositoryFile.exists() && repositoryFile.isDirectory()) {
			log.info("Lendo repositorio: " + repositoryFile.getAbsolutePath());

			List<File> configurationFiles = Arrays.asList(repositoryFile.listFiles());
			/*
			 * Carrega a lista de todos os arquivos de configuracao que existem
			 * nos diretorios de template
			 */

			Collection<File> configFileList = Collections2.filter(configurationFiles, new PredicateConfigurationFile());

			/* carrega os arquivos de configuracao */
			Collection<Configuration> configurations = Collections2.transform(configFileList, new FunctionConfiguration());
			/* gera os template descriptors */
			CollectionUtils.forAllDo(configurations, new ClosureConfigurationProcessor());
		}
	}

	private void initVelocity() {
		Properties properties = new Properties();
		properties.put("resource.loader", "file");
		properties.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
		properties.put("file.resource.loader.path", StringUtils.join(templatePathList, ","));
		log.info("Path de templates: " + StringUtils.join(templatePathList, ","));
		try {
			Velocity.init(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class PredicateConfigurationFile implements Predicate<File> {

		@Override
		public boolean apply(File file) {
			String configPath = file.getAbsolutePath() + "/configuration.xml";
			File configFile = new File(configPath);
			return configFile.exists() && !configFile.isDirectory();
		}

	}

	private class FunctionConfiguration implements Function<File, Configuration> {

		@Override
		public Configuration apply(File file) {
			File configurationFile = new File(file.getAbsoluteFile() + "/configuration.xml");
			log.info("Carregando arquivo de configuração: " + configurationFile);
			templatePathList.add(file.getAbsolutePath());
			TemplateLoaderDigester digester = new TemplateLoaderDigester();
			return digester.digester(configurationFile);
		}

	}

	private class FunctionTemplateDescriptor implements Function<Template, TemplateDescriptor> {

		@Override
		public TemplateDescriptor apply(Template template) {
			log.info("Processando template: " + template.getTemplateName());
			TemplateReader reader = new TemplateReader(template);
			TemplateDescriptor descriptor = new TemplateDescriptor();
			descriptor.setFilePreffix(reader.getFilePreffix());
			descriptor.setFileSuffix(reader.getFileSuffix());
			descriptor.setFileAppend(reader.getFileAppend());
			descriptor.setFileName(reader.getFileName());
			descriptor.setFileOutput(reader.getFileOutput());
			descriptor.setFileUncapitalize(reader.getFileUncapitalize());
			descriptor.setFileExtention(reader.getFileExtention());
			descriptor.setTemplateName(reader.getTemplateName());
			log.info("Template Descriptor carregado: " + descriptor);
			return descriptor;
		}

	}

	private class ClosureConfigurationProcessor implements Closure {

		@Override
		public void execute(Object configurationItem) {
			log.info("Inicializando Template Descriptor");
			Configuration configuration = (Configuration) configurationItem;
			// List<Template> templates = configuration.getTemplates();
			Collection<TemplateDescriptor> templateDescriptorList = Collections2.transform(configuration.getTemplates(),
					new FunctionTemplateDescriptor());
			descriptors.addAll(templateDescriptorList);
			log.info("Total de descriptor carregados: " + templateDescriptorList.size());
		}

	}

	public List<TemplateDescriptor> getDescriptors() {
		return new ArrayList<TemplateDescriptor>(descriptors);
	}

	public void setDescriptors(List<TemplateDescriptor> descriptors) {
		this.descriptors = descriptors;
	}

}
