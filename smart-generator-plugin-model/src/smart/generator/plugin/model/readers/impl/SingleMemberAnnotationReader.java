package smart.generator.plugin.model.readers.impl;

import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;

import smart.generator.plugin.model.readers.IAnnotationReader;

import com.google.common.collect.ArrayListMultimap;

public class SingleMemberAnnotationReader implements IAnnotationReader<SingleMemberAnnotation> {

	@Override
	public String name(SingleMemberAnnotation annotation) {
		return annotation.getTypeName().toString();
	}

	@Override
	public ArrayListMultimap<String, String> getValue(SingleMemberAnnotation annotation) {
		ArrayListMultimap<String, String> multimap = ArrayListMultimap.create();
		multimap.put("value", annotation.getValue().toString());
		return multimap;
	}

	@Override
	public ArrayListMultimap<String, String> getValue(MemberValuePair pair) {
		return null;
	}

}
