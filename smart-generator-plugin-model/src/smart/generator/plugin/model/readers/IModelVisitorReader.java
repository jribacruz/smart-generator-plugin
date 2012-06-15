package smart.generator.plugin.model.readers;

import java.util.List;

import org.eclipse.jdt.core.dom.TypeDeclaration;

import smart.generator.plugin.model.metamodel.XAnnotation;

public interface IModelVisitorReader {
	public String getName(TypeDeclaration declaration);

	public String getSuperClass(TypeDeclaration declaration);

	public List<XAnnotation> getAnnotation(TypeDeclaration declaration);

}
