package smart.generator.plugin.template.manager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

import smart.generator.plugin.template.descriptor.TemplateDescriptor;
import smart.generator.plugin.template.service.FileService;

public class TemplateManager {

	private List<TemplateDescriptor> descriptors = new ArrayList<TemplateDescriptor>();

	private FileService service = new FileService();

	public void loadDescriptors(String repoPath) {
		File repo = new File(repoPath);
		if (repo.exists() && repo.isDirectory()) {
			File[] fileList = repo.listFiles();
			for (File file : fileList) {
				String genPath = file.getAbsolutePath().concat("/gen.properties");
				Properties properties = service.load(genPath);
				descriptors.add(getDescriptor(properties));
			}
		}
	}

	private TemplateDescriptor getDescriptor(Properties properties) {
		TemplateDescriptor descriptor = new TemplateDescriptor();
		String fileName = properties.getProperty("smart.generator.artifact.file.name");
		String fileSuffix = properties.getProperty("smart.generator.artifact.file.suffix");
		String fileOutput = properties.getProperty("smart.generator.artifact.file.ouput.dir");
		String extention = properties.getProperty("smart.generator.artifact.file.extention");
		// String description =
		// properties.getProperty("smart.generator.artifact.description");
		boolean append = new Boolean(properties.getProperty("smart.generator.artifact.file.append"));
		boolean uncapitalize = new Boolean(properties.getProperty("smart.generator.artifact.file.name.uncapitalize"));

		String completeFileName = fileName.concat(fileSuffix).concat(".").concat(extention);

		descriptor.setFileName(completeFileName);
		descriptor.setAppend(append);
		descriptor.setUncapitalize(uncapitalize);
		descriptor.setOutput(fileOutput);
		descriptor.setAppendModelName(StringUtils.isEmpty(fileName) ? true : false);
		return descriptor;
	}

	public List<TemplateDescriptor> getDescriptors() {
		return descriptors;
	}

}
