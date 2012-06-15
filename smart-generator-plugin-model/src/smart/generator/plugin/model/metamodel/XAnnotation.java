package smart.generator.plugin.model.metamodel;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.ArrayListMultimap;

/**
 * Classe que representa as anotações do metamodelo.
 * 
 * @author jrmc
 * 
 */
public class XAnnotation implements Serializable {
	private static final long serialVersionUID = 1L;

	/*
	 * Nome da annotation
	 */
	private String name;

	private ArrayListMultimap<String, String> values;

	public XAnnotation() {
		super();
		values = ArrayListMultimap.create();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		XAnnotation other = (XAnnotation) obj;
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
		builder.append("XAnnotation [");
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (values != null) {
			builder.append("values=");
			builder.append(values);
		}
		builder.append("]");
		return builder.toString();
	}

	public String getString(String key) {
		return this.values.get(key).size() > 0 ? this.values.get(key).get(0) : new String();
	}

	public String getValue() {
		return this.values.get("value").size() > 0 ? this.values.get("value").get(0) : new String();
	}

	public List<String> getList(String key) {
		return this.values.get(key);
	}

	public boolean hasKey(final String key) {
		return this.values.containsKey(key);
	}

}
