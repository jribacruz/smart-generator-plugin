package smart.generator.plugin.model.readers;

import org.eclipse.jdt.core.dom.MemberValuePair;

import com.google.common.collect.Multimap;

public interface IAnnotationReader<T> {
	public String getName(T annotation);

	public Multimap<String, String> getValue(T annotation);

	public Multimap<String, String> getValue(MemberValuePair pair);
}
