package smart.generator.plugin.loader.digester;

import java.io.File;
import java.io.IOException;

import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

import smart.generator.plugin.console.core.Log;
import smart.generator.plugin.loader.model.Configuration;
import smart.generator.plugin.loader.model.Dependencies;
import smart.generator.plugin.loader.model.Dependency;
import smart.generator.plugin.loader.model.Template;

public class TemplateLoaderDigester {

	private Log log = new Log();

	public Configuration digester(File configurationFile) {
		Digester digester = new Digester();
		digester.setValidating(false);
		digester.addObjectCreate("configuration", Configuration.class);
		digester.addObjectCreate("configuration/template", Template.class);

		digester.addBeanPropertySetter("configuration/template/file-name", "fileName");
		digester.addBeanPropertySetter("configuration/template/file-append", "fileAppend");
		digester.addBeanPropertySetter("configuration/template/file-suffix", "fileSuffix");
		digester.addBeanPropertySetter("configuration/template/file-preffix", "filePreffix");
		digester.addBeanPropertySetter("configuration/template/file-extention", "fileExtention");
		digester.addBeanPropertySetter("configuration/template/file-uncapitalize", "fileUncapitalize");
		digester.addBeanPropertySetter("configuration/template/file-output", "fileOutput");

		digester.addSetProperties("configuration/template", "name", "templateName");
		digester.addSetProperties("configuration/template", "auto", "auto");
		digester.addSetNext("configuration/template", "setTemplate");

		digester.addObjectCreate("configuration/dependencies", Dependencies.class);
		digester.addSetNext("configuration/dependencies", "setDependencies");
		digester.addObjectCreate("configuration/dependencies/dependency", Dependency.class);

		digester.addObjectCreate("configuration/dependencies/dependency/template", Template.class);

		digester.addBeanPropertySetter("configuration/dependencies/dependency/template/file-name", "fileName");
		digester.addBeanPropertySetter("configuration/dependencies/dependency/template/file-append", "fileAppend");
		digester.addBeanPropertySetter("configuration/dependencies/dependency/template/file-suffix", "fileSuffix");
		digester.addBeanPropertySetter("configuration/dependencies/dependency/template/file-preffix", "filePreffix");
		digester.addBeanPropertySetter("configuration/dependencies/dependency/template/file-extention", "fileExtention");
		digester.addBeanPropertySetter("configuration/dependencies/dependency/template/file-uncapitalize",
				"fileUncapitalize");
		digester.addBeanPropertySetter("configuration/dependencies/dependency/template/file-output", "fileOutput");

		digester.addSetProperties("configuration/dependencies/dependency/template", "name", "templateName");
		digester.addSetProperties("configuration/dependencies/dependency/template", "auto", "auto");

		digester.addSetNext("configuration/dependencies/dependency/template", "setTemplate");
		digester.addSetNext("configuration/dependencies/dependency", "addDependency");

		Configuration configuration = new Configuration();
		try {
			configuration = (Configuration) digester.parse(configurationFile);
		} catch (IOException e) {
			log.error(e.getMessage());
		} catch (SAXException e) {
			log.error(e.getMessage());
		}

		return configuration;
	}
}
