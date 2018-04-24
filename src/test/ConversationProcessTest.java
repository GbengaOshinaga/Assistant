package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.constants.Speech;
import main.processes.ConversationProcess;

class ConversationProcessTest {

  @Test
  void shouldExecuteRequest() {
    ConversationProcess process = ConversationProcess.getInstance();
    process.setText("what is your name");
    assertEquals(Speech.NAME, process.execute());
    process.setText("what is my name");
    assertEquals(Speech.USER_NAME, process.execute());
    process.setText("how old are you");
    assertEquals(Speech.AGE, process.execute());
    process.setText("what can you do");
    assertEquals(Speech.CAN_DO, process.execute());
    process.setText("who created you");
    assertEquals(Speech.CREATOR, process.execute());
    process.setText("what is your gender");
    assertEquals(Speech.GENDER, process.execute());
    process.setText("thanks");
    assertEquals(Speech.YOU_WELCOME, process.execute());
    process.setText("you should not understand");
    assertEquals("I'm sorry, I don't understand", process.execute());
  }

}
