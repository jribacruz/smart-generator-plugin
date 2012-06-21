package smart.generator.plugin.loader.model;

public class Dependency {
	private Template template;

	public Dependency() {
		super();
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Dependency [");
		if (template != null) {
			builder.append("template=");
			builder.append(template);
		}
		builder.append("]");
		return builder.toString();
	}

}
