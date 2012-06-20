package smart.generator.plugin.loader.model;

import java.util.ArrayList;
import java.util.List;

public class Dependencies {
	private List<Dependency> dependencyList;

	public Dependencies() {
		super();
		this.dependencyList = new ArrayList<Dependency>();
	}

	public void addDependency(Dependency dependency) {
		this.dependencyList.add(dependency);
	}

	public List<Dependency> getDependencyList() {
		return dependencyList;
	}

	public void setDependencyList(List<Dependency> dependency) {
		this.dependencyList = dependency;
	}

}
