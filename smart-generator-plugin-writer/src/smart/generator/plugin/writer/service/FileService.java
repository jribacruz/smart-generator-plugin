package smart.generator.plugin.writer.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import smart.generator.plugin.model.descriptors.TemplateDescriptor;

public class FileService {

	public void write(String projectPath, TemplateDescriptor descriptor, byte[] data) {
		File outputdir = new File(descriptor.getFileOutput());
		makeDirectories(outputdir);
		File file = new File(projectPath + "/" + outputdir);
		try {
			FileOutputStream outputStream = new FileOutputStream(file);
			outputStream.write(data);
			outputStream.flush();
			outputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void makeDirectories(File outputdir) {
		if (!outputdir.exists()) {
			outputdir.mkdirs();
		}
	}
}
