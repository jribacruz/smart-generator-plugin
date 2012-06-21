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
	private boolean fileUncapitalize;

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

	public boolean isFileUncapitalize() {
		return fileUncapitalize;
	}

	public void setFileUncapitalize(boolean fileUncapitalize) {
		this.fileUncapitalize = fileUncapitalize;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Template [");
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
		if (fileSuffix != null) {
			builder.append("fileSuffix=");
			builder.append(fileSuffix);
			builder.append(", ");
		}
		if (fileOutput != null) {
			builder.append("fileOutput=");
			builder.append(fileOutput);
			builder.append(", ");
		}
		if (filePreffix != null) {
			builder.append("filePreffix=");
			builder.append(filePreffix);
			builder.append(", ");
		}
		if (fileExtention != null) {
			builder.append("fileExtention=");
			builder.append(fileExtention);
			builder.append(", ");
		}
		if (description != null) {
			builder.append("description=");
			builder.append(description);
			builder.append(", ");
		}
		builder.append("fileAppend=");
		builder.append(fileAppend);
		builder.append(", fileUncapitalize=");
		builder.append(fileUncapitalize);
		builder.append("]");
		return builder.toString();
	}

}
