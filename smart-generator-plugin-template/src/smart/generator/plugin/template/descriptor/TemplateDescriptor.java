package smart.generator.plugin.template.descriptor;

public class TemplateDescriptor {
	private String fileName;
	private String output;
	private boolean append;
	private boolean uncapitalize;
	private boolean appendModelName;

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
