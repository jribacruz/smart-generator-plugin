package smart.generator.plugin.model.descriptors;

public class ModelDescriptor {
	public String fileName;
	public boolean fileAppend;
	public String fileOutput;

	public ModelDescriptor() {
		super();
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public boolean isFileAppend() {
		return fileAppend;
	}

	public void setFileAppend(boolean fileAppend) {
		this.fileAppend = fileAppend;
	}

	public String getFileOutput() {
		return fileOutput;
	}

	public void setFileOutput(String fileOutput) {
		this.fileOutput = fileOutput;
	}

}
