package smart.generator.plugin.model.processor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import smart.generator.plugin.console.core.Log;
import smart.generator.plugin.model.metamodel.XAttribute;
import smart.generator.plugin.model.metamodel.XImport;
import smart.generator.plugin.model.metamodel.XModel;
import smart.generator.plugin.model.metamodel.XTemplate;

public class ModelProcessor {
	private XModel model;

	private Log log = new Log();

	public ModelProcessor(XModel model) {
		super();
		this.model = model;
		process();
	}

	private void process() {
		processTemplates();
		processImports();
	}

	private void processTemplates() {
		Set<XTemplate> templates = new HashSet<XTemplate>();
		if (model.hasAnnotation("Template")) {
			List<String> modelTemplateList = model.getAnnotation("Template").getList();
			for (XAttribute attribute : model.getAttributes()) {
				if (attribute.hasAnnotation("Template")) {
					List<String> attributeTemplateList = attribute.getAnnotation("Template").getList();
					// modelTemplateList.addAll(attributeTemplateList);
					for (String attributeTemplate : attributeTemplateList) {
						templates.add(new XTemplate(attributeTemplate));
					}
				}
			}
			for (String template : modelTemplateList) {
				templates.add(new XTemplate(template));
			}
		}
		model.setTemplates(templates);
	}

	private void processImports() {
		Set<XImport> imports = new HashSet<XImport>();
		if (model.hasAnnotation("Import")) {
			List<String> modelTemplateList = model.getAnnotation("Import").getList();
			for (XAttribute attribute : model.getAttributes()) {
				if (attribute.hasAnnotation("Import")) {
					List<String> attributeImportList = attribute.getAnnotation("Import").getList();
					// modelTemplateList.addAll(attributeTemplateList);
					for (String attributeImport : attributeImportList) {
						imports.add(new XImport(attributeImport));
					}
				}
			}
			for (String importItem : modelTemplateList) {
				imports.add(new XImport(importItem));
			}
		}
		log.info("Imports: "+imports);
		model.setImports(imports);
	}

	public XModel getModel() {
		return model;
	}

}
