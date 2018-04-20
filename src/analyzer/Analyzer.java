package analyzer;

import helpers.Task;

/**
 * Analyzes text entered by the user
 * @author Gbenga Oshinaga
 *
 */
public class Analyzer {

	/**
	 * Analyze text to determine what task should be performed
	 * @param text
	 * 
	 * @return type of task to be performed
	 */
	public static Enum<Task> analyze(String text) {
		if (text.contains("define") || text.contains("definition")) {
			return Task.DICTIONARY;
		}
		return Task.CONVERSATION;
	}
}
