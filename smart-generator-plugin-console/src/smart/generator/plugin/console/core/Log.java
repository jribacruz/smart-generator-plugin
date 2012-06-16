package smart.generator.plugin.console.core;

import java.io.IOException;

import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

public class Log {

	private static final String SMART_GENERATOR_CONSOLE_NAME = "Smart Generator";

	private MessageConsole getConsole() {
		ConsolePlugin plugin = ConsolePlugin.getDefault();
		IConsoleManager conMan = plugin.getConsoleManager();
		IConsole[] existing = conMan.getConsoles();
		for (int i = 0; i < existing.length; i++) {
			if (SMART_GENERATOR_CONSOLE_NAME.equals(existing[i].getName())) {
				return (MessageConsole) existing[i];
			}
		}
		// no console found, so create a new one
		MessageConsole myConsole = new MessageConsole(SMART_GENERATOR_CONSOLE_NAME, null);
		conMan.addConsoles(new IConsole[] { myConsole });
		return myConsole;
	}

	private MessageConsoleStream getConsoleStream() {
		MessageConsole myConsole = getConsole();
		MessageConsoleStream out = myConsole.newMessageStream();
		return out;
	}

	public void info(String message) {
		getConsoleStream().println("[INFO] " + message);
	}

	public void error(String message, Throwable exception) {
		try {
			getConsoleStream().write("[ERROR] " + message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
