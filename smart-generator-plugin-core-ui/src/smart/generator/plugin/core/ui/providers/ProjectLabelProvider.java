package smart.generator.plugin.core.ui.providers;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public class ProjectLabelProvider extends LabelProvider {

	@Override
	public Image getImage(Object element) {
		return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages. IMG_OBJ_PROJECT);
	}

	@Override
	public String getText(Object element) {
		IProject project = (IProject) element;
		return project.getName();
	}

}
