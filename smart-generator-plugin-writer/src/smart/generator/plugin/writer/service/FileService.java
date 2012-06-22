package smart.generator.plugin.writer.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import smart.generator.plugin.console.core.Log;
import smart.generator.plugin.model.descriptors.ModelDescriptor;

import com.google.inject.Inject;

public class FileService {

	@Inject
	private Log log;

	public void write(String projectPath, ModelDescriptor descriptor, byte[] data) {
		File outputdir = new File(projectPath + "/" + descriptor.getFileOutput());
		makeDirectories(outputdir);
		File file = new File(outputdir + "/" + descriptor.getFileName());
		log.info("Diretorio de saida: " + file.getAbsolutePath());
		try {
			FileOutputStream outputStream = new FileOutputStream(file, descriptor.isFileAppend());
			outputStream.write(data);
			outputStream.flush();
			outputStream.close();
			log.info("Arquivo escrito com sucesso: " + file.getName());
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	private void makeDirectories(File outputdir) {
		if (!outputdir.exists()) {
			outputdir.mkdirs();
		}
	}
}
