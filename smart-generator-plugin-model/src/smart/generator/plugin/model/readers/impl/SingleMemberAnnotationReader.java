package smart.generator.plugin.model.readers.impl;

import java.util.Iterator;

import org.eclipse.jdt.core.dom.ArrayInitializer;
import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.StringLiteral;

import smart.generator.plugin.console.core.Log;
import smart.generator.plugin.model.readers.IAnnotationReader;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class SingleMemberAnnotationReader implements IAnnotationReader<SingleMemberAnnotation> {

	private Log log = new Log();

	@Override
	public String getName(SingleMemberAnnotation annotation) {
		return annotation.getTypeName().toString();
	}

	@Override
	public Multimap<String, String> getValue(SingleMemberAnnotation annotation) {
		Multimap<String, String> multimap = ArrayListMultimap.create();
		if (annotation.getValue().getClass() == ArrayInitializer.class) {
			ArrayInitializer array = (ArrayInitializer) annotation.getValue();
			Iterator<?> iterator = array.expressions().iterator();
			while (iterator.hasNext()) {
				Object item = iterator.next();
				if (item.getClass() == StringLiteral.class) {
					StringLiteral literal = (StringLiteral) item;
					multimap.put("value", literal.getEscapedValue());
				} else if (item.getClass() == NumberLiteral.class) {
					NumberLiteral literal = (NumberLiteral) item;
					multimap.put("value", literal.getToken());
				}
			}
		} else {
			multimap.put("value", annotation.getValue().toString());
		}
		return multimap;
	}

	@Override
	public Multimap<String, String> getValue(MemberValuePair pair) {
		return null;
	}

}
