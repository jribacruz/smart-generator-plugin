package smart.generator.plugin.model.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import smart.generator.plugin.model.metamodel.XModel;
import smart.generator.plugin.model.visitor.ModelVisitor;

/**
 * Classe responsavel por gerenciar os metamodelos.
 * 
 * @author jrmc
 *
 */
public class ModelManager {

	private Map<ICompilationUnit, XModel> models = new HashMap<ICompilationUnit, XModel>();

	/**
	 * Dado uma ICompilationUnit retorna o metamodelo correspondente
	 * 
	 * @param unit
	 * @return
	 */
	public XModel put(ICompilationUnit unit) {
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(unit);
		parser.setResolveBindings(true);
		CompilationUnit cunit = (CompilationUnit) parser.createAST(null);
		ModelVisitor visitor = new ModelVisitor();
		cunit.accept(visitor);
		models.put(unit, visitor.getModel());
		return visitor.getModel();
	}

	/**
	 * Retorna o metamodelo correspondente
	 * 
	 * @param unit
	 * @return
	 */
	public XModel get(ICompilationUnit unit) {
		return models.get(unit);
	}

	public List<XModel> getModels() {
		return new ArrayList<XModel>(models.values());
	}
}
