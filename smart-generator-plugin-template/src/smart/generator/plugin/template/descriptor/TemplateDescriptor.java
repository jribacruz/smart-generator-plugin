package smart.generator.plugin.template.descriptor;

public class TemplateDescriptor {
	private String templateName;
	private String fileName;
	private String output;
	private String condition;
	private boolean append;
	private boolean uncapitalize;
	private boolean appendModelName;

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public boolean isAppend() {
		return append;
	}

	public void setAppend(boolean append) {
		this.append = append;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public boolean isUncapitalize() {
		return uncapitalize;
	}

	public void setUncapitalize(boolean uncapitalize) {
		this.uncapitalize = uncapitalize;
	}

	public String getFullPath() {
		return this.output.concat("/").concat(fileName);
	}

	public boolean isAppendModelName() {
		return appendModelName;
	}

	public void setAppendModelName(boolean appendModelName) {
		this.appendModelName = appendModelName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TemplateDescriptor [");
		if (templateName != null) {
			builder.append("templateName=");
			builder.append(templateName);
			builder.append(", ");
		}
		if (fileName != null) {
			builder.append("fileName=");
			builder.append(fileName);
			builder.append(", ");
		}
		if (output != null) {
			builder.append("output=");
			builder.append(output);
			builder.append(", ");
		}
		if (condition != null) {
			builder.append("condition=");
			builder.append(condition);
			builder.append(", ");
		}
		builder.append("append=");
		builder.append(append);
		builder.append(", uncapitalize=");
		builder.append(uncapitalize);
		builder.append(", appendModelName=");
		builder.append(appendModelName);
		builder.append("]");
		return builder.toString();
	}

}
