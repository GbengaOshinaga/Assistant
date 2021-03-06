/**
 * 
 */
package processes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Properties;

import javax.net.ssl.SSLHandshakeException;

import com.google.gson.Gson;

import dictionarymodel.DictionaryModel;
import dictionarymodel.Entry;
import dictionarymodel.LexicalEntry;
import dictionarymodel.Result;
import dictionarymodel.Sense;
import dictionarymodel.Subsense;

/**
 * Process for responding to dictionary requests
 * @author Gbenga Oshinaga
 *
 */
public class DictionaryProcess extends Process {

	private final String URL = "https://od-api.oxforddictionaries.com/api/v1";

	private final String lang = "en";

	private String appId;

	private String key;

	private static final DictionaryProcess INSTANCE = new DictionaryProcess();

	private DictionaryProcess() {
		Properties props = super.getProps();
		this.appId = props.getProperty("DICTIONARY_APP_ID");
		this.key = props.getProperty("DICTIONARY_KEY");
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
    String word = getWord(this.getText());
    String response = "";
    try {
      URLConnection conn = new URL(URL + "/entries/" + lang + "/" + word + "/definitions").openConnection();
      conn.addRequestProperty("Accept", "application/json");
      conn.addRequestProperty("app_id", appId);
      conn.addRequestProperty("app_key", key);

      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

      StringBuilder stringBuilder = new StringBuilder();
      int charInteger;
      while ((charInteger = bufferedReader.read()) != -1) {
        stringBuilder.append((char) charInteger);
      }
      response = stringBuilder.toString();
    } catch (MalformedURLException | SSLHandshakeException | UnknownHostException e) {
      return "An error occurred, you are most likely not connected to the internet";
    } catch (FileNotFoundException e) {
      return "I could not find " + word + " in the dictionary";
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return getDefinitions(response);
  }
  
  
  /**
   * Gets the word to search for from statement
   * @param statement
   * @return word to search dictionary for
   */
  private String getWord(String statement) {
    String word = "";
    String[] splitStatement = statement.split("\\s+");
    
    if (statement.contains("define")) {
      for (int i = 0; i<splitStatement.length - 1; i++) {
        if (splitStatement[i].equals("define")) {
          word = splitStatement[i + 1];
        }
      }
    }
    
    if (statement.contains("definition") && 
        !splitStatement[splitStatement.length - 1].equals("definition")) {
      for (int i = 0; i<splitStatement.length - 1; i++) {
        if (splitStatement[i].equals("definition") && splitStatement[i+1].equals("of")) {
          word = splitStatement[i+2];
        }
      }
    }
    
    if (word.contains("?")) {
      word = word.replace("?", "");
    }
    
    return word;
  }
  
  
  /**
   * Get definitions of word from Json
   * @param json
   * @return list of definitions
   */
  private String getDefinitions(String json) {
    ArrayList<String> definitions = new ArrayList<>();
    Gson gson = new Gson();
    DictionaryModel model = gson.fromJson(json, DictionaryModel.class);
    
    Result results = model.getResults().get(0);
    LexicalEntry entries = results.getLexicalEntries().get(0);
    Entry entry;
    Sense sense;
    Subsense subsense;
    
    for (int i = 0; i< entries.getEntries().size(); i++) {
      entry = entries.getEntries().get(i);
      for (int j = 0; j < entry.getSenses().size(); j++) {
        sense = entry.getSenses().get(j);
        for (int k = 0; k < sense.getDefinitions().size(); k++) {
          definitions.add(sense.getDefinitions().get(k));
          for (int l = 0; l < sense.getSubsenses().size(); l++) {
            subsense = sense.getSubsenses().get(l);
            for (int m = 0; m < subsense.getDefinitions().size(); m++) {
              definitions.add(subsense.getDefinitions().get(m));
            }
          }
        }
      }
    }
    
    StringBuilder builder = new StringBuilder();
    for (String definition : definitions) {
      builder.append("~ " + definition);
      builder.append("\n");
    }
    
    return builder.toString();
  }

}
