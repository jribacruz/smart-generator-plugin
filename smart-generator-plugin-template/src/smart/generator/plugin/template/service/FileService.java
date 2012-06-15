package smart.generator.plugin.template.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import smart.generator.plugin.template.descriptor.TemplateDescriptor;

public class FileService {

	public Properties load(String path) {
		Properties properties = new Properties();
		File file = new File(path);
		try {
			FileInputStream inputStream = new FileInputStream(file);
			properties.load(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	public void write(TemplateDescriptor descriptor, byte[] data) {
		File outputdir = new File(descriptor.getOutput());
		makeDirectories(outputdir);
		File file = new File(descriptor.getFullPath());
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
