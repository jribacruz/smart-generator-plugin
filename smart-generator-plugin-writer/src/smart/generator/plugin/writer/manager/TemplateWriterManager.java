package smart.generator.plugin.writer.manager;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import smart.generator.plugin.console.core.Log;
import smart.generator.plugin.model.descriptors.TemplateDescriptor;
import smart.generator.plugin.model.manager.ModelManager;
import smart.generator.plugin.model.metamodel.XModel;
import smart.generator.plugin.writer.utils.TemplateUtils;

import com.google.inject.Inject;

public class TemplateWriterManager {

	@Inject
	private Log log;

	@Inject
	private ModelManager manager;

	public void write() {

	}

	private VelocityContext initContext(XModel model) {
		VelocityContext context = new VelocityContext();
		context.put("app", manager.getModels());
		context.put("model", model);
		context.put("metamodel", model);
		context.put("expS", "#{");
		context.put("expE", "}");
		return context;
	}

	private String substitutorDescriptor(XModel model, TemplateDescriptor descriptor) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("PACKAGE_DIR", model.getPackageName());
		map.put("package", model.getPackageName());
		map.put("entity", model.getName().toLowerCase());
		return TemplateUtils.substitutor(descriptor.getFileOutput(), map);
	}

	private String substitutorData(XModel model, String data) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("PACKAGE", model.getAnnotation("Package").getString("name"));
		return TemplateUtils.substitutor(data, map);
	}

	private String merge(XModel model, TemplateDescriptor descriptor) {
		VelocityContext context = initContext(model);
		StringWriter writer = new StringWriter();
		try {
			Template template = Velocity.getTemplate(descriptor.getTemplateName().concat(".vm"));
			template.merge(context, writer);
		} catch (ResourceNotFoundException e) {
			log.error("Recurso não existe: " + e.getMessage());
			e.printStackTrace();
		} catch (ParseErrorException e) {
			log.error("Erro de parser: " + e.getTemplateName() + " linha: " + e.getLineNumber() + " coluna: "
					+ e.getColumnNumber());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.substitutorData(model, writer.toString());
	}
}
