package smart.generator.plugin.writer.readers;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrSubstitutor;

import smart.generator.plugin.model.descriptors.TemplateDescriptor;
import smart.generator.plugin.model.metamodel.XModel;

public class ModelDescriptorReader {
	private Map<String, String> maps;
	private TemplateDescriptor descriptor;
	private XModel model;

	public ModelDescriptorReader(Map<String, String> maps, TemplateDescriptor descriptor, XModel model) {
		super();
		this.maps = maps;
		this.descriptor = descriptor;
		this.model = model;
	}

	public String getFileName() {
		StringBuilder builder = new StringBuilder();
		builder.append(descriptor.getFilePreffix());
		if (StringUtils.isEmpty(descriptor.getFileName())) {
			builder.append(model.getName());
		} else {
			builder.append(descriptor.getFileName());
		}

		builder.append(descriptor.getFileSuffix());
		builder.append(".");
		builder.append(descriptor.getFileExtention());
		return builder.toString();
	}

	public String getFileOutput() {
		StrSubstitutor substitutor = new StrSubstitutor(maps, "@", "@");
		return substitutor.replace(descriptor.getFileOutput());
	}

	public boolean isFileAppend() {
		return descriptor.isFileAppend();
	}

}
