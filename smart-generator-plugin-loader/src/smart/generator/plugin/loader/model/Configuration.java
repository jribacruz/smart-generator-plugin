package smart.generator.plugin.loader.model;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;

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
		templateList.addAll(Collections2.transform(dependencies.getDependencyList(), new Function<Dependency, Template>() {
			@Override
			public Template apply(Dependency dependencyItem) {
				return dependencyItem.getTemplate();
			}
		}));
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
