package processes;

import constants.Task;

/**
 * Factory class for process
 * @author Gbenga Oshinaga
 *
 */
public class ProcessFactory {

  /**
   * Factory method for getting process type
   * @param task
   * @return process depending on task
   */
	public Process getProcess(Enum<Task> task) {
		if (task == Task.DICTIONARY) {
			return DictionaryProcess.getInstance();
		} 
		if (task == Task.WIKI) {
		  return WikiIntroProcess.getInstance();
		}
		return ConversationProcess.getInstance();
	}
}
