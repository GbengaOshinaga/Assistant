package application;

import analyzer.Analyzer;
import helpers.Task;

public class Application {

	private String text = "";
	
	public void assistant(String input) {
		this.text = input.toLowerCase();
		Enum<Task> task = Analyzer.analyze(this.text);
	}
}
