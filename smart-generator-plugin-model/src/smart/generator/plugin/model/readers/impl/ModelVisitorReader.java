package smart.generator.plugin.model.readers.impl;

import java.util.List;

import org.eclipse.jdt.core.dom.TypeDeclaration;

import smart.generator.plugin.model.metamodel.XAnnotation;
import smart.generator.plugin.model.readers.IModelVisitorReader;

public class ModelVisitorReader implements IModelVisitorReader {

	@Override
	public String getName(TypeDeclaration declaration) {
		return declaration.getName().toString();
	}

	@Override
	public String getSuperClass(TypeDeclaration declaration) {
		return declaration.getSuperclassType() != null ? declaration.getSuperclassType().toString() : new String();
	}

	@Override
	public List<XAnnotation> getAnnotation(TypeDeclaration declaration) {
		return null;
	}

}
