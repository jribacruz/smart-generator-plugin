package smart.generator.core.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Model implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String superClassName;
	private String packageName;
	private Set<Marker> markers;

	public Model() {
		super();
		this.markers = new HashSet<Marker>();
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

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Set<Marker> getMarkers() {
		return markers;
	}

	public void setMarkers(Set<Marker> markers) {
		this.markers = markers;
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
		Model other = (Model) obj;
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
		builder.append("Model [");
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
		if (packageName != null) {
			builder.append("packageName=");
			builder.append(packageName);
		}
		builder.append("]");
		return builder.toString();
	}

}
