package smart.generator.plugin.core.ui.providers;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.LabelProvider;

public class ProjectLabelProvider extends LabelProvider {

	@Override
	public String getText(Object element) {
		IProject project = (IProject) element;
		return project.getName();
	}

}
