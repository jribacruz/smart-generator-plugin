package smart.generator.plugin.model.transformer;

import java.util.Iterator;

import org.apache.commons.collections.Transformer;
import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.NormalAnnotation;

import smart.generator.plugin.model.metamodel.XAnnotation;
import smart.generator.plugin.model.readers.impl.NormalAnnotationReader;

public class NormalAnnotationTransformer implements Transformer {

	@Override
	public Object transform(Object annotation) {
		XAnnotation xannotation = new XAnnotation();
		NormalAnnotation normalAnnotation = (NormalAnnotation) annotation;
		NormalAnnotationReader reader = new NormalAnnotationReader();
		Iterator<?> iter = normalAnnotation.values().iterator();
		xannotation.setName(reader.getName(normalAnnotation));
		while (iter.hasNext()) {
			MemberValuePair pair = (MemberValuePair) iter.next();
			xannotation.getValues().putAll(reader.getValue(pair));
		}
		return xannotation;
	}

}
