/**
 * 
 */
package processes;

import java.util.Properties;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Process for responding to dictionary requests
 * @author Gbenga Oshinaga
 *
 */
public class DictionaryProcess extends Process {

	private final String URL = "https://od-api.oxforddictionaries.com/api/v1";

	private final String LANG = "en";

	private String APP_ID;

	private String KEY;

	private static final DictionaryProcess INSTANCE = new DictionaryProcess();

	private DictionaryProcess() {
		Properties props = super.getProps();
		this.APP_ID = props.getProperty("DICTIONARY_APP_ID");
		this.KEY = props.getProperty("DICTIONARY_KEY");
	}

	/**
	 * Returns static instance of class
	 * @return instance of class
	 */
	public static DictionaryProcess getInstance() {
		return INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see processes.Process#execute()
	 */
	@Override
	public Object execute() {
	  String word = this.getText();
	  String response = "";
	  try {
	    URLConnection conn = new URL(URL + "/entries/" + LANG + "/" + word + "/definitions").openConnection();
	    conn.addRequestProperty("Accept", "application/json");
	    conn.addRequestProperty("app_id", APP_ID);
	    conn.addRequestProperty("app_key", KEY);

	    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
		  sb.append((char) cp);
	    }
	    response = sb.toString();
	  } catch (MalformedURLException e) {
		  e.printStackTrace();
	  } catch (IOException e) {
		  e.printStackTrace();
	  }
	return response;
	}

}
