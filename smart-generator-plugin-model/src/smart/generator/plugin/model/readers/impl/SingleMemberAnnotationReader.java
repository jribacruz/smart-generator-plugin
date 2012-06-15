package smart.generator.plugin.model.readers.impl;

import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;

import smart.generator.plugin.model.readers.IAnnotationReader;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class SingleMemberAnnotationReader implements IAnnotationReader<SingleMemberAnnotation> {

	@Override
	public String getName(SingleMemberAnnotation annotation) {
		return annotation.getTypeName().toString();
	}

	@Override
	public Multimap<String, String> getValue(SingleMemberAnnotation annotation) {
		Multimap<String, String> multimap = ArrayListMultimap.create();
		multimap.put("value", annotation.getValue().toString());
		return multimap;
	}

	@Override
	public Multimap<String, String> getValue(MemberValuePair pair) {
		return null;
	}

}
