package smart.generator.plugin.model.manager;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import smart.generator.plugin.model.metamodel.XModel;
import smart.generator.plugin.model.visitor.ModelVisitor;

public class ModelManager {

	private Map<ICompilationUnit, XModel> models = new HashMap<ICompilationUnit, XModel>();

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

	public XModel get(ICompilationUnit unit) {
		return models.get(unit);
	}
}
