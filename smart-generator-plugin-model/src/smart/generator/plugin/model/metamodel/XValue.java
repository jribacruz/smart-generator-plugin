package smart.generator.plugin.model.metamodel;

import java.util.ArrayList;

public class XValue extends ArrayList<String> {
	private static final long serialVersionUID = 1L;

	public String getValue() {
		return this.size() > 0 ? this.get(0) : new String();
	}
}
