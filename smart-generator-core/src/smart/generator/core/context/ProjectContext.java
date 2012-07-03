package smart.generator.core.context;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

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
	}

	/**
	 * 
	 * @return
	 */
	public String getProjectLocation() {
		if (this.getSelectedCompilationUnit() != null) {
			return this.getSelectedCompilationUnit().getJavaProject().getProject().getLocation().toString();
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
	public ICompilationUnit getSelectedCompilationUnit() {
		if (this.selection != null && !this.selection.isEmpty()) {
			IStructuredSelection structuredSelection = (IStructuredSelection) this.selection;
			if (structuredSelection.getFirstElement() instanceof ICompilationUnit) {
				return (ICompilationUnit) structuredSelection.getFirstElement();
			}
		}
		return null;
	}

}
