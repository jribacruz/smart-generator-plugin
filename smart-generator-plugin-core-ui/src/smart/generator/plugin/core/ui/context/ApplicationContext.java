package smart.generator.plugin.core.ui.context;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;

public class ApplicationContext {

	private IJavaProject jproject;
	private ISelection selection;

	public Set<ICompilationUnit> getCompilationUnitList() {
		Set<ICompilationUnit> units = new HashSet<ICompilationUnit>();
		if (jproject != null) {
			try {
				IPackageFragment[] fragments = jproject.getPackageFragments();
				for (IPackageFragment fragment : fragments) {
					System.out.println("Package Fragments: "+fragment.getElementName());
					ICompilationUnit[] unitList = fragment.getCompilationUnits();
					for (ICompilationUnit unit : unitList) {
						units.add(unit);
					}
				}
			} catch (JavaModelException e) {
				e.printStackTrace();
			}
		}
		return units;
	}

	public void setSelection(ISelection selection) {
		this.selection = selection;
		if (selection != null && !selection.isEmpty()) {
			Iterator<?> iterator = ((ITreeSelection) selection).iterator();
			while (iterator.hasNext()) {
				Object item = iterator.next();
				if (item instanceof IJavaProject) {
					this.jproject = (IJavaProject) item;
					System.out.println("Java Project: "+this.jproject);
				}
			}
		}
	}

}
