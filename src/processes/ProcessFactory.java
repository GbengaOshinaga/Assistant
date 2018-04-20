package processes;

import helpers.Task;

public class ProcessFactory {

	public Process getProcess(Enum<Task> task) {
		if (task == Task.DICTIONARY) {
			return DictionaryProcess.getInstance();
		} 
		return null;
	}
}
