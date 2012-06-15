package smart.generator.plugin.model.metamodel;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

/**
 * Classe que representa os atributos do modelo
 * 
 * @author jrmc
 * 
 */
public class XAttribute implements Serializable {
	private static final long serialVersionUID = 1L;

	/*
	 * Nome do atributo
	 */
	private String name;

	/*
	 * Nome do tipo do atributo
	 */
	private String type;

	/*
	 * Nome do tipo parametrizado
	 */
	private String parameterizedType;

	/*
	 * 
	 */
	private Set<XAnnotation> annotations;

	public XAttribute() {
		super();
		this.annotations = new HashSet<XAnnotation>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParameterizedType() {
		return parameterizedType;
	}

	public void setParameterizedType(String parameterizedType) {
		this.parameterizedType = parameterizedType;
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
		XAttribute other = (XAttribute) obj;
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
		builder.append("XAttribute [");
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (type != null) {
			builder.append("type=");
			builder.append(type);
			builder.append(", ");
		}
		if (parameterizedType != null && !parameterizedType.isEmpty()) {
			builder.append("parameterizedType=");
			builder.append(parameterizedType);
			builder.append(", ");
		}
		if (annotations != null) {
			builder.append("annotations=");
			builder.append(annotations);
		}
		builder.append("]");
		return builder.toString();
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
