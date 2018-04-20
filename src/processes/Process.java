package processes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public abstract class Process {

	private static Properties props = new Properties();
	
	private String text;
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

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

	public abstract Object execute();
}
