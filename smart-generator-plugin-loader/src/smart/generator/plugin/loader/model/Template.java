package smart.generator.plugin.loader.model;

public class Template {
	private String templateName;
	private String fileName;
	private String fileSuffix;
	private String fileOutput;
	private String filePreffix;
	private String fileExtention;
	private String description;
	private boolean fileAppend;
	private boolean uncapitalize;

	public Template() {
		super();
	}

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

	public String getFileSuffix() {
		return fileSuffix;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	public String getFileOutput() {
		return fileOutput;
	}

	public void setFileOutput(String fileOutput) {
		this.fileOutput = fileOutput;
	}

	public String getFilePreffix() {
		return filePreffix;
	}

	public void setFilePreffix(String filePreffix) {
		this.filePreffix = filePreffix;
	}

	public String getFileExtention() {
		return fileExtention;
	}

	public void setFileExtention(String fileExtention) {
		this.fileExtention = fileExtention;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isFileAppend() {
		return fileAppend;
	}

	public void setFileAppend(boolean fileAppend) {
		this.fileAppend = fileAppend;
	}

	public boolean isUncapitalize() {
		return uncapitalize;
	}

	public void setUncapitalize(boolean uncapitalize) {
		this.uncapitalize = uncapitalize;
	}

}
