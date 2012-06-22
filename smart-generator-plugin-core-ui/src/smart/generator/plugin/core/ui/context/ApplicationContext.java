package smart.generator.plugin.core.ui.context;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;

import smart.generator.plugin.console.core.Log;
import smart.generator.plugin.core.ui.Activator;
import smart.generator.plugin.core.ui.preferences.PreferenceConstants;

import com.google.inject.Inject;

public class ApplicationContext {

	@Inject
	private Log log;

	private IJavaProject jproject;
	private ISelection selection;
	private String projectMetamodelPath;

	public Set<ICompilationUnit> getCompilationUnitList() {
		Set<ICompilationUnit> units = new HashSet<ICompilationUnit>();
		if (jproject != null) {
			try {
				IPackageFragment[] fragments = jproject.getPackageFragments();
				for (IPackageFragment fragment : fragments) {
					ICompilationUnit[] unitList = fragment.getCompilationUnits();
					for (ICompilationUnit unit : unitList) {
						units.add(unit);
					}
				}
			} catch (JavaModelException e) {
				e.printStackTrace();
			}
		}
		log.info("Total de compilation units selecionadas: " + units.size());
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
				}
			}
		}

		log.info("Projeto selecionado: " + this.jproject.getElementName());
		this.projectMetamodelPath = jproject.getProject().getLocation().toString();
		log.info("Projeto Location: " + this.projectMetamodelPath);
	}

	public String getRepositoryPath() {
		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		return preferenceStore.getString(PreferenceConstants.P_PATH);
	}

	public List<IProject> getOpenProjectList() {
		List<IProject> openProjectList = new ArrayList<IProject>();
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		for (IProject project : root.getProjects()) {
			if (project.isOpen()) {
				openProjectList.add(project);
			}
		}
		return openProjectList;
	}

	public void refresh() {
		if (jproject != null) {
			IProject project = jproject.getProject();
			try {
				project.refreshLocal(IProject.DEPTH_INFINITE, null);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}

	public String getProjectMetamodelPath() {
		return this.projectMetamodelPath;
	}
}
