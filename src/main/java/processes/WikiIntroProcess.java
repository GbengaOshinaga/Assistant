/**
 * 
 */
package processes;

import java.net.URLConnection;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author Gbenga Oshinaga
 *
 */
public class WikiIntroProcess extends Process {
  
  private final String URL = "https://en.wikipedia.org/w/api.php?format=json&action=query" + 
      "&prop=extracts&exintro=&explaintext=&titles=";
  
  private static final WikiIntroProcess INSTANCE = new WikiIntroProcess();
  
  private WikiIntroProcess() {
  }
  
  
  /**
   * Returns the instance of the class
   * @return INSTANCE
   */
  public static WikiIntroProcess getInstance() {
    return INSTANCE;
  }

  
  /* (non-Javadoc)
   * @see processes.Process#execute()
   */
  @Override
  public Object execute() {
    String title = getTitle(this.getText());
    String response = "";
    String result = "";
    try {
      URLConnection conn = new URL(URL+title).openConnection();
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      
      StringBuilder stringBuilder = new StringBuilder();
      int charInteger;
      while ((charInteger = bufferedReader.read()) != -1) {
        stringBuilder.append((char) charInteger);
      }
      response = stringBuilder.toString();
      
      JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
      JsonObject pageId = jsonObject.getAsJsonObject("query").getAsJsonObject("pages");
      Object[] pageIdArray = pageId.keySet().toArray();
      result = pageId.getAsJsonObject(pageIdArray[0].toString()).get("extract").toString();
      
      if (result.length() < 3) {
        return "I didn't get any text for " + title.replace("%20", " ");
      }
      if (result.endsWith(":\"")) {
        return "Please be specific about the " + title + " you're looking for";
      }
    } catch (IOException e) {
      return "An error occurred, you are most likely not connected to the internet";
    } catch (NullPointerException e) {
      return "I did not find a page with " + title.replace("%20", " ") + " as title";
    }
    return result;
  }
  
  
  /**
   * Get title to search for from statement
   * @param statement
   * @return title
   */
  private String getTitle(String statement) {
    String[] splitStatement = statement.split("\\s+");
    String title = "";
    if (splitStatement[0].equals("wiki")) {
      for(int i = 1; i<splitStatement.length; i++){
        title += splitStatement[i].substring(0,1).toUpperCase() + splitStatement[i].substring(1) + " ";
      }
    } else if (splitStatement[splitStatement.length-1].equals("wiki")) {
      for(int i = 0; i<splitStatement.length-1; i++) {
        title += splitStatement[i].substring(0,1).toUpperCase() + splitStatement[i].substring(1) + " ";
      }
    }
    return title.trim().replace(" ", "%20");
  }

}
