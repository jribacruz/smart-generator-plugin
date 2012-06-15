package smart.generator.plugin.model.readers.impl;

import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import smart.generator.plugin.model.metamodel.XAnnotation;
import smart.generator.plugin.model.predicates.SingleMemberAnnotationPredicate;
import smart.generator.plugin.model.readers.IAttributeVisitorReader;

public class AttributeVisitorReader implements IAttributeVisitorReader {

	@Override
	public String getName(FieldDeclaration declaration) {
		return ((VariableDeclarationFragment) declaration.fragments().get(0)).getName().toString();
	}

	@Override
	public String getType(FieldDeclaration declaration) {
		return declaration.getType().getClass() == ParameterizedType.class ? ((ParameterizedType) declaration.getType())
				.getType().toString() : declaration.getType().toString();
	}

	@Override
	public String getParameterizedType(FieldDeclaration declaration) {
		return declaration.getType().getClass() == ParameterizedType.class ? ((ParameterizedType) declaration.getType())
				.typeArguments().get(0).toString() : null;
	}

	@Override
	public List<XAnnotation> getAnnotations(FieldDeclaration declaration) {
		Collection<?> singleMemberAnnotationList = CollectionUtils.select(declaration.modifiers(),
				new SingleMemberAnnotationPredicate());

		return null;
	}

}
