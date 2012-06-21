package smart.generator.plugin.writer.manager;

import java.io.StringWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import smart.generator.plugin.console.core.Log;
import smart.generator.plugin.model.descriptors.TemplateDescriptor;
import smart.generator.plugin.model.manager.ModelManager;
import smart.generator.plugin.model.metamodel.XModel;
import smart.generator.plugin.model.metamodel.XTemplate;
import smart.generator.plugin.writer.utils.TemplateUtils;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.inject.Inject;

public class TemplateWriterManager {

	@Inject
	private Log log;

	@Inject
	private ModelManager manager;

	public TemplateWriterManager() {
		super();
	}

	public void write(List<TemplateDescriptor> descriptors, String projectPath, final XModel model) {
		log.info("Iniciando escrita: " + model.getName());
		initContext(model);
		Collection<TemplateDescriptor> selectedDescriptorList = Collections2.filter(descriptors,
				new Predicate<TemplateDescriptor>() {
			@Override
			public boolean apply(TemplateDescriptor descriptor) {
				return model.getTemplates().contains(new XTemplate(descriptor.getTemplateName()));
			}
		});

		log.info("Total de template para modelo: " + model.getName() + " " + selectedDescriptorList.size());
		log.info("Templates para modelo: " + model.getName() + " : " + selectedDescriptorList);

		CollectionUtils.forAllDo(selectedDescriptorList, new Closure() {

			@Override
			public void execute(Object descriptorItem) {
				TemplateDescriptor descriptor = (TemplateDescriptor) descriptorItem;
				descriptor.setFileOutput(substitutorDescriptor(model, descriptor));
				String data = substitutorData(model, merge(model, descriptor));
				log.info("\n" + data);
			}
		});

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
