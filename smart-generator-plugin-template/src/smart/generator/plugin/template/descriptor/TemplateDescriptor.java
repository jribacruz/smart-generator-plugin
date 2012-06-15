package smart.generator.plugin.template.descriptor;

public class TemplateDescriptor {
	private String fileName;
	private String output;
	private boolean append;
	private boolean uncapitalize;

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

}
