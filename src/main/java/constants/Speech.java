package constants;

public final class Speech {

  private Speech() {
  }


  /*=============================================================================
   * Common Statements
   ============================================================================*/
  public static final String NAME = "My name is Assistant";
  public static final String USER_NAME = "Your name is " + System.getProperty("user.name");
  public static final String AGE = "I am ageless";
  public static final String CREATOR = "I was created by a dude called Gbenga Oshinaga";
  public static final String GENDER = "I have none, I'm a computer program";
  public static final String CAN_DO = "Well, I can:\n"
      + "Define words\n"
      + "Get wikipedia introduction text on a title(P.S. You have to add the word 'wiki' to the start or end of your statement)\n"
      + "That's basically it for now...";
  public static final String YOU_WELCOME = "you're welcome";
  public static final String[] OK = {"okay","cool","yea","ok"};


  /*=============================================================================
   * Common Greetings And Response to Greetings
   ============================================================================*/
  public static final String[] GREETINGS = {"whats up", "hello", "hey",
      "how far", "how are you", "how are you?",
      "hi", "how far?", "what's up", "what's up?", "whats up?", "wats up"};
  public static final String[] RESPONSE_TO_GREETINGS = {"I dey", "I'm fine", "I'm cool",
      "I'm okay", "I'm awesome"};
}
