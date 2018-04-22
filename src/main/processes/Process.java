package main.processes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Abstract class for process
 * @author Gbenga Oshinaga
 *
 */
public abstract class Process {

	private static Properties props = new Properties();
	
	private String text;
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Loads props if empty and returns it
	 * @return props
	 */
	public static Properties getProps() {
		if (props.isEmpty()) {
			try {
				FileInputStream in = new FileInputStream("./src/config/assistant.properties");
				props.load(in);
				in.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return props;
	}

	/**
	 * All subclasses override this method to provide an implementation
	 * for responding to the input from the user
	 * @return response
	 */
	public abstract Object execute();
}
