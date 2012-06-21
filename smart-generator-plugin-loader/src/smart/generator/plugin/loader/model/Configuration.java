package smart.generator.plugin.loader.model;

import java.util.ArrayList;
import java.util.List;

public class Configuration {
	private Template template;
	private Dependencies dependencies;

	public Configuration() {
		super();
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public Dependencies getDependencies() {
		return dependencies;
	}

	public void setDependencies(Dependencies dependencies) {
		this.dependencies = dependencies;
	}

	public List<Template> getTemplates() {
		List<Template> templateList = new ArrayList<Template>();
		templateList.add(this.template);
		if (dependencies != null) {
			for (Dependency dependency : dependencies.getDependencyList()) {
				templateList.add(dependency.getTemplate());
			}
		}
		return templateList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Configuration [");
		if (template != null) {
			builder.append("template=");
			builder.append(template);
			builder.append(", ");
		}
		if (dependencies != null) {
			builder.append("dependencies=");
			builder.append(dependencies);
		}
		builder.append("]");
		return builder.toString();
	}

}
