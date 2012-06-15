package smart.generator.plugin.model.metamodel;

import java.io.Serializable;
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
	 * 
	 */
	private String superClassName;

	/*
	 * Lista de atributos do modelo
	 */
	private Set<XAttribute> attributes;

	/*
	 * 
	 */
	private Set<XAnnotation> annotations;

	public XModel() {
		super();
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

	public XAttribute getAttribute(final String name) {
		return (XAttribute) CollectionUtils.find(this.attributes, new Predicate() {
			@Override
			public boolean evaluate(Object xattribute) {
				return xattribute.equals(name);
			}
		});
	}

	public boolean hasAttribute(final String name) {
		return this.attributes.contains(name);
	}

	public XAnnotation getAnnotation(final String name) {
		return (XAnnotation) CollectionUtils.find(this.annotations, new Predicate() {
			@Override
			public boolean evaluate(Object xannotation) {
				return xannotation.equals(name);
			}
		});
	}

	public boolean hasAnnotation(final String name) {
		return this.annotations.contains(name);
	}

}
