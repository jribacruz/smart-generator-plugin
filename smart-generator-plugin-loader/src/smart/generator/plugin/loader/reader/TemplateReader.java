package smart.generator.plugin.loader.reader;

import org.apache.commons.lang.StringUtils;

import smart.generator.plugin.loader.model.Template;

public class TemplateReader {
	public String getTemplateName(Template template) {
		return template.getTemplateName();
	}

	public boolean getFileAppend(Template template) {
		return template.isFileAppend();
	}

	public boolean appendModelName(Template template) {
		return StringUtils.isEmpty(template.getFileName());
	}

	public String getFileName(Template template) {
		return template.getFilePreffix() + template.getFileName() + template.getFileSuffix() + "."
				+ template.getFileExtention();
	}

	public String getFileOutput(Template template) {
		return template.getFileOutput();
	}

	public boolean getFileUncapitalize(Template template) {
		return template.isFileUncapitalize();
	}

	public String getFileExtention(Template template) {
		return template.getFileExtention();
	}

	public String getFilePreffix(Template template) {
		return template.getFilePreffix();
	}

	public String getFileSuffix(Template template) {
		return template.getFileSuffix();
	}
}
