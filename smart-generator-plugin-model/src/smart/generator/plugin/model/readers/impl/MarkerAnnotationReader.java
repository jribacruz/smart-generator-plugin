package smart.generator.plugin.model.readers.impl;

import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MemberValuePair;

import smart.generator.plugin.model.readers.IAnnotationReader;

import com.google.common.collect.Multimap;

public class MarkerAnnotationReader implements IAnnotationReader<MarkerAnnotation> {

	@Override
	public String getName(MarkerAnnotation annotation) {
		return annotation.getTypeName().toString();
	}

	@Override
	public Multimap<String, String> getValue(MarkerAnnotation annotation) {
		return null;
	}

	@Override
	public Multimap<String, String> getValue(MemberValuePair pair) {
		return null;
	}

}
