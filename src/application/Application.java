package application;

import analyzer.Analyzer;
import helpers.Task;
import processes.Process;
import processes.ProcessFactory;

public class Application {
	
	public Application() {
	}

	// Text entered by user
	private String text = "";
	
	public Object assistant(String input) {
		this.text = input.toLowerCase();
		Enum<Task> task = Analyzer.analyze(this.text);
		ProcessFactory factory = new ProcessFactory();
		Process process = factory.getProcess(task);
		process.setText(this.text);
		return process.execute();
	}
}
