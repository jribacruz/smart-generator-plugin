package smart.generator.plugin.loader.manager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import smart.generator.plugin.model.descriptors.TemplateDescriptor;

public class TemplateLoaderManager {

	private File repositoryFile;

	private List<TemplateDescriptor> descriptors;

	public TemplateLoaderManager() {
		super();
		this.descriptors = new ArrayList<TemplateDescriptor>();

	}

	public boolean init(String repositoryPath) {
		this.repositoryFile = new File(repositoryPath);
		return this.repositoryFile.exists();
	}

}
