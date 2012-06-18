package smart.generator.plugin.model.metamodel;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

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

}
