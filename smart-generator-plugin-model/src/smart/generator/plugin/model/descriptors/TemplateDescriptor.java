package smart.generator.plugin.model.descriptors;

public class TemplateDescriptor {
	private String templateName;
	private String templatePath;
	private String fileName;
	private String fileOutput;
	private boolean fileAppend;
	private String fileSuffix;
	private String filePreffix;
	private String fileExtention;
	private boolean fileAuto;
	private boolean fileUncapitalize;

	public TemplateDescriptor() {
		super();
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileOutput() {
		return fileOutput;
	}

	public void setFileOutput(String fileOutput) {
		this.fileOutput = fileOutput;
	}

	public boolean isFileAppend() {
		return fileAppend;
	}

	public void setFileAppend(boolean fileAppend) {
		this.fileAppend = fileAppend;
	}

	public boolean isFileAuto() {
		return fileAuto;
	}

	public void setFileAuto(boolean fileAuto) {
		this.fileAuto = fileAuto;
	}

	public String getFileSuffix() {
		return fileSuffix;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	public String getFilePreffix() {
		return filePreffix;
	}

	public void setFilePreffix(String filePreffix) {
		this.filePreffix = filePreffix;
	}

	public boolean isFileUncapitalize() {
		return fileUncapitalize;
	}

	public void setFileUncapitalize(boolean fileUncapitalize) {
		this.fileUncapitalize = fileUncapitalize;
	}

	public String getFileExtention() {
		return fileExtention;
	}

	public void setFileExtention(String fileExtention) {
		this.fileExtention = fileExtention;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((templateName == null) ? 0 : templateName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TemplateDescriptor other = (TemplateDescriptor) obj;
		if (templateName == null) {
			if (other.templateName != null) {
				return false;
			}
		} else if (!templateName.equals(other.templateName)) {
			return false;
		}
		return true;
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
		if (templatePath != null) {
			builder.append("templatePath=");
			builder.append(templatePath);
			builder.append(", ");
		}
		if (fileName != null) {
			builder.append("fileName=");
			builder.append(fileName);
			builder.append(", ");
		}
		if (fileOutput != null) {
			builder.append("fileOutput=");
			builder.append(fileOutput);
			builder.append(", ");
		}
		builder.append("fileAppend=");
		builder.append(fileAppend);
		builder.append(", ");
		if (fileSuffix != null) {
			builder.append("fileSuffix=");
			builder.append(fileSuffix);
			builder.append(", ");
		}
		if (filePreffix != null) {
			builder.append("filePreffix=");
			builder.append(filePreffix);
			builder.append(", ");
		}
		builder.append("fileAuto=");
		builder.append(fileAuto);
		builder.append(", fileUncapitalize=");
		builder.append(fileUncapitalize);
		builder.append("]");
		return builder.toString();
	}

}
