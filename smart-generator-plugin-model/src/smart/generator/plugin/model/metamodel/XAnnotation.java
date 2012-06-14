package smart.generator.plugin.model.metamodel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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

	private Map<String, XValue> values;

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

	public String getString(String key) {
		return this.values.get(key).getValue();
	}

	public List<String> getList(String key) {
		return this.values.get(key);
	}

	public boolean hasKey(final String key) {
		return this.values.containsKey(key);
	}

}
