package smart.generator.plugin.model.transformer;

import org.apache.commons.collections.Transformer;
import org.eclipse.jdt.core.dom.MarkerAnnotation;

import smart.generator.plugin.model.metamodel.XAnnotation;
import smart.generator.plugin.model.readers.impl.MarkerAnnotationReader;

public class MarkerAnnotationTransformer implements Transformer {

	@Override
	public Object transform(Object annotation) {
		XAnnotation xannotation = new XAnnotation();
		MarkerAnnotation markerAnnotation = (MarkerAnnotation) annotation;
		MarkerAnnotationReader reader = new MarkerAnnotationReader();
		xannotation.setName(reader.getName(markerAnnotation));
		return xannotation;
	}

}
