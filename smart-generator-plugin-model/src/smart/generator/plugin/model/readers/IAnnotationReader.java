package smart.generator.plugin.model.readers;

import org.eclipse.jdt.core.dom.MemberValuePair;

import com.google.common.collect.ArrayListMultimap;

public interface IAnnotationReader<T> {
	public String name(T annotation);

	public ArrayListMultimap<String, String> getValue(T annotation);

	public ArrayListMultimap<String, String> getValue(MemberValuePair pair);
}
