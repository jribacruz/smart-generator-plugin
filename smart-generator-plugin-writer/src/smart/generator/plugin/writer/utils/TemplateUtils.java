package smart.generator.plugin.writer.utils;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrSubstitutor;

public class TemplateUtils {
	public static String asPath(File absoluteFile, String path) {
		return absoluteFile.getAbsolutePath().concat("/").concat(path);
	}

	public static String asPath(String absoluteFile, String path) {
		return absoluteFile.concat("/").concat(path);
	}

	public static String toDir(String packageDir) {
		return packageDir.replaceAll("\\.", "/").replaceAll("\\\\", "/");
	}

	public static String concat(String ... itens) {
		StringBuilder builder = new StringBuilder();
		for(String item: itens) {
			builder.append(item);
		}
		return builder.toString();
	}

	public static String normalize(String path) {
		return path.replaceAll("\\\\", "/");
	}

	public static String join(Collection<?> collection) {
		return StringUtils.join(collection, ",");
	}

	public static String substitutor(String data, Map<String, String> keys) {
		StrSubstitutor substitutor = new StrSubstitutor(keys, "@", "@");
		return substitutor.replace(data);
	}
}
