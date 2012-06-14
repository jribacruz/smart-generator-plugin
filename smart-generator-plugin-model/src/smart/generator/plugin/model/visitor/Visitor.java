package smart.generator.plugin.model.visitor;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class Visitor extends ASTVisitor {

	@Override
	public boolean visit(TypeDeclaration node) {
		return super.visit(node);
	}

}
