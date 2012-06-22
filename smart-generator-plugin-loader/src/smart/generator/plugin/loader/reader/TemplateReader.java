package smart.generator.plugin.loader.reader;

import smart.generator.plugin.loader.model.Template;

public class TemplateReader {

	private Template template;

	public TemplateReader(Template template) {
		super();
		this.template = template;
	}

	public String getTemplateName() {
		return template.getTemplateName();
	}

	public boolean getFileAppend() {
		return template.isFileAppend();
	}

	public String getFileName() {
		return template.getFileName();
	}

	public String getFileOutput() {
		return template.getFileOutput();
	}

	public boolean getFileUncapitalize() {
		return template.isFileUncapitalize();
	}

	public String getFileExtention() {
		return template.getFileExtention();
	}

	public String getFilePreffix() {
		return template.getFilePreffix();
	}

	public String getFileSuffix() {
		return template.getFileSuffix();
	}

}
