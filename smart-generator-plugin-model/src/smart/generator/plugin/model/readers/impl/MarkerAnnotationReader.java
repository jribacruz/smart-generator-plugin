package smart.generator.plugin.model.readers.impl;

import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MemberValuePair;

import smart.generator.plugin.model.readers.IAnnotationReader;

import com.google.common.collect.ArrayListMultimap;

public class MarkerAnnotationReader implements IAnnotationReader<MarkerAnnotation> {

	@Override
	public String name(MarkerAnnotation annotation) {
		return annotation.getTypeName().toString();
	}

	@Override
	public ArrayListMultimap<String, String> getValue(MarkerAnnotation annotation) {
		return null;
	}

	@Override
	public ArrayListMultimap<String, String> getValue(MemberValuePair pair) {
		return null;
	}

}
