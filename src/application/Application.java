package application;

import analyzer.Analyzer;
import helpers.Task;
import processes.Process;
import processes.ProcessFactory;

/**
 * Main application class
 * @author Gbenga Oshinaga
 *
 */
public class Application {
	
	public Application() {
	}

	private String text = "";
	
	/**
	 * Accepts and analyzers input from user
	 * @param input
	 * @return the output from the assistant
	 */
	public Object assistant(String input) {
		this.text = input.toLowerCase();
		Enum<Task> task = Analyzer.analyze(this.text);
		ProcessFactory factory = new ProcessFactory();
		Process process = factory.getProcess(task);
		process.setText(this.text);
		return process.execute();
	}
}
