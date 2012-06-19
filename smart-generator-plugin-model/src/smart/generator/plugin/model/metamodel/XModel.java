package smart.generator.plugin.model.metamodel;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;

/**
 * Classe que representa o metamodelo
 * 
 * @author jrmc
 * 
 */
public class XModel implements Serializable {
	private static final long serialVersionUID = 1L;

	/*
	 * Nome do modelo.
	 */
	private String name;

	/*
	 * Nome da super classe
	 */
	private String superClassName;

	/*
	 * Lista de atributos do modelo
	 */
	private Set<XAttribute> attributes;

	/*
	 * Nome do pacote
	 */
	private String packageName;

	private String instanceName;

	/*
	 * 
	 */
	private Set<XAnnotation> annotations;

	/*
	 * Lista de templates
	 */
	private Set<XTemplate> templates;

	/*
	 * Lista de imports
	 */
	private Set<XImport> imports;

	public XModel() {
		super();
		this.attributes = new HashSet<XAttribute>();
		this.annotations = new HashSet<XAnnotation>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSuperClassName() {
		return superClassName;
	}

	public void setSuperClassName(String superClassName) {
		this.superClassName = superClassName;
	}

	public Set<XAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<XAttribute> attributes) {
		this.attributes = attributes;
	}

	public Set<XAnnotation> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(Set<XAnnotation> annotations) {
		this.annotations = annotations;
	}

	public String getPackageName() {
		if (hasAnnotation("Package")) {
			return getAnnotation("Package").getString("value");
		}
		return new String();
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Set<XTemplate> getTemplates() {
		return templates;
	}

	public void setTemplates(Set<XTemplate> templates) {
		this.templates = templates;
	}

	public Set<XImport> getImports() {
		return imports;
	}

	public void setImports(Set<XImport> imports) {
		this.imports = imports;
	}

	public String getInstanceName() {
		return StringUtils.uncapitalize(this.name);
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		XModel other = (XModel) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("XModel [");
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (superClassName != null) {
			builder.append("superClassName=");
			builder.append(superClassName);
			builder.append(", ");
		}
		if (attributes != null) {
			builder.append("attributes=");
			builder.append(attributes);
			builder.append(", ");
		}
		if (packageName != null) {
			builder.append("packageName=");
			builder.append(packageName);
			builder.append(", ");
		}
		if (annotations != null) {
			builder.append("annotations=");
			builder.append(annotations);
			builder.append(", ");
		}
		if (templates != null) {
			builder.append("templates=");
			builder.append(templates);
			builder.append(", ");
		}
		if (imports != null) {
			builder.append("imports=");
			builder.append(imports);
		}
		builder.append("]");
		return builder.toString();
	}

	public XAttribute getAttribute(final String name) {
		return (XAttribute) CollectionUtils.find(this.attributes, new Predicate() {
			@Override
			public boolean evaluate(Object xattribute) {
				return ((XAttribute) xattribute).getName().equals(name);
			}
		});
	}

	public boolean hasAttribute(final String name) {
		return this.getAttribute(name) != null;
	}

	public XAnnotation getAnnotation(final String name) {
		return (XAnnotation) CollectionUtils.find(this.annotations, new Predicate() {
			@Override
			public boolean evaluate(Object xannotation) {
				return ((XAnnotation) xannotation).getName().equals(name);
			}
		});
	}

	public boolean hasAnnotation(final String name) {
		return this.getAnnotation(name) != null;
	}

	public boolean isCoreAnnotation(XAnnotation annotation) {
		for (XImport importData : this.imports) {
			if (importData.getValue().replaceAll("\"", "").endsWith(annotation.getName())) {
				return true;
			}
		}
		return false;
	}

	public String getCoreAnnotation(XAnnotation annotation) {
		StringBuilder builder = new StringBuilder();

		builder.append("@");
		builder.append(annotation.getName());

		if (annotation.getValues().size() > 0) {
			builder.append("(");
			Iterator<String> iterator = annotation.getValues().keySet().iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				List<String> values = annotation.getValues().get(key);
				builder.append(key);
				builder.append("=");
				if (values.size() > 1) {
					builder.append("{");
				}
				if (values.size() == 1) {
					builder.append(values.get(0));
				} else if (values.size() > 1) {
					builder.append(StringUtils.join(values, ","));
				}
				if (values.size() > 1) {
					builder.append("}");
				}
			}
			builder.append(")");
		}

		return builder.toString();
	}
}
