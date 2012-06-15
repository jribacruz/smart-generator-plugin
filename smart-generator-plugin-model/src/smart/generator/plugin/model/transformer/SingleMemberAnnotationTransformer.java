package smart.generator.plugin.model.transformer;

import org.apache.commons.collections.Transformer;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;

import smart.generator.plugin.model.metamodel.XAnnotation;
import smart.generator.plugin.model.readers.impl.SingleMemberAnnotationReader;

public class SingleMemberAnnotationTransformer implements Transformer {

	public SingleMemberAnnotationTransformer() {
		super();
	}

	@Override
	public Object transform(Object annotation) {
		XAnnotation xannotation = new XAnnotation();
		SingleMemberAnnotation smAnnotation = (SingleMemberAnnotation) annotation;
		SingleMemberAnnotationReader reader = new SingleMemberAnnotationReader();
		xannotation.setName(reader.getName(smAnnotation));
		xannotation.getValues().putAll(reader.getValue(smAnnotation));
		return xannotation;
	}

}
