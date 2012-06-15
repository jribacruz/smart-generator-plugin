package smart.generator.plugin.model.readers.impl;

import org.eclipse.jdt.core.dom.ArrayInitializer;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.StringLiteral;

import smart.generator.plugin.model.readers.IAnnotationReader;

import com.google.common.collect.ArrayListMultimap;

public class NormalAnnotationReader implements IAnnotationReader<NormalAnnotation> {

	@Override
	public String name(NormalAnnotation annotation) {
		return annotation.getTypeName().toString();
	}

	@Override
	public ArrayListMultimap<String, String> getValue(NormalAnnotation annotation) {
		return null;
	}

	@Override
	public ArrayListMultimap<String, String> getValue(MemberValuePair pair) {
		ArrayListMultimap<String, String> multimap = ArrayListMultimap.create();
		Expression expression = pair.getValue();
		if (expression.getClass() == StringLiteral.class) {
			StringLiteral literal = (StringLiteral) expression;
			multimap.put(pair.getName().toString(), literal.getEscapedValue());
		} else if(expression.getClass() == ArrayInitializer.class) {
			ArrayInitializer array = (ArrayInitializer) expression;
		}
		return null;
	}

}
