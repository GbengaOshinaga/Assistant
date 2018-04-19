package analyzer;

import helpers.Task;

public class Analyzer {

	public static Enum<Task> analyze(String text) {
		if (text.contains("define") || text.contains("definition")) {
			return Task.DICTIONARY;
		}
		return Task.CONVERSATION;
	}
}
