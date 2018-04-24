/**
 * 
 */
package main.processes;

import java.util.Random;

import main.constants.Speech;

/**
 * @author Gbenga Oshinaga
 *
 */
public class ConversationProcess extends Process {
  
  private static final ConversationProcess INSTANCE = new ConversationProcess();
  
  private ConversationProcess() {
  }
  
  public static ConversationProcess getInstance() {
    return INSTANCE;
  }

  /* (non-Javadoc)
   * @see main.processes.Process#execute()
   */
  @Override
  public Object execute() {
    String statement = this.getText();
    if (statement.contains("your") && statement.contains("name")) {
      return Speech.NAME;
    }
    if (statement.contains("my") && statement.contains("name")) {
      return Speech.USER_NAME;
    }
    if (statement.contains("your") && statement.contains("age") ||
        statement.contains("old") && statement.contains("you")) {
      return Speech.AGE;
    }
    if (statement.contains("created") && statement.contains("you") || 
        statement.contains("your") && statement.contains("creator")) {
      return Speech.CREATOR;
    }
    if (statement.contains("your") && statement.contains("gender") ||
        statement.contains("your") && statement.contains("sex")) {
      return Speech.GENDER;
    }
    if (statement.contains("can") && statement.contains("you") && statement.contains("do")) {
      return Speech.CAN_DO;
    }
    if (statement.contains("ok") || statement.contains("cool") || statement.contains("okay") || 
        statement.contains("yea") ||statement.contains("yh")) {
      return getRandom(Speech.OK);
    }
    if (statement.contains("thanks") || statement.contains("thank")) {
      return Speech.YOU_WELCOME;
    }
    for (String greet : Speech.GREETINGS) {
      if (statement.equalsIgnoreCase(greet)) {
        return getRandom(Speech.RESPONSE_TO_GREETINGS);
      }
    }
    return "I'm sorry, I don't understand";
  }
  
  
  /**
   * Utility method for getting random responses
   * @param s array containing responses
   * @return random response
   */
  private String getRandom(String[] s){
      int random = new Random().nextInt(s.length);
      return s[random];
  }

}
