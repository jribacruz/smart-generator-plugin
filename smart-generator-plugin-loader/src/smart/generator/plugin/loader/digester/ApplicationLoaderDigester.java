package smart.generator.plugin.loader.digester;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

import smart.generator.plugin.console.core.Log;
import smart.generator.plugin.loader.Activator;
import smart.generator.plugin.loader.model.Application;
import smart.generator.plugin.loader.model.Properties;
import smart.generator.plugin.loader.model.Property;

public class ApplicationLoaderDigester {
	private Log log = new Log();

	public Application digester(File applicationFile) {
		Digester digester = new Digester();
		digester.setValidating(false);

		digester.setClassLoader(Activator.class.getClassLoader());

		digester.addObjectCreate("application", Application.class);
		digester.addSetProperties("application", "name", "name");

		digester.addObjectCreate("application/properties", Properties.class);
		digester.addSetNext("application/properties", "setProperties");

		digester.addObjectCreate("application/properties/property", Property.class);
		digester.addSetProperties("application/properties/property", "key", "key");
		digester.addSetProperties("application/properties/property", "value", "value");

		digester.addSetNext("application/properties/property", "addProperty");

		Application application = new Application();

		try {
			application = (Application) digester.parse(applicationFile);
			log.info("");
			log.info("Digester application finalizado: " + application);
		} catch (IOException e) {
			StringWriter stringWriter = new StringWriter();
			e.printStackTrace(new PrintWriter(stringWriter));
			log.error(stringWriter.toString());
		} catch (SAXException e) {
			StringWriter stringWriter = new StringWriter();
			e.printStackTrace(new PrintWriter(stringWriter));
			log.error(stringWriter.toString());
		}

		return application;
	}
}
