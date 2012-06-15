package smart.generator.plugin.model.readers;

import java.util.List;

import org.eclipse.jdt.core.dom.FieldDeclaration;

import smart.generator.plugin.model.metamodel.XAnnotation;

public interface IAttributeVisitorReader {
	public String getName(FieldDeclaration declaration);

	public String getType(FieldDeclaration declaration);

	public String getParameterizedType(FieldDeclaration declaration);

	public List<XAnnotation> getAnnotations(FieldDeclaration declaration);
}
