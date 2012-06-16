package smart.generator.plugin.core.ui.providers;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jface.viewers.LabelProvider;

public class CompilationUnitLabelProvider extends LabelProvider {

	@Override
	public String getText(Object element) {
		ICompilationUnit unit = (ICompilationUnit) element;
		return unit.getElementName();
	}

}
