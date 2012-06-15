package smart.generator.plugin.model.predicates;

import org.apache.commons.collections.Predicate;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;

public class SingleMemberAnnotationPredicate implements Predicate {

	@Override
	public boolean evaluate(Object annotation) {
		return annotation.getClass() == SingleMemberAnnotation.class;
	}

}
