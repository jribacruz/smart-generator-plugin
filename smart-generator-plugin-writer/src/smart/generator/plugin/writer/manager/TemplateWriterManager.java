package smart.generator.plugin.writer.manager;

import java.io.StringWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.text.StrSubstitutor;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import smart.generator.plugin.console.core.Log;
import smart.generator.plugin.loader.model.Application;
import smart.generator.plugin.model.descriptors.ModelDescriptor;
import smart.generator.plugin.model.descriptors.TemplateDescriptor;
import smart.generator.plugin.model.manager.ModelManager;
import smart.generator.plugin.model.metamodel.XModel;
import smart.generator.plugin.model.metamodel.XTemplate;
import smart.generator.plugin.writer.readers.ModelDescriptorReader;
import smart.generator.plugin.writer.service.FileService;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.inject.Inject;

public class TemplateWriterManager {

	@Inject
	private Log log;

	@Inject
	private ModelManager manager;

	@Inject
	private FileService service;

	private Map<String, String> maps;

	private List<TemplateDescriptor> descriptors;

	private String projectPath;

	public TemplateWriterManager() {
		super();
		this.maps = new HashMap<String, String>();
	}

	public void init(String projectPath, List<TemplateDescriptor> descriptors) {
		this.descriptors = descriptors;
		this.projectPath = projectPath;
	}

	public void write(final XModel model, Application application) {
		this.initMap(model, application);
		Collection<TemplateDescriptor> templateDescriptorList = getTemplateDescriptorList(descriptors, model);
		Collection<ModelDescriptor> selectedDescriptorList = this.getSelectedDescriptorList(templateDescriptorList, model);
		this.executeWrite(selectedDescriptorList, projectPath, model);

	}

	private Collection<TemplateDescriptor> getTemplateDescriptorList(List<TemplateDescriptor> descriptorList,
			final XModel model) {
		return Collections2.filter(descriptorList, new Predicate<TemplateDescriptor>() {
			@Override
			public boolean apply(TemplateDescriptor descriptor) {
				return model.getTemplates().contains(new XTemplate(descriptor.getTemplateName()));
			}
		});
	}

	/**
	 * Transforma um TemplateDescriptor em ModelDescriptor
	 * 
	 * @param descriptors
	 * @param model
	 * @return
	 */
	private Collection<ModelDescriptor> getSelectedDescriptorList(Collection<TemplateDescriptor> descriptors, final XModel model) {
		return Collections2.transform(descriptors, new Function<TemplateDescriptor, ModelDescriptor>() {
			@Override
			public ModelDescriptor apply(TemplateDescriptor templateDescriptor) {
				ModelDescriptorReader reader = new ModelDescriptorReader(maps, templateDescriptor, model);
				ModelDescriptor modelDescriptor = new ModelDescriptor();
				modelDescriptor.setFileAppend(reader.isFileAppend());
				modelDescriptor.setFileName(reader.getFileName());
				modelDescriptor.setFileOutput(reader.getFileOutput());
				modelDescriptor.setTemplateName(reader.getTemplateName());
				return modelDescriptor;
			}
		});
	}

	/**
	 * Executa a operação de escrita
	 * 
	 * @param modelDescriptorList
	 * @param projectPath
	 * @param model
	 */
	private void executeWrite(Collection<ModelDescriptor> modelDescriptorList, final String projectPath, final XModel model) {
		CollectionUtils.forAllDo(modelDescriptorList, new Closure() {
			@Override
			public void execute(Object descriptorItem) {
				ModelDescriptor descriptor = (ModelDescriptor) descriptorItem;
				byte[] data = merge(model, descriptor);
				service.write(projectPath, descriptor, data);
			}
		});
	}

	private VelocityContext initContext(XModel model) {
		log.info("");
		log.info("Inicializando contexto velocity para " + model.getName());
		VelocityContext context = new VelocityContext();
		context.put("app", manager.getModels());
		context.put("model", model);
		context.put("metamodel", model);
		context.put("expS", "#{");
		context.put("expE", "}");
		return context;
	}

	private void initMap(XModel model, Application application) {
		maps.put("entity", model.getInstanceName());
		maps.put("package", model.getPackageName());
		if (application != null) {
			maps.putAll(application.getProperties(maps));
		}
		log.info("Map:");
		log.info(maps.toString());
		log.info("");
	}

	public Map<String, String> getKeys() {
		return this.maps;
	}

	public void appKeys(Map<String, String> map) {
		this.maps.putAll(map);
	}

	private byte[] merge(XModel model, ModelDescriptor descriptor) {
		VelocityContext context = initContext(model);
		StringWriter writer = new StringWriter();
		try {
			Template template = Velocity.getTemplate(descriptor.getTemplateName().concat(".vm"));
			template.merge(context, writer);
		} catch (ResourceNotFoundException e) {
			log.error("Recurso não existe: " + e.getMessage());
		} catch (ParseErrorException e) {
			log.error("Erro de parser: " + e.getTemplateName() + " linha: " + e.getLineNumber() + " coluna: "
					+ e.getColumnNumber());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		StrSubstitutor substitutor = new StrSubstitutor(maps, "@", "@");
		return substitutor.replace(writer.toString()).getBytes();
	}
}
