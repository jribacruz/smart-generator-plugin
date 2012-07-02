package smart.generator.core.context;

import java.io.File;
import java.util.Iterator;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;

import smart.generator.core.Activator;
import smart.generator.core.preferences.PreferenceConstants;

/**
 * Classe com o dados do contexto.
 * 
 * @author jrmc
 * 
 */
public class ProjectContext {
	/*
	 * Aramazena a ICompilationUnit selecionada
	 */
	private ISelection selection;
	private IJavaProject javaproject;

	public ISelection getSelection() {
		return selection;
	}

	/**
	 * Define a seleção e seta o projeto selecionado.
	 * 
	 * @param selection
	 */
	public void setSelection(ISelection selection) {
		this.selection = selection;
		if (selection != null && !selection.isEmpty()) {
			Iterator<?> iterator = ((ITreeSelection) selection).iterator();
			while (iterator.hasNext()) {
				Object item = iterator.next();
				if (item instanceof IJavaProject) {
					this.javaproject = (IJavaProject) item;
				}
			}
		}
	}

	/**
	 * Retorna o objecto File do projeto selecionado.
	 * 
	 * @return Objeto File do projeto
	 */
	public File getProjectLocation() {
		if (this.javaproject != null) {
			return this.javaproject.getProject().getLocation().toFile();
		}
		return null;
	}

	/**
	 * Retorna o caminho do diretorio de templates setdo nas preferencias
	 * 
	 * @return
	 */
	public String getTemplateDirectoryPath() {
		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		return preferenceStore.getString(PreferenceConstants.P_PATH);
	}

	/**
	 * Retorna a unidade de compilacao selecionada
	 * 
	 * @return
	 */
	public ICompilationUnit getCompilationUnit() {
		if (this.selection != null && !this.selection.isEmpty()) {
			IStructuredSelection structuredSelection = (IStructuredSelection) this.selection;
			if (structuredSelection.getFirstElement() instanceof ICompilationUnit) {
				return (ICompilationUnit) structuredSelection.getFirstElement();
			}
		}
		return null;
	}

}
