package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.junit.jupiter.api.Test;
import org.powermock.api.mockito.PowerMockito;

import main.processes.DictionaryProcess;
import test.mock.JsonMock;

class DictionaryProcessTest {

  @Test
  void shouldReturnWord() {
    DictionaryProcess process = DictionaryProcess.getInstance();
    Class[] parameters = {String.class};
    try {
      Method method = process.getClass().getDeclaredMethod("getWord", parameters);
      method.setAccessible(true);
      assertEquals("ship", method.invoke(process, "define ship"));
      assertEquals("meat", method.invoke(process, "can you define  meat for me?"));
      assertEquals("boat", method.invoke(process, "definition of boat"));
      assertEquals("gold", method.invoke(process, "can you give me the definition of gold?"));
    } catch (NoSuchMethodException | 
        SecurityException | 
        IllegalAccessException | 
        IllegalArgumentException |
        InvocationTargetException e) {
      e.printStackTrace();
    }
  }
  
  @Test
  void shouldReturnDefinitions() {
    DictionaryProcess process = DictionaryProcess.getInstance();
    Class[] parameters = {String.class};
    try {
      Method method = process.getClass().getDeclaredMethod("getDefinitions", parameters);
      method.setAccessible(true);
      assertEquals(JsonMock.goatDefinitons, method.invoke(process, JsonMock.goatJson));
    }  catch (NoSuchMethodException | 
        SecurityException | 
        IllegalAccessException | 
        IllegalArgumentException |
        InvocationTargetException e) {
      e.printStackTrace();
    }
  }
  
  @Test
  void shouldExecuteRequest() {
    DictionaryProcess process = DictionaryProcess.getInstance();
    process.setText("define goat");
    String apiUrl = "https://od-api.oxforddictionaries.com/api/v1/entries/en/goat/definitions";
    try {
      URLConnection conn = new URL(apiUrl).openConnection();
      PowerMockito.whenNew(URLConnection.class).withAnyArguments().thenReturn(conn);
      File file = new File("./src/test/mock/goat.json");
      InputStream stream = new FileInputStream(file);
      PowerMockito.when(conn.getInputStream()).thenReturn(stream);
    } catch (Exception e) {
    }
    assertEquals(JsonMock.goatDefinitons, process.execute());
  }

}
