package smart.generator.core.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Attribute implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String type;
	private String parameterizedType;
	private Set<Marker> markers;

	public Attribute() {
		super();
		this.markers = new HashSet<Marker>();
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
		Attribute other = (Attribute) obj;
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
		builder.append("Attribute [");
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
		if (parameterizedType != null) {
			builder.append("parameterizedType=");
			builder.append(parameterizedType);
		}
		builder.append("]");
		return builder.toString();
	}

}
