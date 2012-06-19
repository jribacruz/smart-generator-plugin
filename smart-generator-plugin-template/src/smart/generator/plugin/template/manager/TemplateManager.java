package smart.generator.plugin.template.manager;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrSubstitutor;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import smart.generator.plugin.console.core.Log;
import smart.generator.plugin.model.manager.ModelManager;
import smart.generator.plugin.model.metamodel.XModel;
import smart.generator.plugin.model.metamodel.XTemplate;
import smart.generator.plugin.template.descriptor.TemplateDescriptor;
import smart.generator.plugin.template.service.FileService;

import com.google.inject.Inject;

public class TemplateManager {

	@Inject
	private Log log;

	@Inject
	private ModelManager manager;

	private List<TemplateDescriptor> descriptors = new ArrayList<TemplateDescriptor>();

	private List<String> templatePathList = new ArrayList<String>();

	private FileService service = new FileService();

	public void write(String projectPath, XModel model) {
		initContext(model);
		log.info("Projeto Alvo: " + projectPath);
		for (TemplateDescriptor descriptor : descriptors) {
			if (hasTemplate(model, descriptor)) {
				log.info("Descriptor: " + descriptor.getTemplateName());
				descriptor.setOutput(projectPath.concat("/").concat(this.subsDescriptor(model, descriptor))
						.replaceAll("\\.", "/"));
				log.info("Output: " + descriptor.getOutput());
				if (descriptor.isAppendModelName()) {
					descriptor.setFileName(model.getName().concat(descriptor.getFileName()));
				}
				String data = merge(model, descriptor);
				data = subsData(model, data);
				service.write(descriptor, data.getBytes());
			}
		}
	}

	public void loadDescriptors(String repoPath) {
		log.info("Path do repositorio: " + repoPath);
		File repo = new File(repoPath);
		if (repo.exists() && repo.isDirectory()) {
			File[] fileList = repo.listFiles();
			for (File file : fileList) {
				templatePathList.add(file.getAbsolutePath().replaceAll("\\\\", "/"));
				String genPath = file.getAbsolutePath().concat("/gen.properties");
				log.info("Generator path: " + genPath);
				Properties properties = service.load(genPath);
				descriptors.add(getDescriptor(properties));
			}
		}
		init();
	}

	private void init() {
		Properties properties = new Properties();
		properties.put("resource.loader", "file");
		properties.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
		properties.put("file.resource.loader.path", StringUtils.join(templatePathList, ","));
		log.info("Path de templates: " + StringUtils.join(templatePathList, ","));
		try {
			Velocity.init(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	private String subsDescriptor(XModel model, TemplateDescriptor descriptor) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("PACKAGE_DIR", model.getPackageName());
		map.put("entity", model.getName().toLowerCase());
		StrSubstitutor substitutor = new StrSubstitutor(map, "@", "@");
		return substitutor.replace(descriptor.getOutput());
	}

	private String subsData(XModel model, String data) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("PACKAGE", model.getAnnotation("Package").getString("name"));
		StrSubstitutor substitutor = new StrSubstitutor(map, "@", "@");
		return substitutor.replace(data);
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
		return writer.toString();
	}

	private TemplateDescriptor getDescriptor(Properties properties) {
		TemplateDescriptor descriptor = new TemplateDescriptor();
		String templateName = properties.getProperty("smart.generator.artifact.template.name");
		String fileName = properties.getProperty("smart.generator.artifact.file.name");
		String fileSuffix = properties.getProperty("smart.generator.artifact.file.suffix");
		String fileOutput = properties.getProperty("smart.generator.artifact.file.ouput.dir");
		String extention = properties.getProperty("smart.generator.artifact.file.extention");
		String condition = properties.getProperty("smart.generator.template.condition.annotation");

		// String description =
		// properties.getProperty("smart.generator.artifact.description");
		boolean append = new Boolean(properties.getProperty("smart.generator.artifact.file.append"));
		boolean uncapitalize = new Boolean(properties.getProperty("smart.generator.artifact.file.name.uncapitalize"));

		String completeFileName = fileName.concat(fileSuffix).concat(".").concat(extention);

		descriptor.setTemplateName(templateName);
		descriptor.setFileName(completeFileName);
		descriptor.setAppend(append);
		descriptor.setUncapitalize(uncapitalize);
		descriptor.setOutput(fileOutput);
		descriptor.setAppendModelName(StringUtils.isEmpty(fileName) ? true : false);
		descriptor.setCondition(condition);
		return descriptor;
	}

	public List<TemplateDescriptor> getDescriptors() {
		return descriptors;
	}

	/**
	 * Verifica se o template esta declarado no metamodelo
	 * 
	 * @param model
	 * @param descriptor
	 * @return
	 */
	private boolean hasTemplate(XModel model, final TemplateDescriptor descriptor) {
		return CollectionUtils.exists(model.getTemplates(), new Predicate() {
			@Override
			public boolean evaluate(Object template) {
				XTemplate xtemplate = (XTemplate) template;
				return xtemplate.getTemplate().equals((descriptor.getTemplateName()));
			}
		});
	}

}
