package smart.generator.plugin.model.visitor;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import smart.generator.plugin.model.metamodel.XAnnotation;
import smart.generator.plugin.model.metamodel.XAttribute;
import smart.generator.plugin.model.metamodel.XModel;
import smart.generator.plugin.model.predicates.MarkerAnnotationPredicate;
import smart.generator.plugin.model.predicates.NormalAnnotationPredicate;
import smart.generator.plugin.model.predicates.SingleMemberAnnotationPredicate;
import smart.generator.plugin.model.readers.impl.AttributeVisitorReader;
import smart.generator.plugin.model.readers.impl.ModelVisitorReader;
import smart.generator.plugin.model.transformer.MarkerAnnotationTransformer;
import smart.generator.plugin.model.transformer.NormalAnnotationTransformer;
import smart.generator.plugin.model.transformer.SingleMemberAnnotationTransformer;

public class ModelVisitor extends ASTVisitor {

	private XModel model = new XModel();
	private ModelVisitorReader modelVisitorReader = new ModelVisitorReader();
	private AttributeVisitorReader attributeVisitorReader = new AttributeVisitorReader();

	@Override
	public boolean visit(TypeDeclaration node) {
		model.setName(modelVisitorReader.getName(node));
		model.setSuperClassName(modelVisitorReader.getSuperClass(node));
		model.setAnnotations(processAnnotation(node));
		return super.visit(node);
	}

	@Override
	public boolean visit(FieldDeclaration node) {
		XAttribute attribute = new XAttribute();
		attribute.setName(attributeVisitorReader.getName(node));
		attribute.setParameterizedType(attributeVisitorReader.getParameterizedType(node));
		attribute.setType(attributeVisitorReader.getType(node));

		attribute.setAnnotations(processAnnotation(node));

		model.getAttributes().add(attribute);
		return super.visit(node);
	}

	private Set<XAnnotation> processAnnotation(BodyDeclaration node) {
		Set<XAnnotation> annotationsSet = new HashSet<XAnnotation>();

		annotationsSet.addAll(processSingleMemberAnnotation(node));
		annotationsSet.addAll(processNormalAnnotation(node));
		annotationsSet.addAll(processMarkerAnnotation(node));

		return annotationsSet;

	}

	@SuppressWarnings("unchecked")
	private Collection<XAnnotation> processMarkerAnnotation(BodyDeclaration node) {
		Collection<MarkerAnnotation> markerAnnotationList = CollectionUtils.select(node.modifiers(),
				new MarkerAnnotationPredicate());
		return CollectionUtils.collect(markerAnnotationList, new MarkerAnnotationTransformer());
	}

	@SuppressWarnings("unchecked")
	private Collection<XAnnotation> processSingleMemberAnnotation(BodyDeclaration node) {
		Collection<SingleMemberAnnotation> singleMemberAnnotationList = CollectionUtils.select(node.modifiers(),
				new SingleMemberAnnotationPredicate());
		return CollectionUtils.collect(singleMemberAnnotationList, new SingleMemberAnnotationTransformer());
	}

	@SuppressWarnings("unchecked")
	private Collection<XAnnotation> processNormalAnnotation(BodyDeclaration node) {
		Collection<NormalAnnotation> normalAnnotationList = CollectionUtils.select(node.modifiers(),
				new NormalAnnotationPredicate());

		return CollectionUtils.collect(normalAnnotationList, new NormalAnnotationTransformer());
	}

	public XModel getModel() {
		return model;
	}

}
