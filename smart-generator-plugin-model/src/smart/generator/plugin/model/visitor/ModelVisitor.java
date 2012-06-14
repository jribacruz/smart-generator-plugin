package smart.generator.plugin.model.visitor;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import smart.generator.plugin.model.metamodel.XAttribute;
import smart.generator.plugin.model.metamodel.XModel;
import smart.generator.plugin.model.reader.impl.AttributeVisitorReader;
import smart.generator.plugin.model.reader.impl.ModelVisitorReader;

public class ModelVisitor extends ASTVisitor {

	private XModel model = new XModel();
	private ModelVisitorReader modelVisitorReader = new ModelVisitorReader();
	private AttributeVisitorReader attributeVisitorReader = new AttributeVisitorReader();

	@Override
	public boolean visit(TypeDeclaration node) {
		model.setName(modelVisitorReader.getName(node));
		model.setSuperClassName(modelVisitorReader.getSuperClass(node));

		return super.visit(node);
	}

	@Override
	public boolean visit(FieldDeclaration node) {
		XAttribute attribute = new XAttribute();
		attribute.setName(attributeVisitorReader.getName(node));
		attribute.setParameterizedType(attributeVisitorReader.getParameterizedType(node));
		attribute.setType(attributeVisitorReader.getType(node));

		model.getAttributes().add(attribute);
		return super.visit(node);
	}

}
