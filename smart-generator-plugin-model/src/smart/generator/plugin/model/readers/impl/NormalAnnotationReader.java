package smart.generator.plugin.model.readers.impl;

import java.util.Iterator;

import org.eclipse.jdt.core.dom.ArrayInitializer;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.StringLiteral;

import smart.generator.plugin.model.readers.IAnnotationReader;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class NormalAnnotationReader implements IAnnotationReader<NormalAnnotation> {

	@Override
	public String getName(NormalAnnotation annotation) {
		return annotation.getTypeName().toString();
	}

	@Override
	public ArrayListMultimap<String, String> getValue(NormalAnnotation annotation) {
		return null;
	}

	@Override
	public Multimap<String, String> getValue(MemberValuePair pair) {
		Multimap<String, String> multimap = ArrayListMultimap.create();
		Expression expression = pair.getValue();
		if (expression.getClass() == StringLiteral.class) {
			StringLiteral literal = (StringLiteral) expression;
			multimap.put(pair.getName().toString(), literal.getEscapedValue());
		} else if (expression.getClass() == ArrayInitializer.class) {
			ArrayInitializer array = (ArrayInitializer) expression;
			Iterator<?> iterator = array.expressions().iterator();
			while (iterator.hasNext()) {
				Object item = iterator.next();
				if (item.getClass() == StringLiteral.class) {
					StringLiteral literal = (StringLiteral) item;
					multimap.put(pair.getName().toString(), literal.getEscapedValue());
				} else if(item.getClass() == NumberLiteral.class) {
					NumberLiteral literal = (NumberLiteral) item;
					multimap.put(pair.getName().toString(), literal.getToken());
				} else if(item.getClass() == QualifiedName.class) {
					QualifiedName qualifiedName = (QualifiedName) item;
					multimap.put(pair.getName().toString(), qualifiedName.getFullyQualifiedName());
				}
			}
		} else if(expression.getClass() == QualifiedName.class) {
			QualifiedName qualifiedName = (QualifiedName) expression;
			multimap.put(pair.getName().toString(), qualifiedName.getFullyQualifiedName());
		}
		return multimap;
	}

}
